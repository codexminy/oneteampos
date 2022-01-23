package oneteampos.main.mainComponent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import oneteampos.main.MainFrame;
import oneteampos.main.mainAction.MainButtonEnterAction;
import oneteampos.main.mainAction.MainButtonMouseAction;


public class MainButton extends JButton {
	
	public MainButton(String text, MainFrame mainFrame) {
		setText(text);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusable(false);
		addActionListener(new MainButtonEnterAction(mainFrame));
		addMouseListener(new MainButtonMouseAction());
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));
		g2d.setColor(new Color(135, 136, 138));
		
		if(getModel().isRollover()) {
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
