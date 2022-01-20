package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;

public class Member_inquiryAction extends MouseAdapter {

	private MainFrame mainFrame;
	private MenuRightPanel mrp;
	
	public Member_inquiryAction(MainFrame mainFrame, MenuRightPanel mrp) {
		this.mainFrame = mainFrame;
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mrp.getCart().getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			mrp.setMemberCheckDialog(new Member_inquiryDialog(mainFrame));
			mrp.getMemeberCheckDialog().setVisible(true);
		}
	}
	
}
