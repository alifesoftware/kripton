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
package com.abubusoft.kripton.android.sqlite;

/**
 * The Class SqlUtils.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public abstract class SqlUtils {
	
	/**
	 * Display string <code>String.format(format, objects)</code> only if condition is true
	 *
	 * @param condition the condition
	 * @param format the format
	 * @param objects the objects
	 * @return the string
	 */
	public static String printIf(boolean condition, String format, Object ...objects)
	{
		return condition ? String.format(format, objects) : "";
	}
	
}
