package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import oneteampos.database.DBConnector;
import oneteampos.menu.container.MenuLeftPanel;

public class MenuInsertAction implements ActionListener {

	private MenuLeftPanel menuLeftPanel;
	
	public MenuInsertAction(MenuLeftPanel menuLeftPanel) {
		this.menuLeftPanel = menuLeftPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int cnt = 0;
		
		for(int i=0; i<menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount(); ++i) {
			Object data = menuLeftPanel.getMenuManagePanel().getRsMenu().getValueAt(0, i);
			cnt = data.equals("") ? cnt+=1 : cnt;
		}
		
		if(cnt == menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount()) {
			JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요!", "information", JOptionPane.CANCEL_OPTION);
			return;
		}
		
		insertMenu();
		new MenuUpdateAction(menuLeftPanel);
		
		for(int i=0; i<menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount(); ++i) {
			menuLeftPanel.getMenuManagePanel().getRsMenu().setValueAt("", 0, i);
		}
		
		JOptionPane.showMessageDialog(null, "메뉴가 추가되었습니다", "추가 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void insertMenu() {
		String sql = "INSERT INTO menu VALUES(menu_menu_id_seq.nextval ,?,?,?)";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			for(int i=0; i<menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount()-1; ++i) {
				pstmt.setObject(i+1, menuLeftPanel.getMenuManagePanel().getRsMenu().getValueAt(0, i+1));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}