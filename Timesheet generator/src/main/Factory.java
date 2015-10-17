package main;

import datatier.timesheet.dao.WriterDAO;
import dominio.Time;

public interface Factory {
	public WriterDAO getWriter();
}
