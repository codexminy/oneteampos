package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.component.MemberCheckDialog;
import oneteampos.menu.container.MenuRightPanel;

public class DcBoxCheckAction implements ActionListener {

	private MainFrame mainFrame;
	private MemberCheckDialog mcd;
	
	public DcBoxCheckAction(MainFrame mainFrame, MemberCheckDialog mcd) {
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
			int total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2));
			
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
