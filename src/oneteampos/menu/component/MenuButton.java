package oneteampos.menu.component;

import java.awt.Color;

import javax.swing.JToggleButton;

import oneteampos.menu.action.MenuBtnAction;
import oneteampos.menu.container.MenuLeftPanel;

public class MenuButton extends JToggleButton {
	
	public MenuButton(MenuLeftPanel menuLeftPanel, String text, Color color) {
		setText(text);
		setBackground(color);
		setBorderPainted(false);
		setFocusPainted(false);
		addActionListener(new MenuBtnAction(menuLeftPanel));
	}
}
