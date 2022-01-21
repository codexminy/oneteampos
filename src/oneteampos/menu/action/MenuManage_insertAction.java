package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;

public class MenuManage_insertAction implements ActionListener {

	private MainFrame mainFrame;

	public MenuManage_insertAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		JTable rsMenu = mlp.getMenuManage_dialog().getRsMenu();
		int cnt = 0;
		
		for(int i=0; i<rsMenu.getColumnCount(); ++i) {
			Object data = mlp.getMenuManage_dialog().getRsMenu().getValueAt(0, i);
			cnt = data.equals("") ? cnt+=1 : cnt;
			
			if(data.equals("")) {
				JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요!", "information", JOptionPane.CANCEL_OPTION);
				return;
			}
		}
		
		int check = JOptionPane.showOptionDialog(null, "메뉴를 추가하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(check == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "메뉴가 추가되었습니다", "추가 완료", JOptionPane.INFORMATION_MESSAGE);
			insertMenu(rsMenu);
			new MenuManage_updateAction(mainFrame);
			
			for(int i=0; i<rsMenu.getColumnCount(); ++i) {
				rsMenu.setValueAt("", 0, i);
			}
		}
	}
	
	public void insertMenu(JTable rsMenu) {
		String sql = "INSERT INTO menu VALUES(menu_menu_id_seq.nextval ,?,?,?)";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			for(int i=0; i<rsMenu.getColumnCount(); ++i) {
				pstmt.setObject(i+1, rsMenu.getValueAt(0, i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}