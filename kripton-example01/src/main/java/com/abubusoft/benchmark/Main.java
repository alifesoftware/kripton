package com.abubusoft.benchmark;

import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.abubusoft.benchmark.model.Friend;
import com.abubusoft.benchmark.model.Response;
import com.abubusoft.kripton.KriptonBinder;
import com.abubusoft.kripton.exception.KriptonRuntimeException;

public class Main {

	public static void main(String[] args) throws Exception {
		{
			URL base = Main.class.getClassLoader().getResource("benchmark/largesample.json");

			String input = IOUtils.toString(base, Charset.forName("UTF-8"));
			System.out.println(base.getPath());

			long start = System.currentTimeMillis();
			for (int i = 0; i < 20000; i++) {
				Response output = KriptonBinder.jsonBind().parse(input, Response.class);

				if (!"success".equals(output.status)) {
					throw new KriptonRuntimeException();
				}
			}
			long end = System.currentTimeMillis();

			System.out.println("Time to elaborate " + (end - start));
		}
		{
			Friend input = new Friend();
			input.id = 23;
			input.name = "dummy name";

			String buffer = KriptonBinder.jsonBind().serialize(input);
			System.out.println(buffer);
		}

	}

}
