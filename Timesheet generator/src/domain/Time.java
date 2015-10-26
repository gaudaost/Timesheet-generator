package domain;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Time {
	
	private String getFormatedDate(long timestamp, String format) {
		Date date=new Date(timestamp);
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	protected String getDay(long timestamp) {
		return getFormatedDate(timestamp, "dd");
	}
	
	protected String getMonth(long timestamp) {
		return getFormatedDate(timestamp, "MM");
	}
	
	protected int getDayOfWeek(long timestamp) {
		return Integer.parseInt(getFormatedDate(timestamp, "u"));
	}
	
	protected String getYear(long timestamp) {
		return getFormatedDate(timestamp, "YYYY");
	}
	
	protected String getDate(long timestamp) {
		return getFormatedDate(timestamp, "dd-MM-YYYY");
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
