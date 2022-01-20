package oneteampos.menu.action;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Menu_nextPageAction implements ActionListener {

	private JPanel innerCardPanel;
	
	public Menu_nextPageAction(JPanel innerCardPanel) {
		this.innerCardPanel = innerCardPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout card = (CardLayout)innerCardPanel.getLayout();
		card.next(innerCardPanel);
	}
	
}
