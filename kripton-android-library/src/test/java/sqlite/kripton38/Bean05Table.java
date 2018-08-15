package sqlite.kripton38;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Bean05</code> is associated to table <code>ws_bean</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Bean05
 */
public class Bean05Table implements SQLiteTable {
  /**
   * Costant represents typeName of table ws_bean
   */
  public static final String TABLE_NAME = "ws_bean";

  /**
   * <p>
   * DDL to create table ws_bean
   * </p>
   *
   * <pre>CREATE TABLE ws_bean (pk INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, bean_type TEXT, content BLOB, creation_time TEXT, number INTEGER, text TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE ws_bean (pk INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, bean_type TEXT, content BLOB, creation_time TEXT, number INTEGER, text TEXT);";

  /**
   * <p>
   * DDL to drop table ws_bean
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS ws_bean;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS ws_bean;";

  /**
   * Entity's property <code>pk</code> is associated to table column <code>pk</code>. This costant represents column name.
   *
   *  @see Bean05#pk
   */
  public static final String COLUMN_PK = "pk";

  /**
   * Entity's property <code>beanType</code> is associated to table column <code>bean_type</code>. This costant represents column name.
   *
   *  @see Bean05#beanType
   */
  public static final String COLUMN_BEAN_TYPE = "bean_type";

  /**
   * Entity's property <code>content</code> is associated to table column <code>content</code>. This costant represents column name.
   *
   *  @see Bean05#content
   */
  public static final String COLUMN_CONTENT = "content";

  /**
   * Entity's property <code>creationTime</code> is associated to table column <code>creation_time</code>. This costant represents column name.
   *
   *  @see Bean05#creationTime
   */
  public static final String COLUMN_CREATION_TIME = "creation_time";

  /**
   * Entity's property <code>number</code> is associated to table column <code>number</code>. This costant represents column name.
   *
   *  @see Bean05#number
   */
  public static final String COLUMN_NUMBER = "number";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see Bean05#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_PK, COLUMN_BEAN_TYPE, COLUMN_CONTENT, COLUMN_CREATION_TIME, COLUMN_NUMBER, COLUMN_TEXT};

  /**
   * for attribute content serialization
   */
  public static byte[] serializeContent(byte[] value) {
    return value;
  }

  /**
   * for attribute content parsing
   */
  public static byte[] parseContent(byte[] input) {
    return input;
  }

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
