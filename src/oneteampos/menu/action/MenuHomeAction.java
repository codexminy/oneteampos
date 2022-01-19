package oneteampos.menu.action;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.main.MainFrame;

public class MenuHomeAction extends MouseAdapter {

	private MainFrame mainFrame;
	
	public MenuHomeAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		CardLayout card = (CardLayout)mainFrame.getMainPanel().getCardPanel().getLayout();
		card.first(mainFrame.getMainPanel().getCardPanel());
	}

}
