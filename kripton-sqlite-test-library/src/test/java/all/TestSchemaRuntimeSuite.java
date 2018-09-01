/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sqlite.feature.schema.TestSchemaUpdater;
import sqlite.feature.schema.TestSchemaUpdater2;
import sqlite.feature.schema.TestSchemaUpdater3;

/**
 * The Class TestSchemaRuntimeSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{  
		TestSchemaUpdater.class, 
		TestSchemaUpdater2.class,
		TestSchemaUpdater3.class
		 })
//@formatter:on
public class TestSchemaRuntimeSuite {

}
