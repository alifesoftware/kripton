/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 * 
 */
package com.abubusoft.kripton.processor.sqlite;

import static com.abubusoft.kripton.processor.core.reflect.TypeUtility.typeName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PaginatedResult;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.SQLTypeAdapterUtils;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.ImmutableUtility;
import com.abubusoft.kripton.processor.core.ModelAnnotation;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLChecker;
import com.abubusoft.kripton.processor.sqlite.grammars.jql.JQLProjection;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteEntity;
import com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod;
import com.abubusoft.kripton.processor.sqlite.transform.SQLTransformer;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

// TODO: Auto-generated Javadoc
/**
 * The Class SelectPaginatedResultHelper.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 * @param <ElementUtils>
 *            the generic type
 * @since 29/01/2017
 */
public class SelectPaginatedResultHelper<ElementUtils> extends AbstractSelectCodeGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.AbstractSelectCodeGenerator#generate(com.squareup.javapoet.TypeSpec.Builder, boolean,
	 * com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod)
	 */
	@Override
	public void generate(TypeSpec.Builder classBuilder, boolean mapFields, SQLiteModelMethod method) {
		//SQLiteDaoDefinition daoDefinition = method.getParent();
		String pagedResultName = buildSpecializedPagedResultClass(classBuilder, method);

		Set<JQLProjection> fieldList = JQLChecker.getInstance().extractProjections(method, method.jql.value,
				method.getEntity());

		{
			MethodSpec.Builder methodBuilder = generateMethodBuilder(method);

			// create PaginatedResult

			String separator = "";
			methodBuilder.addCode("$L paginatedResult=new $L(", pagedResultName, pagedResultName);
			for (Pair<String, TypeName> item : method.getParameters()) {
				// field
				methodBuilder.addCode(separator + "$L", item.value0);
				separator = ", ";
			}
			methodBuilder.addCode(");\n");

			generateCommonPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields(),
					GenerationType.NO_CONTENT, null);
			methodBuilder.addStatement("return paginatedResult");

			classBuilder.addMethod(methodBuilder.build());
		}

		// generate paged result method
		{
			MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(method.getName())
					.addModifiers(Modifier.PRIVATE);
			generateMethodSignature(method, methodBuilder,
					TypeUtility.parameterizedTypeName(TypeUtility.className(List.class),
							TypeUtility.typeName(method.getEntity().getElement())),
					ParameterSpec.builder(TypeUtility.typeName(pagedResultName), "paginatedResult").build());

			generateCommonPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields(),
					GenerationType.NO_METHOD_SIGN, null,
					JavadocPart.build(JavadocPartType.ADD_PARAMETER, "paginatedResult", "handler of paginated result"),
					JavadocPart.build(JavadocPartType.RETURN, "", "result list"));

			methodBuilder.addComment("Specialized part II - $L - BEGIN", this.getClass().getSimpleName());
			generateSpecializedPart(method, classBuilder, methodBuilder, fieldList, selectType.isMapFields());
			methodBuilder.addComment("Specialized part II - $L - END", this.getClass().getSimpleName());

			classBuilder.addMethod(methodBuilder.build());
		}

	}

	/** Used to generate specialized Paged Result classes;. */
	static int pagedResultCounter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.sqlite.AbstractSelectCodeGenerator#generateSpecializedPart(com.abubusoft.kripton.processor.sqlite.model.SQLiteModelMethod,
	 * com.squareup.javapoet.TypeSpec.Builder, com.squareup.javapoet.MethodSpec.Builder, java.util.Set, boolean)
	 */
	@Override
	public void generateSpecializedPart(SQLiteModelMethod method, TypeSpec.Builder classBuilder,
			MethodSpec.Builder methodBuilder, Set<JQLProjection> fieldList, boolean mapFields) {		
		SQLiteEntity entity =method.getEntity();
		
		// get return type (in this case is always a list)		
		ClassName returnRawListClazzName = ClassName.get(List.class);

		TypeName entityClass = typeName(entity.getElement());

		methodBuilder.addCode("\n");
		methodBuilder.addStatement("$T<$T> resultList=new $T<$T>(_cursor.getCount())", List.class, entityClass,
				ArrayList.class, entityClass);

		methodBuilder.addStatement("$T resultBean=null", entityClass);
		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addCode("\n");
			methodBuilder.addComment("initialize temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableVariableInit(entity, methodBuilder);
		}

		methodBuilder.addCode("\n");
		methodBuilder.beginControlFlow("if (_cursor.moveToFirst())");

		// generate index from columns
		methodBuilder.addCode("\n");
		{
			int i = 0;
			for (JQLProjection a : fieldList) {
				SQLProperty item = a.property;

				methodBuilder.addStatement("int index$L=_cursor.getColumnIndex($S)", (i++), item.columnName);
				if (item.hasTypeAdapter()) {
					methodBuilder.addStatement("$T $LAdapter=$T.getAdapter($T.class)",
							item.typeAdapter.getAdapterTypeName(), item.getName(), SQLTypeAdapterUtils.class,
							item.typeAdapter.getAdapterTypeName());
				}
			}
		}
		methodBuilder.addCode("\n");

		methodBuilder.beginControlFlow("do\n");

		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addComment("reset temporary variable for immutable POJO");
			ImmutableUtility.generateImmutableVariableReset(entity, methodBuilder);
		} else {
			methodBuilder.addCode("resultBean=new $T();\n\n", entityClass);
		}

		// generate mapping
		int i = 0;
		for (JQLProjection a : fieldList) {
			SQLProperty item = a.property;

			if (item.isNullable()) {
				methodBuilder.addCode("if (!_cursor.isNull(index$L)) { ", i);
			}
			SQLTransformer.cursor2Java(methodBuilder, typeName(entity.getElement()), item, "resultBean", "_cursor",
					"index" + i + "");
			methodBuilder.addCode(";");
			if (item.isNullable()) {
				methodBuilder.addCode(" }");
			}
			methodBuilder.addCode("\n");

			i++;
		}
		methodBuilder.addCode("\n");

		// immutable management
		if (entity.isImmutablePojo()) {
			methodBuilder.addComment("define immutable POJO");
			ImmutableUtility.generateImmutableEntityCreation(entity, methodBuilder, "resultBean", false);
		}

		methodBuilder.addCode("resultList.add(resultBean);\n");
		methodBuilder.endControlFlow("while (_cursor.moveToNext())");

		methodBuilder.endControlFlow();

		methodBuilder.addCode("\n");

		// return list or immutable list
		if (entity.isImmutablePojo()) {
			methodBuilder.addCode("return ");
			ImmutableUtility.generateImmutableCollectionIfPossible(entity,  methodBuilder, "resultList", ParameterizedTypeName.get(returnRawListClazzName, entityClass));	
			methodBuilder.addCode(";\n");
		} else {
			methodBuilder.addCode("return resultList;\n");
		}

		
		methodBuilder.endControlFlow();
	}

	/**
	 * Build paginated result class handler.
	 *
	 * @param classBuilder
	 *            the class builder
	 * @param method
	 *            the method
	 * @return name of generated class
	 */
	private static String buildSpecializedPagedResultClass(TypeSpec.Builder classBuilder, SQLiteModelMethod method) {
		//TypeName entityTypeName = TypeUtility.typeName(method.getParent().getEntityClassName());
		TypeName entityTypeName = TypeUtility.typeName(method.getEntity().getName());

		String pagedResultName = "PaginatedResult" + (pagedResultCounter++);

		TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(pagedResultName).addModifiers(Modifier.PUBLIC).superclass(
				TypeUtility.parameterizedTypeName(TypeUtility.className(PaginatedResult.class), entityTypeName));

		// add fields and define constructor
		MethodSpec.Builder setupBuilder = MethodSpec.constructorBuilder();

		MethodSpec.Builder executeBuilder = MethodSpec.methodBuilder("execute").addModifiers(Modifier.PUBLIC)
				.returns(TypeUtility.parameterizedTypeName(TypeUtility.className(List.class), entityTypeName));
		executeBuilder.addCode("list=$T.this.$L(",
				TypeUtility.typeName(method.getParent().getElement(), BindDaoBuilder.SUFFIX), method.getName());

		// we have always a first parameter
		String separator = "";
		ParameterSpec parameterSpec;
		for (Pair<String, TypeName> item : method.getParameters()) {
			if (method.hasDynamicPageSizeConditions() && method.dynamicPageSizeName.equals(item.value0)) {
				setupBuilder.addStatement("this.pageSize=$L", item.value0);
			} else {
				// field
				typeBuilder.addField(item.value1, item.value0);
				setupBuilder.addStatement("this.$L=$L", item.value0, item.value0);
			}

			// construtor
			parameterSpec = ParameterSpec.builder(item.value1, item.value0).build();
			setupBuilder.addParameter(parameterSpec);

			// execute
			if (method.dynamicPageSizeName != null && method.dynamicPageSizeName.equals(item.value0)) {
				executeBuilder.addCode(separator + "this.pageSize");
			} else {
				executeBuilder.addCode(separator + item.value0);
			}
			separator = ", ";
		}

		if (!method.hasDynamicPageSizeConditions()) {
			ModelAnnotation annotation = method.getAnnotation(BindSqlSelect.class);
			int pageSize = annotation.getAttributeAsInt(AnnotationAttributeType.PAGE_SIZE);
			setupBuilder.addStatement("this.pageSize=$L", pageSize);
		}

		typeBuilder.addMethod(setupBuilder.build());

		executeBuilder.addCode(separator + "this);\n");
		executeBuilder.addStatement("return list");

		typeBuilder.addMethod(executeBuilder.build());

		classBuilder.addType(typeBuilder.build());

		return pagedResultName;
	}

}
