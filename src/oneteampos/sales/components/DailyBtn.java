package oneteampos.sales.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.order.actions.StockBtnListener;
import oneteampos.sales.actions.DailyBtnListener;
import oneteampos.sales.containers.RoundedButton;
import oneteampos.sales.containers.SalesPanel;

public class DailyBtn extends RoundedButton{

	SalesPanel salesPanel;
	
	public DailyBtn(MainFrame mainframe, SalesPanel salesPanel) {
		super("일매출");
		this.salesPanel = salesPanel;
		setBounds(30, 65, 300, 30);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorderPainted(false);
		
		addMouseListener(new DailyBtnListener(mainframe, salesPanel));
	}
	

	
}
