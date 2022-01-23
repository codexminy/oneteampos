package oneteampos.order.cotainer;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JMenuItem;

public class JMenuItemTemplete extends JMenuItem{
	
	
	public JMenuItemTemplete(String name) {
		setText(name);
		setFont(new Font("나눔스퀘어", Font.BOLD, 18));
		setBackground(new Color(135, 136, 138));
		setForeground(Color.WHITE);
	}
}
