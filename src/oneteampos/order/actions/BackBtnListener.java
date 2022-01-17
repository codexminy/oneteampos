package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.staff.containers.LoginStaffJPanel;
import oneteampos.staff.containers.StaffJPanel;

public class BackBtnListener implements MouseListener {
	
	MainFrame mainframe;
	JPanel jpanel;
	String panelName;
	
	public BackBtnListener(MainFrame mainframe, JPanel jpanel, String panelName) {
		this.mainframe = mainframe;
		this.jpanel = jpanel;
		this.panelName = panelName;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*뒤로 가기 버튼 눌렀을 때 리스너*/
		
		// 발주 패널 감추기
		if(panelName.equals("orderJPanel")) {
			((OrderJPanel) jpanel).setVisibleFalse();
		} else if(panelName.equals("staffPanel")) {
			((StaffJPanel)jpanel).setVisibleFalse();
		} else if (panelName.contains("LoginStaffPanel")) {
			((LoginStaffJPanel)jpanel).setVisibleFalse();
		}
		// 메인 패널 보이기
		mainframe.getMainPanel().setVisibleTrue();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
