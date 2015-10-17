package main;

import persistenza.file.PersistenceDAOFile;
import view.TaskbarIcon;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import modelli.Model;
public class Main {
	private static Model model;
	public static final int FORMAT_EXCEL=0;
	public static final int FORMAT_CSV=1;

	public static void main(String[] args) {
		//Initialize the desired state persistence
		PersistenceDAOFile persistence=PersistenceDAOFile.getPersistenza();
		//Initialize the desired format of the time sheet output
		Factory factory=ExcelFactory.getFactory();
		//Get the model
		model = new Model(persistence,factory);
		Runnable r = new Runnable() {
			public void run() {
				//Start the main task
				model.task();
			}
		};
		new Thread(r).start();
		//Initizialize taskbar icon
		TaskbarIcon taskbarIcon=new TaskbarIcon();
		taskbarIcon.create();
	}
}
