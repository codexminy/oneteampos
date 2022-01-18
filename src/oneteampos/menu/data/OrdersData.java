package oneteampos.menu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdersData {
	
	private int order_id;
	private String order_date;
	private int member_id;
	private int total;
	private int point_save;
	private int discount_amount;
	private int payment_amount;
	
	public OrdersData(ResultSet rs) throws SQLException {
		this.order_id = rs.getInt("order_id");
		this.order_date = rs.getString("order_date");
		this.member_id = rs.getInt("member_id");
		this.total = rs.getInt("total");
		this.point_save = rs.getInt("point_save");
		this.discount_amount = rs.getInt("discount_amount");
		this.payment_amount = rs.getInt("payment_amount");
	}
	
	@Override
	public String toString() {
		return String.format("%d/%s/%d/%d/%d/%d/%d", order_id, order_date, member_id, member_id, total, point_save, discount_amount, payment_amount);
	}
	
	public int getOrderId() {
		return this.order_id;
	}
}
