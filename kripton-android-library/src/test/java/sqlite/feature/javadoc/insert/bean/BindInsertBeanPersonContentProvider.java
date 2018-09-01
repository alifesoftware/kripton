package sqlite.feature.javadoc.insert.bean;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;

/**
 * <p>This is the content provider generated for {@link InsertBeanPersonDataSource}</p>
 *
 * <h2>Content provider authority:</h2>
 * <pre>sqlite.feature.javadoc.bean</pre>
 *
 * <h2>Supported insert operations</h2>
 * <table>
 * <tr><th>URI</th><th>DAO.METHOD</th></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons</pre></td><td>{@link InsertBeanPersonDaoImpl#insertOneBean0ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/name</pre></td><td>{@link InsertBeanPersonDaoImpl#insertOneBeanFieldName1ForContentProvider}</td></tr>
 * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/surname</pre></td><td>{@link InsertBeanPersonDaoImpl#insertOneBeanFieldSurname2ForContentProvider}</td></tr>
 * </table>
 *
 *
 *
 */
public class BindInsertBeanPersonContentProvider extends ContentProvider {
  /**
   * <p>content provider's URI.</p>
   * <pre>content://sqlite.feature.javadoc.bean</pre>
   */
  public static final String URI = "content://sqlite.feature.javadoc.bean";

  /**
   * <p>datasource singleton</p>
   */
  private static BindInsertBeanPersonDataSource dataSource;

  /**
   * <p>Content provider authority</p>
   */
  public static final String AUTHORITY = "sqlite.feature.javadoc.bean";

  /**
   * <p>URI matcher</p>
   */
  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   */
  private static final Uri URI_PATH_PERSON_1 = Uri.parse(URI+"/persons");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre>
   */
  private static final Uri URI_PATH_PERSON_2 = Uri.parse(URI+"/persons/name");

  /**
   * <p>Uri</p>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre>
   */
  private static final Uri URI_PATH_PERSON_3 = Uri.parse(URI+"/persons/surname");

  static final String PATH_PERSON_1 = "persons";

  static final String PATH_PERSON_2 = "persons/name";

  static final String PATH_PERSON_3 = "persons/surname";

  static final int PATH_PERSON_1_INDEX = 1;

  static final int PATH_PERSON_2_INDEX = 2;

  static final int PATH_PERSON_3_INDEX = 3;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <p>Method associated to this URI is {@link InsertBeanPersonDaoImpl#insertOneBean0ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT_ONE_BEAN = URI_PATH_PERSON_1;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre>
   *
   * <p>Method associated to this URI is {@link InsertBeanPersonDaoImpl#insertOneBeanFieldName1ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT_ONE_BEAN_FIELD_NAME = URI_PATH_PERSON_2;

  /**
   * <h2>URI standard</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre></p>
   * <h2>URI with parameters</h2>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre>
   *
   * <p>Method associated to this URI is {@link InsertBeanPersonDaoImpl#insertOneBeanFieldSurname2ForContentProvider}</p>
   */
  public static final Uri URI_PERSON_INSERT_ONE_BEAN_FIELD_SURNAME = URI_PATH_PERSON_3;

  static {
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_1, PATH_PERSON_1_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_2, PATH_PERSON_2_INDEX);
    sURIMatcher.addURI(AUTHORITY, PATH_PERSON_3, PATH_PERSON_3_INDEX);
  }

  /**
   * <p>Create datasource and open database in read mode.</p>
   *
   * @see android.content.ContentProvider#onCreate()
   */
  @Override
  public boolean onCreate() {
    if (KriptonLibrary.getContext()==null) {
      KriptonLibrary.init(getContext());
    }
    dataSource = BindInsertBeanPersonDataSource.getInstance();
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

  @Override
  public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
      String sortOrder) {
    throw new IllegalArgumentException("Unknown URI for SELECT operation: " + uri);
  }

  /**
   *
   * <h2>Supported insert operations</h2>
   * <table>
   * <tr><th>URI</th><th>DAO.METHOD</th></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons</pre></td><td>{@link InsertBeanPersonDaoImpl#insertOneBean0ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/name</pre></td><td>{@link InsertBeanPersonDaoImpl#insertOneBeanFieldName1ForContentProvider}</td></tr>
   * <tr><td><pre>content://sqlite.feature.javadoc.bean/persons/surname</pre></td><td>{@link InsertBeanPersonDaoImpl#insertOneBeanFieldSurname2ForContentProvider}</td></tr>
   * </table>
   *
   */
  @Override
  public Uri insert(Uri uri, ContentValues contentValues) {
    long _id=-1;
    Uri _returnURL=null;
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        _id=dataSource.getInsertBeanPersonDao().insertOneBean0ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_PERSON_2_INDEX: {
        _id=dataSource.getInsertBeanPersonDao().insertOneBeanFieldName1ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      case PATH_PERSON_3_INDEX: {
        _id=dataSource.getInsertBeanPersonDao().insertOneBeanFieldSurname2ForContentProvider(uri, contentValues);
        _returnURL=Uri.withAppendedPath(uri, String.valueOf(_id));
        break;
      }
      default: {
        throw new IllegalArgumentException("Unknown URI for INSERT operation: " + uri);
      }
    }
    // log section for content provider insert BEGIN
    if (dataSource.isLogEnabled()) {
      Logger.info("Element is created with URI '%s'", _returnURL);
      Logger.info("Changes are notified for URI '%s'", uri);
    }
    // log section for content provider insert END
    getContext().getContentResolver().notifyChange(uri, null);
    return _returnURL;
  }

  @Override
  public int update(Uri uri, ContentValues contentValues, String selection,
      String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for UPDATE operation: " + uri);
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    throw new IllegalArgumentException("Unknown URI for DELETE operation: " + uri);
  }

  @Override
  public String getType(Uri uri) {
    switch (sURIMatcher.match(uri)) {
      case PATH_PERSON_1_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_2_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
      case PATH_PERSON_3_INDEX: {
        return "vnd.android.cursor.item/vnd.sqlite.feature.javadoc.bean.person";
      }
    }
    throw new IllegalArgumentException("Unknown URI for getType operation: " + uri);
  }
}
