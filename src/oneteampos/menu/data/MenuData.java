package oneteampos.menu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuData {
	private int menu_id;
	private String menu_name;
	private int price;
	private String menu_type;
	
	public MenuData(ResultSet rs) throws SQLException {
		this.menu_id = rs.getInt("menu_id");
		this.menu_name = rs.getString("menu_name");
		this.price = rs.getInt("price");
		this.menu_type = rs.getString("menu_type");
	}
	
	public int getMenuId() {
		return this.menu_id;
	}
	
	public String getMenuName() {
		return this.menu_name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String getMenuType() {
		return this.menu_type;
	}
}
