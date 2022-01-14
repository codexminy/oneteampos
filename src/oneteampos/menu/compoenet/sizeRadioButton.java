package oneteampos.menu.compoenet;

import javax.swing.JRadioButton;

import oneteampos.menu.action.sizeRadioAction;
import oneteampos.menu.container.MenuDetailsPanel;

public class sizeRadioButton extends JRadioButton {
	
	public sizeRadioButton(MenuDetailsPanel details, String text, String name) {
		super(text);
		setName(name);
		addActionListener(new sizeRadioAction(details));
	}
	
}
