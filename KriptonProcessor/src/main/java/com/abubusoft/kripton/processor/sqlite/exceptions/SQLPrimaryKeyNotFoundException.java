package com.abubusoft.kripton.processor.sqlite.exceptions;

import com.abubusoft.kripton.processor.sqlite.SQLEntity;

public class SQLPrimaryKeyNotFoundException extends SQLiteProcessorException {

	private static final long serialVersionUID = 8462705406839489618L;

	public SQLPrimaryKeyNotFoundException(SQLEntity entity)
	{
		super("Bean '"+entity.getName()+"' does not have a primary key");
	}
}
