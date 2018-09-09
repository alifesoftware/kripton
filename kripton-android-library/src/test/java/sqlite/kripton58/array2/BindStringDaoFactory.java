package sqlite.kripton58.array2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for StringDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see StringDataSource
 * @see StringDao
 * @see StringDaoImpl
 * @see StringBean
 */
public interface BindStringDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao StringDao.
   *
   * @return dao implementation
   */
  StringDaoImpl getStringDao();
}
