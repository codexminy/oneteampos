package oneteampos.login.components;

import java.awt.Color;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JLabel;

import main.MainFrame;


public class LoginStaffNameJLabel extends JLabel{
	
	MainFrame mainframe;

	public LoginStaffNameJLabel(MainFrame mainframe) {
		
		this.mainframe = mainframe;
		
		setLayout(null);
		setBounds(100, 10, 80, 40);
		setBackground(Color.WHITE);
		setVisible(false);
		setFont(new Font("고딕", Font.PLAIN, 20));
		
		mainframe.add(this);
	}

}
