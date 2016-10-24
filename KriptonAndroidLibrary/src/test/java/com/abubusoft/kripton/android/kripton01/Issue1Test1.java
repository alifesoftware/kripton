/**
 * 
 */
package com.abubusoft.kripton.android.kripton01;

import java.util.Calendar;

import org.junit.Before;

import com.abubusoft.kripton.android.all.IssueBaseTest;



/**
 * @author xcesco
 *
 */
public class Issue1Test1 extends IssueBaseTest<Bean02> {

	@Before
	public void setup()
	{
		beanInput=new Bean02();
		
		beanInput.setName("Tonj");
		beanInput.setSurname("Manero");
		
		Calendar calendar=Calendar.getInstance();
		calendar.set(1965, 6, 12);
		beanInput.setBirthday(calendar.getTime());
		Integer[] array= {1, 2, 4};
		
		beanInput.setTickets(array);
	}
	

}
