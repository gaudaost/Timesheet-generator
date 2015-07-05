package dominio;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Time {
	
	protected String getDay(long timestamp) {
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat("dd");
		return sdf.format(date);
	}
	
	protected String getMonth(long timestamp) {
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat("MM");
		return sdf.format(date);
	}
	
	protected int getDayOfWeek(long timestamp) {
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat("u");
		return Integer.parseInt(sdf.format(date));
	}
	
	protected String getYear(long timestamp) {
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY");
		return sdf.format(date);
	}
	
	public int getDayDifference(long timestamp1, long timestamp2) {
		int day1=Integer.parseInt(getDay(timestamp1));
		int day2=Integer.parseInt(getDay(timestamp2));
		if(day1!=day2 || getHourDifference(timestamp1, timestamp2)>24) {
			return (int) Math.ceil(((double)(timestamp2-timestamp1))/(1000*60*60*24));
		}
		else {
			return 0;
		}
	}
	
	protected float getHourDifference(long timestamp1, long timestamp2) {
		return ((float)(timestamp2-timestamp1))/(1000*60*60);
	}
	
}
