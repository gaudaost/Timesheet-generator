package persistenza.dao;

import dominio.TimesheetData;

public interface WriterDAO {
	public void write(TimesheetData container);
}
