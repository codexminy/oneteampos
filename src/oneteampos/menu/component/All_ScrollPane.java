package oneteampos.menu.component;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class All_ScrollPane extends JScrollPane {

	public All_ScrollPane(JTable table) {
		super(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		settings();
	}
	
	public All_ScrollPane(JTable table, String vertical) {
		super(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		settings();
	}
	
	private void settings() {
		getViewport().setBackground(Color.WHITE);
	}
}
