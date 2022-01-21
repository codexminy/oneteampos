package oneteampos.menu.component;

import javax.swing.JButton;

import oneteampos.menu.action.MenuDetail_minusAction;
import oneteampos.menu.container.MenuDetail_dialog;

public class MenuDetail_minusBtn extends All_btn {
	
	public MenuDetail_minusBtn(MenuDetail_dialog details, String name) {
		super("◀");
		setName(name);
		addActionListener(new MenuDetail_minusAction(details));
	}
	
}
