package main;

import factory.ExcelFactory;
import factory.Factory;
import models.Model;
import views.TaskbarIcon;
public class Main {
	private static Model model;
	public static final int FORMAT_EXCEL=0;
	public static final int FORMAT_CSV=1;

	public static void main(String[] args) {
		//Initialize the desired format of the time sheet output
		Factory factory=ExcelFactory.getFactory();
		//Get the model
		model = new Model(factory);
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
