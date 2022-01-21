package oneteampos.menu.component;

import java.awt.Font;

import javax.swing.JRadioButton;

import oneteampos.menu.action.MenuDetail_sizeRadioAction;
import oneteampos.menu.container.MenuDetail_dialog;

public class MenuDetail_radioBtn extends JRadioButton {

	public MenuDetail_radioBtn(String text) {
		settins(text);
	}
	
	public MenuDetail_radioBtn(MenuDetail_dialog details, String text, String name) {
		settins(text);
		setName(name);
		addActionListener(new MenuDetail_sizeRadioAction(details));
	}
	
	private void settins(String text) {
		setText(text);
		setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 14));
		setOpaque(false);
	}
}
