package dominio;

import java.util.Vector;

public class CSVGenerator {
	XMLParser parser;
	Time time;
	
	public CSVGenerator(Time time) {
		this.time=time;
		parser=new XMLParser();
	}
	
	private Vector<String> generateVectorString(long timestamp, float hoursWorked) {
		Vector<String> output=new Vector<String>();
		String day=time.getDay(timestamp);
		String month=time.getMonth(timestamp);
		String year=time.getYear(timestamp);
		output.add(year);
		output.add(month);
		output.add(day);
		output.add(String.format("%.1f", hoursWorked));
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
		return new CSVContainer(csvData);
	}
	
}
