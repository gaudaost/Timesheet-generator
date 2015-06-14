package main;

import persistenza.dao.WriterDAO;
import dominio.BasicContainerFactory;
import dominio.Time;

public interface Factory {

	public BasicContainerFactory getContainerFactory(Time time);
	public WriterDAO getWriter();
}
