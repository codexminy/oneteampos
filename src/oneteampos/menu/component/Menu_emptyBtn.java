package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class Menu_emptyBtn extends Menu_itemBtn {

	public Menu_emptyBtn() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
}
