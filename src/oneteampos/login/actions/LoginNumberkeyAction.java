package oneteampos.login.actions;

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
import oneteampos.sales.containers.RoundedButton;


public class LoginNumberkeyAction implements MouseListener{
	private final static ArrayList<String> password = new ArrayList<>();
	
	static {
		String sql = "SELECT * FROM staff WHERE job_id <= 3";
		try(
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		){
			while(rs.next()) {
				String pwd = String.format("%04d", rs.getInt("stf_id"+""));
				password.add(pwd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	MainFrame mainframe;
	JPanel loginPanel;
	JLabel[] inputlabel;
	RoundedButton keyboard;
	JLabel cntLabel;
	JLabel loginFailLabel;
	
	public LoginNumberkeyAction(MainFrame mainframe,JPanel loginPanel, JLabel[] inputlabel, RoundedButton keyboard, JLabel cntLabel, JLabel loginFailLabel) {
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

			for (int i = 0; i < password.size(); ++i) {
				if (password.get(i).equals(inputpwd)) {
					
					ArrayList<Staff> staff = new ArrayList<>();
					String sql = "SELECT * FROM staff WHERE stf_id = "+inputpwd;
					
					try(
						Connection conn = DBConnector.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery();
					){
						while(rs.next()) {
							staff.add(new Staff(rs));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					// 4자리수 채우기
					String id = staff.get(0).getStfId() + "";
				
					StringBuilder sb = new StringBuilder();
				    while (sb.length() < 4 - id.length()) {
				        sb.append('0');
				    }
				    sb.append(id);
				    id = sb.toString();
					
					// 메인판넬로 넘어가기
					loginPanel.setVisible(false);
					MainPanel mainPanel = mainframe.getMainPanel();
					mainPanel.setVisibleTrue();
					mainframe.add(mainPanel.getCardPanel());
					mainframe.getLoginStaffIDJLabel().setText(id);
					mainframe.getStaffNameJLabel().setText(staff.get(0).getStfName());
					mainPanel.getMenuPanel().getLeftPanel().getInfoIdLabel().setText(id);
					mainPanel.getMenuPanel().getLeftPanel().getInfoNameLabel().setText("  " + staff.get(0).getStfName());
					mainPanel.getMember().getLoginInfo().setText(id + " " + staff.get(0).getStfName());
					System.out.println("로그인 성공!");
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
