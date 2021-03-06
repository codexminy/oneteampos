package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.etc.ChangeString;

public class Cart_discountSaveCancelAction extends MouseAdapter {

	MenuRightPanel mrp;
	
	public Cart_discountSaveCancelAction(MenuRightPanel mrp) {
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int choice = JOptionPane.showOptionDialog(null, "할인&적립을 취소하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(choice == JOptionPane.YES_OPTION) {
			JLabel totalPrice = mrp.getTotalPrice();
			JLabel discountCash = mrp.getDiscountCash();
			Member_inquiryDialog mid = mrp.getMemberInquiryDialog();
			
			mid.setPoint(0);
			totalPrice.setText(ChangeString.setCashMark(ChangeString.setErase(totalPrice.getText()) + ChangeString.setErase(discountCash.getText())));
			mid.getSaveCash().setText("");
			
			discountCash.setText("0");
			discountCash.setVisible(false);
			
			mrp.getCancelBtn().setVisible(false);
			JOptionPane.showMessageDialog(null, "할인&적립이 취소되었습니다.");
			mrp.setIsDisCnt(false);
		}
	}
	
}
