package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.component.MemberCheckDialog;
import oneteampos.menu.container.MenuRightPanel;

public class MemberApplyAction extends MouseAdapter {

	private MainFrame mainFrame;
	private MemberCheckDialog mcd;
	
	public MemberApplyAction(MainFrame mainFrame, MemberCheckDialog mcd) {
		this.mainFrame = mainFrame;
		this.mcd = mcd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int choice = JOptionPane.showConfirmDialog(null, "적용하겠습니까?", "Message", JOptionPane.YES_NO_OPTION);
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		
		if(choice == JOptionPane.YES_OPTION) {
			int totalPrice = Integer.parseInt(rp.getTotalPrice().getText().substring(2));
			int discnt = mcd.getDiscnt();

			JLabel discountCash = mcd.getDiscountCash();
			JLabel saveCash = mcd.getSaveCash();
			JCheckBox dcBox = mcd.getDcBox();
			JCheckBox svBox = mcd.getSvBox();

			if(totalPrice >= discnt) {
				mcd.setPoint(0);
			} else {
				mcd.setPoint(discnt - totalPrice);
			}

			int dis = Integer.parseInt(discountCash.getText());
			
			if(dcBox.isSelected() && svBox.isSelected()) {
				mcd.setPoint(discnt - Integer.parseInt(discountCash.getText()) + Integer.parseInt(saveCash.getText()));
				rp.getDiscountCash().setText(dis+"");
				rp.getTotalPrice().setText("￦ " + (totalPrice-dis));
			} else if(dcBox.isSelected()) {
				mcd.setPoint(discnt - Integer.parseInt(discountCash.getText()));
				rp.getDiscountCash().setText(dis+"");
				rp.getTotalPrice().setText("￦ " + (totalPrice-dis));
			} else if(svBox.isSelected()) {
				mcd.setPoint(discnt + Integer.parseInt(saveCash.getText()));
			}
			
			rp.getDiscountCash().setVisible(true);
			rp.getCancelBtn().setVisible(true);
			rp.setIsDisCnt(true);
			JOptionPane.showMessageDialog(null, "적용되었습니다.");
			mcd.dispose();
		}
	}
}
