package datatier.timesheet.dao;

import domain.TimesheetData;

public interface WriterDAO {
	public void write(TimesheetData container);
}
