package main;

import persistenza.dao.WriterDAO;
import persistenza.file.WriterDAOCSV;
import dominio.BasicContainerFactory;
import dominio.Time;

public class CSVFactory implements Factory {
	private static CSVFactory csvFactory;

	private CSVFactory() {
	}

	@Override
	public BasicContainerFactory getContainerFactory(Time time) {
		return new BasicContainerFactory(time);
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
