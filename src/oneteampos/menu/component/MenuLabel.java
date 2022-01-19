package oneteampos.menu.component;

import java.awt.Font;

import javax.swing.JLabel;

public class MenuLabel extends JLabel {
	
	public MenuLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		setFont(new Font(Font.DIALOG, Font.BOLD, 13));
	}
}
