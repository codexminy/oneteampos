package oneteampos.receipt.container;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import oneteampos.datamodel.ReturnInfo;
import oneteampos.receipt.components.BackToReceiptBtn;

public class ReturnJPanel extends JPanel{
	
	private ReceiptJPanel receiptJPanel;
	private ArrayList<ReturnInfo> returnList = new ArrayList<>();
	BackToReceiptBtn backBtn;
	
	public ReturnJPanel(ReceiptJPanel receiptJPanel) {
		setBounds(0, 100, 1280, 680);
		setBackground(Color.WHITE);
		setLayout(null);
		
		this.receiptJPanel = receiptJPanel;
		
		
		// 뒤로가기 버튼
		backBtn = new BackToReceiptBtn(receiptJPanel, this);
		add(backBtn);
	
		// 테이블에 쓸 데이터를 리스트에 넣기
		setReceiptLsit();
		addOrderMenuTable();
		addPayTable();
	}
	
	public void setReceiptLsit( ) { //리스트에 값 넣기 
		int row = receiptJPanel.getReceiptTablePanel().getReceiptInfoTable().getSelectedRow();
		int receitpNum = Integer.parseInt(receiptJPanel.getReceiptTablePanel().getReceiptInfoTable().getValueAt(row, 1).toString());
		String sql = "SELECT c.order_history_id,m.menu_name,m.menu_type ,m.price\r\n"
				+ ",c.menu_amount,m.price * c.menu_amount AS menu_totalprice\r\n"
				+ ", o.total, o.discount_amount, o.payment_amount, p.pay_type, o.point_save \r\n"
				+ "FROM cart c INNER JOIN menu m ON c.menu_id = m.menu_id\r\n"
				+ "INNER JOIN payment p ON c.order_history_id = p.order_history_id\r\n"
				+ "INNER JOIN order_details od ON od.order_history_id = p.order_history_id\r\n"
				+ "INNER JOIN orders o ON o.order_id = od.order_id\r\n"
				+ "WHERE p.pay_id = "+ receitpNum
				+ "ORDER BY c.cart_id ASC";

		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				returnList.add(new ReturnInfo(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void addOrderMenuTable() {
		// 영수증 상세내역 테이블 만들기 
		String[] columnNames = {"번호","품명","단가","수량","금액"};
		String[][] rowData = new String[returnList.size()][columnNames.length];
		int idx = 1;
		int cnt = 0; // 추가메뉴 카운트
		String menutype = "";
		
		for (int i = 0; i < returnList.size(); ++i) {
			menutype = returnList.get(i).getMenuType();
			if (menutype.equals("extra") || menutype.equals("size") || menutype.equals("temperature")) {
				cnt++;
			} else {
				cnt = 0;
			}
			for (int j = 0; j < columnNames.length; ++j) {
				if (j == 0) {
					rowData[i][j] = idx++ + "";
				} else {
					if (cnt != 0 && j == 1) {
						for (int k = 0; k < cnt; ++k) {
							rowData[i][j] += "└";
							rowData[i][j] = rowData[i][j].replace("null", "");
						}
						rowData[i][j] += returnList.get(i).getRowData(j - 1, 0);
					} else {
						rowData[i][j] = returnList.get(i).getRowData(j - 1, 0);
					}

				}
			}
		}
		
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
		JTable orderMenuTable = new JTable(model);
		
		// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
		JScrollPane sp = new JScrollPane(orderMenuTable);
		
		setSizeColumnWidth(orderMenuTable);
				
		sp.setBounds(100, 20, 800, 250);
				
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
		add(sp);
	
	}
	
	public void addPayTable() {
		// 영수증 상세내역 테이블 만들기 
		String[] columnNames = {"", ""};
		String[][] rowData = new String[6][2];
		rowData[0][0] = "주문 합계";
		rowData[0][1] = returnList.get(0).getTotal();
		rowData[1][0] = "할인 금액";
		rowData[1][1] = returnList.get(0).getDiscountAmount();
		rowData[2][0] = "받을 금액";
		rowData[2][1] = returnList.get(0).getPayAmount();
		rowData[3][0] = "현금";
		rowData[3][1] = returnList.get(0).getTotalAmount("현금");
		rowData[4][0] = "카드";
		rowData[4][1] = returnList.get(0).getTotalAmount("카드");
		rowData[5][0] = "포인트";
		rowData[5][1] = returnList.get(0).getPoint();
	
		
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
		JTable payTable = new JTable(model);
		
		// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
		JScrollPane sp = new JScrollPane(payTable);
		
		setSizeColumnWidth(payTable);
//		payTable.setBounds(100, 270, 800, 300);
				
		sp.setBounds(100, 270, 800, 220);
				
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				
		add(sp);
//		add(payTable);
	}
	
	public void setSizeColumnWidth(JTable table) { // 테이블 셀의 너비, 높이 조정
		// 셀 간격 조정
		
		// 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		// 왼쪽정렬
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
		
		if(table.getColumnCount() >2) {
			table.getTableHeader().setFont(new Font("돋움", Font.BOLD, 18));
			for(int i =0; i < table.getColumnCount(); ++i) {
				if(i == 1) {
					table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignLeft);
				} else {
					table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignCenter);
				}
			}
		} else {
			for(int i =0; i < table.getColumnCount(); ++i) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignLeft);
			}
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
		
		table.setRowHeight(table.getRowHeight()+15);
		table.setFont(new Font("돋움", Font.PLAIN , 16));

	}
	
	public ArrayList<ReturnInfo> getReturnList() {
		return returnList;
	}
	
	public ReceiptJPanel getReceiptJPanel() {
		return receiptJPanel;
	}

}
