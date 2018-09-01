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
 */
package com.abubusoft.kripton.processor.sqlite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.android.ColumnType;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;
import com.abubusoft.kripton.android.annotation.BindGeneratedDao;
import com.abubusoft.kripton.android.annotation.BindSqlColumn;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlParam;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.annotation.BindSqlType;
import com.abubusoft.kripton.android.sqlite.ForeignKeyAction;
import com.abubusoft.kripton.common.CaseFormat;
import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.common.One;
import com.abubusoft.kripton.common.Pair;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.bind.JavaWriterHelper;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MEntity;
import com.abubusoft.kripton.processor.bind.model.many2many.M2MModel;
import com.abubusoft.kripton.processor.core.AnnotationAttributeType;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility;
import com.abubusoft.kripton.processor.core.reflect.AnnotationUtility.MethodFoundListener;
import com.abubusoft.kripton.processor.core.reflect.TypeUtility;
import com.abubusoft.kripton.processor.element.GeneratedTypeElement;
import com.abubusoft.kripton.processor.sqlite.core.JavadocUtility;
import com.abubusoft.kripton.processor.sqlite.model.SQLProperty;
import com.abubusoft.kripton.processor.utils.AnnotationProcessorUtilis;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * The Class BindM2MBuilder.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class BindM2MBuilder extends AbstractBuilder {

	public static TypeName findPrimaryKeyFieldType(String entityFullName) {
		TypeElement element = BaseProcessor.elementUtils.getTypeElement(entityFullName);
		List<? extends Element> list = BaseProcessor.elementUtils.getAllMembers(element);
		// fill property map from current class
		Element idField = null;
		String enumValue;
		for (Element item : list) {
			if (item.getKind() == ElementKind.FIELD) {
				enumValue = AnnotationUtility.extractAsEnumerationValue(item, BindSqlColumn.class,
						AnnotationAttributeType.COLUMN_TYPE);
				if (StringUtils.hasText(enumValue) && (ColumnType.valueOf(enumValue) == ColumnType.PRIMARY_KEY
						|| ColumnType.valueOf(enumValue) == ColumnType.PRIMARY_KEY_UNMANGED)) {
					return TypeUtility.typeName(item);
				} else if (item.getSimpleName().toString().equals("id")) {
					idField = item;
				}
			}
		}
		if (idField != null) {
			return TypeUtility.typeName(idField.asType());
		}

		// go up in class hierarchy and extract other fields
		String parentClassName = element.getSuperclass().toString();
		if (parentClassName != null && !Object.class.getCanonicalName().equals(parentClassName)) {
			TypeElement parentTypeElement = BaseProcessor.elementUtils.getTypeElement(parentClassName);
			if (parentTypeElement != null) {
				return findPrimaryKeyFieldType(parentClassName);
			}
		}

		return null;
	}

	/** The Constant PREFIX. */
	public static final String PREFIX = "Bind";

	/** The Constant SUFFIX. */
	public static final String SUFFIX = "Cursor";

	/** The entity result. */
	private static Set<GeneratedTypeElement> entityResult = new HashSet<GeneratedTypeElement>();

	/** The dao result. */
	private static Set<GeneratedTypeElement> daoResult = new HashSet<GeneratedTypeElement>();

	/**
	 * Instantiates a new bind M 2 M builder.
	 *
	 * @param filer
	 *            the filer
	 */
	public BindM2MBuilder(Filer filer) {
		super(BaseProcessor.elementUtils, filer, null);
	}

	/**
	 * Generate.
	 *
	 * @param filer
	 *            the filer
	 * @param model
	 *            the model
	 * @return the pair
	 * @throws Exception
	 *             the exception
	 */
	public static Pair<Set<GeneratedTypeElement>, Set<GeneratedTypeElement>> generate(Filer filer, M2MModel model)
			 {
		entityResult.clear();
		daoResult.clear();
		BindM2MBuilder visitor = new BindM2MBuilder(filer);

		for (M2MEntity item : model.getEntities()) {
			visitor.generate(item);
		}

		Pair<Set<GeneratedTypeElement>, Set<GeneratedTypeElement>> result = new Pair<>();
		result.value0 = entityResult;
		result.value1 = daoResult;

		return result;
	}

	/**
	 * Generate.
	 *
	 * @param entity
	 *            the entity
	 * @throws Exception
	 *             the exception
	 */
	public void generate(M2MEntity entity) {
		generateEntity(entity);
		generateDaoPart(entity);
	}

	/**
	 * Generate dao part.
	 *
	 * @param entity
	 *            the entity
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void generateDaoPart(M2MEntity entity) {
		String daoClassName = entity.daoName.simpleName();

		String daoPackageName = entity.daoName.packageName();
		String entityPackageName = entity.getPackageName();
		String generatedDaoClassName = "Generated" + daoClassName;

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDaoMany2Many.class, daoPackageName, generatedDaoClassName);
		// @formatter:off
		classBuilder = TypeSpec.interfaceBuilder(generatedDaoClassName).addModifiers(Modifier.PUBLIC)
				.addAnnotation(AnnotationSpec.builder(BindDao.class)
						.addMember("value", "$T.class", TypeUtility.className(entityPackageName, entity.name)).build())
				.addAnnotation(AnnotationSpec.builder(BindGeneratedDao.class)
						.addMember("dao", "$T.class", entity.daoName).build())
				.addAnnotation(AnnotationSpec.builder(BindDaoMany2Many.class)
						.addMember("entity1", "$T.class", entity.entity1Name)
						.addMember("entity2", "$T.class", entity.entity2Name).build())
				.addSuperinterface(entity.daoName);
		// @formatter:on

		JavadocUtility.generateJavadocGeneratedBy(classBuilder);

		if (entity.generateMethods) {
			generateSelects(entity, entityPackageName);
			generateDeletes(entity, entityPackageName);
			generateInsert(entity, entityPackageName);
		}

		TypeSpec typeSpec = classBuilder.build();

		try {
			JavaWriterHelper.writeJava2File(filer, daoPackageName, typeSpec);
		} catch (IOException e) {
			throw new KriptonRuntimeException(e);
		}

		GeneratedTypeElement daoPartElement = new GeneratedTypeElement(daoPackageName, classBuilder.build(), null,
				null);
		daoResult.add(daoPartElement);
	}

	/**
	 * Generate selects.
	 *
	 * @param entity
	 *            the entity
	 * @param generatedEntity
	 * @param packageName
	 *            the package name
	 */
	private void generateSelects(M2MEntity entity, String packageName) {
		String idPart = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);

		String methodName = "";
		String workId = "";

		methodName = "selectBy" + idPart;
		if (!isMethodAlreadyDefined(entity, methodName)) {
			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class)
							.addMember("where", "$S",
									entity.idName + "=" + SqlAnalyzer.PARAM_PREFIX + entity.idName
											+ SqlAnalyzer.PARAM_SUFFIX)
							.build())
					.addParameter(ParameterSpec.builder(entity.propertyPrimaryKey, entity.idName)
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class)
									.addMember("value", "$S", entity.idName).build())
							.build())
					.returns(TypeUtility.className(packageName, entity.name)).build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}

		methodName = entity.entity1Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
		workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
		methodName = "selectBy" + methodName;
		if (!isMethodAlreadyDefined(entity, methodName)) {
			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class)
							.addMember("where", "$S",
									workId + "=" + SqlAnalyzer.PARAM_PREFIX + workId + SqlAnalyzer.PARAM_SUFFIX)
							.build())
					.addParameter(ParameterSpec.builder(entity.propertyKey1, workId)
							.addAnnotation(
									AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S", workId).build())
							.build())
					.returns(TypeUtility.parameterizedTypeName(TypeUtility.className(List.class),
							TypeUtility.className(packageName, entity.name)))
					.build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}

		methodName = entity.entity2Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
		workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
		methodName = "selectBy" + methodName;
		if (!isMethodAlreadyDefined(entity, methodName)) {

			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlSelect.class)
							.addMember("where", "$S",
									workId + "=" + SqlAnalyzer.PARAM_PREFIX + workId + SqlAnalyzer.PARAM_SUFFIX)
							.build())
					.addParameter(ParameterSpec.builder(entity.propertyKey2, workId)
							.addAnnotation(
									AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S", workId).build())
							.build())
					.returns(TypeUtility.parameterizedTypeName(TypeUtility.className(List.class),
							TypeUtility.className(packageName, entity.name)))
					.build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}
	}

	/**
	 * analyze DAO definition to check if method is already defined.
	 *
	 * @param entity
	 *            the entity
	 * @param methodName
	 *            the method name
	 * @return true, if is method already defined
	 */
	private boolean isMethodAlreadyDefined(M2MEntity entity, final String methodName) {

		final One<Boolean> found = new One<Boolean>(false);
		SqlBuilderHelper.forEachMethods(entity.daoElement, new MethodFoundListener() {

			@Override
			public void onMethod(ExecutableElement executableMethod) {
				if (executableMethod.getSimpleName().toString().equals(methodName)) {
					found.value0 = true;
				}

			}
		});

		return found.value0;
	}

	/**
	 * Generate deletes.
	 *
	 * @param entity
	 *            the entity
	 * @param generatedEntity
	 * @param packageName
	 *            the package name
	 */
	private void generateDeletes(M2MEntity entity, String packageName) {
		String idPart = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);

		String methodName = "";
		String workId = "";

		methodName = "deleteBy" + idPart;
		if (!isMethodAlreadyDefined(entity, methodName)) {
			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlDelete.class)
							.addMember("where", "$S",
									entity.idName + "=" + SqlAnalyzer.PARAM_PREFIX + entity.idName
											+ SqlAnalyzer.PARAM_SUFFIX)
							.build())
					.addParameter(ParameterSpec.builder(entity.propertyPrimaryKey, entity.idName)
							.addAnnotation(AnnotationSpec.builder(BindSqlParam.class)
									.addMember("value", "$S", entity.idName).build())
							.build())
					.returns(Integer.TYPE).build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}

		methodName = entity.entity1Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
		workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
		methodName = "deleteBy" + methodName;
		if (!isMethodAlreadyDefined(entity, methodName)) {

			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlDelete.class)
							.addMember("where", "$S",
									workId + "=" + SqlAnalyzer.PARAM_PREFIX + workId + SqlAnalyzer.PARAM_SUFFIX)
							.build())
					.addParameter(ParameterSpec.builder(entity.propertyKey1, workId)
							.addAnnotation(
									AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S", workId).build())
							.build())
					.returns(Integer.TYPE).build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}

		methodName = entity.entity2Name.simpleName() + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entity.idName);
		workId = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, methodName);
		methodName = "deleteBy" + methodName;
		if (!isMethodAlreadyDefined(entity, methodName)) {
			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT)
					.addAnnotation(AnnotationSpec.builder(BindSqlDelete.class)
							.addMember("where", "$S",
									workId + "=" + SqlAnalyzer.PARAM_PREFIX + workId + SqlAnalyzer.PARAM_SUFFIX)
							.build())
					.addParameter(ParameterSpec.builder(entity.propertyKey2, workId)
							.addAnnotation(
									AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S", workId).build())
							.build())
					.returns(Integer.TYPE).build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}
	}

	/**
	 * Generate insert.
	 *
	 * @param entity
	 *            the entity
	 * @param packageName
	 *            the package name
	 */
	private void generateInsert(M2MEntity entity, String packageName) {
		if (!isMethodAlreadyDefined(entity, "insert")) {
			// @formatter:off
			MethodSpec methodSpec = MethodSpec.methodBuilder("insert").addModifiers(Modifier.PUBLIC)
					.addModifiers(Modifier.ABSTRACT).addAnnotation(AnnotationSpec.builder(BindSqlInsert.class).build())
					.addParameter(ParameterSpec.builder(TypeUtility.className(packageName, entity.name), "bean")
							.addAnnotation(
									AnnotationSpec.builder(BindSqlParam.class).addMember("value", "$S", "bean").build())
							.build())
					.returns(Integer.TYPE).build();
			// @formatter:on
			classBuilder.addMethod(methodSpec);
		}
	}

	/**
	 * Generate entity.
	 *
	 * @param entity
	 *            the entity
	 * @return
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void generateEntity(M2MEntity entity) {
		entity.propertyPrimaryKey = TypeName.LONG;
		entity.propertyKey1 = findPrimaryKeyFieldType(entity.entity1Name.toString());
		entity.propertyKey2 = findPrimaryKeyFieldType(entity.entity2Name.toString());
		if (!entity.needToCreate) {
			return;
		}

		String tableName = entity.tableName;
		String entityClassName = entity.name;

		AnnotationProcessorUtilis.infoOnGeneratedClasses(BindDaoMany2Many.class, entity.getPackageName(),
				entityClassName);

		Converter<String, String> converterFK = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_CAMEL);
		Converter<String, String> converterFieldName = CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_CAMEL);
		Converter<String, String> converterField2ColumnName = CaseFormat.LOWER_CAMEL
				.converterTo(CaseFormat.LOWER_UNDERSCORE);
		String fkPrefix = converterFK.convert(entity.idName);

		String fk1Name = converterField2ColumnName.convert(entity.entity1Name.simpleName() + fkPrefix);
		String fk2Name = converterField2ColumnName.convert(entity.entity2Name.simpleName() + fkPrefix);

		String field1Name = converterFieldName.convert(entity.entity1Name.simpleName() + fkPrefix);
		String field2Name = converterFieldName.convert(entity.entity2Name.simpleName() + fkPrefix);

		List<SQLProperty> properties = new ArrayList<SQLProperty>();

		// we define property type later
		{
			SQLProperty property = new SQLProperty(entity.idName, entity.getClassName(), entity.propertyPrimaryKey);
			property.columnType = ColumnType.PRIMARY_KEY;
			property.columnName = entity.idName;
			property.setNullable(false);
			property.setPrimaryKey(true);
			property.foreignParentClassName = null;
			properties.add(property);
		}

		{
			SQLProperty property = new SQLProperty(field1Name, entity.getClassName(), entity.propertyKey1);
			property.columnType = ColumnType.INDEXED;
			property.columnName = fk1Name;
			property.setNullable(false);
			property.setPrimaryKey(false);
			property.onDeleteAction = ForeignKeyAction.CASCADE;
			property.foreignParentClassName = entity.entity1Name.toString();
			properties.add(property);
		}

		{
			SQLProperty property = new SQLProperty(field2Name, entity.getClassName(), entity.propertyKey2);
			property.columnType = ColumnType.INDEXED;
			property.columnName = fk2Name;
			property.setNullable(false);
			property.setPrimaryKey(false);
			property.onDeleteAction = ForeignKeyAction.CASCADE;
			property.foreignParentClassName = entity.entity2Name.toString();
			properties.add(property);
		}

		// @formatter:off
		classBuilder = TypeSpec.classBuilder(entityClassName).addModifiers(Modifier.PUBLIC)
				.addAnnotation(AnnotationSpec.builder(BindSqlType.class).addMember("name", "$S", tableName).build());
		// @formatter:on

		if (entity.immutable) {
			// create constructor
			Builder constructorBuilder = MethodSpec.constructorBuilder().addModifiers(Modifier.PUBLIC);
			for (SQLProperty p : properties) {
				constructorBuilder
						.addParameter(ParameterSpec.builder(p.getPropertyType().getTypeName(), p.getName()).build());
				constructorBuilder.addStatement("this.$L=$L", p.getName(), p.getName());
			}
			classBuilder.addMethod(constructorBuilder.build());
		}

		Modifier fieldModifier = entity.immutable ? Modifier.PRIVATE : Modifier.PUBLIC;

		// javadoc for class
		classBuilder.addJavadoc("<p>");
		classBuilder.addJavadoc("\nGenerated entity implementation for <code>$L</code>\n", entity.name);
		classBuilder.addJavadoc("</p>\n");
		JavadocUtility.generateJavadocGeneratedBy(classBuilder);
		// classBuilder.addJavadoc(" @see $T\n",		

		{
			// @formatter:off
			FieldSpec fieldSpec = FieldSpec.builder(entity.propertyPrimaryKey, entity.idName, fieldModifier)
					.addJavadoc("Primary key\n")
					.addAnnotation(AnnotationSpec.builder(BindSqlColumn.class)
							.addMember("columnType", "$T.$L", ColumnType.class, ColumnType.PRIMARY_KEY).build())
					.build();
			// @formatter:on
			classBuilder.addField(fieldSpec);
		}

		{
			// @formatter:off
			FieldSpec fieldSpec = FieldSpec
					.builder(entity.propertyKey1, field1Name, fieldModifier)
					.addJavadoc("Foreign key to $T model class\n", entity.entity1Name)
					.addAnnotation(AnnotationSpec.builder(BindSqlColumn.class)
							.addMember(AnnotationAttributeType.PARENT_ENTITY.getValue(), "$T.class", entity.entity1Name)
							.addMember(AnnotationAttributeType.ON_DELETE.getValue(), "$T.$L", ForeignKeyAction.class,
									ForeignKeyAction.CASCADE)
							.build())
					.build();
			// @formatter:on
			classBuilder.addField(fieldSpec);
		}

		{
			// @formatter:off
			FieldSpec fieldSpec = FieldSpec
					.builder(entity.propertyKey2, field2Name, fieldModifier)
					.addJavadoc("Foreign key to $T model class\n", entity.entity2Name)
					.addAnnotation(AnnotationSpec.builder(BindSqlColumn.class)
							.addMember(AnnotationAttributeType.PARENT_ENTITY.getValue(), "$T.class", entity.entity2Name)
							.addMember(AnnotationAttributeType.ON_DELETE.getValue(), "$T.$L", ForeignKeyAction.class,
									ForeignKeyAction.CASCADE)
							.build())
					.build();
			// @formatter:on
			classBuilder.addField(fieldSpec);
		}

		if (entity.immutable) {
			// getters
			for (SQLProperty p : properties) {
				Builder methodBuilder = MethodSpec
						.methodBuilder("get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, p.getName()))
						.returns(p.getPropertyType().getTypeName()).addStatement("return this.$L", p.getName())
						.addModifiers(Modifier.PUBLIC);

				classBuilder.addMethod(methodBuilder.build());
			}
		}

		TypeSpec typeSpec = classBuilder.build();

		try {
			JavaWriterHelper.writeJava2File(filer, entity.getPackageName(), typeSpec);
		} catch (IOException e) {
			throw new KriptonRuntimeException(e);
		}

		GeneratedTypeElement entityElement = new GeneratedTypeElement(entity.getPackageName(), classBuilder.build(),
				tableName, fk1Name + ", " + fk2Name);

		entityElement.properties = properties;

		entityResult.add(entityElement);
	}

}
