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
package sqlite.quickstart.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * Entity <code>Post</code> is associated to table <code>post</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Post
 */
public class PostTable implements SQLiteTable {
  
  /** Costant represents typeName of table post. */
  public static final String TABLE_NAME = "post";

  /** <p> DDL to create table post </p>  <pre>CREATE TABLE post (user_id INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, body TEXT, FOREIGN KEY(user_id) REFERENCES user(id));</pre>. */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE post (user_id INTEGER, id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, body TEXT, FOREIGN KEY(user_id) REFERENCES user(id));";

  /** <p> DDL to drop table post </p>  <pre>DROP TABLE IF EXISTS post;</pre>. */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS post;";

  /**
   * Entity's property <code>userId</code> is associated to table column <code>user_id</code>. This costant represents column name.
   *
   *  @see Post#userId
   */
  public static final String COLUMN_USER_ID = "user_id";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Post#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>title</code> is associated to table column <code>title</code>. This costant represents column name.
   *
   *  @see Post#title
   */
  public static final String COLUMN_TITLE = "title";

  /**
   * Entity's property <code>body</code> is associated to table column <code>body</code>. This costant represents column name.
   *
   *  @see Post#body
   */
  public static final String COLUMN_BODY = "body";

  /** Columns array. */
  private static final String[] COLUMNS = {COLUMN_USER_ID, COLUMN_ID, COLUMN_TITLE, COLUMN_BODY};

  /**
   * Columns array.
   *
   * @return the string[]
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name.
   *
   * @return the string
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}