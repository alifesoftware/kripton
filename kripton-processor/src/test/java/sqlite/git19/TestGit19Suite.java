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
package sqlite.git19;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import sqlite.AbstractBindSQLiteProcessorTest;
import sqlite.git19.case1.TestGit19_1;
import sqlite.git19.case2.TestGit19_2;
import sqlite.git19.case3.TestGit19_3;
import sqlite.git19.case4.TestGit19_4;

@RunWith(Suite.class)
//@formatter:off
@Suite.SuiteClasses(
{ 
	TestGit19_1.class,
	TestGit19_2.class,
	TestGit19_3.class,
	TestGit19_4.class
 })
//@formatter:on
public class TestGit19Suite extends AbstractBindSQLiteProcessorTest {

}
