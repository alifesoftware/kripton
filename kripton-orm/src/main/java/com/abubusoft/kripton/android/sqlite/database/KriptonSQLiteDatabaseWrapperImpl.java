/*******************************************************************************
 * Copyright 2016-2019 Francesco Benincasa (info@abubusoft.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.abubusoft.kripton.android.sqlite.database;

import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;

/**
 * The Class KriptonDatabaseWrapper.
 */
public class KriptonSQLiteDatabaseWrapperImpl implements SupportSQLiteDatabase {

	private SQLiteDatabase safeDb;

	public KriptonSQLiteDatabaseWrapperImpl(SQLiteDatabase database) {
		safeDb = database;
	}
	
	public void update(SQLiteDatabase database) {
		this.safeDb=database;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SupportSQLiteStatement compileStatement(String sql) {
		return (new KriptonSQLiteStatement(safeDb.compileStatement(sql)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beginTransaction() {
		safeDb.beginTransaction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beginTransactionNonExclusive() {
		safeDb.beginTransactionNonExclusive();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beginTransactionWithListener(SQLiteTransactionListener listener) {
		safeDb.beginTransactionWithListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener listener) {
		safeDb.beginTransactionWithListenerNonExclusive(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endTransaction() {
		safeDb.endTransaction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTransactionSuccessful() {
		safeDb.setTransactionSuccessful();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean inTransaction() {
		if (safeDb.isOpen()) {
			return (safeDb.inTransaction());
		}

		throw new IllegalStateException("You should not be doing this on a closed database");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDbLockedByCurrentThread() {
		if (safeDb.isOpen()) {
			return (safeDb.isDbLockedByCurrentThread());
		}

		throw new IllegalStateException("You should not be doing this on a closed database");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean yieldIfContendedSafely() {
		if (safeDb.isOpen()) {
			return (safeDb.yieldIfContendedSafely());
		}

		throw new IllegalStateException("You should not be doing this on a closed database");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean yieldIfContendedSafely(long sleepAfterYieldDelay) {
		if (safeDb.isOpen()) {
			return (safeDb.yieldIfContendedSafely(sleepAfterYieldDelay));
		}

		throw new IllegalStateException("You should not be doing this on a closed database");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getVersion() {
		return (safeDb.getVersion());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setVersion(int version) {
		safeDb.setVersion(version);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getMaximumSize() {
		return (safeDb.getMaximumSize());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long setMaximumSize(long numBytes) {
		return (safeDb.setMaximumSize(numBytes));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long getPageSize() {
		return (safeDb.getPageSize());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPageSize(long numBytes) {
		safeDb.setPageSize(numBytes);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cursor query(String sql) {
		return (query(new SimpleSQLiteQuery(sql)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cursor query(String sql, Object[] selectionArgs) {
		return (query(new SimpleSQLiteQuery(sql, selectionArgs)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cursor query(final SupportSQLiteQuery supportQuery) {
		return (query(supportQuery, null));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Cursor query(final SupportSQLiteQuery supportQuery, CancellationSignal signal) {
		BindingsRecorder hack = new BindingsRecorder();

		supportQuery.bindTo(hack);

		return (safeDb.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() {
			@Override
			public Cursor newCursor(SQLiteDatabase db,
					SQLiteCursorDriver masterQuery, String editTable, SQLiteQuery query) {
				supportQuery.bindTo(new KriptonSQLiteProgram(query));
				return new SQLiteCursor(masterQuery, editTable, query);
			}
		}, supportQuery.getSql(), hack.getBindings(), null));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long insert(String table, int conflictAlgorithm, ContentValues values) {
		return (safeDb.insertWithOnConflict(table, null, values, conflictAlgorithm));
	}

	/**
	 * {@inheritDoc}
	 */	
	@Override
	public int delete(String table, String whereClause, Object[] whereArgs) {
		String[] safeArgs = mapToStrings(whereArgs);
		return safeDb.delete(table, whereClause, safeArgs);
	}

	/**
	 * @param whereArgs
	 * @return
	 */
	public String[] mapToStrings(Object[] whereArgs) {
		String safeArgs[]=new String[whereArgs.length];
		for (int i=0; i<safeArgs.length;i ++) {
			safeArgs[i]=(String)whereArgs[i];
		}
		return safeArgs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(String table, int conflictAlgorithm, ContentValues values, String whereClause,
			Object[] whereArgs) {		
		String[] safeArgs=mapToStrings(whereArgs);
		return safeDb.update(table, values, whereClause, safeArgs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execSQL(String sql) throws SQLException {
		safeDb.execSQL(sql);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execSQL(String sql, Object[] bindArgs) throws SQLException {
		safeDb.execSQL(sql, bindArgs);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReadOnly() {
		return (safeDb.isReadOnly());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOpen() {
		return (safeDb.isOpen());
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public boolean needUpgrade(int newVersion) {
		return (safeDb.needUpgrade(newVersion));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPath() {
		return (safeDb.getPath());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLocale(Locale locale) {
		safeDb.setLocale(locale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMaxSqlCacheSize(int cacheSize) {
		safeDb.setMaxSqlCacheSize(cacheSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setForeignKeyConstraintsEnabled(boolean enable) {
		safeDb.setForeignKeyConstraintsEnabled(enable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean enableWriteAheadLogging() {
		return safeDb.enableWriteAheadLogging();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void disableWriteAheadLogging() {
		safeDb.disableWriteAheadLogging();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWriteAheadLoggingEnabled() {
		return safeDb.isWriteAheadLoggingEnabled();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Pair<String, String>> getAttachedDbs() {
		return (safeDb.getAttachedDbs());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDatabaseIntegrityOk() {
		return (safeDb.isDatabaseIntegrityOk());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		safeDb.close();
	}

}
