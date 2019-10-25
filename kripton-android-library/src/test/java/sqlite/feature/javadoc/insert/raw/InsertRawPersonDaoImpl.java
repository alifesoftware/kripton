package sqlite.feature.javadoc.insert.raw;

import android.content.ContentValues;
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

/**
 * <p>
 * DAO implementation for entity <code>Person</code>, based on interface <code>InsertRawPersonDao</code>
 * </p>
 *
 *  @see sqlite.feature.javadoc.Person
 *  @see InsertRawPersonDao
 *  @see sqlite.feature.javadoc.PersonTable
 */
public class InsertRawPersonDaoImpl extends Dao implements InsertRawPersonDao {
  private static SupportSQLiteStatement insertOneRawPreparedStatement0;

  private static final Set<String> insertOneRaw0ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname");

  private static SupportSQLiteStatement insertOneRawFieldNamePreparedStatement1;

  private static final Set<String> insertOneRawFieldName1ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name");

  private static SupportSQLiteStatement insertOne2RawFieldNamePreparedStatement2;

  private static final Set<String> insertOne2RawFieldName2ForContentProviderColumnSet = CollectionUtils.asSet(String.class, "person_name", "person_surname");

  public InsertRawPersonDaoImpl(BindInsertRawPersonDaoFactory daoFactory) {
    super(daoFactory.getContext());
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO person (person_name, person_surname) VALUES (:name, :personSurname)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>personSurname</dt><dd>is binded to query's parameter <strong>:personSurname</strong> and method's parameter <strong>personSurname</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column value <strong>person_name</strong>
   * @param personSurname
   * 	is binded to column value <strong>person_surname</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneRaw(String name, String personSurname) {
    // Specialized Insert - InsertType - BEGIN
    if (insertOneRawPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO person (person_name, person_surname) VALUES (?, ?)";
      insertOneRawPreparedStatement0 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOneRawPreparedStatement0);

    _contentValues.put("person_name", name);
    _contentValues.put("person_surname", personSurname);

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
    long result = KriptonDatabaseHelper.insert(insertOneRawPreparedStatement0, _contentValues);
    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO Person (personName, personSurname) VALUES (:personName, :personSurname)</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT INTO person (person_name, person_surname) VALUES (:personName, :personSurname)</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneRaw0ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneRaw0ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
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
    long result = getDatabase().insert("person", 0, _contentValues.values());
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name) VALUES (:name)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong> and method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to column value <strong>person_name</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOneRawFieldName(String name) {
    // Specialized Insert - InsertType - BEGIN
    if (insertOneRawFieldNamePreparedStatement1==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO person (person_name) VALUES (?)";
      insertOneRawFieldNamePreparedStatement1 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOneRawFieldNamePreparedStatement1);

    _contentValues.put("person_name", name);

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
      Logger.info("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertOneRawFieldNamePreparedStatement1, _contentValues);
    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/name</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (personName) VALUES (:personName)</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name) VALUES (:personName)</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/name"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOneRawFieldName1ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOneRawFieldName1ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/name', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
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
    // conflict algorithm REPLACE
    // insert operation
    long result = getDatabase().insert("person", 5, _contentValues.values());
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name, person_surname) VALUES (:name, :surnname)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong> and method's parameter <strong>name</strong></dd>
   * 	<dt>surnname</dt><dd>is binded to query's parameter <strong>:surnname</strong> and method's parameter <strong>surnname</strong></dd>
   * </dl>
   *
   * @param surnname
   * 	is binded to column value <strong>person_surname</strong>
   * @param name
   * 	is binded to column value <strong>person_name</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insertOne2RawFieldName(String surnname, String name) {
    // Specialized Insert - InsertType - BEGIN
    if (insertOne2RawFieldNamePreparedStatement2==null) {
      // generate static SQL for statement
      String _sql="INSERT OR REPLACE INTO person (person_name, person_surname) VALUES (?, ?)";
      insertOne2RawFieldNamePreparedStatement2 = KriptonDatabaseHelper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertOne2RawFieldNamePreparedStatement2);

    _contentValues.put("person_name", name);
    _contentValues.put("person_surname", surnname);

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
      Logger.info("INSERT OR REPLACE INTO person (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

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
    long result = KriptonDatabaseHelper.insert(insertOne2RawFieldNamePreparedStatement2, _contentValues);
    return (int)result;
    // Specialized Insert - InsertType - END
  }

  /**
   * <h1>Content provider URI (INSERT operation):</h1>
   * <pre>content://sqlite.feature.javadoc.bean/persons/surname</pre>
   *
   * <h2>JQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO Person (personName, personSurname) VALUES (${personName}, ${personSurname})</pre>
   *
   * <h2>SQL INSERT for Content Provider</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name, person_surname) VALUES (${personName}, ${personSurname})</pre>
   *
   * <p><strong>Dynamic where statement is ignored, due no param with @BindSqlDynamicWhere was added.</strong></p>
   *
   * <p><strong>In URI, * is replaced with [*] for javadoc rapresentation</strong></p>
   *
   * @param uri "content://sqlite.feature.javadoc.bean/persons/surname"
   * @param contentValues content values
   * @return new row's id
   */
  long insertOne2RawFieldName2ForContentProvider(Uri uri, ContentValues contentValues) {
    Logger.info("Execute INSERT for URI %s", uri.toString());
    KriptonContentValues _contentValues=contentValuesForContentProvider(contentValues);
    for (String columnName:_contentValues.values().keySet()) {
      if (!insertOne2RawFieldName2ForContentProviderColumnSet.contains(columnName)) {
        throw new KriptonRuntimeException(String.format("For URI 'content://sqlite.feature.javadoc.bean/persons/surname', column '%s' does not exists in table '%s' or can not be defined in this INSERT operation", columnName, "person" ));
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
    // conflict algorithm REPLACE
    // insert operation
    long result = getDatabase().insert("person", 0, _contentValues.values());
    return result;
  }

  /**
   * <h2>SQL insert</h2>
   * <pre>INSERT OR REPLACE INTO person (person_name) SELECT person_name FROM person WHERE person_name=:name</pre>
   *
   * <h2>Method parameters used as sql parameters</h2>
   * <dl>
   * 	<dt>name</dt><dd>is binded to query's parameter <strong>:name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is used as parameter
   *
   */
  @Override
  public void insertRawFromSelect(String name) {
    // Specialized Insert - InsertType - BEGIN
    KriptonContentValues _contentValues=contentValuesForUpdate();
    // build where condition
    _contentValues.addWhereArgs((name==null?"":name));
    // log section BEGIN
    if (_context.isLogEnabled()) {
      // log for insert -- BEGIN 

      Logger.info("INSERT OR REPLACE INTO person (person_name) SELECT person_name FROM person WHERE person_name=?");

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

    getDatabase().execSQL("INSERT OR REPLACE INTO person (person_name) SELECT person_name FROM person WHERE person_name=?", _contentValues.whereArgsAsArray());
    // Specialized Insert - InsertType - END
  }

  public static void clearCompiledStatements() {
    try {
      if (insertOneRawPreparedStatement0!=null) {
        insertOneRawPreparedStatement0.close();
        insertOneRawPreparedStatement0=null;
      }
      if (insertOneRawFieldNamePreparedStatement1!=null) {
        insertOneRawFieldNamePreparedStatement1.close();
        insertOneRawFieldNamePreparedStatement1=null;
      }
      if (insertOne2RawFieldNamePreparedStatement2!=null) {
        insertOne2RawFieldNamePreparedStatement2.close();
        insertOne2RawFieldNamePreparedStatement2=null;
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
