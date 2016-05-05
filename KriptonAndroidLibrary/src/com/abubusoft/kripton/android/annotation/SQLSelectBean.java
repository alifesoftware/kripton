package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface SQLSelectBean {
	
	boolean distinct() default false;
	
	/**
	 * properties to include into UPDATE command
	 * @return
	 * 		property's names to include
	 */
	String[] value() default "";
	
	/**
	 * properties to include into UPDATE command
	 * @return
	 * 		property's names to exclude
	 */
	String[] excludedFields() default "";
	
	/**
	 * where condition
	 * 
	 * @return
	 * 		where condition
	 */
	String where();

}
