package sqlite.feature.childselect.error3;

import com.abubusoft.kripton.android.annotation.BindRelation;
import com.abubusoft.kripton.android.annotation.BindTable;

@BindTable
public class Album {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String name;

	@BindRelation(foreignKey = "albumId")
	protected Song songs;

	public Song getSongs() {
		return songs;
	}

	public void setSongs(Song songs) {
		this.songs = songs;
	}
}