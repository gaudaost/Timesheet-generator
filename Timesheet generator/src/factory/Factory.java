package factory;

import datatier.state.dao.PersistenceDAOState;
import datatier.timesheet.dao.WriterDAO;

public interface Factory {
	public WriterDAO getTimesheetWriter();
	public PersistenceDAOState getStateWriter();
}
