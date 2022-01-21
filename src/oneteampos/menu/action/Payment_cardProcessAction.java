package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.container.Payment_dialog;

public class Payment_cardProcessAction extends MouseAdapter {

	private Payment_dialog pd;
	private MenuRightPanel mrp;
	
	public Payment_cardProcessAction(Payment_dialog pd, MenuRightPanel mrp) {
		this.pd = pd;
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new Payment_executeAction(pd, mrp, "card");
	}
}
