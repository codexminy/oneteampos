package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.order.cotainer.StockListJPanel;

public class StockBtnListener implements MouseListener{
	
	OrderJPanel orderPanel;
	
	public StockBtnListener(OrderJPanel orderPanel) {
		this.orderPanel = orderPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.isMetaDown()) {
			
		} else {
			/*재고 버튼 눌렀을 때*/
			orderPanel.getOrderListBtn().setSelected(false);
			// 재고 판넬 보이기 
			orderPanel.getStockListJPanel().setVisibleTrue();
			// 나머지 판넬 가리기
			orderPanel.getOrderListJPanel().setVisibleFalse();
		}
		
		
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
