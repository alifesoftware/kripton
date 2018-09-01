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
package com.abubusoft.kripton.processor;

import java.io.InputStream;
import java.util.Properties;

/**
 * The Class Version.
 */
public abstract class Version {
	
	/** The version. */
	private static String VERSION = "development";

	static {
		// load version from generated pom.properties
		InputStream resourceAsStream = Version.class.getResourceAsStream("/META-INF/maven/com.abubusoft/kripton-processor/pom.properties");
		Properties props = new Properties();
		try {
			props.load(resourceAsStream);
			VERSION = (String) props.get("version");
		} catch (Throwable e) {
			//e.printStackTrace();
		} finally {

		}
	}

	

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public static String getVersion() {
		return VERSION;
	}
}
