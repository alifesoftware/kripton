/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package com.abubusoft.kripton.android.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.abubusoft.kripton.android.SqlTypeAdapter;
import com.abubusoft.kripton.android.sqlite.NoAdapter;
 
/**
 * The Interface BindSqlParam.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BindSqlParam {

	/**
	 * Name used into jql to rapresents this parameter.
	 *
	 * @return the string
	 */
	String value() default "";

	/**
	 * used type adapter.
	 *
	 * @return the class&lt;? extends sql type adapter&lt;?,?&gt;&gt;
	 */
	Class<? extends SqlTypeAdapter<?, ?>> adapter() default NoAdapter.class;	
		
}
