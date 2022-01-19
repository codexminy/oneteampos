package oneteampos.staff.actions;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.staff.containers.StaffInfoJPanel;
import oneteampos.staff.containers.StaffJPanel;

public class DeleteStaffBtnListener implements MouseListener {
	
	StaffInfoJPanel staffInfoPanel;
	
	public DeleteStaffBtnListener(StaffInfoJPanel staffInfoPanel) {
		this.staffInfoPanel = staffInfoPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (staffInfoPanel.getStaffJoinJobs().getSalary() == null
				&& staffInfoPanel.getStaffJoinJobs().getStaffName() == null) {
			JOptionPane.showMessageDialog(staffInfoPanel, "삭제할 사원을 선택하세요");
		} else {
			
			int result = JOptionPane.showConfirmDialog(staffInfoPanel,
					staffInfoPanel.getStaffJoinJobs().getStaffName() + " 사원을 삭제하시겠습니까? ", "사원 삭제",
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (result == 0) { // 삭제
				
				DefaultTableModel model = staffInfoPanel.getModel();
				JTable staffInfoTable = staffInfoPanel.getStaffInfoTable();
				int idx = staffInfoTable.getSelectedRow();

				// 데이터 지우기
				String sql = "DELETE FROM staff WHERE stf_name = ? AND tel = ?";

				try (Connection conn = DBConnector.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);) {
					pstmt.setString(1, staffInfoTable.getValueAt(idx, 1).toString());
					pstmt.setString(2, staffInfoTable.getValueAt(idx, 5).toString());

					int rows = pstmt.executeUpdate();

					if (rows > 0) {
						// 테이블에서 지우기
						model.removeRow(idx);
						
						StaffJPanel staffPanel = staffInfoPanel.getStaffJpanel();
						
						staffInfoPanel.setVisible(false);
						
						staffPanel.add(new StaffInfoJPanel(staffPanel));
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			} else if (result == 1) { // 삭제 X

			} else if (result == 2) { // 취소
				JOptionPane.showMessageDialog(staffInfoPanel, "사원 삭제에 실패하였습니다.");
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
		staffInfoPanel.getDeleteStaffBtn().setBackground(new Color(237, 241, 247));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		staffInfoPanel.getDeleteStaffBtn().setBackground(new Color(247, 245, 247));
	}

}
