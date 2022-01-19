package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptInfo {
	
	String pay_id;
	String pay_type;
	Integer payment_amount;
	String order_date;
	String order_confirmation;
	
	public ReceiptInfo(ResultSet rs) throws SQLException {
		pay_id = String.format("%04d", rs.getInt("pay_id"+""));
		pay_type = rs.getString("pay_type");
		payment_amount = rs.getInt("payment_amount");
		order_date = rs.getString("order_date");
		order_confirmation = rs.getString("order_confirmation");
	}
	
	public String getRowData(int idx) {
		String data = "";

		if(idx == 0) {
			data = pay_id;
		} else if(idx == 1) {
			data = pay_type.equals("card") ? "카드" : "현금";
		} else if(idx == 2){
			data = payment_amount + "";
		} else if (idx == 3) {
			data = order_date;
		} else {
			data = order_confirmation.equals("Y") ? "결제완료" : "결제취소" ;
		}
		
		return data;
	}
}
