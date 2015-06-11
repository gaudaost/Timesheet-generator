package dominio;

import java.util.Vector;

public class CSVGenerator {
	XMLParser parser;
	Time time;
	
	public CSVGenerator(Time time) {
		this.time=time;
		parser=new XMLParser();
	}
	
	private boolean isWeekend(long timestamp) {
		int dayOfWeek=time.getDayOfWeek(timestamp);
		if(dayOfWeek==6 || dayOfWeek==7) {
			return true;
		}
		return false;
	}
	
	private Vector<String> generateVectorString(long timestamp, float hoursWorked) {
		Vector<String> output=new Vector<String>();
		String day=time.getDay(timestamp);
		String dayName=parser.getNameDay(time.getDayOfWeek(timestamp));
		String month=time.getMonth(timestamp);
		String monthName=parser.getNameMonth(Integer.parseInt(month));
		String year=time.getYear(timestamp);
		String reasonOfAbsence=isWeekend(timestamp)? parser.getWeReasonOfAbsence():"";
		String project=isWeekend(timestamp)? "":parser.getCurrProj();
		String place=isWeekend(timestamp)? "":parser.getCurrPlace();
		output.add(year);
		output.add(month);
		output.add(monthName);
		output.add(day);
		output.add(dayName);
		output.add(String.format("%.1f", hoursWorked));
		output.add(reasonOfAbsence);
		output.add(project);
		output.add(place);
		return output;
	}
	
	private Vector<String> generateLine(long[] timestamps, int dayOffset) {
		if (dayOffset==0) {
			float hoursWorked=time.getHourDifference(timestamps[0], timestamps[1])-1;
			return generateVectorString(timestamps[0], hoursWorked);
		}
		else {
			int dayInMilliseconds=1000*60*60*24;
			return generateVectorString(timestamps[0]+dayOffset*dayInMilliseconds, 0);
		}
	}
	
	public CSVContainer getCSV(long[] timestamps, int differenceDays) {
		Vector<Vector<String>> csvData=new Vector<Vector<String>>();
		for (int i=0; i<differenceDays;i++) {
			csvData.add(generateLine(timestamps, i));
		}
		return new CSVContainer(csvData,parser.getHeader());
	}
	
}
