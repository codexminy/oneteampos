package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTable;

import oneteampos.order.cotainer.AddItemJFrame;
import oneteampos.order.cotainer.OrderListJPanel;

public class TableAddItemListener implements MouseListener{
	
	OrderListJPanel orderListPanel;
	AddItemJFrame addItemPanel;
	JTable table;
	JLabel itemNameLabel;
	JLabel itemPriceLabel;
	
	public TableAddItemListener(OrderListJPanel orderListPanel ) {
		this.orderListPanel = orderListPanel;
//		this.addItemPanel = orderListPanel.getaddItemPanel();
//		this.itemNameLabel = addItemPanel.getItemNameLabel();
//		this.itemPriceLabel = addItemPanel.getItemRealPriceLabel();
		table = orderListPanel.getItemTable();
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
		/* 품목테이블 행을 눌렀을 때 리스너 */
		int count = e.getClickCount() % 2 == 0 ? 1 : 1; 
		if(e.isMetaDown()) {
			
		} else {
			if(count == 1) {
				// 프레임열기
				if(orderListPanel.getAddItemFrame() != null) {
					orderListPanel.getAddItemFrame().dispose();
				} 
					AddItemJFrame addItemFrame = new AddItemJFrame(orderListPanel);
					orderListPanel.setAddItemFrame(addItemFrame);
					int idx = table.getSelectedRow(); // 테이블 row에서 가져온 array index 
					// 선택한 행의 이름과 가격을 라벨에 넣어주기
					String itemName = orderListPanel.getItemName(idx);
					Integer itemPrice = orderListPanel.getitemPrice(idx);
					addItemFrame.getItemNameLabel().setText(itemName);
					addItemFrame.getItemRealPriceLabel().setText(itemPrice + "");
				
			}
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
