package com.abubusoft.kripton.processor.test04primary_key;

import java.lang.String;

/**
 * Generated by Kripton Library.
 *
 *  @since Tue Jun 07 22:47:03 CEST 2016
 *
 */
public class Bean05Table {
  public static final String TABLE_NAME = "bean05";

  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean05( pk INTEGER PRIMARY KEY AUTOINCREMENT, number INTEGER, bean_type TEXT, text TEXT, content BLOB, creation_time TEXT);";

  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean05;";

  public static final String COLUMN_PK = "pk";

  public static final String COLUMN_NUMBER = "number";

  public static final String COLUMN_BEAN_TYPE = "bean_type";

  public static final String COLUMN_TEXT = "text";

  public static final String COLUMN_CONTENT = "content";

  public static final String COLUMN_CREATION_TIME = "creation_time";
}