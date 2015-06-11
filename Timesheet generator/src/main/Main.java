package main;

import persistenza.file.PersistenceDAOFile;
import modelli.Model;

public class Main {
	private static Model model;

	public static void main(String[] args) {
		PersistenceDAOFile persistence=PersistenceDAOFile.getPersistenza();
		model = new Model(persistence);
		Runnable r = new Runnable() {
			public void run() {
				model.task();
			}
		};
		new Thread(r).start();
	}
}
