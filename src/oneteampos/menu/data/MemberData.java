package oneteampos.menu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberData {
	
	private int member_id;
	private String phone_number;
	private String name;
	private int grade_id;
	private int sum_amount;
	private int point;
	private MemberGradeData mgd;
	
	public MemberData(ResultSet rs) throws SQLException {
		this.member_id = rs.getInt("member_id");
		this.phone_number = rs.getString("phone_number").replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		this.name = rs.getString("name");
		this.grade_id = rs.getInt("grade_id");
		this.sum_amount = rs.getInt("sum_amount");
		this.point = rs.getInt("point");
		this.mgd = new MemberGradeData(rs);
	}
	
	public int getMemberId() {
		return this.member_id;
	}
	
	public String getPhoneNumber() {
		return this.phone_number;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getGradeId() {
		return this.grade_id;
	}
	
	public int getSumAmount() {
		return this.sum_amount;
	}
	
	public int getPoint() {
		return this.point;
	}
	
	public String getGrade() {
		return this.mgd.getGrade();
	}
	
	public double getSave() {
		return this.mgd.getSave();
	}
	
	public int getThresHold() {
		return this.mgd.getThresHold();
	}
}

class MemberGradeData {
	
	private int grade_id;
	private String grade;
	private double save;
	private int threshold;
	
	public MemberGradeData(ResultSet rs) throws SQLException {
		this.grade_id = rs.getInt("grade_id");
		this.grade = rs.getString("grade");
		this.save = rs.getDouble("save");
		this.threshold = rs.getInt("threshold");
	}
	
	public String getGrade() {
		return this.grade;
	}
	
	public double getSave() {
		return this.save;
	}
	
	public int getThresHold() {
		return this.threshold;
	}
}















