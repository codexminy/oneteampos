package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.Icon;

public class ChkBoxIcon implements Icon {

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		AbstractButton ab = (AbstractButton)c;
	    ButtonModel bm = ab.getModel();
	    
	    Color color = bm.isSelected() ?  Color.DARK_GRAY : Color.LIGHT_GRAY;
	    
	    g.setColor(color);
	    g.fillRect(1, 1, 50, 50);
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
