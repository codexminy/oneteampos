package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuManage_refreshDialog;
import oneteampos.menu.data.CafeMenuData;
import oneteampos.menu.data.MenuData;

public class MenuManage_confirmAction implements ActionListener {

	private MainFrame mainFrame;
	private MenuManage_refreshDialog mrd;
	
	public MenuManage_confirmAction(MainFrame mainFrame, MenuManage_refreshDialog mrd) {
		this.mainFrame = mainFrame;
		this.mrd = mrd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		DefaultTableModel tm = (DefaultTableModel)mlp.getMenuManage_dialog().getMenuTable().getModel();
		TreeSet<Integer> list = mainFrame.getMainPanel().getMenuPanel().getList();
		
		int row = list.first();
		int id = Integer.parseInt(tm.getValueAt(row, 1).toString());
		String name = mrd.getNameField().getText();
		Integer price = Integer.parseInt(mrd.getPriceField().getText());
		String type = mrd.getTypeField().getText();
		
		CafeMenuData menuData = mlp.getCafeMenuData();
		ArrayList<MenuData> menuList = menuData.getMenuData();
		MenuData info = menuList.get(row);
		
		if(info.getMenuName().equals(name) && info.getPrice() == price && info.getMenuType().equals(type)) {
			JOptionPane.showMessageDialog(null, "변동사항이 없습니다!", "Message", JOptionPane.CANCEL_OPTION);
			return;
		}
		
		for(int i=0; i<menuList.size(); ++i) {
			if(name.equals(menuList.get(i).getMenuName())) {
				JOptionPane.showMessageDialog(null, "같은 메뉴가 존재합니다!", "Message", JOptionPane.CANCEL_OPTION);
				return;
			}
		}
		
		updateMenu(name, price, type, id);
		
		menuData.insertMenuData();
		
		menuList = menuData.getMenuData();
		
		tm.setValueAt(menuList.get(row).getMenuName(), row, 2);
		tm.setValueAt(menuList.get(row).getPrice(), row, 3);
		tm.setValueAt(menuList.get(row).getMenuType(), row, 4);

		new MenuManage_updateAction(mainFrame);
		
		JOptionPane.showMessageDialog(null, "메뉴 수정이 완료되었습니다.");
		
		mrd.dispose();
	}

	public void updateMenu(String name, int price, String type, int id) {
		String sql = "UPDATE menu SET menu_name = ?, price = ?, menu_type = ? WHERE menu_id = ?";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setString(3, type);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
