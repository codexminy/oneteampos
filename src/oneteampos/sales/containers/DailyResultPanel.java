package oneteampos.sales.containers;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Sales;
import oneteampos.datamodel.Stock;

public class DailyResultPanel extends JPanel{
	
	String sql;
	Date startDate;
	long calDateDays;
	
	public DailyResultPanel( Date startDate, long calDateDays) {
		setBounds(0, 10, 1280, 500);
		setBackground(Color.white);
		setLayout(null);
		
		this.sql = sql;
		this.startDate = startDate;
		this.calDateDays = calDateDays;
		addDayTable(sql);
	}
	
	public void addDayTable(String sql) {
		
		ArrayList<Sales> dayList = new ArrayList<>();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		String day = fm.format(startDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		
		for(int i = 0; i <= calDateDays; i++ ) {
			sql = "select TO_CHAR(p.pay_time, 'YYYY-MM-DD') AS day, count(*) AS cnt_orders\r\n"
					+ ", \r\n"
					+ "(SELECT sum(o.payment_amount) \r\n"
					+ "FROM payment p \r\n"
					+ "INNER JOIN order_details od ON p.order_history_id = od.order_history_id \r\n"
					+ "INNER JOIN orders o ON o.order_id = od.order_id\r\n"
					+ "WHERE \r\n"
					+ "TO_CHAR(p.pay_time, 'YYYY-MM-DD') = TO_DATE('"+day+"', 'YY/MM/DD')\r\n"
					+ "AND p.pay_type = 'card' AND od.order_confirmation = 'Y') AS card_amount\r\n"
					+ ",\r\n"
					+ "(SELECT sum(o.payment_amount) \r\n"
					+ "FROM payment p \r\n"
					+ "INNER JOIN order_details od ON p.order_history_id = od.order_history_id \r\n"
					+ "INNER JOIN orders o ON o.order_id = od.order_id\r\n"
					+ "WHERE \r\n"
					+ "TO_CHAR(p.pay_time, 'YYYY-MM-DD') = TO_DATE('"+day+"', 'YY/MM/DD')\r\n"
					+ "AND p.pay_type = 'cash' AND od.order_confirmation = 'Y' ) AS cash_amount\r\n"
					+ ",SUM(o.payment_amount) pay_amount\r\n"
					+ "FROM payment p \r\n"
					+ "INNER JOIN order_details od ON p.order_history_id = od.order_history_id \r\n"
					+ "INNER JOIN orders o ON o.order_id = od.order_id\r\n"
					+ "WHERE od.order_confirmation = 'Y' AND TO_CHAR(p.pay_time, 'YYYY-MM-DD') = TO_DATE('"+day+"', 'YY/MM/DD')\r\n"
					+ "GROUP BY TO_CHAR(p.pay_time, 'YYYY-MM-DD')";
			
			//데이터 넣기
			try (
					Connection conn = DBConnector.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
			) {

				while (rs.next()) {
					dayList.add(new Sales(rs));
				}
				
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cal.add(Calendar.DATE, 1);
			day = fm.format(cal.getTime());

		}
		// 일매출 테이블 만들기 
		String[] columnNames = {"일자", "일주문건수", "카드매출", "현금매출","합계"};
		String[][] rowData = new String[dayList.size()][columnNames.length];
		
		for(int i = 0; i < dayList.size(); ++i) {
			
			for(int j = 0; j < columnNames.length; ++j) {
				rowData[i][j] = dayList.get(i).getRowdData(j) ;
			}
		}
		
		
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		JTable dayTable = new JTable(model);
		
		setSizeColumnWidth(dayTable);
		
		
		// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
		JScrollPane sp = new JScrollPane(dayTable);
		
		sp.setBounds(100, 170, 800, 300);
		sp.setBackground(new Color(135, 136, 138));
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(sp);
		
	}
	
	public void setSizeColumnWidth(JTable table) {
		// 셀 간격 조정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
	
		for(int i =0; i < table.getColumnCount(); ++i) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignCenter);
		}
		
		table.setRowHeight(table.getRowHeight()+20);
		table.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		table.getTableHeader().setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		table.getTableHeader().setBackground(new Color(135, 136, 138));
		table.getTableHeader().setForeground(Color.WHITE);
		table.setRowHeight(30);
	}
}
