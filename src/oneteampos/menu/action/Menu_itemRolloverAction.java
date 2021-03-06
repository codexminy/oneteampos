package oneteampos.menu.action;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Menu_itemRolloverAction extends MouseAdapter {

	private JLabel menuName;
	private JLabel menuPrice;
	
	public Menu_itemRolloverAction(JLabel menuName, JLabel menuPrice) {
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		menuName.setForeground(Color.WHITE);
		menuPrice.setForeground(Color.WHITE);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		menuName.setForeground(Color.DARK_GRAY);
		menuPrice.setForeground(Color.DARK_GRAY);
	}
}
