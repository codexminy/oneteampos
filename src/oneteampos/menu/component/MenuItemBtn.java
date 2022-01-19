package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import oneteampos.menu.action.MenuDetailAction;
import oneteampos.menu.action.MenuItemRolloverAction;

public class MenuItemBtn extends JButton {
	
	public MenuItemBtn(JDialog menuDetailsPanel, JLabel menuName, JLabel menuPrice) {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new MenuItemRolloverAction(menuName, menuPrice));
		addActionListener(new MenuDetailAction(menuDetailsPanel));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		
		if(getModel().isSelected()) {
			g2d.setColor(Color.DARK_GRAY);
		} else if(getModel().isRollover()) {
			g2d.setColor(Color.DARK_GRAY);
		}
		
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	}
}
