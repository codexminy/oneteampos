package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;

public class Member_turnOnAction extends MouseAdapter {

	private MainFrame mainFrame;
	private MenuRightPanel mrp;
	
	public Member_turnOnAction(MainFrame mainFrame, MenuRightPanel mrp) {
		this.mainFrame = mainFrame;
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mrp.getCart().getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
		} else if(mrp.getIsDiscnt()) { 
			JOptionPane.showMessageDialog(null, "할인&적립을 취소해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			mrp.setMemberInquiryDialog(new Member_inquiryDialog(mainFrame));
			mrp.getMemberInquiryDialog().setVisible(true);
		}
	}
	
}
