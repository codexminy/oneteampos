package oneteampos.menu.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import oneteampos.menu.container.MenuLeftPanel;

public class MenuBtnAction implements ActionListener {
	
	private MenuLeftPanel menuLeftPanel;
	
	public MenuBtnAction(MenuLeftPanel menuLeftPanel) {
		this.menuLeftPanel = menuLeftPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton btn = (JToggleButton)e.getSource();
		CardLayout card = (CardLayout)menuLeftPanel.getCardMenuPanel().getLayout();
		card.show(menuLeftPanel.getCardMenuPanel(), btn.getText());
	}

}
