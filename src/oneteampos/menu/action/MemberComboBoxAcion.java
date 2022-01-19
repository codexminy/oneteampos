package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import oneteampos.menu.component.MemberCheckDialog;

public class MemberComboBoxAcion implements ActionListener {
	
	MemberCheckDialog mcd;
	
	public MemberComboBoxAcion(MemberCheckDialog mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> combo = (JComboBox<String>)e.getSource();
		mcd.setItem((String)combo.getSelectedItem());
	}
}
