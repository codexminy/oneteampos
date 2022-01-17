package oneteampos.staff.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.staff.components.AddStaffBtn;
import oneteampos.staff.containers.StaffInfoJFrame;
import oneteampos.staff.containers.StaffInfoJPanel;

public class AddStaffBtnListener implements MouseListener {

	StaffInfoJPanel staffInfoPanel;

	public AddStaffBtnListener(StaffInfoJPanel staffInfoPanel) {
		this.staffInfoPanel = staffInfoPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		StaffInfoJFrame staffFrame = new StaffInfoJFrame(staffInfoPanel);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		staffInfoPanel.getAddStaffBtn().setBackground(new Color(237, 241, 247));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		staffInfoPanel.getAddStaffBtn().setBackground(new Color(247, 245, 247));
	}



}
