package oneteampos.menu.component;

import javax.swing.JRadioButton;

import oneteampos.menu.action.MenuDetail_sizeRadioAction;
import oneteampos.menu.container.MenuDetail_dialog;

public class MenuDetail_sizeRadioBtn extends JRadioButton {
	
	public MenuDetail_sizeRadioBtn(MenuDetail_dialog details, String text, String name) {
		super(text);
		setName(name);
		addActionListener(new MenuDetail_sizeRadioAction(details));
	}
	
}
