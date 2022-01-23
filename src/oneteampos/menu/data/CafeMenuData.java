package oneteampos.menu.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oneteampos.database.DBConnector;

public class CafeMenuData {
	
	private ArrayList<MenuData> menu;
	
	public CafeMenuData() {
		this.menu = new ArrayList<>();
		
		insertMenuData();
	}

	public void insertMenuData() {
		String sql = "SELECT * FROM menu ORDER BY menu_id";
		menu.clear();
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				menu.add(new MenuData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<MenuData> getMenuData() {
		return this.menu;
	}
}
