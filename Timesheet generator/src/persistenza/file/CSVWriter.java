package persistenza.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import dominio.CSVContainer;

public class CSVWriter {
	public void write(CSVContainer container) {
		for (int i = 0; i < container.getSize(); i++) {
			String fileName=container.getYearMonth(i)+".txt";
			String text=container.getCSVLine(i);
			addLine(text, fileName);
		}
	}
	
	private void addLine(String text,String fileName) {
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
		    out.println(text);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
}
