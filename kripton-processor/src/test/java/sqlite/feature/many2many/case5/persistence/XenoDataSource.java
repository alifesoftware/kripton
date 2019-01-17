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
package sqlite.feature.many2many.case5.persistence;


import com.abubusoft.kripton.android.annotation.BindDataSource;


/**
 * The Interface XenoDataSource.
 */
@BindDataSource(daoSet = {PhoneDao.class, PrefixConfigDao.class, CountryDao.class, Person2PhoneDao.class, PersonDao.class}, fileName = "xeno.db", asyncTask = true, schema = true)
public interface XenoDataSource {
}
