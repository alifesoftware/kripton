package sqlite.stack44633883;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>SchoolLunch</code>, based on interface <code>SchoolLunchDAO</code>
 * </p>
 *
 *  @see SchoolLunch
 *  @see SchoolLunchDAO
 *  @see SchoolLunchTable
 */
public class SchoolLunchDAOImpl extends Dao implements SchoolLunchDAO {
  /**
   * SQL definition for method get1
   */
  private static final String GET1_SQL1 = "SELECT * FROM SchoolLunches ORDER BY fruits COLLATE LOCALIZED";

  /**
   * SQL definition for method getAll
   */
  private static final String GET_ALL_SQL2 = "SELECT lunch_id, contains_meat, fresh, fruits FROM SchoolLunches";

  private static SQLiteStatement insertAllPreparedStatement0;

  private static SQLiteStatement deleteAllPreparedStatement1;

  public SchoolLunchDAOImpl(BindSchoolLunchDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT * FROM SchoolLunches ORDER BY fruits COLLATE LOCALIZED</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link SchoolLunch}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>lunch_id</dt><dd>is associated to bean's property <strong>lunchId</strong></dd>
   * 	<dt>contains_meat</dt><dd>is associated to bean's property <strong>containsMeat</strong></dd>
   * 	<dt>fresh</dt><dd>is associated to bean's property <strong>fresh</strong></dd>
   * 	<dt>fruits</dt><dd>is associated to bean's property <strong>fruits</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<SchoolLunch> get1() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET1_SQL1;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<SchoolLunch> resultList=new ArrayList<SchoolLunch>(_cursor.getCount());
      SchoolLunch resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("lunch_id");
        int index1=_cursor.getColumnIndex("contains_meat");
        int index2=_cursor.getColumnIndex("fresh");
        int index3=_cursor.getColumnIndex("fruits");

        do
         {
          resultBean=new SchoolLunch();

          resultBean.setLunchId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setContainsMeat(_cursor.getInt(index1)==0?false:true); }
          if (!_cursor.isNull(index2)) { resultBean.setFresh(_cursor.getInt(index2)==0?false:true); }
          if (!_cursor.isNull(index3)) { resultBean.setFruits(SchoolLunchTable.parseFruits(_cursor.getBlob(index3))); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT lunch_id, contains_meat, fresh, fruits FROM SchoolLunches</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link SchoolLunch}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>lunch_id</dt><dd>is associated to bean's property <strong>lunchId</strong></dd>
   * 	<dt>contains_meat</dt><dd>is associated to bean's property <strong>containsMeat</strong></dd>
   * 	<dt>fresh</dt><dd>is associated to bean's property <strong>fresh</strong></dd>
   * 	<dt>fruits</dt><dd>is associated to bean's property <strong>fruits</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<SchoolLunch> getAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=GET_ALL_SQL2;
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
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",_cursor.getCount());
      }
      // log section END
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<SchoolLunch> resultList=new ArrayList<SchoolLunch>(_cursor.getCount());
      SchoolLunch resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("lunch_id");
        int index1=_cursor.getColumnIndex("contains_meat");
        int index2=_cursor.getColumnIndex("fresh");
        int index3=_cursor.getColumnIndex("fruits");

        do
         {
          resultBean=new SchoolLunch();

          resultBean.setLunchId(_cursor.getLong(index0));
          if (!_cursor.isNull(index1)) { resultBean.setContainsMeat(_cursor.getInt(index1)==0?false:true); }
          if (!_cursor.isNull(index2)) { resultBean.setFresh(_cursor.getInt(index2)==0?false:true); }
          if (!_cursor.isNull(index3)) { resultBean.setFruits(SchoolLunchTable.parseFruits(_cursor.getBlob(index3))); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO SchoolLunches (contains_meat, fresh, fruits) VALUES (:schoolLunches.containsMeat, :schoolLunches.fresh, :schoolLunches.fruits)</pre>
   *
   * <p><code>schoolLunches.lunchId</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>contains_meat</dt><dd>is mapped to <strong>:schoolLunches.containsMeat</strong></dd>
   * 	<dt>fresh</dt><dd>is mapped to <strong>:schoolLunches.fresh</strong></dd>
   * 	<dt>fruits</dt><dd>is mapped to <strong>:schoolLunches.fruits</strong></dd>
   * </dl>
   *
   * @param schoolLunches
   * 	is mapped to parameter <strong>schoolLunches</strong>
   *
   */
  @Override
  public void insertAll(SchoolLunch schoolLunches) {
    // Specialized Insert - InsertType - BEGIN
    if (insertAllPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO SchoolLunches (contains_meat, fresh, fruits) VALUES (?, ?, ?)";
      insertAllPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertAllPreparedStatement0);
    _contentValues.put("contains_meat", schoolLunches.isContainsMeat());
    _contentValues.put("fresh", schoolLunches.isFresh());
    _contentValues.put("fruits", SchoolLunchTable.serializeFruits(schoolLunches.getFruits()));

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
      Logger.info("INSERT INTO SchoolLunches (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertAllPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    schoolLunches.setLunchId(result);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM SchoolLunches</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM SchoolLunches";
      deleteAllPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement1);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM SchoolLunches");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement1, _contentValues);
  }

  public static void clearCompiledStatements() {
    if (insertAllPreparedStatement0!=null) {
      insertAllPreparedStatement0.close();
      insertAllPreparedStatement0=null;
    }
    if (deleteAllPreparedStatement1!=null) {
      deleteAllPreparedStatement1.close();
      deleteAllPreparedStatement1=null;
    }
  }
}
