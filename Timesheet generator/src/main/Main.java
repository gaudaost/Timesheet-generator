package main;

import persistenza.file.PersistenceDAOFile;
import modelli.interfaccia.ModelloInterfaccia;
import modelli.modello.ModelloInterfacciaModello;

public class Main {
	private static ModelloInterfaccia modello;

	public static void main(String[] args) {
		PersistenceDAOFile persistence=PersistenceDAOFile.getPersistenza();
		modello = new ModelloInterfacciaModello(persistence);
		Runnable r = new Runnable() {
			public void run() {
				modello.task();
			}
		};
		new Thread(r).start();
	}
}
