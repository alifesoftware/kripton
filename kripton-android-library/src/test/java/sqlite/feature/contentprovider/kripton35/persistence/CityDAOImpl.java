package sqlite.feature.contentprovider.kripton35.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.io.IOException;
import java.util.Set;
import sqlite.feature.contentprovider.kripton35.entities.City;

/**
 * <p>
 * DAO implementation for entity <code>City</code>, based on interface <code>CityDAO</code>
 * </p>
 *
 *  @see City
 *  @see CityDAO
 *  @see sqlite.feature.contentprovider.kripton35.entities.CityTable
 */
public class CityDAOImpl extends Dao implements CityDAO {
  private static SupportSQLiteStatement insertBeanPreparedStatement0;

  private static final Set<String> insertBean0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "name");

  /**
   * SQL definition for method selectCityFromPerson
   */
  private static final String SELECT_CITY_FROM_PERSON_SQL2 = "select * from city where id = (select id from person where id=? )";

  private static final Set<String> selectCityFromPerson1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "name");

  public CityDAOImpl(BindPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO city (name) VALUES (:bean.name)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertBean(City bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertBeanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO city (name) VALUES (?)";
      insertBeanPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertBeanPreparedStatement0);
    _contentValues.put("name", bean.name);

    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 
      StringBuffer _columnNameBuffer=new StringBuffer();
      StringBuffer _columnValueBuffer=new StringBuffer();
      String _columnSeparator="";
      for (String columnName:_contentValues.keys()) {
        _columnNameBuffer.append(_columnSeparator+columnName);
        _columnValueBuffer.append(_columnSeparator+":"+columnName);
        _columnSeparator=", ";
      }
      Logger.info("INSERT INTO city (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

      // log for content values -- BEGIN
      Triple<String, Object, KriptonContentValues.ParamType> _contentValue;
      for (int i = 0; i < _contentValues.size(); i++) {
        _contentValue = _contentValues.get(i);
        if (_contentValue.value1==null) {
          Logger.info("==> :%s = <null>", _contentValue.value0);
        } else {
          Logger.info("==> :%s = '%s' (%s)", _contentValue.value0, StringUtils.checkSize(_contentValue.value1), _contentValue.value1.getClass().getCanonicalName());
        }
      }
      // log for content values -- END
      // log for insert -- END 


      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    // insert operation
    long result = KriptonDatabaseHelper.insert(insertBeanPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO City (name) VALUES (:bean.name)</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO city (name) VALUES (:bean.name)</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35/cities"
   * @param contentValues content values
   * @return new row's id
   */
  long insertBean0ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertBean0ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35/cities', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "city" ));
      }
    }


    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:_contentValues.values().keySet()) {
      _contentValue=_contentValues.values().get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // conflict algorithm NONE
    // insert operation
    long result = database().insert("city", 0, _contentValues.values());
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from city where id = (select id from person where id=${personId} )</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link City}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:personId</dt><dd>is binded to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is binded to <code>:personId</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public City selectCityFromPerson(long personId) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_CITY_FROM_PERSON_SQL2;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(personId));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section for select BEGIN
    if (_context.isLogEnabled()) {
      // manage log
      Logger.info(_sql);

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section for select END
    try (Cursor _cursor = database().query(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanHelper - BEGIN

      City resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");

        resultBean=new City();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.contentprovider.kripton35/cities/person/#</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>select * from City where id = (select id from Person where id=${personId} )</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>select * from city where id = (select id from person where id=${personId} )</pre>
   *
   * <h3>Path variables defined:</h3>
   * <ul>
   * <li><strong>:personId</strong> at path segment 2</li>
   * </ul>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.contentprovider.kripton35/cities/person/#"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor selectCityFromPerson1ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("select %s from city ");

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where id = (select id from person where id=? )";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!selectCityFromPerson1ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.contentprovider.kripton35/cities/person/#', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "city" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: selectCityFromPerson1ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
    // Add parameter personId at path segment 2
    _contentValues.addWhereArgs(uri.getPathSegments().get(2));
    String _sql=String.format(_sqlBuilder.toString(), _projectionBuffer.toString());

    // manage log
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _contentValues.whereArgs()) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END

    // execute query
    Cursor _result = database().query(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  public static void clearCompiledStatements() {
    try {
      if (insertBeanPreparedStatement0!=null) {
        insertBeanPreparedStatement0.close();
        insertBeanPreparedStatement0=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
