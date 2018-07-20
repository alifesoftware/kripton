/*******************************************************************************
 * Copyright 2018 Francesco Benincasa (info@abubusoft.com)
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
package sqlite.feature.in;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

import com.abubusoft.kripton.android.sqlite.TransactionResult;

import base.BaseAndroidTest;
import sqlite.feature.in.case5.*;
import sqlite.feature.in.case5.BindAppDataSource.Transaction;

public class TestFeatureRuntime5 extends BaseAndroidTest {

	@Test
	public void testRuntime() {
		BindAppDataSource ds=BindAppDataSource.getInstance();
		ds.execute(new Transaction() {
			
			@Override
			public TransactionResult onExecute(BindAppDaoFactory daoFactory) {
				Calendar c=Calendar.getInstance();
				
				c.set(Calendar.YEAR, 2000);
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DAY_OF_MONTH, 10);
				
				DaoCityImpl dao = daoFactory.getDaoCity();
				
				java.sql.Date date = new java.sql.Date(c.getTime().getTime());
								
				City bean1=new City();
				bean1.name="city1";		
				bean1.setDate(date);
				dao.insert(bean1);
				
				City bean2=new City();
				bean2.name="city2";				
				bean2.setDate(date);
				dao.insert(bean2);
				
				java.sql.Date params[]={new java.sql.Date(date.getTime())};
				
				assertTrue(dao.selectAll(params).size()==2);
			
				
				return TransactionResult.COMMIT;
			}
		});
	}

}
