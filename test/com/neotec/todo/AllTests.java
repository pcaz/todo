package com.neotec.todo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(value={
//		ObjectFactoryTest.class,
		TaskTest.class,
		TaskListTest.class,
//		RecurringTaskTest.class,
//		ProjectTest.class
		XMLSaveTest.class,
		DateUtilTest.class
	})
	
	public class AllTests {
		
		
	}
