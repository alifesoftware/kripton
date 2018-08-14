package sqlite.feature.custombean.case1;

import android.arch.lifecycle.LiveData;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.livedata.KriptonComputableLiveData;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.common.CollectionUtils;
import com.abubusoft.kripton.common.DateUtils;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
  private static final String FIND_ALL_LOANS_SQL10 = "SELECT id, book_id, end_time, start_time, user_id FROM loan";

  private static final Set<String> findAllLoans0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "book_id", "end_time", "start_time", "user_id");

  private static final String FIND_ALL_WITH_USER_AND_BOOK_SQL11 = "SELECT loan.id, book.title as title, user.name as name, loan.start_time, loan.end_time From loan INNER JOIN book ON loan.book_id = book.id INNER JOIN user ON loan.user_id = user.id ";

  private static final Set<String> findAllWithUserAndBook1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "id", "title", "name", "start_time", "end_time");

  private static SQLiteStatement insertLoanPreparedStatement0;

  private static SQLiteStatement deleteAllPreparedStatement1;

  static Collection<WeakReference<KriptonComputableLiveData<?>>> liveDatas = new CopyOnWriteArraySet<WeakReference<KriptonComputableLiveData<?>>>();

  public LoanDaoImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, book_id, end_time, start_time, user_id FROM loan</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Loan}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>book_id</dt><dd>is associated to bean's property <strong>bookId</strong></dd>
   * 	<dt>end_time</dt><dd>is associated to bean's property <strong>endTime</strong></dd>
   * 	<dt>start_time</dt><dd>is associated to bean's property <strong>startTime</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  protected List<Loan> findAllLoansForLiveData() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_ALL_LOANS_SQL10;
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

      ArrayList<Loan> resultList=new ArrayList<Loan>(_cursor.getCount());
      Loan resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("book_id");
        int index2=_cursor.getColumnIndex("end_time");
        int index3=_cursor.getColumnIndex("start_time");
        int index4=_cursor.getColumnIndex("user_id");

        do
         {
          resultBean=new Loan();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.bookId=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.endTime=DateUtils.read(_cursor.getString(index2)); }
          if (!_cursor.isNull(index3)) { resultBean.startTime=DateUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.userId=_cursor.getString(index4); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, book_id, end_time, start_time, user_id FROM loan</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Loan}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>book_id</dt><dd>is associated to bean's property <strong>bookId</strong></dd>
   * 	<dt>end_time</dt><dd>is associated to bean's property <strong>endTime</strong></dd>
   * 	<dt>start_time</dt><dd>is associated to bean's property <strong>startTime</strong></dd>
   * 	<dt>user_id</dt><dd>is associated to bean's property <strong>userId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<Loan>> findAllLoans() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<Loan>> builder=new KriptonComputableLiveData<List<Loan>>() {
      @Override
      protected List<Loan> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Loan>>() {
          @Override
          public List<Loan> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getLoanDao().findAllLoansForLiveData();
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadAll</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT id, bookId, endTime, startTime, userId FROM Loan</pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT id, book_id, end_time, start_time, user_id FROM loan</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.custombean.case1/loan/loadAll"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor findAllLoans0ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s FROM loan");
    String _sqlWhereStatement="";

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!findAllLoans0ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.custombean.case1/loan/loadAll', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "loan" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: findAllLoans0ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
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
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT loan.id, book.title as title, user.name as name, loan.start_time, loan.end_time From loan INNER JOIN book ON loan.book_id = book.id INNER JOIN user ON loan.user_id = user.id </pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LoanWithUserAndBook}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>bookTitle</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>userName</strong></dd>
   * 	<dt>start_time</dt><dd>is associated to bean's property <strong>startTime</strong></dd>
   * 	<dt>end_time</dt><dd>is associated to bean's property <strong>endTime</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  protected List<LoanWithUserAndBook> findAllWithUserAndBookForLiveData() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_ALL_WITH_USER_AND_BOOK_SQL11;
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

      ArrayList<LoanWithUserAndBook> resultList=new ArrayList<LoanWithUserAndBook>(_cursor.getCount());
      LoanWithUserAndBook resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("title");
        int index2=_cursor.getColumnIndex("name");
        int index3=_cursor.getColumnIndex("start_time");
        int index4=_cursor.getColumnIndex("end_time");

        do
         {
          resultBean=new LoanWithUserAndBook();

          resultBean.id=_cursor.getString(index0);
          if (!_cursor.isNull(index1)) { resultBean.bookTitle=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.userName=_cursor.getString(index2); }
          if (!_cursor.isNull(index3)) { resultBean.startTime=DateUtils.read(_cursor.getString(index3)); }
          if (!_cursor.isNull(index4)) { resultBean.endTime=DateUtils.read(_cursor.getString(index4)); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT loan.id, book.title as title, user.name as name, loan.start_time, loan.end_time From loan INNER JOIN book ON loan.book_id = book.id INNER JOIN user ON loan.user_id = user.id </pre>
   *
   * <h2>Mapped class:</h2>
   * {@link LoanWithUserAndBook}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>title</dt><dd>is associated to bean's property <strong>bookTitle</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>userName</strong></dd>
   * 	<dt>start_time</dt><dd>is associated to bean's property <strong>startTime</strong></dd>
   * 	<dt>end_time</dt><dd>is associated to bean's property <strong>endTime</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public LiveData<List<LoanWithUserAndBook>> findAllWithUserAndBook() {
    // common part generation - BEGIN
    // common part generation - END
    final KriptonComputableLiveData<List<LoanWithUserAndBook>> builder=new KriptonComputableLiveData<List<LoanWithUserAndBook>>() {
      @Override
      protected List<LoanWithUserAndBook> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<LoanWithUserAndBook>>() {
          @Override
          public List<LoanWithUserAndBook> onExecute(BindAppDaoFactory daoFactory) {
            return daoFactory.getLoanDao().findAllWithUserAndBookForLiveData();
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h1>Content provider URI (SELECT operation):</h1>
   * <pre>content://sqlite.feature.custombean.case1/loan/loadLoanAndBook</pre>
   *
   * <h2>JQL SELECT for Content Provider</h2>
   * <pre>SELECT Loan.id, Book.title as bookTitle, User.name as userName, Loan.startTime, Loan.endTime From Loan INNER JOIN Book ON Loan.bookId = Book.id INNER JOIN User ON Loan.userId = User.id </pre>
   *
   * <h2>SQL SELECT for Content Provider</h2>
   * <pre>SELECT loan.id, book.title as title, user.name as name, loan.start_time, loan.end_time From loan INNER JOIN book ON loan.book_id = book.id INNER JOIN user ON loan.user_id = user.id </pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.custombean.case1/loan/loadLoanAndBook"
   * @param selection dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @param selectionArgs arguments of dynamic part of <code>where</code> statement <b>NOT USED</b>
   * @return number of effected rows
   */
  Cursor findAllWithUserAndBook1ForContentProvider(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    Logger.info("Execute SELECT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    StringBuilder _projectionBuffer=new StringBuilder();
    _sqlBuilder.append("SELECT %s From loan INNER JOIN book ON loan.book_id = book.id INNER JOIN user ON loan.user_id = user.id ");
    String _sqlWhereStatement="";

    // manage projected columns
    String _columnSeparator="";
    if (projection!=null && projection.length>0) {
      for (String columnName:projection) {
        if (!findAllWithUserAndBook1ForContentProviderColumnSet.contains(columnName)) {
          throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.custombean.case1/loan/loadLoanAndBook', column '%s' does not exists in table '%s' or can not be defined in this SELECT operation", columnName, "loan_with_user_and_book" ));
        }
        _projectionBuffer.append(_columnSeparator + columnName);
        _columnSeparator=", ";
      }
    } else {
      for (String column: findAllWithUserAndBook1ForContentProviderColumnSet) {
        _projectionBuffer.append(_columnSeparator + column);
        _columnSeparator=", ";
      }
    }
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
    Cursor _result = database().rawQuery(_sql, _contentValues.whereArgsAsArray());
    return _result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO loan (id, book_id, end_time, start_time, user_id) VALUES (:loan.id, :loan.bookId, :loan.endTime, :loan.startTime, :loan.userId)</pre>
   *
   * <p><code>loan.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>id</dt><dd>is mapped to <strong>:loan.id</strong></dd>
   * 	<dt>book_id</dt><dd>is mapped to <strong>:loan.bookId</strong></dd>
   * 	<dt>end_time</dt><dd>is mapped to <strong>:loan.endTime</strong></dd>
   * 	<dt>start_time</dt><dd>is mapped to <strong>:loan.startTime</strong></dd>
   * 	<dt>user_id</dt><dd>is mapped to <strong>:loan.userId</strong></dd>
   * </dl>
   *
   * @param loan
   * 	is mapped to parameter <strong>loan</strong>
   *
   */
  @Override
  public void insertLoan(Loan loan) {
    // Specialized Insert - InsertType - BEGIN
    if (insertLoanPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO loan (id, book_id, end_time, start_time, user_id) VALUES (?, ?, ?, ?, ?)";
      insertLoanPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertLoanPreparedStatement0);
    _contentValues.put("id", loan.id);
    _contentValues.put("book_id", loan.bookId);
    _contentValues.put("end_time", DateUtils.write(loan.endTime));
    _contentValues.put("start_time", DateUtils.write(loan.startTime));
    _contentValues.put("user_id", loan.userId);

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
    long result = KriptonDatabaseWrapper.insert(insertLoanPreparedStatement0, _contentValues);
    // support for livedata
    registryEvent(result>0?1:0);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM loan</pre>
   *
   * <p>No where parameters were found.</p>
   *
   */
  @Override
  public void deleteAll() {
    if (deleteAllPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM loan";
      deleteAllPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteAllPreparedStatement1);

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM loan");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteAllPreparedStatement1, _contentValues);
    // support for livedata
    registryEvent(result);
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindAppDataSource.LOAN_DAO_UID);
    } else {
      invalidateLiveData();
    }
  }

  protected void registryLiveData(KriptonComputableLiveData<?> value) {
    liveDatas.add(new WeakReference<KriptonComputableLiveData<?>>(value));
  }

  /**
   * <p>Invalidate livedata.</p>
   *
   */
  public void invalidateLiveData() {
    for (WeakReference<KriptonComputableLiveData<?>> item: liveDatas) {
      if (item.get()!=null) {
        item.get().invalidate();
      }
    }
  }

  public static void clearCompiledStatements() {
    if (insertLoanPreparedStatement0!=null) {
      insertLoanPreparedStatement0.close();
      insertLoanPreparedStatement0=null;
    }
    if (deleteAllPreparedStatement1!=null) {
      deleteAllPreparedStatement1.close();
      deleteAllPreparedStatement1=null;
    }
  }
}
