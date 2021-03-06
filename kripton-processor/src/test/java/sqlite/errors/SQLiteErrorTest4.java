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
package sqlite.errors;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.abubusoft.kripton.processor.exceptions.InvalidMethodSignException;
import com.abubusoft.kripton.processor.exceptions.UnknownPropertyInJQLException;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.errors.dao.case4.DaoPerson;
import sqlite.errors.dao.case4.Person;
import sqlite.errors.dao.case4.PersonDataSource;

@RunWith(JUnit4.class)
public class SQLiteErrorTest4 extends AbstractBindSQLiteProcessorTest {

	@Test
	public void testCompile() throws IOException, InstantiationException, IllegalAccessException {
		this.expectedException(UnknownPropertyInJQLException.class, "In DAO 'DaoPerson' in method 'update', unknown property 'namex' is used");
		buildDataSourceProcessorTest(DaoPerson.class, Person.class, PersonDataSource.class);
	}

}
