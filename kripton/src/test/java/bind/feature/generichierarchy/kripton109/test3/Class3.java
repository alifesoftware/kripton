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
package bind.feature.generichierarchy.kripton109.test3;

import java.util.Date;

import com.abubusoft.kripton.annotation.BindType;
import com.abubusoft.kripton.annotation.BindTypeVariables;

/**
 * The Class Class3.
 */
@BindType
@BindTypeVariables(value={"A","B", "C"}, typeParameters={Integer.class, Date.class, String.class})
public class Class3 extends Class2<Integer, Date> {

}
