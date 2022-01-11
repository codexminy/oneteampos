package oneteampos.main.mainComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

import oneteampos.main.MainFrame;
import oneteampos.main.mainAction.MainButtonMouseAction;


public class MainButton extends JButton {
	
	private MainFrame mainFrame;
	
	public MainButton(String text, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setText(text);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.LIGHT_GRAY));
		setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 20));
		setFocusable(false);
//		addActionListener(new MainButtonEnterAction(mainFrame));
		addMouseListener(new MainButtonMouseAction());
	}
}
