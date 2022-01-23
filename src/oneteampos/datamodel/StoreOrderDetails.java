package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreOrderDetails {
	
	String order_id;
	String item_name;
	String item_amount;
	String price;
	String totalprice;
	
	public StoreOrderDetails(ResultSet rs) throws SQLException {
		order_id = rs.getString("order_id");
		item_name = rs.getString("item_name");
		item_amount = rs.getString("item_amount");
		price = rs.getString("price");
		totalprice = rs.getString("totalprice");
	}

	public String getRowdData(int idx) {
		String data = "";
		if (idx == 0) {
			data = item_name;
		} else if (idx == 1) {
			data = item_amount;
		} else if (idx == 2) {
			data = price;
		} else if (idx == 3) {
			data = totalprice;
		}
		return data;
	}

	public String getListID() {
		return order_id;
	}
	
	
}
