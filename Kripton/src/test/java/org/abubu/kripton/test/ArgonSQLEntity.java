package org.abubu.kripton.test;

/**
 * 
 */

import com.abubusoft.kripton.annotation.BindDatabase;
import com.abubusoft.kripton.annotation.BindElement;


/**
 * @author xcesco
 *
 */
public class ArgonSQLEntity {

	@BindDatabase(primaryKey=true)
	@BindElement(name="_id")
	public long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}