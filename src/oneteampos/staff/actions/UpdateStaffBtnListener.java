package oneteampos.staff.actions;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import oneteampos.staff.containers.StaffInfoJFrame;
import oneteampos.staff.containers.StaffInfoJPanel;

public class UpdateStaffBtnListener implements MouseListener {
	
	StaffInfoJPanel staffInfoPanel;
	
	public UpdateStaffBtnListener(StaffInfoJPanel staffInfoPanel) {
		this.staffInfoPanel = staffInfoPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		staffInfoPanel.getAddStaffBtn().setSelected(false);
		staffInfoPanel.getDeleteStaffBtn().setSelected(false);
		
		if(staffInfoPanel.getStaffJoinJobs().getSalary() == null && staffInfoPanel.getStaffJoinJobs().getStaffName() == null) {
			JOptionPane.showMessageDialog(staffInfoPanel, "수정할 사원을 선택하세요");
		} else {
			StaffInfoJFrame staffFrame = new StaffInfoJFrame(staffInfoPanel, staffInfoPanel.getStaffJoinJobs());
			staffFrame.setVisible(true);
		}
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
		staffInfoPanel.getUpdateStaffBtn().setBackground(new Color(237, 241, 247));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		staffInfoPanel.getUpdateStaffBtn().setBackground(new Color(247, 245, 247));
	}

}
