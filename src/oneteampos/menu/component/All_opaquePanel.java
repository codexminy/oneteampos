package oneteampos.menu.component;

import java.awt.LayoutManager;

import javax.swing.JPanel;

public class All_opaquePanel extends JPanel {
	
	public All_opaquePanel() {
		setOpaque(false);
	}
	
	public All_opaquePanel(LayoutManager mgr) {
		setLayout(mgr);
		setOpaque(false);
	}
}
