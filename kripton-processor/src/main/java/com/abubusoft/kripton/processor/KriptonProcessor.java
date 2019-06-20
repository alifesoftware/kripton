/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.abubusoft.kripton.processor;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.StringUtils;
import com.sun.source.util.Trees;

/**
 * Annotation processor for json/xml/etc.
 *
 * @author Francesco Benincasa (info@abubusoft.com)
 */
public class KriptonProcessor extends BaseProcessor {

	@Override
	public Set<String> getSupportedOptions() {
		Set<String> options = new LinkedHashSet<String>();

		options.add(KriptonOptions.DEBUG_OPTION_NAME);
		options.addAll(typeProcessor.getSupportedOptions());
		options.addAll(sharedPreferencesProcessor.getSupportedOptions());
		options.addAll(dataSourceProcessor.getSupportedOptions());
		options.addAll(many2ManyProcessor.getSupportedOptions());

		return options;
	}

	/** The many 2 many processor. */
	private BindMany2ManySubProcessor many2ManyProcessor = new BindMany2ManySubProcessor();

	/** The shared preferences processor. */
	private BindSharedPreferencesSubProcessor sharedPreferencesProcessor = new BindSharedPreferencesSubProcessor();

	/** The data source processor. */
	private BindDataSourceSubProcessor dataSourceProcessor = new BindDataSourceSubProcessor();

	/** The type processor. */
	private BindTypeSubProcessor typeProcessor = new BindTypeSubProcessor();

	public static Trees trees;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.BaseProcessor#getSupportedAnnotationClasses()
	 */
	protected Set<Class<? extends Annotation>> getSupportedAnnotationClasses() {
		Set<Class<? extends Annotation>> annotations = new LinkedHashSet<Class<? extends Annotation>>();

		annotations.addAll(typeProcessor.getSupportedAnnotationClasses());
		annotations.addAll(sharedPreferencesProcessor.getSupportedAnnotationClasses());
		annotations.addAll(dataSourceProcessor.getSupportedAnnotationClasses());
		annotations.addAll(many2ManyProcessor.getSupportedAnnotationClasses());

		return annotations;
	}

	/**
	 * Retrieve all supported annotation classes. Added for IntelliJ plugin
	 * 
	 * @return set of all supported annotation classes
	 */
	public static Set<Class<? extends Annotation>> getAllSupportedAnnotationClasses() {
		KriptonProcessor processor = new KriptonProcessor();

		return processor.getSupportedAnnotationClasses();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abubusoft.kripton.processor.BaseProcessor#init(javax.annotation.processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);

		KriptonProcessor.trees = Trees.instance(processingEnv);

		KriptonOptions.init(this, processingEnv);

		typeProcessor.init(processingEnv);
		many2ManyProcessor.init(processingEnv);
		sharedPreferencesProcessor.init(processingEnv);
		dataSourceProcessor.init(processingEnv);

		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
		try {
			count++;
			if (count == 1) {

				many2ManyProcessor.clear();
				typeProcessor.clear();
				sharedPreferencesProcessor.clear();
				dataSourceProcessor.clear();

				// generate @BindGeneratedDao
				many2ManyProcessor.process(annotations, roundEnv);

				// generate bindmap
				typeProcessor.process(annotations, roundEnv);

				sharedPreferencesProcessor.process(annotations, roundEnv);
				sharedPreferencesProcessor.generateClasses();

				dataSourceProcessor.generatedEntities = many2ManyProcessor.result.value0;
				dataSourceProcessor.generatedDaos = many2ManyProcessor.result.value1;
				// dump(1, roundEnv);
				dataSourceProcessor.analyzeRound(annotations, roundEnv);
				dataSourceProcessor.process(annotations, roundEnv);
				dataSourceProcessor.generateClasses(roundEnv);
			} else if (count == 2) {
				dataSourceProcessor.analyzeSecondRound(annotations, roundEnv);
				dataSourceProcessor.processSecondRound(annotations, roundEnv);
				dataSourceProcessor.generateClassesSecondRound(roundEnv);
			}

			return true;
		} catch (Throwable e) {
			String msg = StringUtils.nvl(e.getMessage());
			error(null, e.getClass().getCanonicalName() + ": " + msg);

			if (DEBUG_MODE) {
				if (JUNIT_TEST_MODE) {
					logger.log(Level.SEVERE, msg);
					//e.printStackTrace();
				} else {
					StackTraceElement[] stackTrace = e.getStackTrace();
					for (StackTraceElement item : stackTrace) {
						error(null, String.format("\tat %s.%s(%s:%s)", item.getClassName(), item.getMethodName(), item.getFileName(), item.getLineNumber()));
					}					
				}
			}
			return false;
		}

	}

}
