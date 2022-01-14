package oneteampos.menu.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import oneteampos.main.MainFrame;

public class MenuWindowAction extends WindowAdapter {
	
	MainFrame mainFrame;
	
	public MenuWindowAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		mainFrame.setEnabled(false);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		mainFrame.setEnabled(true);
	}
	
}
