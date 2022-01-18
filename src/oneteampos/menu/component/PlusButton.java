package oneteampos.menu.component;

import javax.swing.JButton;

import oneteampos.menu.action.PlusAction;
import oneteampos.menu.container.MenuDetailsPanel;

public class PlusButton extends JButton {

	public PlusButton(MenuDetailsPanel details, String name) {
		super("▶");
		setName(name);
		addActionListener(new PlusAction(details));
	}
	
}
