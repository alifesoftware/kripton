package sqlite.kripton41;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy02DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy02DataSource
 * @see DaoBeanSelectOK
 * @see DaoBeanSelectOKImpl
 * @see Bean01
 */
public interface BindDummy02DaoFactory extends BindDaoFactory {
  /**
   * Retrieve dao DaoBeanSelectOK.
   *
   * @return dao implementation
   */
  DaoBeanSelectOKImpl getDaoBeanSelectOK();
}
