package oneteampos.staff.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import oneteampos.datamodel.StaffJoinJobs;
import oneteampos.staff.containers.StaffInfoJPanel;

public class StoreStaffListener implements MouseListener {
	
	StaffInfoJPanel staffInfoPanel;
	
	public StoreStaffListener(StaffInfoJPanel staffInfoPanel) {
		this.staffInfoPanel = staffInfoPanel;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable table = (JTable)e.getSource();
		int row = table.getSelectedRow();
		 StaffJoinJobs staffJoinJobs = staffInfoPanel.getStaffJoinJobs();
		 staffJoinJobs.setStf_name(table.getValueAt(row, 1).toString());
		 staffJoinJobs.setJob_name(table.getValueAt(row, 2).toString());
		 staffJoinJobs.setHire_date(table.getValueAt(row, 3).toString());
		 staffJoinJobs.setSalary(table.getValueAt(row, 4).toString());
		 staffJoinJobs.setTel(table.getValueAt(row, 5).toString());
		 staffJoinJobs.setAddress(table.getValueAt(row, 6).toString());

		 
		
		
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
