package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class All_boxPanel extends JPanel {

	public All_boxPanel(float align) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		setAlignmentX(align);
	}
	
	public All_boxPanel(String vertical, float align) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setAlignmentX(align);
	}
	
	public void setEmptyBorder(int top, int left, int bottom, int right) {
		setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
	}
	
	public void setPnSize(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}
	
}
