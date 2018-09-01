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
package sqlite.feature.typeadapter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.BaseProcessorTest;
import sqlite.feature.typeadapter.insert.err1.TestTypeAdapterInsertErr1;
import sqlite.feature.typeadapter.insert.err2.TestTypeAdapterInsertErr2;
import sqlite.feature.typeadapter.kripton180.bean.TestKripton180Bean;
import sqlite.feature.typeadapter.kripton180.raw.TestKripton180Raw;
import sqlite.feature.typeadapter.update.err1.TestTypeAdapterUpdateErr1;
import sqlite.feature.typeadapter.update.err2.TestTypeAdapterUpdateErr2;

/**
 * The Class TestTypeAdapterSuite.
 */
@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
		{ 
		TestTypeAdapter.class,
		TestTypeAdapterUpdateErr1.class,
		TestTypeAdapterUpdateErr2.class,
		TestTypeAdapterInsertErr1.class,
		TestTypeAdapterInsertErr2.class,
		TestBitmapTypeAdapter.class,
		TestBitmapTypeAdapterErr1.class,
		TestBitmapTypeAdapterErr2.class, 
		TestTypeAdapterErr1.class, 
		
		TestKripton180Bean.class,
		TestKripton180Raw.class
		
		 })
//@formatter:on
public class TestTypeAdapterSuite extends BaseProcessorTest {

}
