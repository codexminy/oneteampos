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
		this.menu = insertMenuData();
	}

	public ArrayList<MenuData> insertMenuData() {
		ArrayList<MenuData> list = new ArrayList<>();
		String sql = "SELECT * FROM tempmenu";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				list.add(new MenuData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MenuData> getMenuData() {
		return this.menu;
	}
}
