package sqlite.git21;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.abubusoft.kripton.android.sqlite.Dao;
import com.abubusoft.kripton.android.sqlite.KriptonContentValues;
import com.abubusoft.kripton.android.sqlite.KriptonDatabaseWrapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * DAO implementation for entity <code>Document</code>, based on interface <code>DaoDocument</code>
 * </p>
 *
 *  @see Document
 *  @see DaoDocument
 *  @see DocumentTable
 */
public class DaoDocumentImpl extends Dao implements DaoDocument {
  /**
   * SQL definition for method findByFileName
   */
  private static final String FIND_BY_FILE_NAME_SQL1 = "SELECT file_name FROM document WHERE file_name like ?";

  private static SQLiteStatement insertPreparedStatement0;

  /**
   * SQL definition for method selectAll
   */
  private static final String SELECT_ALL_SQL2 = "SELECT id, file_name FROM document";

  public DaoDocumentImpl(BindDocumentDaoFactory daoFactory) {
    super(daoFactory.context());
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT file_name FROM document WHERE file_name like :input</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Document}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>file_name</dt><dd>is associated to bean's property <strong>fileName</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>:input</dt><dd>is binded to method's parameter <strong>input</strong></dd>
   * </dl>
   *
   * @param input
   * 	is binded to <code>:input</code>
   * @return collection of single value extracted by query.
   */
  @Override
  public Set<String> findByFileName(String input) {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=FIND_BY_FILE_NAME_SQL1;
    // add where arguments
    _contentValues.addWhereArgs((input==null?"":input));
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
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
   * <h2>SQL insert</h2>
   * <pre>INSERT INTO document (file_name) VALUES (:fileName)</pre>
   *
   * <h2>Inserted columns:</strong></h2>
   * <dl>
   * 	<dt>fileName</dt><dd>is binded to query's parameter <strong>:fileName</strong> and method's parameter <strong>fileName</strong></dd>
   * </dl>
   *
   * @param fileName
   * 	is binded to column value <strong>file_name</strong>
   *
   */
  @Override
  public void insert(String fileName) {
    // Specialized Insert - InsertType - BEGIN
    if (insertPreparedStatement0==null) {
      // generate static SQL for statement
      String _sql="INSERT INTO document (file_name) VALUES (?)";
      insertPreparedStatement0 = KriptonDatabaseWrapper.compile(_context, _sql);
    }
    KriptonContentValues _contentValues=contentValuesForUpdate(insertPreparedStatement0);

    _contentValues.put(fileName);

    // insert operation
    long result = KriptonDatabaseWrapper.insert(insertPreparedStatement0, _contentValues);
    // Specialized Insert - InsertType - END
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, file_name FROM document</pre>
   *
   * <h2>Mapped class:</h2>
   * {@link Document}
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>file_name</dt><dd>is associated to bean's property <strong>fileName</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Document> selectAll() {
    // common part generation - BEGIN
    KriptonContentValues _contentValues=contentValues();
    // query SQL is statically defined
    String _sql=SELECT_ALL_SQL2;
    // add where arguments
    String[] _sqlArgs=_contentValues.whereArgsAsArray();
    try (Cursor _cursor = database().rawQuery(_sql, _sqlArgs)) {
      // common part generation - END
      // Specialized part - SelectBeanListHelper - BEGIN

      ArrayList<Document> resultList=new ArrayList<Document>(_cursor.getCount());
      Document resultBean=null;

      if (_cursor.moveToFirst()) {

        int index0=_cursor.getColumnIndex("id");
        int index1=_cursor.getColumnIndex("file_name");

        do
         {
          resultBean=new Document();

          resultBean.id=_cursor.getLong(index0);
          if (!_cursor.isNull(index1)) { resultBean.fileName=_cursor.getString(index1); }

          resultList.add(resultBean);
        } while (_cursor.moveToNext());
      }

      return resultList;
    }
    // Specialized part - SelectBeanListHelper - END
  }

  public static void clearCompiledStatements() {
    if (insertPreparedStatement0!=null) {
      insertPreparedStatement0.close();
      insertPreparedStatement0=null;
    }
  }
}
