package sqlite.feature.pkstring.relations.err2;

import com.abubusoft.kripton.android.annotation.BindSqlInsert;

public interface DaoBase<E> {

	@BindSqlInsert
	void insert(E bean);
}
