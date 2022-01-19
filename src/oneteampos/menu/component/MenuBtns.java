package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuHomeAction;

public class MenuBtns extends JButton {

	public MenuBtns(String text) {
		setText(text);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setPreferredSize(new Dimension(90, 30));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 13));
		
		if(getModel().isSelected()) {
			g2d.setColor(new Color(44, 108, 212));
		} else if(getModel().isRollover()) {
			g2d.setColor(new Color(44, 108, 212));
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
