package sqlite.git19.case2;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(log=true, daoSet = { DaoDocument.class }, fileName = "document.db")
public interface DocumentDataSource {

}
