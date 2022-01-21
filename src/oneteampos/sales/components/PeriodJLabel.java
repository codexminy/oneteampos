package oneteampos.sales.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class PeriodJLabel extends JLabel{
	
	public PeriodJLabel() {
		super("기간 설정 : ");
		setBounds(130, 20, 150, 50);
		setBackground(Color.WHITE);
		setFont(new Font(Font.DIALOG, Font.BOLD, 20));
	}
}
