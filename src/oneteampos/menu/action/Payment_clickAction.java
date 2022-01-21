package oneteampos.menu.action;

import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import oneteampos.menu.container.Payment_dialog;

public class Payment_clickAction extends MouseAdapter {

	private Payment_dialog pd;
	
	public Payment_clickAction(Payment_dialog pd) {
		this.pd = pd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
		CardLayout card = (CardLayout)pd.getCardPanel().getLayout();
		
		card.show(pd.getCardPanel(), btn.getText());
	}
	
}
