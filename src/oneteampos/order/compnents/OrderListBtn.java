package oneteampos.order.compnents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.order.actions.OrderBtnListener;
import oneteampos.order.actions.StockBtnListener;
import oneteampos.order.cotainer.OrderJPanel;

public class OrderListBtn extends JButton{ // 품목 item 발주  store_order 재고 stock 발주리스트 order_list
	
	OrderJPanel orderPanel;
	
	public OrderListBtn(OrderJPanel orderPanel) {
		super("발주");
	
		this.orderPanel = orderPanel;
		setFocusable(false);
		setClearFocus();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new OrderBtnListener(orderPanel));
	}
	
	public void setFocus() {
		setFont(new Font("고딕", Font.BOLD, 17));
		setBounds(340, 65, 300, 30);
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
	}
	
	public void setClearFocus() {
		setFont(new Font("고딕", Font.BOLD, 15));
		setBounds(340, 65, 300, 30);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLACK);
	}

}
