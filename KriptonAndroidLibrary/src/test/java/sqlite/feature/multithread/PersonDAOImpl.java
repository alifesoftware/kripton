package sqlite.feature.multithread;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.SQLContext;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>PersonDAO</code>
 * </p>
 *
 *  @see Person
 *  @see PersonDAO
 *  @see PersonTable
 */
public class PersonDAOImpl extends AbstractDao implements PersonDAO {
  private static SQLiteStatement insertThread1PreparedStatement0;

  private static SQLiteStatement insertThread2PreparedStatement1;

  private static final String SELECT_THREAD1_SQL1 = "SELECT id, name, surname, birth_city, birth_day FROM person";

  private static final String SELECT_THREAD2_SQL2 = "SELECT id, name, surname, birth_city, birth_day FROM person";

  public PersonDAOImpl(SQLContext context) {
    super(context);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${bean.name}, ${bean.surname}, ${bean.birthCity}, ${bean.birthDay})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertThread1(Person bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate(insertThread1PreparedStatement0);
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    if (bean.surname!=null) {
      _contentValues.put("surname", bean.surname);
    } else {
      _contentValues.putNull("surname");
    }
    if (bean.birthCity!=null) {
      _contentValues.put("birth_city", bean.birthCity);
    } else {
      _contentValues.putNull("birth_city");
    }
    if (bean.birthDay!=null) {
      _contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }

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
      Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertThread1PreparedStatement0==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertThread1PreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(_context, insertThread1PreparedStatement0, _contentValues);
    bean.id=result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person (name, surname, birth_city, birth_day) VALUES (${bean.name}, ${bean.surname}, ${bean.birthCity}, ${bean.birthDay})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>birth_city</dt><dd>is mapped to <strong>${bean.birthCity}</strong></dd>
   * 	<dt>birth_day</dt><dd>is mapped to <strong>${bean.birthDay}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insertThread2(Person bean) {
    KriptonContentValues _contentValues=contentValuesForUpdate(insertThread2PreparedStatement1);
    if (bean.name!=null) {
      _contentValues.put("name", bean.name);
    } else {
      _contentValues.putNull("name");
    }
    if (bean.surname!=null) {
      _contentValues.put("surname", bean.surname);
    } else {
      _contentValues.putNull("surname");
    }
    if (bean.birthCity!=null) {
      _contentValues.put("birth_city", bean.birthCity);
    } else {
      _contentValues.putNull("birth_city");
    }
    if (bean.birthDay!=null) {
      _contentValues.put("birth_day", DateUtils.write(bean.birthDay));
    } else {
      _contentValues.putNull("birth_day");
    }

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
      Logger.info("INSERT INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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

    }
    // log section END
    // insert operation
    if (insertThread2PreparedStatement1==null) {
      // generate SQL for insert
      String _sql=String.format("INSERT INTO person (%s) VALUES (%s)", _contentValues.keyList(), _contentValues.keyValueList());
      insertThread2PreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    long result = KriptonDatabaseWrapper.insert(_context, insertThread2PreparedStatement1, _contentValues);
    bean.id=result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectThread1() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_THREAD1_SQL1;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
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
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname, birth_city, birth_day FROM person</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>birth_city</dt><dd>is associated to bean's property <strong>birthCity</strong></dd>
   * 	<dt>birth_day</dt><dd>is associated to bean's property <strong>birthDay</strong></dd>
   * </dl>
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectThread2() {
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_THREAD2_SQL2;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    // log section BEGIN
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
    // log section END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      // log section BEGIN
      if (_context.isLogEnabled()) {
        Logger.info("Rows found: %s",cursor.getCount());
      }
      // log section END

      Person resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("surname");
        int index3=cursor.getColumnIndex("birth_city");
        int index4=cursor.getColumnIndex("birth_day");

        resultBean=new Person();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.name=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.surname=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.birthCity=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.birthDay=DateUtils.read(cursor.getString(index4)); }

      }
      return resultBean;
    }
  }

  public static void clearCompiledStatements() {
    if (insertThread1PreparedStatement0!=null) {
      insertThread1PreparedStatement0.close();
      insertThread1PreparedStatement0=null;
    }
    if (insertThread2PreparedStatement1!=null) {
      insertThread2PreparedStatement1.close();
      insertThread2PreparedStatement1=null;
    }
  }
}
