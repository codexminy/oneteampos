package oneteampos.menu.component;

import java.awt.Font;

import javax.swing.JLabel;

public class Menu_itemLabel extends JLabel {
	
	public Menu_itemLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		setFont(new Font(Font.DIALOG, Font.BOLD, 13));
	}
}
