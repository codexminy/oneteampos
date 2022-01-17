package oneteampos.staff.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.StaffJoinJobs;
import oneteampos.staff.containers.StaffInfoJFrame;

public class StaffInfoUpdateBtn implements MouseListener {
	
	StaffInfoJFrame staffInfoFrame;
	Integer stf_id;
	
	public StaffInfoUpdateBtn(StaffInfoJFrame staffInfoFrame, Integer stf_id) {
		this.staffInfoFrame = staffInfoFrame;
		this.stf_id = stf_id;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ArrayList<StaffJoinJobs> stafflist = staffInfoFrame.getStaffList();
		
				
	
	// 1. 직책이 맞나 확인 
	Integer job_id = 0;
	String sql = "SELECT job_id FROM staff_jobs WHERE job_name = '"+ stafflist.get(0).getJobName() +"' ";

	try (Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();) {
		while (rs.next()) {
			job_id = rs.getInt("job_id");
		}
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
	int rows = 0;
	
	if(job_id != 0) {
		
		// 2. 직책이 맞다면 사원 수정사원 정보 수정
		String sql2 = "UPDATE staff SET stf_name = ?, job_id = ?, hire_date = TO_DATE(?, 'YY/MM/DD' ), salary = ? , tel = ?, address = ? WHERE stf_id =" + stf_id;
		 

		try (
				Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql2);
		) {
			pstmt.setString(1, stafflist.get(0).getStaffName());
			pstmt.setInt(2, job_id);
			pstmt.setString(3,stafflist.get(0).getHireDate().substring(0, 10));
			pstmt.setInt(4, stafflist.get(0).getSalary());
			pstmt.setString(5, stafflist.get(0).getTel());
			pstmt.setString(6, stafflist.get(0).getAddress());
			
			rows = pstmt.executeUpdate();
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	if(rows > 0) {
		// 사원 테이블 수정된 내용 반영
		JTable staffTabel = staffInfoFrame.getStaffInfoJPanel().getStaffInfoTable();

		staffTabel.setValueAt(staffTabel.getSelectedRow() + 1 , staffTabel.getSelectedRow(), 0);
		staffTabel.setValueAt(stafflist.get(0).getStaffName(), staffTabel.getSelectedRow(), 1);
		staffTabel.setValueAt( stafflist.get(0).getJobName(), staffTabel.getSelectedRow(), 2);
		staffTabel.setValueAt(stafflist.get(0).getHireDate(), staffTabel.getSelectedRow(), 3);
		staffTabel.setValueAt(stafflist.get(0).getSalary(), staffTabel.getSelectedRow(), 4);
		staffTabel.setValueAt(stafflist.get(0).getTel(), staffTabel.getSelectedRow(), 5);
		staffTabel.setValueAt(stafflist.get(0).getAddress(), staffTabel.getSelectedRow(), 6);
		
		staffInfoFrame.dispose();

		//성공 팝업
		JOptionPane.showMessageDialog(staffInfoFrame, "사원 수정이 완료되었습니다.");
	} else {
		//실패 팝업
		JOptionPane.showMessageDialog(staffInfoFrame, "사원 수정에 실패하였습니다.");
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
