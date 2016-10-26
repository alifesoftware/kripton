package com.abubusoft.whisper.client.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>ChannelEntity</code> is associated to table <code>channel</code>
 * This class represents table associated to entity.
 * </p>
 * <p><strong>This class is generated by Kripton Annotation Processor (1.2.55-SNAPSHOT)</strong></p>
 *
 *  @since Mon Oct 10 18:52:33 CEST 2016
 *  @see com.abubusoft.whisper.client.entities.ChannelEntity
 */
public class ChannelEntityTable {
  /**
   * Costant represents name of table channel
   */
  public static final String TABLE_NAME = "channel";

  /**
   * <p>
   * DDL to create table channel
   * </p>
   *
   * <pre>CREATE TABLE channel (uid TEXT, image_type TEXT, image_size INTEGER, name TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE channel (uid TEXT, image_type TEXT, image_size INTEGER, name TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT);";

  /**
   * <p>
   * DDL to drop table channel
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS channel;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS channel;";

  /**
   * Entity's property <code>uid</code> is associated to table column <code>uid</code>. This costant represents column name.
   *
   *  @see com.abubusoft.whisper.client.entities.ChannelEntity#uid
   */
  public static final String COLUMN_UID = "uid";

  /**
   * Entity's property <code>imageType</code> is associated to table column <code>image_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.whisper.client.entities.ChannelEntity#imageType
   */
  public static final String COLUMN_IMAGE_TYPE = "image_type";

  /**
   * Entity's property <code>imageSize</code> is associated to table column <code>image_size</code>. This costant represents column name.
   *
   *  @see com.abubusoft.whisper.client.entities.ChannelEntity#imageSize
   */
  public static final String COLUMN_IMAGE_SIZE = "image_size";

  /**
   * Entity's property <code>name</code> is associated to table column <code>name</code>. This costant represents column name.
   *
   *  @see com.abubusoft.whisper.client.entities.ChannelEntity#name
   */
  public static final String COLUMN_NAME = "name";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.whisper.client.entities.ChannelEntity#id
   */
  public static final String COLUMN_ID = "id";
}
