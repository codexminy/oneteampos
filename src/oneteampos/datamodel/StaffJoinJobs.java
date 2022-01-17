package oneteampos.datamodel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffJoinJobs {
	
	String stf_name;
	String job_name;
	String hire_date;
	Integer salary;
	String tel;
	String address;
	
	public StaffJoinJobs(ResultSet rs) throws SQLException {
		stf_name = rs.getString("stf_name");
		job_name = rs.getString("job_name");
		hire_date = rs.getString("hire_date");
		salary = rs.getInt("salary");
		tel = rs.getString("tel");
		address = rs.getString("address");
	}
	
	

	public StaffJoinJobs(String stf_name, String job_name, String hire_date, String salary, String tel,
			String address) {
		super();
		this.stf_name = stf_name;
		this.job_name = job_name;
		this.hire_date = hire_date;
		this.salary = Integer.parseInt(salary);
		this.tel = tel;
		this.address = address;
	}



	public StaffJoinJobs() {
	}



	public String getRowData(int idx) {
		
		if(idx == 0) {
			return stf_name;
		} else if (idx == 1) {
			return job_name;
		} else if (idx == 2) {
			return hire_date;
		} else if (idx == 3) {
			return salary + "" ;
		} else if (idx == 4) {
			return tel ;
		} else {
			return address;
		}
	}
	
	public String getStaffName( ) {
		return stf_name;
	}
	
	public String getJobName() {
		return job_name;
	}
	
	public String getHireDate() {
		return hire_date;
	}
	
	public Integer getSalary() {
		return salary;
	}
	
	public String getTel() {
		return tel;
	}
	
	public String getAddress() {
		return address;
	}



	public void setStf_name(String stf_name) {
		this.stf_name = stf_name;
	}



	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}



	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}



	public void setSalary(String salary) {
		this.salary = Integer.parseInt(salary);
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
}
