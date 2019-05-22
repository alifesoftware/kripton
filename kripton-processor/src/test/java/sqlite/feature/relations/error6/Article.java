package sqlite.feature.relations.error6;

import java.net.URL;

import com.abubusoft.kripton.annotation.BindType;

@BindType("item")
public class Article extends Entity {
	public String title;
	public String description;
	public URL link;
	public String author;
	public String guid;
	public URL comments;

	public long channelId;
}
