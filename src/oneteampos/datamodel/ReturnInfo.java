package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnInfo {
	
	Integer order_history_id;
	String menu_name;
	String menu_type;
	Integer price;
	Integer menu_amount;
	Integer menu_totalprice;
	Integer total;
	Integer discount_amount;
	Integer payment_amount;
	String pay_type;
	Integer point_save;

	public ReturnInfo(ResultSet rs) throws SQLException {
		order_history_id = rs.getInt("order_history_id");
		menu_name = rs.getString("menu_name");
		menu_type = rs.getString("menu_type");
		price = rs.getInt("price");
		menu_amount = rs.getInt("menu_amount");
		menu_totalprice = rs.getInt("menu_totalprice");
		total = rs.getInt("total");
		discount_amount = rs.getInt("discount_amount");
		payment_amount = rs.getInt("payment_amount");
		pay_type = rs.getString("pay_type");
		point_save = rs.getInt("point_save");
	}

	public String getRowData(int idx, int tableNum) {
		String data = "";
		if(tableNum == 0) {
			if(idx == 0) {
				data = menu_name;
			} else if (idx == 1) {
				data = price + "";
			} else if (idx == 2) {
				data = menu_amount + "";
			} else if (idx == 3) {
				data = menu_totalprice + "";
			} 
		} 
		
		return data;
	}

	public String getMenuType() {
		return menu_type;
	}

	public String getTotal() {
		return total+"";
	}

	public String getDiscountAmount() {
		return discount_amount +"";
	}

	public String getPayAmount() {
		return payment_amount+"";
	}

	public String getTotalAmount(String paytype) {
		return pay_type.equals(paytype.equals("현금")? "cash" : "card") ? payment_amount+"" : "";
	}

	public String getPoint() {
		return point_save + "";
	}
	
	public Integer getID() {
		return order_history_id;
	}
	
}
