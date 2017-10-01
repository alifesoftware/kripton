package sqlite.feature.many2many.entity;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.lang.Override;
import java.lang.Throwable;
import java.util.List;
import sqlite.feature.many2many.CityTable;
import sqlite.feature.many2many.PersonTable;

/**
 * <p>
 * Represents implementation of datasource PersonCirtyOk1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyOk1DataSource
 * @see BindPersonCirtyOk1DaoFactory
 * @see PersonOk1Dao
 * @see PersonOk1DaoImpl
 * @see sqlite.feature.many2many.Person
 * @see CityOk1Dao
 * @see CityOk1DaoImpl
 * @see sqlite.feature.many2many.City
 * @see PersonCityOk1Dao
 * @see PersonCityOk1DaoImpl
 * @see PersonCityOk1
 */
public class BindPersonCirtyOk1DataSource extends AbstractDataSource implements BindPersonCirtyOk1DaoFactory, PersonCirtyOk1DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindPersonCirtyOk1DataSource instance;

  /**
   * <p>dao instance</p>
   */
  protected PersonOk1DaoImpl personOk1Dao = new PersonOk1DaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected CityOk1DaoImpl cityOk1Dao = new CityOk1DaoImpl(this);

  /**
   * <p>dao instance</p>
   */
  protected PersonCityOk1DaoImpl personCityOk1Dao = new PersonCityOk1DaoImpl(this);

  protected BindPersonCirtyOk1DataSource(DataSourceOptions options) {
    super("person.db", 1, options);
  }

  @Override
  public PersonOk1DaoImpl getPersonOk1Dao() {
    return personOk1Dao;
  }

  @Override
  public CityOk1DaoImpl getCityOk1Dao() {
    return cityOk1Dao;
  }

  @Override
  public PersonCityOk1DaoImpl getPersonCityOk1Dao() {
    return personCityOk1Dao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. Thedrawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    SQLiteDatabase connection=openWritableDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (transaction!=null) transaction.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindPersonCirtyOk1DataSource instance() {
    if (instance==null) {
      instance=new BindPersonCirtyOk1DataSource(null);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindPersonCirtyOk1DataSource open() {
    BindPersonCirtyOk1DataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindPersonCirtyOk1DataSource openReadOnly() {
    BindPersonCirtyOk1DataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("Create database '%s' version %s",this.name, this.getVersion());
    Logger.info("DDL: %s",PersonCityOk1Table.CREATE_TABLE_SQL);
    database.execSQL(PersonCityOk1Table.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",CityTable.CREATE_TABLE_SQL);
    database.execSQL(CityTable.CREATE_TABLE_SQL);
    Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
    // if we have a populate task (previous and current are same), try to execute it
    if (options.updateTasks != null) {
      SQLiteUpdateTask task = findPopulateTaskList(database.getVersion());
      if (task != null) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        Logger.info("Begin update database from version %s to %s", task.previousVersion, task.currentVersion);
        task.execute(database);
        Logger.info("End update database from version %s to %s", task.previousVersion, task.currentVersion);
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      Logger.info("DDL: %s",PersonCityOk1Table.CREATE_TABLE_SQL);
      database.execSQL(PersonCityOk1Table.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",CityTable.CREATE_TABLE_SQL);
      database.execSQL(CityTable.CREATE_TABLE_SQL);
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindPersonCirtyOk1DataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindPersonCirtyOk1DataSource(options);
    }
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindPersonCirtyOk1DaoFactory> {
  }

  /**
   * Simple class implements interface to define transactions
   */
  public abstract static class SimpleTransaction implements Transaction {
    @Override
    public void onError(Throwable e) {
      throw(new KriptonRuntimeException(e));
    }
  }
}