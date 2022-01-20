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
import oneteampos.sales.containers.SalesPanel;

public class DailyBtn extends JButton{

	SalesPanel salesPanel;
	
	public DailyBtn(MainFrame mainframe, SalesPanel salesPanel) {
		super("일매출");
		this.salesPanel = salesPanel;
		setBounds(30, 65, 300, 30);
		setClearFocus();
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new DailyBtnListener(mainframe, salesPanel));
	}
	
	public void setFocus() {
//		setFont(new Font("고딕", Font.BOLD, 17));
//		setBackground(Color.GRAY);
//		setForeground(Color.WHITE);
	}
	
	public void setClearFocus() {
//		setFont(new Font("고딕", Font.BOLD, 15));
//		setBackground(Color.LIGHT_GRAY);
//		setForeground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		if(getModel().isSelected()) {
			g2d.setColor(new Color(44, 108, 212));
			g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		} else if(getModel().isRollover()) {
			g2d.setColor(new Color(44, 108, 212));
			g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 17));
		}
		
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle sb = fm.getStringBounds(getText(), g2d).getBounds();
		
		int tx = (getWidth() - sb.width) / 2;
		int ty = (getHeight() - sb.height) / 2 + fm.getAscent();

		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
		g2d.setColor(Color.WHITE);
		g2d.drawString(getText(), tx, ty);
	}
	
}
