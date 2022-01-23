package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.container.Cart_holdDialog;
import oneteampos.menu.container.Cart_holdPanel;

public class Cart_holdListBtnAction extends MouseAdapter {

	private Cart_holdPanel holdPanel;
	private Cart_holdDialog holdDialog;
	
	public Cart_holdListBtnAction(Cart_holdPanel holdPanel, Cart_holdDialog holdDialog) {
		this.holdPanel = holdPanel;
		this.holdDialog = holdDialog;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		holdPanel.getHoldDialog().setVisible(true);
	}
	
}
