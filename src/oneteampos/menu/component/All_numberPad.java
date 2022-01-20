package oneteampos.menu.component;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class All_numberPad extends JPanel {

	public All_numberPad(JTextField textField) {
		setLayout(new GridLayout(4,3));
		String[] num = new String[] {"7", "8", "9", "4", "5", "6", "1", "2", "3", "clear", "0", "<"};
		
		for(int i=0; i<12; ++i) {
			JButton btn = new JButton(num[i]);
			add(btn);
			
			btn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String text = textField.getText();
					
					if(btn.getText().equals("clear")) {
						textField.setText("");
					} else if(text.length() != 0 && btn.getText().equals("<")) {
						textField.setText(text.substring(0, text.length()-1));
					} else if(!btn.getText().equals("<")) {
						textField.setText(text + btn.getText());
					}
				}
			});
		}
	}
	
}
