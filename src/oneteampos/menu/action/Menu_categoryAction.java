package oneteampos.menu.action;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import oneteampos.menu.container.MenuLeftPanel;

public class Menu_categoryAction extends MouseAdapter {
	
	private MenuLeftPanel menuLeftPanel;

	public Menu_categoryAction(MenuLeftPanel menuLeftPanel) {
		this.menuLeftPanel = menuLeftPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JRadioButton btn = (JRadioButton)e.getSource();
		CardLayout card = (CardLayout)menuLeftPanel.getCardMenuPanel().getLayout();
		card.show(menuLeftPanel.getCardMenuPanel(), btn.getText());
	}
}
