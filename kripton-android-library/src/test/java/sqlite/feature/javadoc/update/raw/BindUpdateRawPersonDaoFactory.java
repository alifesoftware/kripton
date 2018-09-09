package sqlite.feature.javadoc.update.raw;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for UpdateRawPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see UpdateRawPersonDataSource
 * @see UpdateRawPersonDao
 * @see UpdateRawPersonDaoImpl
 * @see Person
 */
public interface BindUpdateRawPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao UpdateRawPersonDao.
   *
   * @return dao implementation
   */
  UpdateRawPersonDaoImpl getUpdateRawPersonDao();
}
