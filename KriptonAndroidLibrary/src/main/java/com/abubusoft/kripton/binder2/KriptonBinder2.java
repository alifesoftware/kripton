package com.abubusoft.kripton.binder2;

import java.util.HashMap;
import java.util.Map;

import com.abubusoft.kripton.binder2.context.BinderContext;
import com.abubusoft.kripton.binder2.context.JsonBinderContext;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public abstract class KriptonBinder2 {
	
	public static final String MAPPER_CLASS_SUFFIX = "BindMap";
	
	public static void registryBinder(BinderContext factory)
	{
		binders.put(factory.getSupportedFormat(), factory);
	}
	
	private static final Map<BinderType, BinderContext> binders=new HashMap<>();
	
	static {
		registryBinder(new JsonBinderContext());
	}
	
	public static BinderContext getBinder(BinderType format) {
		BinderContext binder=binders.get(format);
		
		if (binder==null) throw new KriptonRuntimeException(String.format("%s format is not supported", format));
		
		return binder;
	}

}