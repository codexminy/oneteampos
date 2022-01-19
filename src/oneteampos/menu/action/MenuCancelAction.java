package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import oneteampos.menu.container.MenuRightPanel;

public class MenuCancelAction extends MouseAdapter {

	MenuRightPanel mrp;
	
	public MenuCancelAction(MenuRightPanel mrp) {
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int choice = JOptionPane.showOptionDialog(null, "할인&적립을 취소하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(choice == JOptionPane.YES_OPTION) {
			JLabel totalPrice = mrp.getTotalPrice();
			JLabel discountCash = mrp.getDiscountCash();
			
			mrp.getMemeberCheckDialog().setPoint(0);
			totalPrice.setText("￦ " + (Integer.parseInt(totalPrice.getText().substring(2)) + Integer.parseInt(discountCash.getText().equals("") ? "0" : discountCash.getText())));
			mrp.getMemeberCheckDialog().getSaveCash().setText("");
			
			discountCash.setText("￦ ");
			discountCash.setVisible(false);
			
			mrp.getCancelBtn().setVisible(false);
			JOptionPane.showMessageDialog(null, "할인&적립이 취소되었습니다.");
			mrp.setIsDisCnt(false);
		}
	}
	
}
