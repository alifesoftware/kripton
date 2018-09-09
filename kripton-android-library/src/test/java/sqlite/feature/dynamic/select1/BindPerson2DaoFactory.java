package sqlite.feature.dynamic.select1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Person2DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Person2DataSource
 * @see PersonDAO2
 * @see PersonDAO2Impl
 * @see Person
 */
public interface BindPerson2DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao PersonDAO2.
   *
   * @return dao implementation
   */
  PersonDAO2Impl getPersonDAO2();
}
