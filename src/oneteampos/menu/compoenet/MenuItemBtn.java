package oneteampos.menu.compoenet;

import javax.swing.JButton;
import javax.swing.JDialog;

import oneteampos.menu.action.MenuDetailAction;

public class MenuItemBtn extends JButton {
	
	public MenuItemBtn(JDialog menuDetailsPanel) {
		addActionListener(new MenuDetailAction(menuDetailsPanel));
	}
}
