package oneteampos.menu.component;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class All_comboBox extends JComboBox<Object> {

	public All_comboBox(Object[] items) {
		super(items);
		settings();
	}
	
	public All_comboBox(Vector<Object> items) {
		super(items);
		settings();
	}
	
	private void settings() {
		setUI(All_arrowUI.createUI(this));
		setBorder(BorderFactory.createLineBorder(new Color(135, 136, 138)));
	}
}
