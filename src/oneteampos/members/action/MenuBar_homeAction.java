package oneteampos.members.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.main.MainFrame;

public class MenuBar_homeAction implements ActionListener {

	private MainFrame mainFrame;
	
	public MenuBar_homeAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout card = (CardLayout)mainFrame.getMainPanel().getCardPanel().getLayout();
		card.first(mainFrame.getMainPanel().getCardPanel());
	}

}
