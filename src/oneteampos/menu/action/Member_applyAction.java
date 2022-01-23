package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.etc.ChangeString;

public class Member_applyAction extends MouseAdapter {

	private MainFrame mainFrame;
	private Member_inquiryDialog mcd;
	
	public Member_applyAction(MainFrame mainFrame, Member_inquiryDialog mcd) {
		this.mainFrame = mainFrame;
		this.mcd = mcd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JCheckBox dcBox = mcd.getDcBox();
		JCheckBox svBox = mcd.getSvBox();
		
		if(!dcBox.isSelected() && !svBox.isSelected()) {
			int check = JOptionPane.showOptionDialog(null, "할인&적립을 하지 않겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			
			if(check == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "할인&적립이 취소되었습니다.");
				mcd.dispose();
			}
		} else {
			int choice = JOptionPane.showConfirmDialog(null, "적용하겠습니까?", "Message", JOptionPane.YES_NO_OPTION);
			
			if(choice == JOptionPane.YES_OPTION) {
				int totalPrice = ChangeString.setErase(rp.getTotalPrice().getText());
				int discnt = mcd.getDiscnt();
				JLabel discountCash = mcd.getDiscountCash();
				JLabel saveCash = mcd.getSaveCash();
				
				if(totalPrice >= discnt) {
					mcd.setPoint(0);
				} else {
					mcd.setPoint(discnt - totalPrice);
				}
				
				int dis = ChangeString.setErase(discountCash.getText());
				
				if(dcBox.isSelected() && svBox.isSelected()) {
					mcd.setPoint(discnt - ChangeString.setErase(discountCash.getText()) + ChangeString.setErase(saveCash.getText()));
					setText(rp, dis, totalPrice);
				} else if(dcBox.isSelected()) {
					mcd.setPoint(discnt - ChangeString.setErase(discountCash.getText()));
					setText(rp, dis, totalPrice);
				} else if(svBox.isSelected()) {
					mcd.setPoint(discnt - ChangeString.setErase(saveCash.getText()));
				}
				
				rp.getDiscountCash().setVisible(true);
				rp.getCancelBtn().setVisible(true);
				rp.setIsDisCnt(true);
				JOptionPane.showMessageDialog(null, "적용되었습니다.");
				mcd.dispose();
			}
		}
	}
	
	private void setText(MenuRightPanel rp, int dis, int totalPrice) {
		rp.getDiscountCash().setText(ChangeString.setCashMark(dis));
		rp.getTotalPrice().setText(ChangeString.setCashMark(totalPrice-dis));
	}
}
