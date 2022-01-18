package oneteampos.menu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailsData {
	private int order_history_id;
	private int order_id;
	private String order_confirmation;
	
	public OrderDetailsData(ResultSet rs) throws SQLException {
		this.order_history_id = rs.getInt("order_history_id");
		this.order_id = rs.getInt("order_id");
		this.order_confirmation = rs.getString("order_confirmation");
	}
	
	public int getOrderHistoryId() {
		return this.order_history_id;
	}
	
	public int getOrderId() {
		return this.order_id;
	}
	
	public String getOrderConfirmation() {
		return this.order_confirmation;
	}
}
