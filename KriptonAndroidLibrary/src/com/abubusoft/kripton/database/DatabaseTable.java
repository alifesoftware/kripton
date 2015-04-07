package com.abubusoft.kripton.database;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.ContentValues;

import com.abubusoft.kripton.android.SQLiteInsert;
import com.abubusoft.kripton.binder.schema.MappingSchema;

/**
 * Database table
 * 
 * @author xcesco
 *
 */
public class DatabaseTable {

	public LinkedHashMap<String, Query> queries = new LinkedHashMap<String, Query>();

	public ArrayList<DatabaseColumn> columns = new ArrayList<DatabaseColumn>();

	public LinkedHashMap<String, DatabaseColumn> field2column = new LinkedHashMap<String, DatabaseColumn>();

	public String name;

	public MappingSchema schema;

	public LinkedHashMap<String, Insert> inserts = new LinkedHashMap<String, Insert>();

	void toContentValue(SQLiteInsert insert, Object bean, ContentValues value) {
		// TODO Auto-generated method stub
		
	}
}
