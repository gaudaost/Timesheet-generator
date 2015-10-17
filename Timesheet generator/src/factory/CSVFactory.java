package factory;

import datatier.state.dao.PersistenceDAOState;
import datatier.state.implementations.PersistenceDAOStateFile;
import datatier.timesheet.dao.WriterDAO;
import datatier.timesheet.implementations.WriterDAOCSV;

public class CSVFactory implements Factory {
	private static CSVFactory csvFactory;

	private CSVFactory() {
	}

	@Override
	public WriterDAO getTimesheetWriter() {
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
	
	@Override
	public PersistenceDAOState getStateWriter() {
		return PersistenceDAOStateFile.getPersistenza();
	}

}
