package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

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
		int total = Integer.parseInt(ChangeString.setErase(pd.totalCash.getText()));
		
		if(pd.takeCash.getText().length() != 0) {
			current = Integer.parseInt(pd.takeCash.getText());
		}

		if(pd.takeCash.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "받은 금액을 적어주세요!", "Message", JOptionPane.WARNING_MESSAGE);
		} else if(current < total) {
			JOptionPane.showMessageDialog(null, "받은 금액이 적습니다!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			pd.changeCash.setText((current - total)+"");
			pd.cashGo.setEnabled(true);
		}
	}
	
}
