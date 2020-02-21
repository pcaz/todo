package com.neotec.todo.NeoTime;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

	/*
	 * Wrapper du DateTime de JodaTime
	 */
	@SuppressWarnings("serial")
	public class DateTime extends org.joda.time.base.BaseDateTime {
		
		public org.joda.time.DateTime value; 
		
		public DateTime(){
			value = new org.joda.time.DateTime();
			System.out.println(value.toString());
		}
		
		public DateTime(org.joda.time.DateTime dateTime){
			value=dateTime;
			System.out.println(value.toString());
		}
		
		public DateTime (long millis){
			value=new org.joda.time.DateTime(millis);
			System.out.println(value.toString());
		}
		
		public DateTime(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour){
			value=new org.joda.time.DateTime(year,monthOfYear,dayOfMonth,hourOfDay, minuteOfHour);
			System.out.println(value.toString());
		}
		
		public DateTime minus(Duration time){
		
			return (toNeo(this.value.minus (time.value)));
		}

		public String toString(){
			return value.toString();


		}
	public static org.joda.time.DateTime toJoda(DateTime neo){
		  return neo.value;
	  }
	 
	public static DateTime toNeo(org.joda.time.DateTime joda){
		  return(new DateTime(joda));
	  }

	public  static DateTime DateTimeParser(String text) {

		  DateTimeFormatter dtp=ISODateTimeFormat.dateTimeParser();
		  return(toNeo(dtp.parseDateTime(text)));
	}
}
