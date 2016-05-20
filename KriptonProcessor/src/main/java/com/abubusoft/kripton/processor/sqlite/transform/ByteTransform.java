package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a Java Byte object
 * 
 * @author bulldog
 *
 */
class ByteTransform implements Transform {
	
	public ByteTransform(boolean nullable)
	{
		defaultValue="0";
		if (nullable)
		{
			defaultValue="null";
		}
	}
	
	@Override
	public void generateReadProperty(MethodSpec.Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName)  {				
		methodBuilder.addCode("$L."+setter(property, "$L.getInt($L)"), beanName,cursorName, indexName);
	}
	
	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getInt($L)", cursorName, indexName);		
	}
	
	@Override
	public void generateDefaultValue(Builder methodBuilder)
	{
		methodBuilder.addCode(defaultValue);		
	}

	@Override
	public String generateWriteProperty(ModelProperty property) {
		// TODO Auto-generated method stub
		return null;
	}

	protected String defaultValue;
	
	@Override
	public void generateResetProperty(Builder methodBuilder, ModelProperty property, String beanName, String cursorName, String indexName) {
		
		methodBuilder.addCode("$L."+setter(property, "0"), beanName);
	}
	
	@Override
	public String generateColumnType(ModelProperty property) {
		return "INTEGER";
	}

}
