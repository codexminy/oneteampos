package oneteampos.main.mainComponent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import oneteampos.main.MainFrame;
import oneteampos.main.mainAction.MainButtonEnterAction;
import oneteampos.main.mainAction.MainButtonMouseAction;


public class MainButton extends JButton {
	
	public MainButton(String text, MainFrame mainFrame) {
		setText(text);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		setFocusable(false);
		addActionListener(new MainButtonEnterAction(mainFrame));
		addMouseListener(new MainButtonMouseAction());
	}
}
