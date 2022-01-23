package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuDetail_turnOnAction;
import oneteampos.menu.action.Menu_itemRolloverAction;
import oneteampos.menu.data.MenuData;

public class Menu_itemBtn extends JButton {
	
	public Menu_itemBtn() {
		
	}
	
	public Menu_itemBtn(MainFrame mainFrame, JLabel menuName, JLabel menuPrice, ArrayList<MenuData> arrayList) {
		setContentAreaFilled(false);
		setBorderPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMouseListener(new Menu_itemRolloverAction(menuName, menuPrice));
		addActionListener(new MenuDetail_turnOnAction(mainFrame, menuName, menuPrice, arrayList));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		
		if(getModel().isRollover()) {
			g2d.setColor(new Color(44, 108, 212));
		}
		
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
		g2d.setColor(new Color(44, 108, 212));
		g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
	}
}
