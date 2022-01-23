package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.container.Payment_dialog;

public class Payment_cashProcessAction extends MouseAdapter {

	private Payment_dialog pd;
	private MenuRightPanel mrp;
	
	public Payment_cashProcessAction(Payment_dialog pd, MenuRightPanel mrp) {
		this.pd = pd;
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(pd.getCashGo().isEnabled()) {
			new Payment_executeAction(pd, mrp, "cash");
			JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
			pd.dispose();
		}
	}
	
}
