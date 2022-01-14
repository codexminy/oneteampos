package oneteampos.order.compnents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

import oneteampos.order.actions.BackBtnListener;
import oneteampos.order.actions.StockBtnListener;
import oneteampos.order.cotainer.OrderJPanel;

public class StockListBtn extends JButton{
	
	OrderJPanel orderPanel;
	
	public StockListBtn(OrderJPanel orderPanel) {
		super("재고");
		this.orderPanel = orderPanel;
		
		setClearFocus();
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new StockBtnListener(orderPanel));

	}
	
	public void setFocus() {
		setFont(new Font("고딕", Font.BOLD, 17));
		setBounds(30, 65, 300, 30);
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
	}
	
	public void setClearFocus() {
		setFont(new Font("고딕", Font.BOLD, 15));
		setBounds(30, 65, 300, 30);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLACK);
	}
	


}
