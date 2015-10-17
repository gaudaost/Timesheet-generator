package factory;

import datatier.state.dao.PersistenceDAOState;
import datatier.state.implementations.PersistenceDAOStateFile;
import datatier.timesheet.dao.WriterDAO;
import datatier.timesheet.implementations.WriterDAOExcel;

public class ExcelFactory implements Factory {
	
	private static CSVFactory excelFactory;

	private ExcelFactory() {
	}
	
	@Override
	public WriterDAO getTimesheetWriter() {
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

	@Override
	public PersistenceDAOState getStateWriter() {
		return PersistenceDAOStateFile.getPersistenza();
	}

}
