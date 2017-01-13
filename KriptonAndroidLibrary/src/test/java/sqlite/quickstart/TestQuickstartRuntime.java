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
package sqlite.quickstart;

import org.junit.Test;

import base.BaseAndroidTest;
import sqlite.quickstart.model.User;
import sqlite.quickstart.persistence.BindQuickStartDaoFactory;
import sqlite.quickstart.persistence.BindQuickStartDataSource;

/**
 * @author xcesco
 *
 */
public class TestQuickstartRuntime extends BaseAndroidTest {

	@Test
	public void testRunSqlite1() {
		BindQuickStartDataSource dataSource = BindQuickStartDataSource.instance();
		
		dataSource.execute(new BindQuickStartDataSource.SimpleTransaction() {
			
			@Override
			public boolean onExecute(BindQuickStartDaoFactory daoFactory) throws Throwable {
				daoFactory.getUserDao().insert(new User());
				
				return true;
			}
		});
	}

}