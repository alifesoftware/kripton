package sqlite.feature.custombean.err2;

import com.abubusoft.kripton.android.annotation.BindContentProvider;
import com.abubusoft.kripton.android.annotation.BindDataSource;
import com.abubusoft.kripton.android.annotation.BindDataSourceOptions;
import com.abubusoft.kripton.android.annotation.BindSqlAdapter;
import com.abubusoft.kripton.android.sqlite.adapters.Date2LongTypeAdapter;

@BindContentProvider(authority = "sqlite.feature.custombean.case1")
@BindDataSourceOptions(inMemory = true)
@BindDataSource(fileName = "app.db", version = 1, daoSet = { BookDao.class, LoanDao.class, UserDao.class }, typeAdapters = {
		@BindSqlAdapter(adapter = Date2LongTypeAdapter.class) }, asyncTask = true)
public interface AppDataSource {

}
