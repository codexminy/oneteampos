package oneteampos.login.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.MainFrame;

public class LoginStaffIDJLabel extends JLabel {
	MainFrame mainframe;
	
	public LoginStaffIDJLabel(MainFrame mainframe ) {
	
		this.mainframe = mainframe;
		
		setBounds(30, 10, 50, 40);
		setBackground(Color.WHITE);
		setFont(new Font("고딕", Font.PLAIN, 20));
		setVisible(false);
		
		mainframe.add(this);
	}

}
