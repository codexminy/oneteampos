package oneteampos.menu.component;

import javax.swing.JButton;

import oneteampos.menu.action.MenuDetail_minusAction;
import oneteampos.menu.container.MenuDetail_dialog;

public class MenuDetail_minusBtn extends JButton {
	
	public MenuDetail_minusBtn(MenuDetail_dialog details, String name) {
		super("◀");
		setName(name);
		addActionListener(new MenuDetail_minusAction(details));
	}
	
}
