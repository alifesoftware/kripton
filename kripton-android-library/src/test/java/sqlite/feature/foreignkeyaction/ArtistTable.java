package sqlite.feature.foreignkeyaction;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>Artist</code> is associated to table <code>artist</code>
 * This class represents table associated to entity.
 * </p>
 *  @see Artist
 */
public class ArtistTable implements SQLiteTable {
  /**
   * Costant represents typeName of table artist
   */
  public static final String TABLE_NAME = "artist";

  /**
   * <p>
   * DDL to create table artist
   * </p>
   *
   * <pre>CREATE TABLE artist (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE artist (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);";

  /**
   * <p>
   * DDL to drop table artist
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS artist;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS artist;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see Artist#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see Artist#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME};

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
