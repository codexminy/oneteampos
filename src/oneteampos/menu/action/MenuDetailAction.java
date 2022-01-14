package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class MenuDetailAction implements ActionListener {

	JDialog menuDetailsPanel;
	
	public MenuDetailAction(JDialog menuDetailsPanel) {
		this.menuDetailsPanel = menuDetailsPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		menuDetailsPanel.setVisible(true);
	}
	
}
