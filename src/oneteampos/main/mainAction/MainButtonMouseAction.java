package oneteampos.main.mainAction;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MainButtonMouseAction extends MouseAdapter {
	
	JButton btn;
	
	@Override
	public void mouseEntered(MouseEvent e) {
		btn = (JButton)e.getSource();
		btn.setForeground(Color.WHITE);
		btn.setBackground(Color.GRAY);
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		btn = (JButton)e.getSource();
		btn.setForeground(Color.BLACK);
		btn.setBackground(Color.WHITE);
	}
}
