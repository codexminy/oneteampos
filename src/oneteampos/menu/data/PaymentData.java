package oneteampos.menu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentData {
	private int pay_id;
	private int order_history_id;
	private String pay_type;
	private int card;
	private String pay_time;
	
	public PaymentData(ResultSet rs) throws SQLException {
		this.pay_id = rs.getInt("pay_id");
		this.order_history_id = rs.getInt("order_history_id");
		this.pay_type = rs.getString("pay_type");
		this.card = rs.getInt("card");
		this.pay_time = rs.getString("pay_time");
	}
	
	public int getPayId() {
		return this.pay_id;
	}
	
	public int getOrderHistoryId() {
		return this.order_history_id;
	}
	
	public String getPayType() {
		return this.pay_type;
	}
	
	public int getCard() {
		return this.card;
	}
	
	public String getPayTime() {
		return this.pay_time;
	}
}
