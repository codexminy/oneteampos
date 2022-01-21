package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;

public class MenuManage_deleteAction implements ActionListener {

	private MainFrame mainFrame;
	
	public MenuManage_deleteAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuLeftPanel leftPanel = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		DefaultTableModel tm = (DefaultTableModel)leftPanel.getMenuManage_dialog().getMenuTable().getModel();
		TreeSet<Integer> list = mainFrame.getMainPanel().getMenuPanel().getList();
		
		if(list.size() == 0) {
			JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요!", "Message", JOptionPane.CANCEL_OPTION);
			return;
		}
		
		int check = JOptionPane.showOptionDialog(null, "메뉴를 삭제하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(check == JOptionPane.YES_OPTION) {
			for(int n : list) {
				deleteMenu(tm.getValueAt(n, 1));
			}
			new MenuManage_updateAction(mainFrame);
			JOptionPane.showMessageDialog(null, "메뉴가 삭제되었습니다", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
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
