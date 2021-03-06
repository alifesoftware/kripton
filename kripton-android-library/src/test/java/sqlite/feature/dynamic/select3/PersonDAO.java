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
package sqlite.feature.dynamic.select3;

import java.util.Date;
import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicOrderBy;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;
import com.abubusoft.kripton.android.annotation.BindSqlInsert;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.dynamic.Person;

/**
 * The Interface PersonDAO2.
 */
@BindDao(Person.class)
public interface PersonDAO {

	@BindSqlSelect(jql="select * from person where name like :dummy || '%' #{DYNAMIC_WHERE}")
	List<Person> select(String dummy, @BindSqlDynamicWhere String where);
	
	@BindSqlSelect(jql="select * from person where name like :dummy || '%' #{DYNAMIC_WHERE} #{DYNAMIC_ORDER_BY}")
	List<Person> select(String dummy, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] dynParam, @BindSqlDynamicOrderBy String order);
	
	@BindSqlInsert
	void insert(String name, String surname, String birthCity, Date birthDay);


}