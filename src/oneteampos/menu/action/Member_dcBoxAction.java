package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.etc.ChangeString;

public class Member_dcBoxAction implements ActionListener {

	private MainFrame mainFrame;
	private Member_inquiryDialog mcd;
	
	public Member_dcBoxAction(MainFrame mainFrame, Member_inquiryDialog mcd) {
		this.mainFrame = mainFrame;
		this.mcd = mcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuRightPanel mrp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable table = mcd.getTable();
		JCheckBox dcBox = mcd.getDcBox();
		JLabel discountCash = mcd.getDiscountCash();
		
		int row = table.getSelectedRow();

		if(row == -1 && dcBox.isSelected()) {
			JOptionPane.showMessageDialog(null, "회원을 선택해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
			dcBox.setSelected(false);
		} else if(dcBox.isSelected()) {
			int total = Integer.parseInt(ChangeString.setErase(mrp.getTotalPrice().getText()));
			
			mcd.setDiscnt((int)table.getValueAt(row, 5));
			int discnt = mcd.getDiscnt();
			
			if(total >= discnt) {
				discountCash.setText(discnt+"");
			} else {
				discountCash.setText(total+"");
			}
			discountCash.setVisible(true);
		} else {
			discountCash.setVisible(false);
		}
	}

}
