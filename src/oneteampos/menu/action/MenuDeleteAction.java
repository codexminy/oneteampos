package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.menu.container.MenuLeftPanel;

public class MenuDeleteAction implements ActionListener {

	private MenuLeftPanel menuLeftPanel;
	
	public MenuDeleteAction(MenuLeftPanel menuLeftPanel) {
		this.menuLeftPanel = menuLeftPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel tm = (DefaultTableModel)menuLeftPanel.getMenuManagePanel().getMenuTable().getModel();
		
		if(menuLeftPanel.getMenuManagePanel().getList().size() == 0) {
			JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요!", "infomation", JOptionPane.CANCEL_OPTION);
			return;
		}
		
		for(int n : menuLeftPanel.getMenuManagePanel().getList()) {
			deleteMenu(tm.getValueAt(n, 1));
		}
		
		new MenuUpdateAction(menuLeftPanel);
		
		JOptionPane.showMessageDialog(null, "메뉴가 삭제되었습니다", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void deleteMenu(Object deleteKey) {
		String sql = String.format("DELETE FROM menu WHERE menu_id = %s", deleteKey);
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
