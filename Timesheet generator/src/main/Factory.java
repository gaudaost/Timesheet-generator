package main;

import persistenza.dao.WriterDAO;
import dominio.BasicContainerBuilder;
import dominio.Time;

public interface Factory {

	public BasicContainerBuilder getContainerFactory(Time time);
	public WriterDAO getWriter();
}
