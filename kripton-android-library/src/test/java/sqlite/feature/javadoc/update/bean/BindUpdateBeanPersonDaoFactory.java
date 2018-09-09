package sqlite.feature.javadoc.update.bean;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for UpdateBeanPersonDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see UpdateBeanPersonDataSource
 * @see UpdateBeanPersonDao
 * @see UpdateBeanPersonDaoImpl
 * @see Person
 */
public interface BindUpdateBeanPersonDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao UpdateBeanPersonDao.
   *
   * @return dao implementation
   */
  UpdateBeanPersonDaoImpl getUpdateBeanPersonDao();
}
