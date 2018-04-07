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
package sqlite.feature.many2many.case5.persistence;


import com.abubusoft.kripton.android.annotation.BindDaoMany2Many;

import sqlite.feature.many2many.case5.model.Person;
import sqlite.feature.many2many.case5.model.PhoneNumber;

/**
 * Created by xcesco on 10/10/2017.
 */
@BindDaoMany2Many(entity1 = Person.class, entity2 = PhoneNumber.class, methods=false)
public interface Person2PhoneDao {
}