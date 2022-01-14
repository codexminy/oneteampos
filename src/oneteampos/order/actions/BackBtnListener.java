package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.main.MainFrame;
import oneteampos.order.cotainer.OrderJPanel;

public class BackBtnListener implements MouseListener {
	
	MainFrame mainframe;
	OrderJPanel orderPanel;
	
	public BackBtnListener(MainFrame mainframe,OrderJPanel orderPanel ) {
		this.mainframe = mainframe;
		this.orderPanel = orderPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*뒤로 가기 버튼 눌렀을 때 리스너*/
		
		// 발주 패널 감추기
		orderPanel.setVisibleFalse();
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
