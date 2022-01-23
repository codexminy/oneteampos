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
import oneteampos.sales.containers.RoundedButton;

public class StockListBtn extends RoundedButton{
	
	OrderJPanel orderPanel;
	
	public StockListBtn(OrderJPanel orderPanel) {
		super("재고");
		this.orderPanel = orderPanel;
		setBounds(30, 65, 300, 30);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new StockBtnListener(orderPanel));

	}

}
