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
import oneteampos.menu.etc.ChangeStr;

public class Member_svBoxAction implements ActionListener {

	private MainFrame mainFrame;
	private Member_inquiryDialog mcd;
	
	public Member_svBoxAction(MainFrame mainFrame, Member_inquiryDialog mcd) {
		this.mainFrame = mainFrame;
		this.mcd = mcd;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuRightPanel mrp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable table = mcd.getTable();
		JCheckBox svBox = mcd.getSvBox();
		JLabel saveCash = mcd.getSaveCash();
		
		int row = table.getSelectedRow();

		if(row == -1 && svBox.isSelected()) {
			JOptionPane.showMessageDialog(null, "회원을 선택해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
			svBox.setSelected(false);
		} else if (svBox.isSelected()) {
			mcd.setDiscnt((int)table.getValueAt(row, 5));

//			int total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2));
			int total = Integer.parseInt(ChangeStr.setErase(mrp.getTotalPrice().getText()));
			double save = (double)table.getValueAt(row, 7);
			
			saveCash.setVisible(true);
			saveCash.setText((int)(total*(save/100))+"");
		} else {
			saveCash.setVisible(false);
		}
	}
	
}
