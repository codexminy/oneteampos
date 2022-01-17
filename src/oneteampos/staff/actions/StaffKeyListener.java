package oneteampos.staff.actions;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import oneteampos.staff.containers.StaffInfoJFrame;

public class StaffKeyListener implements KeyListener {
	
	String attribute;
	StaffInfoJFrame staffJframe;
	int idx;
	
	public StaffKeyListener(StaffInfoJFrame staffJframe, String attribute , int idx) {
		this.staffJframe = staffJframe;
		this.attribute = attribute;
		this.idx = idx;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField)e.getSource();
		attribute = txt.getText();
		if(idx == 0) {
			staffJframe.getStaffjoinjobs().setStf_name(attribute);
		} else if (idx == 1) {
			staffJframe.getStaffjoinjobs().setJob_name(attribute);
		} else if (idx == 2) {
			staffJframe.getStaffjoinjobs().setHire_date(attribute);
		} else if (idx == 3) {
			staffJframe.getStaffjoinjobs().setSalary(attribute);
		} else if (idx == 4) {
			staffJframe.getStaffjoinjobs().setTel(attribute);
		} else if (idx == 5) {
			staffJframe.getStaffjoinjobs().setAddress(attribute);
		}
	
		 
	}

}
