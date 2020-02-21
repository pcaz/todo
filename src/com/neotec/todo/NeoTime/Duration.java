package com.neotec.todo.NeoTime;

import org.joda.time.Period;
import org.joda.time.base.BaseDuration;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;



@SuppressWarnings("serial")
public class Duration extends org.joda.time.base.BaseDuration{
	
	
	/*
	 * Wrapper de la Duration de JodaTime
	 */

	
		
		public org.joda.time.Duration value;
		
		public Duration(long millisecond ){
			super(millisecond);
			value=new org.joda.time.Duration(millisecond);
		}

	
	
	
	
	
	
	  public static org.joda.time.Duration toJoda(Duration neo){
		  return neo.value; 
	  }
	  
	  
	  
	  public static Duration toNeo(org.joda.time.Duration joda){
		  return(new Duration(joda.getMillis()));
		  
	  }
	  
	 
	  public final static Duration DurationParser(String text){
			 

			  Period period=new Period();
			  
			  PeriodFormatter pf=ISOPeriodFormat.standard();
			  period=Period.parse(text,pf);
			  return toNeo(period.toStandardDuration());	  
		  }

		
		
		
		
		
		
}
