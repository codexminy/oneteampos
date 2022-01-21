package oneteampos.menu.component;

import java.awt.Cursor;
import java.awt.Dimension;

public class Menu_btn extends All_btn {

	public Menu_btn(String text) {
		setText(text);
		setPreferredSize(new Dimension(91, 30));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
