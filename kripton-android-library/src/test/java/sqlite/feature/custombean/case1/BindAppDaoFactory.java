package sqlite.feature.custombean.case1;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for AppDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see AppDataSource
 * @see BookDao
 * @see BookDaoImpl
 * @see Book
 * @see LoanDao
 * @see LoanDaoImpl
 * @see Loan
 * @see UserDao
 * @see UserDaoImpl
 * @see User
 */
public interface BindAppDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao BookDao
   */
  BookDaoImpl getBookDao();

  /**
   *
   * retrieve dao LoanDao
   */
  LoanDaoImpl getLoanDao();

  /**
   *
   * retrieve dao UserDao
   */
  UserDaoImpl getUserDao();
}
