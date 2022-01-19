package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class MenuEmptyBtn extends JButton {

	public MenuEmptyBtn() {
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
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
