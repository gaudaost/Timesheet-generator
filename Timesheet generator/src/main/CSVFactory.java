package main;

import datatier.timesheet.dao.WriterDAO;
import datatier.timesheet.implementations.WriterDAOCSV;
import dominio.Time;

public class CSVFactory implements Factory {
	private static CSVFactory csvFactory;

	private CSVFactory() {
	}

	@Override
	public WriterDAO getWriter() {
		return new WriterDAOCSV();
	}

	public static Factory getFactory() {
		if (csvFactory == null) {
			synchronized (CSVFactory.class) {
				if (csvFactory == null) {
					return new CSVFactory();
				}
			}
		}
		return csvFactory;
	}

}
