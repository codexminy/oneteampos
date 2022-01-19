package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CartLabel extends JLabel {

	public CartLabel(String text) {
		setText(text);
		setForeground(Color.WHITE);
		setFont(new Font(Font.DIALOG, Font.BOLD, 15));
	}

}
