package sqlite.test05firt_aid;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for FirstAidDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see FirstAidDataSource
 * @see FirstAidDao
 * @see FirstAidDaoImpl
 * @see FirstAid
 */
public interface BindFirstAidDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao FirstAidDao.
   *
   * @return dao implementation
   */
  FirstAidDaoImpl getFirstAidDao();
}
