package oneteampos.menu.component;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class All_opaquePanel extends JPanel {
	
	public All_opaquePanel() {
		setBackground(Color.WHITE);
	}
	
	public All_opaquePanel(LayoutManager mgr) {
		setLayout(mgr);
		setBackground(Color.WHITE);
	}
}
