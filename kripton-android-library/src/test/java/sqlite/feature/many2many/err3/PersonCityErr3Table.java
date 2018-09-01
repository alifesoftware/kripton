package sqlite.feature.many2many.err3;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>PersonCityErr3</code> is associated to table <code>person_city_err3</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonCityErr3
 */
public class PersonCityErr3Table implements SQLiteTable {
  /**
   * Costant represents typeName of table person_city_err3
   */
  public static final String TABLE_NAME = "person_city_err3";

  /**
   * <p>
   * DDL to create table person_city_err3
   * </p>
   *
   * <pre>CREATE TABLE person_city_err3 (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, city_id INTEGER, person_id INTEGER, FOREIGN KEY(city_id) REFERENCES cities(id), FOREIGN KEY(person_id) REFERENCES persons(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_city_err3 (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, city_id INTEGER, person_id INTEGER, FOREIGN KEY(city_id) REFERENCES cities(id), FOREIGN KEY(person_id) REFERENCES persons(id));";

  /**
   * <p>
   * DDL to drop table person_city_err3
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_city_err3;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_city_err3;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>cityId</code> is associated to table column <code>city_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#cityId
   */
  public static final String COLUMN_CITY_ID = "city_id";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonCityErr3#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_CITY_ID, COLUMN_PERSON_ID};

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
