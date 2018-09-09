package sqlite.feature.pkstring.case2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see DaoAlbum
 * @see DaoAlbumImpl
 * @see Album
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoAlbum.
   *
   * @return dao implementation
   */
  DaoAlbumImpl getDaoAlbum();
}
