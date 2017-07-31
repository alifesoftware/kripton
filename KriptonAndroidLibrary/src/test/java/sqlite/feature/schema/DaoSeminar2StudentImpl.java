package sqlite.feature.schema;

import android.content.ContentValues;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;

/**
 * <p>
 * DAO implementation for entity <code>Seminar2Student</code>, based on interface <code>DaoSeminar2Student</code>
 * </p>
 *
 *  @see Seminar2Student
 *  @see DaoSeminar2Student
 *  @see Seminar2StudentTable
 */
public class DaoSeminar2StudentImpl extends AbstractDao implements DaoSeminar2Student {
  public DaoSeminar2StudentImpl(BindSchoolDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO seminar_2_student (student_id, seminar_id) VALUES (${bean.studentId}, ${bean.seminarId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>student_id</dt><dd>is mapped to <strong>${bean.studentId}</strong></dd>
   * 	<dt>seminar_id</dt><dd>is mapped to <strong>${bean.seminarId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(Seminar2Student bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("student_id", bean.studentId);
    contentValues.put("seminar_id", bean.seminarId);

    //StringUtils and SqlUtils will be used to format SQL
    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO seminar_2_student (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("seminar_2_student", null, contentValues);
    bean.id=result;

    return result;
  }
}