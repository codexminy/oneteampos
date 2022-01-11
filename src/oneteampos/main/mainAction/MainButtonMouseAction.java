package oneteampos.main.mainAction;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MainButtonMouseAction extends MouseAdapter {
	
	JButton btn;
	
	@Override
	public void mouseEntered(MouseEvent e) {
		btn = (JButton)e.getSource();
		btn.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		btn = (JButton)e.getSource();
		btn.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
	}
}
