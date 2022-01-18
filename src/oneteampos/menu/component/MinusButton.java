package oneteampos.menu.component;

import javax.swing.JButton;

import oneteampos.menu.action.MinusAction;
import oneteampos.menu.container.MenuDetailsPanel;

public class MinusButton extends JButton {
	
	public MinusButton(MenuDetailsPanel details, String name) {
		super("◀");
		setName(name);
		addActionListener(new MinusAction(details));
	}
	
}
