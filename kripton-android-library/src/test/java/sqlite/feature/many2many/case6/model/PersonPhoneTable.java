package sqlite.feature.many2many.case6.model;

import com.abubusoft.kripton.android.sqlite.SQLiteTable;

/**
 * <p>
 * Entity <code>PersonPhone</code> is associated to table <code>person_phone</code>
 * This class represents table associated to entity.
 * </p>
 *  @see PersonPhone
 */
public class PersonPhoneTable implements SQLiteTable {
  /**
   * Costant represents typeName of table person_phone
   */
  public static final String TABLE_NAME = "person_phone";

  /**
   * <p>
   * DDL to create table person_phone
   * </p>
   *
   * <pre>CREATE TABLE person_phone (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, buy_date TEXT, person_id INTEGER, phone_number_id INTEGER, FOREIGN KEY(person_id) REFERENCES person(id), FOREIGN KEY(phone_number_id) REFERENCES phone_number(id));</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE person_phone (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, buy_date TEXT, person_id INTEGER, phone_number_id INTEGER, FOREIGN KEY(person_id) REFERENCES person(id), FOREIGN KEY(phone_number_id) REFERENCES phone_number(id));";

  /**
   * <p>
   * DDL to drop table person_phone
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS person_phone;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS person_phone;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see PersonPhone#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>buyDate</code> is associated to table column <code>buy_date</code>. This costant represents column name.
   *
   *  @see PersonPhone#buyDate
   */
  public static final String COLUMN_BUY_DATE = "buy_date";

  /**
   * Entity's property <code>personId</code> is associated to table column <code>person_id</code>. This costant represents column name.
   *
   *  @see PersonPhone#personId
   */
  public static final String COLUMN_PERSON_ID = "person_id";

  /**
   * Entity's property <code>phoneNumberId</code> is associated to table column <code>phone_number_id</code>. This costant represents column name.
   *
   *  @see PersonPhone#phoneNumberId
   */
  public static final String COLUMN_PHONE_NUMBER_ID = "phone_number_id";

  /**
   * Columns array
   */
  private static final String[] COLUMNS = {COLUMN_ID, COLUMN_BUY_DATE, COLUMN_PERSON_ID, COLUMN_PHONE_NUMBER_ID};

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
