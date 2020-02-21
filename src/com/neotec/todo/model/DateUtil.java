package com.neotec.todo.model;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;


public class DateUtil {

	
  static String dateTimePattern="((3[01]|[0-2][0-9]|[0-9])\\/(1[0-2]|0[0-9]|[0-9])\\/(\\d{4}|\\d{2}))?\\ ?([0-9]|[0-1][0-9]|2[0-3])?[h]?(:?([0-9]|[0-5][0-9]))?$";
  static String durationPattern="((\\d{1,3})[H])?(([1-5][0-9]|[0-9])(mn)?)?$";
  
  public static final Duration DurationParser(String text){
	 

	  Period period=new Period();
	  
	  PeriodFormatter pf=ISOPeriodFormat.standard();
	  period=Period.parse(text,pf);
	  return period.toStandardDuration();	  
  }

public static Duration GUIDurationParser (String text){
	
	
	
	
	int hour=0;
	int minute=0;

	
	if(!verifyDuration(text)) throw new IllegalArgumentException();
	text.concat("\n");
	Pattern p = Pattern.compile(durationPattern,Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(text);
	if( m.matches()){
		if(m.group(2)!=null) hour=Integer.parseInt(m.group(2));
		if(m.group(4)!=null) minute=Integer.parseInt(m.group(4));
	}

	return(new Duration(hour*60*60*1000+minute*60*1000));
}


public static String GUIDurationFormatter(Duration value){
	
	int Seconde=0;
	int Hour;
	int Minute;
	String result="";
	
	String text=value.toString();
	String buf=text.substring(2,text.length());
	if(buf.charAt(buf.length()-1)=='S')
		Seconde =Integer.parseInt(buf.substring(0, buf.length()-1));
	
	
	Hour=(Seconde/3600);
	Minute=(Seconde%3600)/60;
	if(Hour!=0) 
		result=((Integer)Hour).toString()+"h";
	
	if(Minute != 0)
		result=result.concat(((Integer)Minute).toString()+ "mn");
	
	return result;
	
	
	
}

public static boolean verifyDuration(String text){
	if(text==null) return false;
	text.concat("\n");
	Pattern p = Pattern.compile(durationPattern,Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(text);
	return m.matches();
		
}


public static DateTime DateTimeParser(String text) {

	  DateTimeFormatter dtp=ISODateTimeFormat.dateTimeParser();
	  return(dtp.parseDateTime(text));
}

public static DateTime GUIDateTimeParser(String text){
	
	
	int day=0;
	int month=0;
	int year=0;
	int hour=0;
	int minute=0;
	
	if(text.equals("")) return(new DateTime(0));
	if(!verifyDateTime(text)) throw new IllegalArgumentException();
	text.concat("\n");
	Pattern p = Pattern.compile(dateTimePattern,Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(text);

	if( m.matches()){
		if(m.group(2)!=null) day=Integer.parseInt(m.group(2));
		if(m.group(3)!=null) month=Integer.parseInt(m.group(3));
		if(m.group(4)!=null) year=Integer.parseInt(m.group(4));
		if(m.group(5)!=null) hour=Integer.parseInt(m.group(5));
		if(m.group(7)!=null) minute=Integer.parseInt(m.group(7));
	}
	
	if(year==0 && month==0 && day==0){
		DateTime now=new DateTime();
		year=now.getYear();
		month=now.getMonthOfYear();
		day=now.getDayOfMonth();
	}
	
	return new DateTime(year,month,day,hour,minute);
		

}

public static String GUIDateTimeFormatter(DateTime dateTime){
	if(dateTime.equals(new DateTime(0))) return "";
	else return dateTime.toString("dd/MM/yyyy HH:mm");
}


public static boolean verifyDateTime(String text){
	if(text==null) return false;
	text.concat("\n");
	Pattern p = Pattern.compile(dateTimePattern,Pattern.CASE_INSENSITIVE);
	Matcher m = p.matcher(text);
	return m.matches();

}
}
