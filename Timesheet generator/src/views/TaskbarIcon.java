package views;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

import controller.TaskbarController;

public class TaskbarIcon {
	public void create() {
		/* Use an appropriate Look and Feel */
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);
		// Schedule a job for the event-dispatching thread:
		// adding TrayIcon.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private void createAndShowGUI() {
		// Check the SystemTray support
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon(createImage("bulb.gif", "tray icon"));
		final SystemTray tray = SystemTray.getSystemTray();
		// Create a popup menu components
		MenuItem aboutItem = new MenuItem("About");
		MenuItem exitItem = new MenuItem("Exit");
		// Add components to popup menu
		popup.add(aboutItem);
		popup.add(exitItem);
		trayIcon.setPopupMenu(popup);
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}
		trayIcon.addActionListener(new TaskbarController());
		aboutItem.addActionListener(new TaskbarController());
		exitItem.addActionListener(new TaskbarController());
	}

	// Obtain the image URL
	protected Image createImage(String path, String description) {
		URL imageURL = ClassLoader.getSystemResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}
}
