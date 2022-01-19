package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.component.MemberCheckDialog;
import oneteampos.menu.container.MenuRightPanel;

public class MemberSelectAction extends MouseAdapter {

	private MainFrame mainFrame;
	private MemberCheckDialog mcd;
	
	public MemberSelectAction(MainFrame mainFrame, MemberCheckDialog mcd) {
		this.mainFrame = mainFrame;
		this.mcd = mcd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuRightPanel mrp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable table = mcd.getTable();
		JLabel membershipCash = mcd.getMembershipCash();
		JLabel saveCash = mcd.getSaveCash();
		JLabel discountCash = mcd.getDiscountCash();
		
		int row = table.getSelectedRow();
		
		membershipCash.setVisible(true);
		membershipCash.setText(table.getValueAt(row, 2)+"");
		
		int total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2));
		double save = (double)table.getValueAt(row, 7);
		int discnt = (int)table.getValueAt(row, 5);
		
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
