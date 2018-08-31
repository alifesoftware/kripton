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
package sqlite.feature.paginatedresult.error5;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;
import com.abubusoft.kripton.android.sqlite.PagedResult;

import sqlite.feature.paginatedresult.error4.Err4Person;
import sqlite.feature.paginatedresult.model.Person;

/**
 * The Interface Err5PersonDAO.
 */
@BindDao(Person.class)
public interface Err5PersonDAO {

	/**
	 * Select paged static 1.
	 *
	 * @return the paginated result
	 */
	@BindSqlSelect
	PagedResult<Err4Person> selectPagedStatic1();

}