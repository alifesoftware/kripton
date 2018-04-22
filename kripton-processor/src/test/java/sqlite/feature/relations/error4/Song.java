package sqlite.feature.relations.error4;

import com.abubusoft.kripton.android.annotation.BindColumn;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Song {
	public long id;
	public String name;
	
	@BindColumn(parentEntity=Album.class)
	public long albumId;
}
