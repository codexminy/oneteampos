package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Cart_label extends JLabel {

	public Cart_label() {
		setForeground(Color.WHITE);
		setFont(new Font(Font.DIALOG, Font.BOLD, 15));
	}
	
	public Cart_label(String text) {
		setText(text);
		setForeground(Color.WHITE);
		setFont(new Font(Font.DIALOG, Font.BOLD, 15));
	}

}
