     package oneteampos.order.cotainer;

import java.awt.Color;
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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Items;
import oneteampos.datamodel.Stock;
import oneteampos.datamodel.Store_order;
import oneteampos.order.actions.TableAddItemListener;

public class OrderListJPanel extends JPanel{

	private ArrayList<Items> item = new ArrayList<>();
	private ArrayList<Store_order> store_order = new ArrayList<>();
	CartJPanel cartPanel;
	JTable itemTable;
	AddItemJPanel addItemPanel;
	JTable storeOrderTable;
	DefaultTableModel smodel;
	
	public OrderListJPanel() {
		setBounds(0, 100, 1280, 680);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(false);
	
		// 장바구니 판넬 추가
		this.cartPanel = new CartJPanel(this);
		// 장바구니 추가 판넬
		this.addItemPanel = new AddItemJPanel(this);
		
		
		setAddItemPanelVisibleFalse();
		add(cartPanel);
		add(addItemPanel);
		
		getTable();
	}
	
	public void setVisibleTrue() {
		setVisible(true);
	}
	
	public void setVisibleFalse() {
		setVisible(false);
	}
	
	public void getTable() {
		
		// 품목 라벨
		setItemLabel();
		// 품목 데이터
		setTableData("SELECT * FROM items");
		// 재고 테이블 만들기 
		String[] columnNames = {"ITEM_NAME","ITEM_PRICE"};
		String[][] rowData = new String[item.size()][columnNames.length];
		// 구분하기 위한 번호
		int item_num = 0 ;
		
		JScrollPane itemTable = setTable(columnNames, rowData, item_num) ;
		itemTable.setBounds(100, 70, 500, 250);
		
		// 발주 라벨 
		setStoreOrderLabel();
		// 품목 데이터
		setTableData("SELECT * FROM store_order ORDER BY order_id");
		// 발주 테이블 만들기 
		String[] columnNames_ = {"ORDER_ID", "TOTAL_PAY", "ORDER_DATE"};
		String[][] rowData_ = new String[store_order.size()][columnNames_.length];
		int	store_num = 1;
	
		
		JScrollPane storeOrderTable = setTable(columnNames_, rowData_, store_num);
		storeOrderTable.setBounds(100, 400, 500, 150);
		
		
	}
	
	public void setTableData(String sql) { // 데이터 넣는 메소드 
		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				if(sql.contains("item")) {
					item.add(new Items(rs));
				} else {
					store_order.add(new Store_order(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setItemLabel() {
		JLabel stockLabel = new JLabel("품목 목록");
		
		stockLabel.setBounds(120, 0, 400, 70);
		stockLabel.setFont(new Font("돋움", Font.BOLD, 25));
		
		add(stockLabel);
	}
	
	public void setStoreOrderLabel() {
		JLabel storeOrderLabel = new JLabel("발주 목록");
		
		storeOrderLabel.setBounds(120, 330, 400, 70);
		storeOrderLabel.setFont(new Font("돋움", Font.BOLD, 25));
		
		add(storeOrderLabel);
	}
	
	public JScrollPane setTable(String[] columnNames , String[][] rowData, int num) {
		int size = num == 0 ? item.size() : store_order.size();
		for(int i = 0; i < size; ++i) {
			for(int j = 0; j < columnNames.length; ++j) {
				if(num == 0) {
					rowData[i][j] = item.get(i).getRowData(j) ;
				} else {
					rowData[i][j] = store_order.get(i).getRowData(j) ;
				
				}
					
			}
		}

		
		if(num == 1) {
			smodel = new DefaultTableModel(rowData, columnNames) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};

			storeOrderTable = new JTable(smodel);
			setSizeColumnWidth(storeOrderTable);	
			// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
			JScrollPane sp = new JScrollPane(storeOrderTable);
//			setSelection(storeOrderTable); // 클릭 안되게 막기
			
			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
			add(sp);
			
			return sp;
			
		} else {
			DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};
			itemTable = new JTable(model);
			itemTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			
			// 행 클릭 리스너 
			itemTable.addMouseListener(new TableAddItemListener(this));
			setSizeColumnWidth(itemTable);	
			
			// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
			JScrollPane sp = new JScrollPane(itemTable);
			
			sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
			add(sp);
			
			return sp;
		}
	
		
	}
	
	public void setSizeColumnWidth(JTable table) {
		// 셀 간격 조정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);

		
		for(int i =0; i < table.getColumnCount(); ++i) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignCenter);
		}
		
		table.setRowHeight(30);
		table.setFont(new Font("돋움", Font.PLAIN , 15));
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 17));

	}
	
	public void setSelection(JTable table) { // 클릭 안되게 막는 메서드
		table.setCellSelectionEnabled(false);
		table.setFocusable(false);
	}
	
	public JTable getItemTable() { // 품목 목록 테이블 
		return itemTable;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	}
	
	public JTable getStoreOrderTable() { // 발주 목록 테이블
		return storeOrderTable;
	}
	
	public DefaultTableModel getSmodel() { // 발주 모델 
		return smodel;
	}
	
	public String getItemName(int idx) {  
		// addItemJPanel에 띄울 메소드 - 품목 이름을 얻는 메소드
		return item.get(idx).getItemName();
	}
	
	public Integer getitemPrice(int idx) { 
		// addItemJPanel에 띄울 메소드 - 품목 가격 얻는 메소드
		return item.get(idx).getItemPrcie();
	}
	
	public AddItemJPanel getaddItemPanel() {
		return addItemPanel;
	}
	
	public void setAddItemPanelVisibleTrue() { 
		addItemPanel.setVisible(true);
	}
	
	public void setAddItemPanelVisibleFalse() {
		addItemPanel.setVisible(false);
	}
	
	public AddItemJPanel getAddItemPanel() {
		return addItemPanel;
	}
	
	public CartJPanel getCartPanel() {
		return cartPanel;
	}

	
	
}
