package oneteampos.order.compnents;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

import oneteampos.order.actions.AddOrderBtnListener;
import oneteampos.order.cotainer.AddItemJPanel;
import oneteampos.order.cotainer.OrderListJPanel;

public class AddOrderBtn extends JButton{
	
	OrderListJPanel orderListPanel;
	AddItemJPanel addItemPanel;
	
	public AddOrderBtn(OrderListJPanel orderListPanel) {
		super("추가");
		this.orderListPanel = orderListPanel;
		
		setBounds(190, 120, 70, 40);
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		
		// 추가 리스너
		addMouseListener(new AddOrderBtnListener(orderListPanel));
	}
}
