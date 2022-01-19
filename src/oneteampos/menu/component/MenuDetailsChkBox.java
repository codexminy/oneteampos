package oneteampos.menu.component;

import java.util.ArrayList;

import javax.swing.JCheckBox;

import oneteampos.menu.action.MenuDetailsChkBoxAction;
import oneteampos.menu.container.MenuDetailsPanel;
import oneteampos.menu.data.MenuData;

public class MenuDetailsChkBox extends JCheckBox {

	public MenuDetailsChkBox(String text, MenuDetailsPanel menuDetailsPanel, ArrayList<MenuData> lists) {
		super(text);
		addActionListener(new MenuDetailsChkBoxAction(menuDetailsPanel, lists));
	}
	
}
