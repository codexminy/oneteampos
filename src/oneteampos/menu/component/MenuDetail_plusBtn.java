package oneteampos.menu.component;

import javax.swing.JButton;

import oneteampos.menu.action.MenuDetail_plusAction;
import oneteampos.menu.container.MenuDetail_dialog;

public class MenuDetail_plusBtn extends JButton {

	public MenuDetail_plusBtn(MenuDetail_dialog details, String name) {
		super("▶");
		setName(name);
		addActionListener(new MenuDetail_plusAction(details));
	}
	
}
