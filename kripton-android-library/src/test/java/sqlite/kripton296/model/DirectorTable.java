package sqlite.kripton296.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Director</code> is associated to table <code>director</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Director
 */
public class DirectorTable implements SQLiteTable {
  /**
   * Costant represents typeName of table director
   */
  public static final String TABLE_NAME = "director";

  /**
   * <p>
   * DDL to create table director
   * </p>
   *
   * <pre>CREATE TABLE director (did INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, full_name TEXT UNIQUE);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE director (did INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, full_name TEXT UNIQUE);";

  /**
   * <p>
   * DDL to drop table director
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS director;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS director;";

  /**
   * Entity's property <code>did</code> is associated to table column <code>did</code>. This costant represents column name.
   *
   *  @see Director#did
   */
  public static final String COLUMN_DID = "did";

  /**
   * Entity's property <code>fullName</code> is associated to table column <code>full_name</code>. This costant represents column name.
   *
   *  @see Director#fullName
   */
  public static final String COLUMN_FULL_NAME = "full_name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_DID, COLUMN_FULL_NAME};

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
