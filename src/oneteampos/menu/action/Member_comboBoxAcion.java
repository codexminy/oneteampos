package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import oneteampos.menu.container.Member_inquiryDialog;

public class Member_comboBoxAcion implements ActionListener {
	
	Member_inquiryDialog mcd;
	
	public Member_comboBoxAcion(Member_inquiryDialog mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> combo = (JComboBox<String>)e.getSource();
		mcd.setItem((String)combo.getSelectedItem());
	}
}
