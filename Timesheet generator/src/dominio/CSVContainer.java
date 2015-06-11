package dominio;

import java.util.Vector;

public class CSVContainer {
	private Vector<Vector<String>> csvData;
	private Vector<String> header;
	
	protected CSVContainer(Vector<Vector<String>> csvData,Vector<String> header) {
		this.csvData=csvData;
		this.header=header;
	}
	
	public int getSize() {
		return csvData.size(); 
	}
	
	public String getYearMonth(int i) {
		Vector<String> line=csvData.get(i);
		return line.get(0)+"-"+line.get(1);
	}
	
	public String getMonth(int i) {
		Vector<String> line=csvData.get(i);
		return line.get(1);
	}
	
	public String getMonthName(int i) {
		Vector<String> line=csvData.get(i);
		return line.get(2);
	}
	
	public String getCSVLine(int i) {
		Vector<String> line=csvData.get(i);
		String output=line.get(3);
		for (int j = 4; j < line.size(); j++) {
			output+=";"+line.get(j);
		}
		return output;
	}
	
	public Vector<String> getHeader() {
		return header;
	}
}
