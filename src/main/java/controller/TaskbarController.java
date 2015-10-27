package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class TaskbarController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand()==null) {
			JOptionPane.showMessageDialog(null, "This dialog box is run when double clicking the tray icon");
			return;
		}
		switch(arg0.getActionCommand()) {
		case "About": 
			JOptionPane.showMessageDialog(null, "This dialog box is run from the About menu item");
			break;
		case "Exit":
			System.exit(0);
			break;
		default:
			break;
		}
	}

}
