package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.order.cotainer.OrderJPanel;

public class OrderBtnListener implements MouseListener{
	
	OrderJPanel orderPanel;
	
	public OrderBtnListener(OrderJPanel orderPanel) {
		this.orderPanel = orderPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*메뉴 발주 버튼 눌렀을 때 리스너*/
		orderPanel.getOrderListBtn().setFocus();
		orderPanel.getStockListBtn().setClearFocus();
		// 품목 판넬 가리기
		orderPanel.getStockListJPanel().setVisibleFalse();
		// 발주 판넬 보이기
		orderPanel.getOrderListJPanel().setVisibleTrue();
		
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
