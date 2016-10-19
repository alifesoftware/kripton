package com.abubusoft.kripton.processor.kripton38;

import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.DaoBase;
import com.abubusoft.kripton.common.StringUtil;

/**
 * <p>
 * DAO implementation for entity <code>Bean03</code>, based on interface <code>DaoBean03</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton38.Bean03
 *  @see com.abubusoft.kripton.processor.kripton38.DaoBean03
 *  @see com.abubusoft.kripton.processor.kripton38.Bean03Table
 */
public class BindDaoBean03 extends DaoBase implements DaoBean03 {
  public BindDaoBean03(BindDummy03DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, text FROM bean03 WHERE id=${id}</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[id]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, text]</pre>
   *
   * @param id
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public Bean03 selectOne(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, text FROM bean03 WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, text FROM bean03 WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    Bean03 resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("text");

      resultBean=new Bean03();

      if (!cursor.isNull(index0)) { resultBean.setId(cursor.getLong(index0)); }
      if (!cursor.isNull(index1)) { resultBean.setText(cursor.getString(index1)); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE bean03 WHERE id=${id}</pre>
   *
   * @param id
   * 	used in where condition
   *
   * @return number of deleted records
   */
  @Override
  public long deleteOne(long id) {
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE bean03 WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("bean03", "id=?", whereConditions);
    return result;
  }
}
