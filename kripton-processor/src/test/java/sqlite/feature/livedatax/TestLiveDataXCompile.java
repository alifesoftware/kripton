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
package sqlite.feature.livedatax;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.KriptonDynamicClassManager;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.feature.livedata.data.Person;
import sqlite.feature.livedatax.persistence0.App0DataSource;
import sqlite.feature.livedatax.persistence0.DaoPerson0;
import sqlite.feature.livedatax.persistence1.App1DataSource;
import sqlite.feature.livedatax.persistence1.DaoPerson1;

/**
 * The Class TestLiveDataCompile.
 */
@RunWith(JUnit4.class)
public class TestLiveDataXCompile extends AbstractBindSQLiteProcessorTest {
	
	/**
	 * Test OK.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testOK() throws Throwable {
		KriptonDynamicClassManager.reset();
		KriptonDynamicClassManager.init();
		//formatter:off
		buildDataSourceProcessorTest(Person.class, DaoPerson0.class, App0DataSource.class);
		//formatter:on
		
		KriptonDynamicClassManager.reset();
	}
	
	/**
	 * Test compile 1.
	 *
	 * @throws Throwable the throwable
	 */
	@Test
	public void testCompile1() throws Throwable {
		KriptonDynamicClassManager.reset();
		KriptonDynamicClassManager.init();
		//formatter:off
		buildDataSourceProcessorTest(Person.class, DaoPerson1.class, App1DataSource.class);
		//formatter:on
		KriptonDynamicClassManager.reset();
	}

}
