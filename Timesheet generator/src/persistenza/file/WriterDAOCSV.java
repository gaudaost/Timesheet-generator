package persistenza.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import persistenza.dao.WriterDAO;
import dominio.BasicContainer;

public class WriterDAOCSV implements WriterDAO {
	
	public WriterDAOCSV() {}
	
	public void write(BasicContainer container) {
		for (int i = 0; i < container.getSize(); i++) {
			String fileName=container.getYearMonth(i)+".txt";
			File file=new File(fileName);
			if(!file.exists()) {
				addHeader(fileName, container,i);
			}
			String text=getCSVLine(container,i);
			addLine(text, fileName);
		}
	}
	
	private String getCSVLine(BasicContainer container, int i) {
		Vector<String> line=container.getLine(i);
		String output=line.get(3);
		for (int j = 4; j < line.size(); j++) {
			output+=";"+line.get(j);
		}
		return output;
	}
	
	private void addHeader(String fileName,BasicContainer container, int i) {
		Vector<String> header=container.getHeader();
		String line1=header.get(0)+";;"+container.getMonth(i)+";"+container.getMonthName(i)+";"+header.get(1)+";";
		String line2=header.get(2)+";"+header.get(3)+";"+header.get(4)+";"+header.get(5)+";"+header.get(6)+";"+header.get(7);
		addLine(line1, fileName);
		addLine(line2, fileName);
	}
	
	private void addLine(String text,String fileName) {
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
		    out.println(text);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
}
