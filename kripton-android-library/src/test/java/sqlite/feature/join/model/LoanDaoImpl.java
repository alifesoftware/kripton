package sqlite.feature.join.model;

import androidx.sqlite.db.SupportSQLiteStatement;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseHelper;
import com.abubusoft.kripton.common.SQLDateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import java.io.IOException;

/**
 * <p>
 * DAO implementation for entity <code>Loan</code>, based on interface <code>LoanDao</code>
 * </p>
 *
 *  @see Loan
 *  @see LoanDao
 *  @see LoanTable
 */
public class LoanDaoImpl extends Dao implements LoanDao {
  private static SupportSQLiteStatement insertPreparedStatement0;

  public LoanDaoImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO loan (book_id, end_time, start_time, user_id) VALUES (:bookId, :endTime, :startTime, :userId)</pre>
   *
   * <p><code>entity.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>book_id</dt><dd>is mapped to <strong>:entity.bookId</strong></dd>
   * 	<dt>end_time</dt><dd>is mapped to <strong>:entity.endTime</strong></dd>
   * 	<dt>start_time</dt><dd>is mapped to <strong>:entity.startTime</strong></dd>
   * 	<dt>user_id</dt><dd>is mapped to <strong>:entity.userId</strong></dd>
   * </dl>
   *
   * @param entity
   * 	is mapped to parameter <strong>entity</strong>
   *
   */
  @Override
  public void insert(Loan entity) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO loan (book_id, end_time, start_time, user_id) VALUES (?, ?, ?, ?)";
      insertPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("book_id", entity.bookId);
    _contentValues.put("end_time", SQLDateUtils.write(entity.endTime));
    _contentValues.put("start_time", SQLDateUtils.write(entity.startTime));
    _contentValues.put("user_id", entity.userId);

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
      Logger.info("INSERT INTO loan (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertPreparedStatement0, _contentValues);
    // if PK string, can not overwrite id (with a long) same thing if column type is UNMANAGED (user manage PK)
    entity.id=result;
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    try {
      if (insertPreparedStatement0!=null) {
        insertPreparedStatement0.close();
        insertPreparedStatement0=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
