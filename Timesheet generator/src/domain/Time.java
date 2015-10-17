package domain;

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
	
	protected String getDate(long timestamp) {
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
		return sdf.format(date);
	}
	
	public int getDayDifference(long timestamp1, long timestamp2) {
		String day1=getDate(timestamp1);
		String day2=getDate(timestamp2);
		if(!day1.equals(day2)) {
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