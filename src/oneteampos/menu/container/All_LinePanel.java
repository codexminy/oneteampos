package oneteampos.menu.container;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class All_LinePanel extends JPanel {

	private int width;
	
	public All_LinePanel(int width) {
		this.width = width;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(0, 0, width, 0);
	}
	
}
