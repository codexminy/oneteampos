package oneteampos.members;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class MemberDefaultJTableDAO {
	
    // 필요한 변수선언
    Connection con;
    Statement st;
    PreparedStatement ps;
    ResultSet rs;
    
    // 로드 연결을 위한 생성자
    public MemberDefaultJTableDAO() {
        try {
        	String id = "hr"; 
            String pw = "1234";
            String url = "jdbc:oracle:thin:@localhost:1521/XE";
            
            // 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("로딩완료");
            // 연결
            con = DriverManager.getConnection(url, id, pw);
            System.out.println("연결완료");
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("실패");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("실패");
        }
    }
    
    // DB 닫기
    public void dbClose() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (ps != null) ps.close();
        } catch (Exception e) {
            System.out.println(e + "=> dbClose fail");
        }
    }
    
    // 전화번호 중복 검색 true = 회원가입 가능, false = 이미 회원
    public boolean getPhoneNumChk (String phone_number) {
    	boolean result = true;
    	
    	try {
    		ps = con.prepareStatement("SELECT * FROM members WHERE phone_number = ?");
    		ps.setString(1, phone_number.trim());
    		rs = ps.executeQuery();	// 실행
    		if (rs.next())
    			result = false;		// 전화번호가 존재하면 false
    		
    	} catch (SQLException e) {
    		System.out.println(e + "phoneNumChk fail");
    	} finally {
    		dbClose();
    	}
    	
    	return result;
    }

    // 회원등록
    public int memberInsert(MemberJDialogGUI member) {
    	int result = 0;
    	try {
    		ps = con.prepareStatement("INSERT INTO members VALUES "
    				+ "(members_member_id_seq.nextval, ?, ?, ?, ?, ?)");
    		
    		ps.setString(1, member.phone_number.getText());
            ps.setString(2, member.name.getText());
            ps.setInt(3, Integer.parseInt(member.grade_id.getText()));
            ps.setInt(4, Integer.parseInt(member.sum_amount.getText()));
            ps.setInt(5, Integer.parseInt(member.point.getText()));
  
            result = ps.executeUpdate(); //실행 -> 저장
             
    	} catch (SQLException e) {
            System.out.println(e + "=> memberJDialogGUI fail");
        } finally {
            dbClose();
        }
 
        return result;	
    }
    
    // member의 레코드 조회
    public void memberSelectAll (DefaultTableModel t_model) {
    	try {
    		st = con.createStatement();
    		// member_id 순서로 정렬해서 조회
    		rs = st.executeQuery("SELECT * FROM members order by member_id");
    		
    		// 기존 데이터 지우기
    		for (int i = 0; i < t_model.getRowCount();) {
    			t_model.removeRow(0);
    		}
    		
    		while (rs.next()) {
    			 Object data[] = { rs.getInt(1), rs.getString(2), rs.getString(3),
                         rs.getString(4), rs.getInt(5), rs.getInt(6) };
  
                 t_model.addRow(data); //DefaultTableModel에 레코드 추가
             }
  
         } catch (SQLException e) {
             System.out.println(e + "=> memberSelectAll fail");
         } finally {
             dbClose();
         }
    }
    
    // member_id에 해당하는 레코드 삭제하기
    public int memberDelete(String phone_number) {
    	int result = 0;
        try {
            ps = con.prepareStatement("DELETE members WHERE member_id = ? ");
            ps.setString(1, phone_number.trim());
            result = ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e + "=> memberDelete fail");
        }finally {
            dbClose();
        }
 
        return result;
    }
    
    // 수정하기
    public int memberUpdate(MemberJDialogGUI member) {
    	int result = 0;
    	String sql = "UPDATE members "
    			+ "SET phone_number = ?, name = ?, grade_id = ?, sum_amount = ?, point = ? WHERE member_id = ?";
    	
    	try {
    		ps = con.prepareStatement(sql);
    		
    		ps.setString(1, member.phone_number.getText());
            ps.setString(2, member.name.getText());
            ps.setInt(3, Integer.parseInt(member.grade_id.getText()));
            ps.setInt(4, Integer.parseInt(member.sum_amount.getText()));
            ps.setInt(5, Integer.parseInt(member.point.getText()));
            ps.setInt(6, Integer.parseInt(member.member_id.getText()));
            
            result = ps.executeUpdate(); //실행 -> 저장
    	
    	} catch (SQLException e) {
    		System.out.println(e + "=> memberUpdate fail");
    	} finally {
    		dbClose();
    	}
    	
    	return result;
    }
    
    
    // 검색단어에 해당하는 레코드 검색
    public void getMemberSearch(DefaultTableModel dt, String fieldName, String word) {
        String sql = "SELECT * FROM members WHERE " + fieldName.trim()
                + " LIKE '%" + word.trim() + "%'";
 
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
 
            // DefaultTableModel에 있는 기존 데이터 지우기
            for (int i = 0; i < dt.getRowCount();) {
                dt.removeRow(0);
            }
 
            while (rs.next()) {
            	Object data[] = { rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6) };
 
                dt.addRow(data);
            }
 
        } catch (SQLException e) {
            System.out.println(e + "=> getmemberSearch fail");
        } finally {
            dbClose();
        } 
    }
}

