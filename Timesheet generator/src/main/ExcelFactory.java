package main;

import persistenza.dao.WriterDAO;
import persistenza.file.WriterDAOExcel;
import dominio.BasicContainerBuilder;
import dominio.Time;

public class ExcelFactory implements Factory {
	
	private static CSVFactory excelFactory;

	private ExcelFactory() {
	}

	@Override
	public BasicContainerBuilder getContainerFactory(Time time) {
		return new BasicContainerBuilder(time);
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
