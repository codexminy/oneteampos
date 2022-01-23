package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oneteampos.menu.container.Payment_dialog;
import oneteampos.menu.etc.ChangeString;

public class Payment_cashInputAction extends MouseAdapter {

	private Payment_dialog pd;
	
	public Payment_cashInputAction(Payment_dialog pd) {
		this.pd = pd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int current = 0;
		int total = ChangeString.setErase(pd.getTotalCash().getText());
		JTextField takeCash = pd.getTakeCash();

		if(takeCash.getText().length() != 0) {
			current = Integer.parseInt(takeCash.getText());
		}
		
		if(takeCash.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "받은 금액을 적어주세요!", "Message", JOptionPane.WARNING_MESSAGE);
		} else if(current < total) {
			JOptionPane.showMessageDialog(null, "받은 금액이 적습니다!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			pd.getChangeCash().setText(ChangeString.setCashMark(current-total));
			pd.getCashGo().setEnabled(true);
		}
	}
	
}
