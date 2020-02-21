package com.neotec.todo;

import static org.junit.Assert.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.neotec.todo.model.DateUtil;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void DateTimeTest() {

		
		String DateTxt;
		DateTime Date;
		DateTime CDate;
		
		DateTxt="10/12/2012";
		Date= new DateTime(2012,12,10,0,0);
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
		assertEquals(Date,CDate);
		System.out.println(CDate.toString());
		
		DateTxt="1/4/2012";
		Date= new DateTime(2012,4,1,0,0);
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
		assertEquals(Date,CDate);
		System.out.println(CDate.toString());
		
		DateTxt="01/04/2012";
		Date= new DateTime(2012,4,1,0,0);
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
		assertEquals(Date,CDate);
		System.out.println(CDate.toString());
		
		DateTxt="01/04/2012 0:0";
		Date= new DateTime(2012,4,1,0,0);
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
		assertEquals(Date,CDate);
		System.out.println(CDate.toString());
		
		DateTxt="01/04/2012 11:58";
		Date= new DateTime(2012,4,1,11,58);
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
		assertEquals(Date,CDate);
		System.out.println(CDate.toString());
		
		DateTxt="11:58";
		Date= new DateTime();
		
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
		//assertEquals(Date,CDate);
		System.out.println(CDate.toString());
		
		DateTxt="18h";
		Date= new DateTime(2012,4,1,11,58);
		CDate=DateUtil.GUIDateTimeParser(DateTxt);
		
//		assertEquals(Date,CDate);
		System.out.println(CDate.toString());
	}
	
	@Test
	public void DurationTest(){
		
		Duration CDuration;
		Duration valDuration;
		
		valDuration=new Duration(5*60*60*1000+23*60*1000);
		CDuration=DateUtil.GUIDurationParser("5H23");
		assertEquals(valDuration,CDuration);
		System.out.println(CDuration);
		
		valDuration=new Duration(5*60*60*1000+23*60*1000);
		CDuration=DateUtil.GUIDurationParser("5h23mn");
		assertEquals(valDuration,CDuration);
		System.out.println(CDuration);
		
//		valDuration=new Duration(5*60*60*1000+23*60*1000);
//		CDuration=DateUtil.GUIDurationParser("323mn");
//		assertEquals(valDuration,CDuration);
//		System.out.println(CDuration);
		
		valDuration=new Duration(5*60*60*1000);
		CDuration=DateUtil.GUIDurationParser("5h");
		assertEquals(valDuration,CDuration);
		System.out.println(CDuration);
		
		String txt=DateUtil.GUIDurationFormatter(valDuration);
	}

}
