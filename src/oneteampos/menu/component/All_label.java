package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

public class All_label extends JLabel {
	
	public All_label() {
		setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 14));
		setForeground(Color.DARK_GRAY);
	}
	
	public All_label(String text) {
		settings(text);
	}

	public All_label(String text, String name) {
		setName(name);
		settings(text);
	}
	
	public All_label(String text, int horizontalAlignment) {
		settings(text);
		setHorizontalAlignment(horizontalAlignment);
	}
	
	public All_label(String text, int horizontalAlignment, int verticalAlignment) {
		settings(text);
		setHorizontalAlignment(horizontalAlignment);
		setVerticalAlignment(verticalAlignment);
	}
	
	private void settings(String text) {
		setText(text);
		setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 14));
		setForeground(Color.DARK_GRAY);
	}
	
}
