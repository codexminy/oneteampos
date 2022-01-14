package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order_list {
	
	String list_id;
	Integer order_id;
	String item_name;
	Integer item_amount;
	
	public Order_list(ResultSet rs) throws SQLException{
		list_id = rs.getString("list_id");
		order_id = rs.getInt("order_id");
		item_name = rs.getString("item_name");
		item_amount = rs.getInt("item_amount");
	}
	
	public Order_list(String list_id, Integer order_id, String item_name, Integer item_amount) {
		this.list_id = list_id;
		this.order_id = order_id;
		this.item_name = item_name;
		this.item_amount = item_amount;
	}
	
	public String getItemName( ) {
		return item_name;
	}
	
	public String getItemAmount() {
		return item_amount + "";
	}
	
	@Override
	public String toString() {
		return String.format("%s , %s, %s , %s \n", list_id, order_id, item_name, item_amount);
	}
}
