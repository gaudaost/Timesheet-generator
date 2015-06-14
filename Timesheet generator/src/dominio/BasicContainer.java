package dominio;

import java.util.Vector;

public class BasicContainer {
	private Vector<Vector<String>> data;
	private Vector<String> header;
	
	protected BasicContainer(Vector<Vector<String>> csvData,Vector<String> header) {
		this.data=csvData;
		this.header=header;
	}
	
	public Vector<Vector<String>> getData() {
		return data;
	}
	
	public Vector<String> getHeader() {
		return header;
	}
	
	public int getSize() {
		return data.size(); 
	}
	
	public String getYearMonth(int i) {
		Vector<String> line=data.get(i);
		return line.get(0)+"-"+line.get(1);
	}
	
	public String getMonth(int i) {
		Vector<String> line=data.get(i);
		return line.get(1);
	}
	
	public String getMonthName(int i) {
		Vector<String> line=data.get(i);
		return line.get(2);
	}
}
