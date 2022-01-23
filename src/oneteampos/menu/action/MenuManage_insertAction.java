package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.data.CafeMenuData;
import oneteampos.menu.data.MenuData;

public class MenuManage_insertAction implements ActionListener {

	private MainFrame mainFrame;

	public MenuManage_insertAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		DefaultTableModel tm = (DefaultTableModel)mlp.getMenuManage_dialog().getMenuTable().getModel();
		JTable rsMenu = mlp.getMenuManage_dialog().getRsMenu();
		
		for(int i=0; i<rsMenu.getColumnCount(); ++i) {
			Object data = mlp.getMenuManage_dialog().getRsMenu().getValueAt(0, i);
			
			if(data.equals("")) {
				JOptionPane.showMessageDialog(null, "메뉴를 입력해주세요!", "Message", JOptionPane.CANCEL_OPTION);
				return;
			}
		}
		
		CafeMenuData menuData = mlp.getCafeMenuData();
		ArrayList<MenuData> menuList = menuData.getMenuData();
		
		for(int i=0; i<menuList.size(); ++i) {
			if(rsMenu.getValueAt(0, 0).toString().equals(menuList.get(i).getMenuName())) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 메뉴입니다!", "Message", JOptionPane.CANCEL_OPTION);
				return;
			}
		}

		int check = JOptionPane.showOptionDialog(null, "메뉴를 추가하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(check == JOptionPane.YES_OPTION) {
			insertMenu(rsMenu);
			
			Vector<Object> vec = new Vector<>();
			menuList = menuData.getMenuData();
			
			menuData.insertMenuData();

			vec.add(false);
			vec.add(menuList.get(menuList.size()-1).getMenuId());
			vec.add(menuList.get(menuList.size()-1).getMenuName());
			vec.add(menuList.get(menuList.size()-1).getPrice());
			vec.add(menuList.get(menuList.size()-1).getMenuType());
			
			tm.addRow(vec);
			
			new MenuManage_updateAction(mainFrame);
			
			for(int i=0; i<rsMenu.getColumnCount(); ++i) {
				rsMenu.setValueAt("", 0, i);
			}
			
			JOptionPane.showMessageDialog(null, "메뉴가 추가되었습니다", "추가 완료", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void insertMenu(JTable rsMenu) {
		String sql = "INSERT INTO menu VALUES(menu_menu_id_seq.nextval,?,?,?)";
		
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