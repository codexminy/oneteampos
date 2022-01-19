package oneteampos.staff.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.StaffJoinJobs;
import oneteampos.staff.containers.StaffInfoJFrame;
import oneteampos.staff.containers.StaffInfoJPanel;
import oneteampos.staff.containers.StaffJPanel;

public class InsertStaffBtn implements MouseListener {
	
	StaffInfoJFrame staffInfoFrame;
	
	public InsertStaffBtn(StaffInfoJFrame staffInfoFrame) {
		this.staffInfoFrame = staffInfoFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 리스트 받아오기
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
		
		if(job_id != 0) {
			// 2. 직책이 맞다면 사원 추가
			sql = "INSERT INTO staff VALUES ( STAFF_STF_ID_SEQ.NEXTVAL, ? , ? , ?, ?, ?, ?)";

			try (
					Connection conn = DBConnector.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			
				pstmt.setString(1, stafflist.get(0).getStaffName());
				pstmt.setInt(2, job_id);
				pstmt.setString(3, stafflist.get(0).getHireDate());
				pstmt.setInt(4, stafflist.get(0).getSalary());
				pstmt.setString(5, stafflist.get(0).getTel());
				pstmt.setString(6, stafflist.get(0).getAddress());
				int rows = pstmt.executeUpdate();
				
				
				if(rows > 0) {
					// 사원 테이블에 추가
					DefaultTableModel model = staffInfoFrame.getStaffInfoJPanel().getModel();
					JTable staffTabel = staffInfoFrame.getStaffInfoJPanel().getStaffInfoTable();
					
					String[] row = new String[7];
					row[0] = staffTabel.getRowCount() +1 + "";
					row[1] = stafflist.get(0).getStaffName();
					row[2] = stafflist.get(0).getJobName();
					row[3] = stafflist.get(0).getAddress();
					row[4] = stafflist.get(0).getSalary() + "";
					row[5] = stafflist.get(0).getTel();
					row[6] = stafflist.get(0).getAddress();
					
					model.addRow(row);
					
					staffInfoFrame.dispose();
					
					StaffJPanel staffPanel = staffInfoFrame.getStaffInfoJPanel().getStaffJpanel();
					
					staffInfoFrame.getStaffInfoJPanel().setVisible(false);
					
					staffPanel.add(new StaffInfoJPanel(staffPanel));
					
					//성공 팝업
					JOptionPane.showMessageDialog(staffInfoFrame, "사원 등록이 완료되었습니다.");
					
				} else {
					//실패 팝업
					JOptionPane.showMessageDialog(staffInfoFrame, "사원 등록에 실패하였습니다.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
