package sqlite.feature.foreignkey;

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
 * DAO implementation for entity <code>BeanA_4</code>, based on interface <code>DaoBeanA_4</code>
 * </p>
 *
 *  @see BeanA_4
 *  @see DaoBeanA_4
 *  @see BeanA_4Table
 */
public class DaoBeanA_4Impl extends Dao implements DaoBeanA_4 {
  private static final String SELECT_ALL_SQL4 = "SELECT pk, bean_a2_id, value_string FROM bean_a_4";

  private static final String SELECT_BY_ID_SQL5 = "SELECT pk, bean_a2_id, value_string FROM bean_a_4 WHERE pk=?";

  private static final String SELECT_BY_STRING_SQL6 = "SELECT pk FROM bean_a_4 WHERE value_string=?";

  private static SQLiteStatement insertPreparedStatement0;

  private static SQLiteStatement updatePreparedStatement1;

  public DaoBeanA_4Impl(BindDummy2DaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT pk, bean_a2_id, value_string FROM bean_a_4</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link BeanA_4}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_4> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL4;
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

      ArrayList<BeanA_4> resultList=new ArrayList<BeanA_4>(_cursor.getCount());
      BeanA_4 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_a2_id");
        int index2=_cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new BeanA_4();

          resultBean.id=_cursor.getLong(index0);
          resultBean.beanA2Id=_cursor.getLong(index1);
          if (!_cursor.isNull(index2)) { resultBean.valueString=_cursor.getString(index2); }

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
   * <pre>SELECT pk, bean_a2_id, value_string FROM bean_a_4 WHERE pk=${id}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link BeanA_4}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>bean_a2_id</dt><dd>is associated to bean's property <strong>beanA2Id</strong></dd>
   * 	<dt>value_string</dt><dd>is associated to bean's property <strong>valueString</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_4> selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL5;
    // add where arguments
    _contentValues.addWhereArgs(String.valueOf(id));
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

      ArrayList<BeanA_4> resultList=new ArrayList<BeanA_4>(_cursor.getCount());
      BeanA_4 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");
        int index1=_cursor.getColumnIndex("bean_a2_id");
        int index2=_cursor.getColumnIndex("value_string");

        do
         {
          resultBean=new BeanA_4();

          resultBean.id=_cursor.getLong(index0);
          resultBean.beanA2Id=_cursor.getLong(index1);
          if (!_cursor.isNull(index2)) { resultBean.valueString=_cursor.getString(index2); }

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
   * <pre>SELECT pk FROM bean_a_4 WHERE value_string=${dummy}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link BeanA_4}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>pk</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:dummy</dt><dd>is binded to method's parameter <strong>value</strong></dd>
   * </dl>
   *
   * @param value
   * 	is binded to <code>:dummy</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<BeanA_4> selectByString(String value) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_STRING_SQL6;
    // add where arguments
    _contentValues.addWhereArgs((value==null?"":value));
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

      ArrayList<BeanA_4> resultList=new ArrayList<BeanA_4>(_cursor.getCount());
      BeanA_4 resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("pk");

        do
         {
          resultBean=new BeanA_4();

          resultBean.id=_cursor.getLong(index0);

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO bean_a_4 (bean_a2_id, value_string) VALUES (:bean.beanA2Id, :bean.valueString)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>bean_a2_id</dt><dd>is mapped to <strong>:bean.beanA2Id</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>:bean.valueString</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(BeanA_4 bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO bean_a_4 (bean_a2_id, value_string) VALUES (?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("bean_a2_id", bean.beanA2Id);
    _contentValues.put("value_string", bean.valueString);

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
      Logger.info("INSERT INTO bean_a_4 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    bean.id=result;

    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE bean_a_4 SET bean_a2_id=:beanA2Id, value_string=:valueString WHERE value_string=${bean.valueString}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>bean_a2_id</dt><dd>is mapped to <strong>:bean.beanA2Id</strong></dd>
   * 	<dt>value_string</dt><dd>is mapped to <strong>:bean.valueString</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.valueString</dt><dd>is mapped to method's parameter <strong>bean.valueString</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   *
   * @return number of updated records
   */
  @Override
  public int update(BeanA_4 bean) {
    if (updatePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="UPDATE bean_a_4 SET bean_a2_id=?, value_string=? WHERE value_string=?";
      updatePreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement1);
    _contentValues.put("bean_a2_id", bean.beanA2Id);
    _contentValues.put("value_string", bean.valueString);

    _contentValues.addWhereArgs((bean.valueString==null?"":bean.valueString));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE bean_a_4 SET bean_a2_id=:bean_a2_id, value_string=:value_string WHERE value_string=?");

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

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement1, _contentValues);
    return result;
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (updatePreparedStatement1!=null) {
      updatePreparedStatement1.close();
      updatePreparedStatement1=null;
    }
  }
}
