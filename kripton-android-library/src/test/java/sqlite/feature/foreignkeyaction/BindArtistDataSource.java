package sqlite.feature.foreignkeyaction;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import java.util.List;

/**
 * <p>
 * Represents implementation of datasource ArtistDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see ArtistDataSource
 * @see BindArtistDaoFactory
 * @see ArtistDao
 * @see ArtistDaoImpl
 * @see Artist
 * @see AlbumDao
 * @see AlbumDaoImpl
 * @see Album
 * @see TrackDao
 * @see TrackDaoImpl
 * @see Track
 */
public class BindArtistDataSource extends AbstractDataSource implements BindArtistDaoFactory, ArtistDataSource {
  /**
   * <p>datasource singleton</p>
   */
  static BindArtistDataSource instance;

  /**
   * Unique identifier for Dao ArtistDao
   */
  public static final int ARTIST_DAO_UID = 0;

  /**
   * Unique identifier for Dao AlbumDao
   */
  public static final int ALBUM_DAO_UID = 1;

  /**
   * Unique identifier for Dao TrackDao
   */
  public static final int TRACK_DAO_UID = 2;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new TrackTable(), new ArtistTable(), new AlbumTable()};

  /**
   * <p>dao instance</p>
   */
  protected ArtistDaoImpl artistDao = new ArtistDaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected AlbumDaoImpl albumDao = new AlbumDaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected TrackDaoImpl trackDao = new TrackDaoImpl(context);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindArtistDataSource(DataSourceOptions options) {
    super("artist.db", 1, options);
  }

  @Override
  public ArtistDaoImpl getArtistDao() {
    return artistDao;
  }

  @Override
  public AlbumDaoImpl getAlbumDao() {
    return albumDao;
  }

  @Override
  public TrackDaoImpl getTrackDao() {
    return trackDao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    execute(transaction, onErrorListener);
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   * @param onErrorListener
   * 	error listener
   */
  public void execute(Transaction transaction, AbstractDataSource.OnErrorListener onErrorListener) {
    boolean needToOpened=!this.isOpenInWriteMode();
    boolean success=false;
    @SuppressWarnings("resource")
    SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
    DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
    currentDaoFactory.onSessionOpened();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(currentDaoFactory)) {
        connection.setTransactionSuccessful();
        success=true;
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (onErrorListener!=null) onErrorListener.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      if (needToOpened) { close(); }
      if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
    }
  }

  /**
   * <p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>
   *
   * @param commands
   * 	batch to execute
   */
  public <T> T executeBatch(Batch<T> commands) {
    return executeBatch(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param commands
   * 	batch to execute
   * @param writeMode
   * 	true to open connection in write mode, false to open connection in read only mode
   */
  public <T> T executeBatch(Batch<T> commands, boolean writeMode) {
    boolean needToOpened=writeMode?!this.isOpenInWriteMode(): !this.isOpen();
    if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
    DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
    currentDaoFactory.onSessionOpened();
    try {
      if (commands!=null) {
        return commands.onExecute(currentDaoFactory);
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      throw(e);
    } finally {
      if (needToOpened) { close(); }
      currentDaoFactory.onSessionClosed();
    }
    return null;
  }

  /**
   * instance
   */
  public static synchronized BindArtistDataSource instance() {
    if (instance==null) {
      DataSourceOptions options=DataSourceOptions.builder()
      	.build();
      instance=new BindArtistDataSource(options);
    }
    return instance;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindArtistDataSource open() {
    BindArtistDataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindArtistDataSource openReadOnly() {
    BindArtistDataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    // log section BEGIN
    if (this.logEnabled) {
      if (options.inMemory) {
        Logger.info("Create database in memory");
      } else {
        Logger.info("Create database '%s' version %s",this.name, this.version);
      }
    }
    // log section END
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",ArtistTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(ArtistTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",AlbumTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(AlbumTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",TrackTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(TrackTable.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
    justCreated=true;
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    }
    // log section END
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("Begin update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        task.execute(database);
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("End update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        previousVersion++;
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",ArtistTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(ArtistTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",AlbumTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(AlbumTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",TrackTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(TrackTable.CREATE_TABLE_SQL);
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
    database.setForeignKeyConstraintsEnabled(true);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  public void clearCompiledStatements() {
    ArtistDaoImpl.clearCompiledStatements();
    AlbumDaoImpl.clearCompiledStatements();
    TrackDaoImpl.clearCompiledStatements();
  }

  /**
   * Build instance.
   * @return dataSource instance.
   */
  public static synchronized BindArtistDataSource build(DataSourceOptions options) {
    if (instance==null) {
      instance=new BindArtistDataSource(options);
    }
    return instance;
  }

  /**
   * Build instance with default config.
   */
  public static synchronized BindArtistDataSource build() {
    return build(DataSourceOptions.builder().build());
  }

  /**
   * List of tables compose datasource:
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindArtistDaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindArtistDaoFactory daoFactory);
  }

  /**
   * Rapresents batch operation.
   */
  public interface Batch<T> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindArtistDaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindArtistDaoFactory {
    private SQLContextInSessionImpl _context;

    protected ArtistDaoImpl _artistDao;

    protected AlbumDaoImpl _albumDao;

    protected TrackDaoImpl _trackDao;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindArtistDataSource.this);
    }

    /**
     *
     * retrieve dao ArtistDao
     */
    public ArtistDaoImpl getArtistDao() {
      if (_artistDao==null) {
        _artistDao=new ArtistDaoImpl(_context);
      }
      return _artistDao;
    }

    /**
     *
     * retrieve dao AlbumDao
     */
    public AlbumDaoImpl getAlbumDao() {
      if (_albumDao==null) {
        _albumDao=new AlbumDaoImpl(_context);
      }
      return _albumDao;
    }

    /**
     *
     * retrieve dao TrackDao
     */
    public TrackDaoImpl getTrackDao() {
      if (_trackDao==null) {
        _trackDao=new TrackDaoImpl(_context);
      }
      return _trackDao;
    }

    protected void onSessionOpened() {
    }

    protected void onSessionClear() {
    }

    protected void onSessionClosed() {
    }

    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}
