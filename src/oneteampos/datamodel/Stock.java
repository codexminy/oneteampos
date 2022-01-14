package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock {
	
	Integer stock_id;
	Integer stock_count;
	String item_name;
	
	public Stock(ResultSet rs) throws SQLException {
		stock_id = rs.getInt("stock_id");
		stock_count = rs.getInt("stock_count");
		item_name = rs.getString("item_name");
	}
	
	public String getRowdData(int idx) {
		String data = "";
		
		if(idx == 0) {
			data = stock_id + "";
		} else if(idx == 1) {
			data = stock_count + "";
		} else {
			data = item_name;
		}
		
		return data;
	}
}
