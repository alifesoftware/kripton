/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package sqlite.kripton84;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.kripton84.BindBean84ADataSource.Transaction;


// TODO: Auto-generated Javadoc
/**
 * The Class Test84RuntimeA.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class Test84RuntimeA extends BaseAndroidTest {

	/**
	 * Test run sqlite.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@Test
	public void testRunSqlite() throws IOException, InstantiationException, IllegalAccessException {
		Assert.assertNotNull(Bean84ATable.class.getName() != null);
		Assert.assertNotNull(Bean84ADaoImpl.class.getName() != null);
		
		
		BindBean84ADataSource dataSource = BindBean84ADataSource.getInstance();
		//dataSource.openWritableDatabase();

		dataSource.execute(new Transaction() {

			@Override
			public TransactionResult onExecute(BindBean84ADaoFactory daoFactory) {
				Bean84ADaoImpl dao = daoFactory.getBean84ADao();

				Bean84A bean = new Bean84A();
				bean.valueString = "hello";

				dao.insertAll(bean);
				List<Bean84A> list = dao.selectById(bean.id);
				Assert.assertEquals("not list ", 1, list.size());

				Assert.assertEquals("not map", 1, list.size());

				// Assert.assertEquals("not set", 1,
				// list.get(0).valueSetString.size());

				return TransactionResult.COMMIT;
			}
		
		});

	}

}
