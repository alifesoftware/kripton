/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package base;

import static com.abubusoft.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static com.google.common.truth.Truth.assertAbout;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.tools.JavaFileObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.abubusoft.kripton.binder2.BinderType;
import com.abubusoft.kripton.binder2.KriptonBinder2;
import com.abubusoft.kripton.processor.BaseProcessor;
import com.abubusoft.kripton.processor.BindDataSourceProcessor;
import com.abubusoft.kripton.processor.BindSharedPreferencesProcessor;
import com.abubusoft.kripton.processor.BindTypeProcessor;
import com.abubusoft.kripton.processor.exceptions.KriptonProcessorException;
import com.abubusoft.testing.compile.CompileTester.CompilationResultsConsumer;
import com.abubusoft.testing.compile.CompileTester.GenerationClause;
import com.abubusoft.testing.compile.CompileTester.SuccessfulCompilationClause;
import com.abubusoft.testing.compile.JavaFileObjects;
import com.google.common.io.ByteStreams;

public class BaseProcessorTest {
	
	protected boolean display=false;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	public <E extends KriptonProcessorException> void expectedException(Class<E> clazzException) throws InstantiationException, IllegalAccessException {
		expectedEx.expect(AssertionError.class);
		expectedEx.expectMessage(clazzException.getSimpleName());
	}

	final TestType testType = TestType.GENERATE;

	@Before
	public void before() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tH:%1$tM:%1$tS.%1$tL %4$-7s [%3$s] (%2$s) %5$s %6$s%n");
	}

	public enum TestType {
		GENERATE, COMPARE
	}
	
	protected void check(Object bean) {
		int xmlSize=serializeAndParse(bean, BinderType.XML);
		int cborSize=serializeAndParseBinary(bean, BinderType.CBOR);
		int jsonSize=serializeAndParse(bean, BinderType.JSON);
		int yamlSize=serializeAndParse(bean, BinderType.YAML);
		int propertySize=serializeAndParse(bean, BinderType.PROPERTIES);
		
		double cborPerc=cborSize*100.0/xmlSize;
		double jsonPerc=jsonSize*100.0/xmlSize;
		double yamlPerc=yamlSize*100.0/xmlSize;
		double propertyPerc=propertySize*100.0/xmlSize;
		
		System.out.println(String.format("xml: %s, cbor: %s (%.0f%%), json: %s (%.0f%%), yaml: %s (%.0f%%), property: %s (%.0f%%)", xmlSize, cborSize, cborPerc, jsonSize, jsonPerc,yamlSize, yamlPerc, propertySize, propertyPerc));
		
	}
	
	/**
	 * @param bean
	 * @param type
	 * @return 
	 */
	public int serializeAndParse(Object bean, BinderType type) {
		String output1=KriptonBinder2.getBinder(type).serialize(bean);
		if (display) System.out.println(output1);
		
		Object bean2=KriptonBinder2.getBinder(type).parse(output1, bean.getClass());				
		
		String output2=KriptonBinder2.getBinder(type).serialize(bean2);
		if (display) System.out.println(output2);
		
		Assert.assertTrue(output1.length()==output2.length());		
		assertEquals(bean, bean2);
		
		return output2.length();
	}

	public int serializeAndParseBinary(Object bean, BinderType type) {
		MyByteArrayOutputStream bar = new MyByteArrayOutputStream();
		KriptonBinder2.getBinder(type).serialize(bean, bar);
		String value1=toString(bar.getBuf());

		if (display) System.out.println(value1);
		Object bean2 = KriptonBinder2.getBinder(type).parse(new ByteArrayInputStream(bar.getBuf()), bean.getClass());

		MyByteArrayOutputStream bar2 = new MyByteArrayOutputStream();
		KriptonBinder2.getBinder(type).serialize(bean2, bar2);
		String value2=toString(bar2.getBuf());
		if (display) System.out.println(value2);

		Assert.assertTrue(value1.length()==value2.length());		
		assertEquals(bean, bean2);
		
		return bar.getCount();
	}
	

	String toString(byte[] input) {
		StringBuilder buffer = new StringBuilder();
		for (int j = 0; j < input.length; j++) {
			buffer.append(String.format("%02X", input[j]));
		}
		return buffer.toString();
	}

	protected static Logger logger = Logger.getGlobal();

	public enum PathSourceType {
		SRC_TEST_JAVA("src/test/java/"), SRC_TEST_EXPECTED("src/test/expected/"), TARGET_TEST_RESULT("target/test/generated/");

		private PathSourceType(String path) {
			this.path = path;
		}

		private String path;

		public String getPath() {
			return path;
		}

		/**
		 * <p>
		 * Create a file starting with specified folder. Path have to start
		 * without "/"
		 * 
		 * @param fileName
		 * @return
		 */
		public File createFile(String fileNameWithRelativePath) {
			if (fileNameWithRelativePath.startsWith("/") || fileNameWithRelativePath.startsWith("\\")) {
				fileNameWithRelativePath = fileNameWithRelativePath.substring(1);
			}
			return new File(this.path + fileNameWithRelativePath);
		}
	};

	protected static JavaFileObject source(Class<?> clazz) throws IOException {
		return getSourceFile(PathSourceType.SRC_TEST_JAVA, clazz);
	}

	protected List<JavaFileObject> sources(Class<?>... clazzes) throws IOException {
		List<JavaFileObject> list = new ArrayList<JavaFileObject>();

		for (Class<?> item : clazzes) {
			list.add(source(item));
		}

		return list;
	}

	protected static void writeGeneratedFile(PathSourceType basePath, JavaFileObject javaFileObject) throws IOException {
		PathSourceType pathSourceType = basePath;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));

		Files.createDirectories(path.getParent());
		byte[] bytes = ByteStreams.toByteArray(javaFileObject.openInputStream());

		Files.write(path, bytes);
	}

	protected static boolean compareGeneratedFile(JavaFileObject javaFileObject) throws IOException {
		PathSourceType pathSourceType = PathSourceType.SRC_TEST_EXPECTED;
		Path path = Paths.get(pathSourceType.getPath(), javaFileObject.getName().replace("SOURCE_OUTPUT", ""));

		byte[] bytes = ByteStreams.toByteArray(javaFileObject.openInputStream());
		byte[] aspectedBytes = Files.readAllBytes(path);

		return Arrays.equals(bytes, aspectedBytes);
	}

	protected static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

	/**
	 * Generate an java file object
	 * 
	 * @param pathSourceType
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	protected static JavaFileObject getSourceFile(PathSourceType pathSourceType, Class<?> clazz) throws IOException {
		Path path = Paths.get(pathSourceType.getPath(), clazz.getCanonicalName().replace(".", Character.toString(File.separatorChar)) + ".java");
		byte[] buffer = Files.readAllBytes(path.toAbsolutePath());

		JavaFileObject source = JavaFileObjects.forSourceLines(clazz.getCanonicalName(), new String(buffer));
		return source;
	}

	protected void withGeneratedSourceCounter(long realValue, long aspected) {
		Assert.assertEquals(realValue, aspected);
	}

	protected long buildSharedPreferencesProcessorTest(Class<?>... classesToTest) throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(BindSharedPreferencesProcessor.class, classesToTest);
	}

	protected long buildBindProcessorTest(Class<?>... classesToTest) throws InstantiationException, IllegalAccessException, IOException  {
		return buildTest(BindTypeProcessor.class, classesToTest);
	}

	protected long buildDataSourceProcessorTest(Class<?>... classesToTest) throws InstantiationException, IllegalAccessException, IOException {
		return buildTest(BindDataSourceProcessor.class, classesToTest);
	}

	/**
	 * Build standard test
	 * 
	 * @param classesToTest
	 *            classes to compile and test
	 * 
	 * @return count of generated sources
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Throwable 
	 */
	protected long buildTest(Class<? extends BaseProcessor> processorClazz, Class<?>... classesToTest) throws IOException, InstantiationException, IllegalAccessException {
			final Map<String, String> mapSet = new HashMap<>();
			final List<JavaFileObject> sourcesPhase1 = sources(classesToTest);

			BindDataSourceProcessor.DEVELOP_MODE = true;

			final AtomicLong counter = new AtomicLong(0);

		//@formatter:off
		SuccessfulCompilationClause result1 = assertAbout(javaSources()).that(
				sourcesPhase1).processedWith(processorClazz.newInstance()).compilesWithoutError();
		//@formatter:on
			GenerationClause<SuccessfulCompilationClause> resultPhase1 = result1.and().generatesSources();
			resultPhase1.forAllOfWhich(new CompilationResultsConsumer() {

				@Override
				public void accept(Map<String, JavaFileObject> t) {
					for (Entry<String, JavaFileObject> item : t.entrySet()) {
						counter.addAndGet(1);
						try {
							String contentFile = getStringFromInputStream(item.getValue().openInputStream());

							mapSet.put(item.getKey(), contentFile);

							if (testType == TestType.COMPARE) {
								writeGeneratedFile(PathSourceType.TARGET_TEST_RESULT, item.getValue());
								boolean result = compareGeneratedFile(item.getValue());
								Assert.assertTrue(String.format("%s not generated as aspected", item.getKey()), result);
							} else {
								writeGeneratedFile(PathSourceType.SRC_TEST_EXPECTED, item.getValue());
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				}
			});

			return counter.longValue();
	}
}
