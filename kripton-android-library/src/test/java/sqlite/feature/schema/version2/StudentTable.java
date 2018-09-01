package sqlite.feature.schema.version2;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Student</code> is associated to table <code>student</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Student
 */
public class StudentTable implements SQLiteTable {
  /**
   * Costant represents typeName of table student
   */
  public static final String TABLE_NAME = "student";

  /**
   * <p>
   * DDL to create table student
   * </p>
   *
   * <pre>CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE student (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, location TEXT, name TEXT);";

  /**
   * <p>
   * DDL to drop table student
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS student;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS student;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Student#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>location</code> is associated to table column <code>location</code>. This costant represents column name.
   *
   *  @see Student#location
   */
  public static final String COLUMN_LOCATION = "location";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Student#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_LOCATION, COLUMN_NAME};

  /**
   * Columns array
   */
  @Override
  public String[] columns() {
    return COLUMNS;
  }

  /**
   * table name
   */
  @Override
  public String name() {
    return TABLE_NAME;
  }
}
