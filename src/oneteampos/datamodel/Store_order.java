package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Store_order {
	
	Integer order_id;
	Integer total_pay;
	String order_date;
	
	public Store_order(ResultSet rs) throws SQLException {
		order_id = rs.getInt("order_id");
		total_pay = rs.getInt("total_pay");
		order_date = rs.getString("order_date");
	}
	
	public String getRowData(int idx) {
		String data = "";
		
		if(idx == 0) {
			data = order_id + "";
		} else if(idx == 1) {
			data = total_pay + "";
		} else {
			data = order_date;
		}
		return data;
	}
	
	public Integer getOrderId( ) {
		return order_id;
	}
	
	public Integer getTotalPay() {
		return total_pay;
	}
	
	public String getOrderDate() {
		return order_date;
	}
}
