package sqlite.feature.jql.kripton163;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>CollegeStudent</code> is associated to table <code>students</code>
 * This class represents table associated to entity.
 * </p>
 *  @see CollegeStudent
 */
public class CollegeStudentTable implements SQLiteTable {
  /**
   * Costant represents typeName of table students
   */
  public static final String TABLE_NAME = "students";

  /**
   * <p>
   * DDL to create table students
   * </p>
   *
   * <pre>CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, first_name TEXT, surname TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, first_name TEXT, surname TEXT);";

  /**
   * <p>
   * DDL to drop table students
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS students;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS students;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see CollegeStudent#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>firstName</code> is associated to table column <code>first_name</code>. This costant represents column name.
   *
   *  @see CollegeStudent#firstName
   */
  public static final String COLUMN_FIRST_NAME = "first_name";

  /**
   * Entity's property <code>surname</code> is associated to table column <code>surname</code>. This costant represents column name.
   *
   *  @see CollegeStudent#surname
   */
  public static final String COLUMN_SURNAME = "surname";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_FIRST_NAME, COLUMN_SURNAME};

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
