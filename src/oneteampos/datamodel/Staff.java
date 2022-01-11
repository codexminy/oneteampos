package oneteampos.datamodel;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Staff {
	Integer stf_id;
	String stf_name;
	Integer job_id;
	Date hire_date;
	Integer salary;
	String tel;
	String address;
	
	public Staff(ResultSet rs) throws SQLException {
		stf_id = rs.getInt("stf_id");
		stf_name = rs.getString("stf_name");
		job_id = rs.getInt("job_id");
		hire_date = rs.getDate("hire_date");
		salary = rs.getInt("salary");
		tel = rs.getString("tel");
		address = rs.getString("address");
	}
	
	public String getStfId() {
		return stf_id+"";
	}
	
	public String getStfName() {
		return stf_name;
	}

}
