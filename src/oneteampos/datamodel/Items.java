package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Items {
	
	String item_name;
	Integer item_price;
	
	public Items(ResultSet rs) throws SQLException {
		item_name = rs.getString("item_name");
		item_price = rs.getInt("item_price");
		
	}
	
	public String getRowData(int idx) {
		String data = "";
		
		if(idx == 0) {
			data = item_name + "";
		} else {
			data = item_price + "";
		}
		
		return data;
	}
	
	public String getItemName() {
		return item_name;
	}
	
	public Integer getItemPrcie() {
		return item_price;
	}
}
