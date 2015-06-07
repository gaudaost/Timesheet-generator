package dominio;

import java.util.Vector;

public class CSVContainer {
	Vector<Vector<String>> csvData;
	//Vector<String> header;
	
	protected CSVContainer(Vector<Vector<String>> csvData) {
		this.csvData=csvData;
		//this.header=header;
	}
	
	public int getSize() {
		return csvData.size(); 
	}
	
	public String getYearMonth(int i) {
		Vector<String> line=csvData.get(i);
		return line.get(0)+"-"+line.get(1);
	}
	
	public String getCSVLine(int i) {
		String output="";
		Vector<String> line=csvData.get(i);
		for (int j = 2; j < line.size(); j++) {
			output+=line.get(j)+";";
		}
		return output;
	}
}
