package main;

import persistenza.dao.WriterDAO;
import persistenza.file.WriterDAOCSV;
import dominio.BasicContainerBuilder;
import dominio.Time;

public class CSVFactory implements Factory {
	private static CSVFactory csvFactory;

	private CSVFactory() {
	}

	@Override
	public BasicContainerBuilder getContainerFactory(Time time) {
		return new BasicContainerBuilder(time);
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
