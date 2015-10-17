package main;

import persistenza.dao.WriterDAO;
import dominio.Time;

public interface Factory {
	public WriterDAO getWriter();
}
