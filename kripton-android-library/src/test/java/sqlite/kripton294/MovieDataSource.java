package sqlite.kripton294;

import com.abubusoft.kripton.android.annotation.BindDataSource;

@BindDataSource(daoSet = {DirectorDao.class, MovieDao.class}, fileName = "movie.db")
public interface MovieDataSource {

  
}
