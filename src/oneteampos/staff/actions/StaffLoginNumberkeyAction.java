package oneteampos.staff.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Staff;
import oneteampos.main.MainFrame;
import oneteampos.main.mainContainer.MainPanel;
import oneteampos.staff.containers.LoginStaffJPanel;
import oneteampos.staff.containers.StaffJPanel;

public class StaffLoginNumberkeyAction implements MouseListener {
	
	private static String password;
	
	static {
		String sql = "SELECT * FROM staff WHERE job_id = 1";
		try(
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		){
			while(rs.next()) {
				password = String.format("%04d", rs.getInt("stf_id"+""));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	MainFrame mainframe;
	JPanel loginPanel;
	JLabel[] inputlabel;
	JButton keyboard;
	JLabel cntLabel;
	JLabel loginFailLabel;
	
	public StaffLoginNumberkeyAction(MainFrame mainframe,JPanel loginPanel, JLabel[] inputlabel, JButton keyboard, JLabel cntLabel, JLabel loginFailLabel) {
		this.mainframe = mainframe;
		this.loginPanel = loginPanel;
		this.inputlabel = inputlabel;
		this.keyboard = keyboard;
		this.cntLabel = cntLabel;
		this.loginFailLabel = loginFailLabel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int cnt = Integer.parseInt(cntLabel.getText());

		// 초기화
		if (cnt == 4) {

			cnt = 0;
			for (int i = 0; i < inputlabel.length; ++i) {
				inputlabel[i].setText("○");
			}
		}

		String keyStr = keyboard.getText();

		inputlabel[cnt].setText(keyStr);

		cntLabel.setText(++cnt + "");

		String inputpwd = "";
		boolean chkpwd = false;
		if (cnt == 4) {
			for (int i = 0; i < inputlabel.length; ++i) {
				inputpwd += inputlabel[i].getText();
			}

			for (int i = 0; i < password.length(); ++i) {
				if (password.equals(inputpwd)) {
					
					// StaffJPanel로 넘어가기
					loginPanel.setVisible(false);
					new StaffJPanel(mainframe);
					return;
				}
			}

			if (!chkpwd) {
				loginFailLabel.setVisible(true);
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
