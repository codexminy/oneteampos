package oneteampos.sales.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class DashJLabel extends JLabel{
	
	public DashJLabel() {
		super("~");
		setBounds(420, 20, 50, 50);
		setBackground(Color.WHITE);
		setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	}
}
