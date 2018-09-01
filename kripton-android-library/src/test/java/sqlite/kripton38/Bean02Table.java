package sqlite.kripton38;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Bean02</code> is associated to table <code>bean02</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean02
 */
public class Bean02Table implements SQLiteTable {
  /**
   * Costant represents typeName of table bean02
   */
  public static final String TABLE_NAME = "bean02";

  /**
   * <p>
   * DDL to create table bean02
   * </p>
   *
   * <pre>CREATE TABLE bean02 (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, text TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean02 (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, text TEXT);";

  /**
   * <p>
   * DDL to drop table bean02
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean02;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean02;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Bean02#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see Bean02#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_TEXT};

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
