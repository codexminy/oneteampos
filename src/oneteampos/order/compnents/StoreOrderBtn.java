package oneteampos.order.compnents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.order.actions.StoreOrderBtnListener;
import oneteampos.order.cotainer.CartJPanel;
import oneteampos.sales.containers.RoundedButton;

public class StoreOrderBtn extends RoundedButton{
	
	CartJPanel cartPanel;
	
	public StoreOrderBtn(CartJPanel cartPanel) {
		super("발주");
		
		this.cartPanel = cartPanel;
		
		// 추가 버튼 속성 지정
		setFocusable(false);
		setBounds(10, 520, 100, 40);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(null);
		addMouseListener(new StoreOrderBtnListener(cartPanel));
		
	}
}
