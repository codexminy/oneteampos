package oneteampos.receipt.container;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.ReceiptInfo;
import oneteampos.receipt.components.PrintBtn;
import oneteampos.receipt.components.ReturnBtn;

public class ReceiptTableJPanel extends JPanel{
	
	DefaultTableModel model;
	JTable receiptInfoTable;
	ReceiptJPanel receiptJPanel;
	JButton returnBtn;
	JButton printBtn;
	
	public ReceiptTableJPanel(ReceiptJPanel receiptJPanel) {
		setBounds(0, 100, 1280, 680);
		setBackground(Color.WHITE);
		setLayout(null);
		
		this.receiptJPanel = receiptJPanel;
		// 테이블 추가
		addReceiptTable();
		
		// 반품 버튼 추가
		returnBtn = new ReturnBtn(receiptJPanel, false);
		add(returnBtn);
		// 출력 버튼 추가
		printBtn = new PrintBtn(receiptJPanel, false);
		add(printBtn);
		
	}

	public void addReceiptTable() {
		// 영수증 데이터 가져오기
		ArrayList<ReceiptInfo> receiptInfo = new ArrayList<>();

		String sql = "SELECT p.pay_id,  p.pay_type, o.payment_amount \r\n"
				+ ", o.order_date   , od.order_confirmation \r\n"
				+ "FROM orders o INNER JOIN order_details od ON o.order_id = od.order_id\r\n"
				+ "INNER JOIN payment p ON od.order_history_id = p.order_history_id";

		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				receiptInfo.add(new ReceiptInfo(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 영수증 테이블 만들기 
		String[] columnNames = {"","영수증번호", "결제수단", "결제금액", "결제시간", "승인여부"};
		String[][] rowData = new String[receiptInfo.size()][columnNames.length];
		int idx = 1;
		for(int i = 0; i < receiptInfo.size(); ++i) {
			
			for(int j = 0; j < columnNames.length; ++j) {
				if(j == 0) {
					rowData[i][j] = idx++ + "";
				}else {
					rowData[i][j] = receiptInfo.get(i).getRowData(j-1) ;
				}
			}
		}
		
		model = new DefaultTableModel(rowData, columnNames){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
	
		receiptInfoTable = new JTable(model);
		setSizeColumnWidth(receiptInfoTable);
		
		// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
		JScrollPane sp = new JScrollPane(receiptInfoTable);
		
		sp.setBounds(100, 50, 800, 360);
		
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(sp);
	}
	
	public void setSizeColumnWidth(JTable table) { // 테이블 셀의 너비, 높이 조정
		// 셀 간격 조정
		
		// 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i =0; i < table.getColumnCount(); ++i) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignCenter);
		}
		
		// 셀 너비
		for (int column = 0; column < table.getColumnCount(); column++) { 
			final TableColumnModel columnModel = table.getColumnModel();
			int width = 50; // Min width 
			for (int row = 0; row < table.getRowCount(); row++) { 
				TableCellRenderer renderer = table.getCellRenderer(row, column); 
				Component comp = table.prepareRenderer(renderer, row, column); 
				width = Math.max(comp.getPreferredSize().width +1 , width); 
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
		
		table.setRowHeight(table.getRowHeight()+20);
		table.setFont(new Font("돋움", Font.PLAIN , 16));
		table.getTableHeader().setFont(new Font("돋움", Font.BOLD, 18));

	}
	
	public JTable getReceiptInfoTable() {
		return receiptInfoTable;
	}
	
	public ReceiptJPanel getReceiptPanel() {
		return receiptJPanel;
	}
	
	public JButton getReturnBtn() {
		return returnBtn;
	}
	
	public JButton getPrintBtn() {
		return printBtn;
	}
	
	public ReceiptTableJPanel getReceptTablePanel() {
		return this;
	}
}
