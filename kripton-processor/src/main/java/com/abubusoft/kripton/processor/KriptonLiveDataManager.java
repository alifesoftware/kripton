package com.abubusoft.kripton.processor;

import java.util.HashSet;
import java.util.Set;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class KriptonLiveDataManager {

	private static final String KRIPTON_X_PAGED_LIVE_DATA_HANDLER_IMPL_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.KriptonXPagedLiveDataHandlerImpl";

	private static final String KRIPTON_X_PAGED_LIVE_DATA_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.PagedLiveData";

	private static final String KRIPTON_X_MUTABLE_LIVE_DATA_CLASS_NAME = "androidx.lifecycle.MutableLiveData";

	private static final String KRIPTON_X_LIVE_DATA_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.KriptonXLiveData";

	private static final String KRIPTON_X_LIVE_DATA_HANDLER_IMPL_CLASS_NAME = "com.abubusoft.kripton.androidx.livedata.KriptonXLiveDataHandlerImpl";

	private static KriptonLiveDataManager instance;

	public static KriptonLiveDataManager getInstance() {
		return instance;
	}

	private Set<String> liveDataClazzSet = new HashSet<>();

	public static void init(String paramValue) {
		boolean value = "true".equals(paramValue) ? true : false;

		if (instance == null) {
			instance = new KriptonLiveDataManager(value);

			instance.liveDataClazzSet.clear();

			if (instance.androidxSupport) {
				instance.liveDataHandlerClazz = ClassName.bestGuess(KRIPTON_X_LIVE_DATA_HANDLER_IMPL_CLASS_NAME);
				instance.liveDataClazz = ClassName.bestGuess(KRIPTON_X_LIVE_DATA_CLASS_NAME);
				instance.mutableLiveDataClazz = ClassName.bestGuess(KRIPTON_X_MUTABLE_LIVE_DATA_CLASS_NAME);

				instance.pagedLiveDataClazz = ClassName.bestGuess(KRIPTON_X_PAGED_LIVE_DATA_CLASS_NAME);
				instance.pagedLiveDataHandlerClazz = ClassName.bestGuess(KRIPTON_X_PAGED_LIVE_DATA_HANDLER_IMPL_CLASS_NAME);

				instance.liveDataClazzSet.add("androidx.lifecycle.LiveData");
			} else {
				instance.liveDataHandlerClazz = ClassName.bestGuess("com.abubusoft.kripton.android.livedata.KriptonLiveDataHandlerImpl");
				instance.liveDataClazz = ClassName.bestGuess("com.abubusoft.kripton.android.livedata.KriptonLiveData");
				instance.mutableLiveDataClazz = ClassName.bestGuess("android.arch.lifecycle.MutableLiveData");

				instance.pagedLiveDataClazz = ClassName.bestGuess("com.abubusoft.kripton.android.livedata.PagedLiveData");
				instance.pagedLiveDataHandlerClazz = ClassName.bestGuess("com.abubusoft.kripton.android.livedata.KriptonPagedLiveDataHandlerImpl");

				instance.liveDataClazzSet.add("android.arch.lifecycle.LiveData");
			}

			instance.liveDataClazzSet.add(instance.liveDataClazz.toString());
			instance.liveDataClazzSet.add(instance.mutableLiveDataClazz.toString());
			instance.liveDataClazzSet.add(instance.pagedLiveDataClazz.toString());
		}
	}

	/**
	 * test purpouse
	 */
	public static void reset() {
		instance = null;
	}

	private boolean androidxSupport;

	public boolean isAndroidxSupport() {
		return androidxSupport;
	}

	private ClassName liveDataClazz;

	private ClassName liveDataHandlerClazz;
	private ClassName mutableLiveDataClazz;

	private ClassName pagedLiveDataClazz;

	private ClassName pagedLiveDataHandlerClazz;

	private KriptonLiveDataManager(boolean value) {
		androidxSupport = value;
	}

	public ClassName getLiveDataClazz() {
		return liveDataClazz;
	}

	public ClassName getLiveDataHandlerClazz() {
		return liveDataHandlerClazz;
	}

	public ClassName getMutableLiveDataClazz() {
		return this.mutableLiveDataClazz;
	}

	public ClassName getPagedLiveDataClazz() {
		return pagedLiveDataClazz;
	}

	public ClassName getPagedLiveDataHandlerClazz() {
		return pagedLiveDataHandlerClazz;
	}

	public boolean isLiveData(String wrapperName) {
		return this.liveDataClazzSet.contains(wrapperName);
		// if (!androidxSupport &&
		// (com.abubusoft.kripton.android.livedata.KriptonLiveData.class.getName().equals(wrapperName)
		// ||
		// android.arch.lifecycle.MutableLiveData.class.getName().equals(wrapperName)
		// ||
		// android.arch.lifecycle.LiveData.class.getName().equals(wrapperName)))
		// {
		// return true;
		// }
		//
		// if (androidxSupport &&
		// (com.abubusoft.kripton.androidx.livedata.KriptonXLiveData.class.getName().equals(wrapperName)
		// ||
		// androidx.lifecycle.MutableLiveData.class.getName().equals(wrapperName)
		// || androidx.lifecycle.LiveData.class.getName().equals(wrapperName)))
		// {
		// return true;
		// }
		//
		// return false;
	}

	public boolean isPagedLiveData(TypeName liveDataReturnClass) {
		if (liveDataReturnClass instanceof ParameterizedTypeName) {
			ParameterizedTypeName p = (ParameterizedTypeName) liveDataReturnClass;
			ClassName r = p.rawType;
			return pagedLiveDataClazz.toString().equals(r.toString());
		}

		return false;
	}
}
