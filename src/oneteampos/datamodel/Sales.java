package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sales {
	
	String day ;
	Integer cnt_orders;
	Integer card_amount;
	Integer cash_amount;
	Integer pay_amount;
	
	public Sales(ResultSet rs) throws SQLException {
		day = rs.getString("day");
		cnt_orders = rs.getInt("cnt_orders");
		card_amount = rs.getInt("card_amount");
		cash_amount = rs.getInt("cash_amount");
		pay_amount = rs.getInt("pay_amount");
	}

	public String getRowdData(int idx) {
		if(idx == 0) {
			return day;
		} else if (idx ==1) {
			return cnt_orders + "";
		} else if (idx == 2) {
			return card_amount + "";
		} else if (idx == 3) {
			return cash_amount + "" ;
		} else  {
			return pay_amount + "";
		}
		
	}
	
	

}
