package sqlite.feature.pagedadapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.LiveDataHandler;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.PageRequest;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import com.abubusoft.kripton.android.sqlite.PagedResultImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteEvent;
import com.abubusoft.kripton.androidx.livedata.KriptonXPagedLiveDataHandlerImpl;
import com.abubusoft.kripton.androidx.livedata.PagedLiveData;
import com.abubusoft.kripton.common.StringUtils;
import com.abubusoft.kripton.common.Triple;
import io.reactivex.subjects.PublishSubject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>DaoPerson</code>
 * </p>
 *
 *  @see Person
 *  @see DaoPerson
 *  @see PersonTable
 */
public class DaoPersonImpl extends Dao implements DaoPerson {
  private static SQLiteStatement insertPreparedStatement0;

  /**
   * SQL definition for method selectById
   */
  private static final String SELECT_BY_ID_SQL1 = "SELECT id, name, surname FROM person WHERE id=?";

  private static SQLiteStatement deleteByIdPreparedStatement1;

  private static SQLiteStatement updatePreparedStatement2;

  static Collection<WeakReference<LiveDataHandler>> liveDatas = new CopyOnWriteArraySet<WeakReference<LiveDataHandler>>();

  private static final PublishSubject<SQLiteEvent> subject = PublishSubject.create();

  public DaoPersonImpl(BindAppDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person LIMIT 30 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<Person> selectAll(PaginatedResult0 paginatedResult) {
    // total count - BEGIN
    paginatedResult.setTotalElements(this.selectAllTotalCount(paginatedResult));
    // total count - END
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT "+paginatedResult.getPageSize();
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+paginatedResult.getOffset();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  private int selectAllTotalCount(PaginatedResult0 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT count(*) FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN
      // manage query for total count eements
      int _result=-1;

      if (_cursor.moveToFirst()) {
        _result=_cursor.getInt(0);
      }
      // log section for select BEGIN
      if (_context.isLogEnabled()) {
        // manage log

        // log for where parameters -- BEGIN
        int _whereParamCounter=0;
        for (String _whereParamItem: _contentValues.whereArgs()) {
          Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
        }
        // log for where parameters -- END
        Logger.info("Total elements found: %s", _result);
        // log section for select END
      }
      return _result;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person LIMIT 30 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @param pageRequest
   * 	page request
   * @return result list
   */
  private List<Person> selectAllWithPageRequest(PageRequest pageRequest) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    String _sqlWhereStatement="";
    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT "+pageRequest.getPageSize();
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+pageRequest.getOffset();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person LIMIT 30 OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public PagedLiveData<List<Person>> selectAll() {
    // common part generation - BEGIN
    // common part generation - END
    final PaginatedResult0 paginatedResult=new PaginatedResult0();
    final KriptonXPagedLiveDataHandlerImpl<List<Person>> builder=new KriptonXPagedLiveDataHandlerImpl<List<Person>>(paginatedResult) {
      @Override
      protected List<Person> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Person>>() {
          @Override
          public List<Person> onExecute(BindAppDaoFactory daoFactory) {
            return paginatedResult.execute(daoFactory);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person WHERE name like :filter || '%' LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:filter</dt><dd>is binded to method's parameter <strong>filter</strong></dd>
   * </dl>
   *
   * @param filter
   * 	is binded to <code>:filter</code>
   * @param paginatedResult
   * 	handler of paginated result
   * @return result list
   */
  private List<Person> selectByName(String filter, PaginatedResult1 paginatedResult) {
    // total count - BEGIN
    paginatedResult.setTotalElements(this.selectByNameTotalCount(filter, paginatedResult));
    // total count - END
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT "+paginatedResult.getPageSize();
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+paginatedResult.getOffset();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs((filter==null?"":filter));
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  private int selectByNameTotalCount(String filter, PaginatedResult1 paginatedResult) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT count(*) FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs((filter==null?"":filter));
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN
      // manage query for total count eements
      int _result=-1;

      if (_cursor.moveToFirst()) {
        _result=_cursor.getInt(0);
      }
      // log section for select BEGIN
      if (_context.isLogEnabled()) {
        // manage log

        // log for where parameters -- BEGIN
        int _whereParamCounter=0;
        for (String _whereParamItem: _contentValues.whereArgs()) {
          Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
        }
        // log for where parameters -- END
        Logger.info("Total elements found: %s", _result);
        // log section for select END
      }
      return _result;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person WHERE name like :filter || '%' LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:filter</dt><dd>is binded to method's parameter <strong>filter</strong></dd>
   * </dl>
   *
   * @param filter
   * 	is binded to <code>:filter</code>
   * @param pageRequest
   * 	page request
   * @return result list
   */
  private List<Person> selectByNameWithPageRequest(String filter, PageRequest pageRequest) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    StringBuilder _sqlBuilder=sqlBuilder();
    _sqlBuilder.append("SELECT id, name, surname FROM person");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE name like ? || '%'";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END
    // generation limit - BEGIN
    String _sqlLimitStatement=" LIMIT "+pageRequest.getPageSize();
    _sqlBuilder.append(_sqlLimitStatement);
    // generation limit - END

    // generation offset - BEGIN
    String _sqlOffsetStatement=" OFFSET "+pageRequest.getOffset();
    _sqlBuilder.append(_sqlOffsetStatement);
    // generation offset - END

    String _sql=_sqlBuilder.toString();
    // add where arguments
    _contentValues.addWhereArgs((filter==null?"":filter));
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
      // Specialized part II - SelectPaginatedResultHelper - BEGIN

      List<Person> resultList=new ArrayList<Person>(_cursor.getCount());
      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        do
         {
          resultBean=new Person();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
          if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part II - SelectPaginatedResultHelper - END
  }

  /**
   * <h2>Live data</h2>
   * <p>This method open a connection internally.</p>
   *
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person WHERE name like :filter || '%' LIMIT #{DYNAMIC_PAGE_SIZE} OFFSET #{DYNAMIC_PAGE_OFFSET}</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:filter</dt><dd>is binded to method's parameter <strong>filter</strong></dd>
   * </dl>
   *
   * @param filter
   * 	is binded to <code>:filter</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public PagedLiveData<List<Person>> selectByName(final String filter) {
    // common part generation - BEGIN
    // common part generation - END
    final PaginatedResult1 paginatedResult=new PaginatedResult1(filter);
    final KriptonXPagedLiveDataHandlerImpl<List<Person>> builder=new KriptonXPagedLiveDataHandlerImpl<List<Person>>(paginatedResult) {
      @Override
      protected List<Person> compute() {
        return BindAppDataSource.getInstance().executeBatch(new BindAppDataSource.Batch<List<Person>>() {
          @Override
          public List<Person> onExecute(BindAppDaoFactory daoFactory) {
            return paginatedResult.execute(daoFactory);
          }
        });
      }
    };
    registryLiveData(builder);
    return builder.getLiveData();
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (name, surname) VALUES (:bean.name, :bean.surname)</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   */
  @Override
  public void insert(Person bean) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (name, surname) VALUES (?, ?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);
    _contentValues.put("name", bean.name);
    _contentValues.put("surname", bean.surname);

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
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createInsertWithId(result));
    }
    // support for livedata
    registryEvent(result>0?1:0);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, name, surname FROM person WHERE id=:id</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Person}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>:id</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Person selectById(long id) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_BY_ID_SQL1;
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
      // Specialized part - SelectBeanHelper - BEGIN

      Person resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("name");
        int index2=_cursor.getColumnIndex("surname");

        resultBean=new Person();

        resultBean.id=_cursor.getLong(index0);
        if (!_cursor.isNull(index1)) { resultBean.name=_cursor.getString(index1); }
        if (!_cursor.isNull(index2)) { resultBean.surname=_cursor.getString(index2); }

      }
      return resultBean;
    }
    // Specialized part - SelectBeanHelper - END
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person WHERE id=:id</pre>
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>:id</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>:id</strong>
   *
   * @return number of deleted records
   */
  @Override
  public long deleteById(long id) {
    if (deleteByIdPreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="DELETE FROM person WHERE id=?";
      deleteByIdPreparedStatement1 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(deleteByIdPreparedStatement1);
    _contentValues.addWhereArgs(String.valueOf(id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("DELETE FROM person WHERE id=?");

      // log for where parameters -- BEGIN
      int _whereParamCounter=0;
      for (String _whereParamItem: _contentValues.whereArgs()) {
        Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
      }
      // log for where parameters -- END
    }
    // log section END
    int result = KriptonDatabaseWrapper.updateDelete(deleteByIdPreparedStatement1, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createDelete(result));
    }
    // support for livedata
    registryEvent(result);
    return result;
  }

  /**
   * <h2>SQL update</h2>
   * <pre>UPDATE person SET name=:name, surname=:surname WHERE id=${bean.id}</pre>
   *
   * <h2>Updated columns</h2>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>:bean.name</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>:bean.surname</strong></dd>
   * </dl>
   *
   * <h2>Parameters used in where conditions:</h2>
   * <dl>
   * 	<dt>:bean.id</dt><dd>is mapped to method's parameter <strong>bean.id</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is used as <code>:bean</code>
   */
  @Override
  public void update(Person bean) {
    if (updatePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="UPDATE person SET name=?, surname=? WHERE id=?";
      updatePreparedStatement2 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(updatePreparedStatement2);
    _contentValues.put("name", bean.name);
    _contentValues.put("surname", bean.surname);

    _contentValues.addWhereArgs(String.valueOf(bean.id));

    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    // log section BEGIN
    if (_context.isLogEnabled()) {

      // display log
      Logger.info("UPDATE person SET name=:name, surname=:surname WHERE id=?");

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
    int result = KriptonDatabaseWrapper.updateDelete(updatePreparedStatement2, _contentValues);
    if (result>0) {
      // rx management 
      subject.onNext(SQLiteEvent.createUpdate(result));
    }
    // support for livedata
    registryEvent(result);
  }

  protected void registryEvent(int affectedRows) {
    if (affectedRows==0) {
      return;
    }
    if (_context.isInSession()) {
      _context.registrySQLEvent(BindAppDataSource.DAO_PERSON_UID);
    } else {
      invalidateLiveData();
    }
  }

  /**
   * <p>Allows to registry change on this DAO in a transaction, in an batch operation or in a standalone operation.</p>
   *
   */
  public void registryChange() {
    registryEvent(1);
  }

  protected void registryLiveData(LiveDataHandler value) {
    liveDatas.add(new WeakReference<LiveDataHandler>(value));
  }

  /**
   * <p>Invalidate livedata.</p>
   *
   */
  public void invalidateLiveData() {
    for (WeakReference<LiveDataHandler> item: liveDatas) {
      if (item.get()!=null) {
        item.get().invalidate();
      }
    }
  }

  public PublishSubject<SQLiteEvent> getSubject() {
    return subject;
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
    if (deleteByIdPreparedStatement1!=null) {
      deleteByIdPreparedStatement1.close();
      deleteByIdPreparedStatement1=null;
    }
    if (updatePreparedStatement2!=null) {
      updatePreparedStatement2.close();
      updatePreparedStatement2=null;
    }
  }

  public class PaginatedResult0 extends PagedResultImpl<Person> {
    PaginatedResult0() {
      this.pageSize=30;
    }

    public List<Person> execute() {
      // Executor builder - BEGIN
      // paged result is used in live data, so this method must be empty
      return null;
      // Executor builder - END
    }

    public List<Person> execute(BindAppDaoFactory daoFactory) {
      return daoFactory.getDaoPerson().selectAll(this);
    }

    public List<Person> execute(PageRequest pageRequest) {
      // Executor with pageRequet - BEGIN
      return BindAppDataSource.getInstance().executeBatch(daoFactory -> daoFactory.getDaoPerson().selectAllWithPageRequest(pageRequest));
      // Executor with pageRequet - END
    }
  }

  public class PaginatedResult1 extends PagedResultImpl<Person> {
    String filter;

    PaginatedResult1(String filter) {
      this.filter=filter;
      this.pageSize=20;
    }

    public List<Person> execute() {
      // Executor builder - BEGIN
      // paged result is used in live data, so this method must be empty
      return null;
      // Executor builder - END
    }

    public List<Person> execute(BindAppDaoFactory daoFactory) {
      return daoFactory.getDaoPerson().selectByName(filter, this);
    }

    public List<Person> execute(PageRequest pageRequest) {
      // Executor with pageRequet - BEGIN
      return BindAppDataSource.getInstance().executeBatch(daoFactory -> daoFactory.getDaoPerson().selectByNameWithPageRequest(filter, pageRequest));
      // Executor with pageRequet - END
    }
  }
}
