package sqlite.select.scalarlist;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see sqlite.select.Person
 *  @see PersonDAO
 *  @see sqlite.select.PersonTable
 */
public class PersonDAOImpl extends Dao implements PersonDAO {
  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL1 = "SELECT type_name FROM person ORDER BY type_name";

  /**
   * SQL definition for method selectAll2
   */
  private static final String SELECT_ALL2_SQL2 = "SELECT birth_day FROM person ORDER BY type_name";

  public PersonDAOImpl(BindPersonDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT type_name FROM person ORDER BY type_name</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link sqlite.select.Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>type_name</dt><dd>is associated to bean's property <strong>typeName</strong></dd>
   * </dl>
   *
   * @return collection of single value extracted by query.
   */
  @Override
  public Set<String> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL1;
    // add where arguments
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
      // Specialized part - SelectScalarListHelper - BEGIN

      LinkedHashSet<String> resultList=new LinkedHashSet<String>();


      if (_cursor.moveToFirst()) {

        do
         {
          if (!_cursor.isNull(0)) {
            resultList.add(_cursor.getString(0));
          } else {
            resultList.add(null);
          }
        } while (_cursor.moveToNext());
      }
      return resultList;
    }
    // Specialized part - SelectScalarListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT birth_day FROM person ORDER BY type_name</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link sqlite.select.Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return collection of single value extracted by query.
   */
  @Override
  public ArrayList<Date> selectAll2() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL2_SQL2;
    // add where arguments
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
      // Specialized part - SelectScalarListHelper - BEGIN

      ArrayList<Date> resultList=new ArrayList<Date>(_cursor.getCount());


      if (_cursor.moveToFirst()) {

        do
         {
          if (!_cursor.isNull(0)) {
            resultList.add(DateUtils.read(_cursor.getString(0)));
          } else {
            resultList.add(null);
          }
        } while (_cursor.moveToNext());
      }
      return resultList;
    }
    // Specialized part - SelectScalarListHelper - END
  }

  public static void clearCompiledStatements() {
  }
}
