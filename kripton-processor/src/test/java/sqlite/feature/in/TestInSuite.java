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
package sqlite.feature.in;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;

/**
 * The Class TestGenericHierarchySuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestFeatureIn1.class,
		TestFeatureIn2.class,
		TestFeatureIn3.class,
		TestFeatureIn4.class,
		TestFeatureIn5.class,
		TestFeatureInErr1.class,
		TestFeatureInErr2.class,
		TestFeatureInErr3.class
		
		 })
//@formatter:on
public class TestInSuite extends BaseProcessorTest {

}
