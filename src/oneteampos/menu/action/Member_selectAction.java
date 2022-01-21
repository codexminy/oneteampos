package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.etc.ChangeString;

public class Member_selectAction extends MouseAdapter {

	private MainFrame mainFrame;
	private Member_inquiryDialog mcd;
	
	public Member_selectAction(MainFrame mainFrame, Member_inquiryDialog mcd) {
		this.mainFrame = mainFrame;
		this.mcd = mcd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuRightPanel mrp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable table = mcd.getTable();
		JLabel membershipCash = mcd.getMembershipNum();
		JLabel saveCash = mcd.getSaveCash();
		JLabel discountCash = mcd.getDiscountCash();
		
		int row = table.getSelectedRow();
		
		membershipCash.setVisible(true);
		membershipCash.setText(table.getValueAt(row, 2)+"");
		
		int total = Integer.parseInt(ChangeString.setErase(mrp.getTotalPrice().getText()));
		double save = (double) table.getValueAt(row, 7);
		int discnt = (int) table.getValueAt(row, 5);
		
		if(mcd.getSvBox().isSelected()) {
			saveCash.setVisible(true);
			saveCash.setText((int)(total*(save/100))+"");
		} else {
			saveCash.setVisible(false);
		}
		
		if(mcd.getDcBox().isSelected()) {
			discountCash.setVisible(true);
			discountCash.setText(discnt+"");
		} else {
			discountCash.setVisible(false);
		}
		
		if(total >= discnt) {
			discountCash.setText(discnt+"");
		} else {
			discountCash.setText(total+"");
		}
	}
}