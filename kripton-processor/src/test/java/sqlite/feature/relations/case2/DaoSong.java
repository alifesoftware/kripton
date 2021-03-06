package sqlite.feature.relations.case2;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

@BindDao(Song.class)
public interface DaoSong {

	@BindSqlSelect
	List<Song> selectAll();
}
