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
package sqlite.feature.javadoc.select.bean;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;

// TODO: Auto-generated Javadoc
/**
 * <p>This is the content provider generated for {@link SelectBeanPersonDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.javadoc.bean</pre>
 *
 * <h2>Supported query operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons</pre></td><td>{@link SelectBeanPersonDaoImpl#selectAllBeans0}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBean2}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/a/${love.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectAllBeansCount1}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamic3}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicOrder5}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicOrderAndListener6}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicAndArgs4}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectWithJQL7}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectWithJQLAndInnerSQL8}</td></tr>
 * </table>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * </table>
 *
 */
public class BindSelectBeanPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.javadoc.bean</pre>
   */
  public static final String URI = "content://sqlite.feature.javadoc.bean";

  /** <p>datasource singleton</p>. */
  private static BindSelectBeanPersonDataSource dataSource;

  /** <p>Content provider authority</p>. */
  public static final String AUTHORITY = "sqlite.feature.javadoc.bean";

  /** <p>URI matcher</p>. */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   */
  private static final Uri URI_PATH_PERSON_1 = Uri.parse(URI+"/persons");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre>
   */
  private static final Uri URI_PATH_PERSON_2 = Uri.parse(URI+"/persons/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/#</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons/a/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre>
   */
  private static final Uri URI_PATH_PERSON_4 = Uri.parse(URI+"/persons/dynamic/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#</pre>
   */
  private static final Uri URI_PATH_PERSON_5 = Uri.parse(URI+"/persons/dynamicOrder/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#</pre>
   */
  private static final Uri URI_PATH_PERSON_6 = Uri.parse(URI+"/persons/dynamicOrderAndLis/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#</pre>
   */
  private static final Uri URI_PATH_PERSON_7 = Uri.parse(URI+"/persons/dynamicandArgs/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/#</pre>
   */
  private static final Uri URI_PATH_PERSON_8 = Uri.parse(URI+"/persons/jql/#");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/#</pre>
   */
  private static final Uri URI_PATH_PERSON_9 = Uri.parse(URI+"/persons/jqlAndInnserSQL/#");

  /** The Constant PATH_PERSON_1. */
  static final String PATH_PERSON_1 = "persons";

  /** The Constant PATH_PERSON_2. */
  static final String PATH_PERSON_2 = "persons/#";

  /** The Constant PATH_PERSON_3. */
  static final String PATH_PERSON_3 = "persons/a/#";

  /** The Constant PATH_PERSON_4. */
  static final String PATH_PERSON_4 = "persons/dynamic/#";

  /** The Constant PATH_PERSON_5. */
  static final String PATH_PERSON_5 = "persons/dynamicOrder/#";

  /** The Constant PATH_PERSON_6. */
  static final String PATH_PERSON_6 = "persons/dynamicOrderAndLis/#";

  /** The Constant PATH_PERSON_7. */
  static final String PATH_PERSON_7 = "persons/dynamicandArgs/#";

  /** The Constant PATH_PERSON_8. */
  static final String PATH_PERSON_8 = "persons/jql/#";

  /** The Constant PATH_PERSON_9. */
  static final String PATH_PERSON_9 = "persons/jqlAndInnserSQL/#";

  /** The Constant PATH_PERSON_1_INDEX. */
  static final int PATH_PERSON_1_INDEX = 1;

  /** The Constant PATH_PERSON_2_INDEX. */
  static final int PATH_PERSON_2_INDEX = 2;

  /** The Constant PATH_PERSON_3_INDEX. */
  static final int PATH_PERSON_3_INDEX = 3;

  /** The Constant PATH_PERSON_4_INDEX. */
  static final int PATH_PERSON_4_INDEX = 4;

  /** The Constant PATH_PERSON_5_INDEX. */
  static final int PATH_PERSON_5_INDEX = 5;

  /** The Constant PATH_PERSON_6_INDEX. */
  static final int PATH_PERSON_6_INDEX = 6;

  /** The Constant PATH_PERSON_7_INDEX. */
  static final int PATH_PERSON_7_INDEX = 7;

  /** The Constant PATH_PERSON_8_INDEX. */
  static final int PATH_PERSON_8_INDEX = 8;

  /** The Constant PATH_PERSON_9_INDEX. */
  static final int PATH_PERSON_9_INDEX = 9;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectAllBeans0}</p>
   */
  public static final Uri URI_PERSON_SELECT_ALL_BEANS = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectOneBean2}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE_BEAN = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/a/${love.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectAllBeansCount1}</p>
   */
  public static final Uri URI_PERSON_SELECT_ALL_BEANS_COUNT = URI_PATH_PERSON_3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamic3}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE_BEAN_WITH_DYNAMIC = URI_PATH_PERSON_4;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicOrder5}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE_BEAN_WITH_DYNAMIC_ORDER = URI_PATH_PERSON_5;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicOrderAndListener6}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE_BEAN_WITH_DYNAMIC_ORDER_AND_LISTENER = URI_PATH_PERSON_6;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicAndArgs4}</p>
   */
  public static final Uri URI_PERSON_SELECT_ONE_BEAN_WITH_DYNAMIC_AND_ARGS = URI_PATH_PERSON_7;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jql/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectWithJQL7}</p>
   */
  public static final Uri URI_PERSON_SELECT_WITH_J_Q_L = URI_PATH_PERSON_8;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/#</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/${bean.id}</pre>
   *
   * <p>Method associated to this URI is {@link SelectBeanPersonDaoImpl#selectWithJQLAndInnerSQL8}</p>
   */
  public static final Uri URI_PERSON_SELECT_WITH_J_Q_L_AND_INNER_S_Q_L = URI_PATH_PERSON_9;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_4, PATH_PERSON_4_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_5, PATH_PERSON_5_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_6, PATH_PERSON_6_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_7, PATH_PERSON_7_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_8, PATH_PERSON_8_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_9, PATH_PERSON_9_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @return true, if successful
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    if (KriptonLibrary.context()==null) {
      KriptonLibrary.init(getContext());
    }
    dataSource = BindSelectBeanPersonDataSource.instance();
    dataSource.openWritableDatabase();
    return true;
  }

  /**
   * <p>Close database.</p>
   *
   * @see android.content.ContentProvider#shutdown()
   */
  @Override
  public void shutdown() {
    super.shutdown();
    dataSource.close();
  }

  /**
   * <h2>Supported query operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons</pre></td><td>{@link SelectBeanPersonDaoImpl#selectAllBeans0}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBean2}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/a/${love.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectAllBeansCount1}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamic3}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrder/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicOrder5}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicOrderAndListener6}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectOneBeanWithDynamicAndArgs4}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jql/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectWithJQL7}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/${bean.id}</pre></td><td>{@link SelectBeanPersonDaoImpl#selectWithJQLAndInnerSQL8}</td></tr>
   * </table>
   *
   * @param uri the uri
   * @param projection the projection
   * @param selection the selection
   * @param selectionArgs the selection args
   * @param sortOrder the sort order
   * @return the cursor
   */
  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    Cursor returnCursor=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons
        returnCursor=dataSource.getSelectBeanPersonDao().selectAllBeans0(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_2_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectOneBean2(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_3_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/a/${love.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectAllBeansCount1(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_4_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/dynamic/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectOneBeanWithDynamic3(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_5_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/dynamicOrder/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectOneBeanWithDynamicOrder5(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_6_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/dynamicOrderAndLis/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectOneBeanWithDynamicOrderAndListener6(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_7_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/dynamicandArgs/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectOneBeanWithDynamicAndArgs4(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_8_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/jql/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectWithJQL7(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      case PATH_PERSON_9_INDEX: {
        // URI: content://sqlite.feature.javadoc.bean/persons/jqlAndInnserSQL/${bean.id}
        returnCursor=dataSource.getSelectBeanPersonDao().selectWithJQLAndInnerSQL8(uri, projection, selection, selectionArgs, sortOrder);
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
      }
    }
    return returnCursor;
  }

  /**
   * <h2>Supported insert operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * </table>
   *
   * @param uri the uri
   * @param contentValues the content values
   * @return the uri
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[])
   */
  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#delete(android.net.Uri, java.lang.String, java.lang.String[])
   */
  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
  }

  /* (non-Javadoc)
   * @see android.content.ContentProvider#getType(android.net.Uri)
   */
  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_2_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_4_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_5_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_6_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_7_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_8_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_9_INDEX: {
        return "vnd.android.cursor.dir/vnd.sqlite.feature.javadoc.bean.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}