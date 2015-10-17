package main;

import datatier.timesheet.dao.WriterDAO;
import datatier.timesheet.implementations.WriterDAOExcel;
import dominio.Time;

public class ExcelFactory implements Factory {
	
	private static CSVFactory excelFactory;

	private ExcelFactory() {
	}

	@Override
	public WriterDAO getWriter() {
		return new WriterDAOExcel();
	}
	
	public static Factory getFactory() {
		if (excelFactory == null) {
			synchronized (CSVFactory.class) {
				if (excelFactory == null) {
					return new ExcelFactory();
				}
			}
		}
		return excelFactory;
	}

}
