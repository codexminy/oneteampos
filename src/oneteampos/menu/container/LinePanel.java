package oneteampos.menu.container;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class LinePanel extends JPanel {

	MenuDetailsPanel menuDetailsPanel;
	
	public LinePanel(MenuDetailsPanel menuDetailsPanel) {
		this.menuDetailsPanel = menuDetailsPanel;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(0, 0, menuDetailsPanel.getWidth(), 0);
	}
	
}
