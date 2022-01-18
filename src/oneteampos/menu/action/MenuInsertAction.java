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

public class MenuInsertAction implements ActionListener {

	private MainFrame mainFrame;

	public MenuInsertAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		int cnt = 0;
//		
//		for(int i=0; i<menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount(); ++i) {
//			Object data = menuLeftPanel.getMenuManagePanel().getRsMenu().getValueAt(0, i);
//			cnt = data.equals("") ? cnt+=1 : cnt;
//		}
//		
//		if(cnt == menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount()) {
//			JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요!", "information", JOptionPane.CANCEL_OPTION);
//			return;
//		}
//		
//		insertMenu();
//		new MenuUpdateAction(menuLeftPanel);
//		
//		for(int i=0; i<menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount(); ++i) {
//			menuLeftPanel.getMenuManagePanel().getRsMenu().setValueAt("", 0, i);
//		}
		
		int cnt = 0;
		
//		Vector<Object> addList = new Vector<>();
		
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		JTable rsMenu = mlp.getMenuManagePanel().getRsMenu();
		
		for(int i=0; i<rsMenu.getColumnCount(); ++i) {
			Object data = mlp.getMenuManagePanel().getRsMenu().getValueAt(0, i);
			cnt = data.equals("") ? cnt+=1 : cnt;
			if(data.equals("")) {
				JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요!", "information", JOptionPane.CANCEL_OPTION);
				return;
			} else {
//				addList.add(data);
			}
		}
		
//		if(cnt == rsMenu.getColumnCount()) {
//			JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요!", "information", JOptionPane.CANCEL_OPTION);
//			return;
//		}

		int check = JOptionPane.showOptionDialog(null, "메뉴를 추가하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(check == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "메뉴가 추가되었습니다", "추가 완료", JOptionPane.INFORMATION_MESSAGE);

			insertMenu(rsMenu);
			new MenuUpdateAction(mainFrame);
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
//			for(int i=0; i<menuLeftPanel.getMenuManagePanel().getRsMenu().getColumnCount()-1; ++i) {
//				pstmt.setObject(i+1, menuLeftPanel.getMenuManagePanel().getRsMenu().getValueAt(0, i+1));
//			}
			
			for(int i=0; i<rsMenu.getColumnCount(); ++i) {
				pstmt.setObject(i+1, rsMenu.getValueAt(0, i));
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}