package oneteampos.menu.component;

import java.awt.Cursor;
import java.awt.Dimension;

public class Cart_btn extends All_btn {
	
	public Cart_btn() {
		
	}
	
	public Cart_btn(String text) {
		setText(text);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setPreferredSize(new Dimension(90, 30));
	}
}
