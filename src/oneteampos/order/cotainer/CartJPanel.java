package oneteampos.order.cotainer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.datamodel.Order_list;
import oneteampos.datamodel.Store_order;
import oneteampos.order.actions.DeleteCartListener;
import oneteampos.order.compnents.StoreOrderBtn;

public class CartJPanel extends JPanel{
	
	StoreOrderBtn storeOrderBtn;
	ArrayList<Store_order> storeOrderList;
	ArrayList<Order_list> orderList;
	JTable orderTable;
	DefaultTableModel model;
	JTable deleteTable;
	DefaultTableModel dmodel;
	JLabel totalPriceLabel;
	OrderListJPanel orderListPanel;
	
	public CartJPanel(OrderListJPanel orderListPanel) {
		// 카트판넬 속성 변경
		setBounds(980, 0, 300, 680);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		// 오더 리스트 판넬 받아오기
		this.orderListPanel = orderListPanel;
		
		// 발주 버튼 추가 
		this.storeOrderBtn = new StoreOrderBtn(this);
		add(storeOrderBtn);
		
		// 데이터를 담을 리스트 추가
		storeOrderList = new ArrayList<Store_order>();
		orderList = new ArrayList<Order_list>();
		
		
		getCartLabel();
		getTotalLabel();
		getCartTable();
		getDeleteTable();
	}
	
	@Override
	public void paint(Graphics g) { // 선 긋기
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(10, 60, 280, 60);
		g.drawLine(10, 450, 280, 450);
	}
	
	public void getCartLabel() { // 선택한 메뉴 텍스트 라벨
		JLabel cartLabel = new JLabel("선택한 메뉴");
		
		cartLabel.setBounds(12, 20, 250, 30);
		cartLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 18));
		cartLabel.setForeground(Color.LIGHT_GRAY);
		
		add(cartLabel);
	}
	
	public void getCartTable() { // 발주 리스트 테이블 
		String[] columnNames = {"품명", "수량"};
		String[] rowData = new String[columnNames.length];
		
		for(int i = 0; i < orderList.size(); ++i) {
			for(int j = 0; j < 2; ++j) {
				if(j == 0) {
					rowData[j] = orderList.get(i).getItemName();
				} else if(j == 1) {
					rowData[j] = orderList.get(i).getItemAmount();
				}
			}
		}
		
		// 모델 생성 
		model = new DefaultTableModel(columnNames, 0);
		// 테이블 생성
		orderTable = new JTable(model);
		// 테이블 속성 지정
		orderTable.setBounds(10 , 65, 220, 300);
		orderTable.setFont(new Font("나눔스퀘어", Font.PLAIN , 17));
		orderTable.setBackground(Color.DARK_GRAY);
		orderTable.setForeground(Color.LIGHT_GRAY);
		orderTable.setShowGrid(false);
		// 셀 높이 지정
		orderTable.setRowHeight(23);
		// 셀 너비 지정
		orderTable.getColumn("품명").setPreferredWidth(180);
		orderTable.getColumn("수량").setPreferredWidth(40);
			
		// 한 셀 선택
		orderTable.setCellSelectionEnabled(true);
		
		// 셀 클릭 막기
		orderTable.setCellSelectionEnabled(false);
		orderTable.setFocusable(false);
		
		// 테이블 수정 금지
		setSelection(orderTable);
		
		// 텍스트 좌측정렬
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
		
		for(int i =0; i < orderTable.getColumnCount(); ++i) {
			orderTable.getColumn(orderTable.getColumnName(i)).setCellRenderer(celAlignLeft);
		}
		
		
		add(orderTable);
	}
	
	public void getDeleteTable( ) { // 발주 리스트 삭제 테이블
		String[] columnNames = {"삭제"};
		String[] rowData = new String[columnNames.length];
		
		for(int i = 0; i < orderList.size(); ++i) {
					rowData[i] = "X";
		}
		
		// 모델 생성
		dmodel = new DefaultTableModel(columnNames, 0) {
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		// 테이블 생성
		deleteTable = new JTable(dmodel);
		// 테이블 속성 지정
		deleteTable.setBounds(240 , 65, 30, 300);
		deleteTable.setFont(new Font("나눔스퀘어", Font.BOLD , 17));
		deleteTable.setBackground(Color.DARK_GRAY);
		deleteTable.setForeground(Color.LIGHT_GRAY);
		deleteTable.setShowGrid(false);
		deleteTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteTable.setFocusable(false);
		// 셀 높이 지정
		deleteTable.setRowHeight(23);
		// 한 셀 선택
		deleteTable.setCellSelectionEnabled(true);
		// 셀 클릭 리스너 - 한 행 삭제 
		deleteTable.addMouseListener(new DeleteCartListener(this));
		
		// 텍스트 중앙정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i =0; i < deleteTable.getColumnCount(); ++i) {
			deleteTable.getColumn(deleteTable.getColumnName(i)).setCellRenderer(celAlignCenter);
		}
		
		add(deleteTable);
		
	}
	
	public void getTotalLabel() { // 결제 금액 텍스트 라벨 , totalPriceLabel
		// 결제 금액 라벨 생성
		JLabel totalLabel = new JLabel("결제 금액");
		totalLabel.setBounds(12, 460, 100, 60);
		totalLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 18));
		totalLabel.setForeground(Color.LIGHT_GRAY);
		// totalPriceLabel(결제할 총 금액)라벨 생성
		totalPriceLabel = new JLabel("0");
		totalPriceLabel.setBounds(120, 460, 100, 60);
		totalPriceLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 21));
		totalPriceLabel.setForeground(Color.LIGHT_GRAY);
		
		add(totalLabel);
		add(totalPriceLabel);
	}
	
	public void setSelection(JTable table) { // 클릭 안되게 막는 메서드
		table.setCellSelectionEnabled(false);
		table.setFocusable(false);
	}
	
	public ArrayList<Store_order> getStoreOrderList(){ // 디비에 들어갈 발주 데이터
		return storeOrderList;
	}
	
	public ArrayList<Order_list> getOrderList() { // 디비에 들어갈 발주 리스트 데이터 
		return orderList;
	}
	
	public JTable getOrderTable() {
		return orderTable;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	public JTable getDeleteJTable() {
		return deleteTable;
	}
	
	public DefaultTableModel getDModel() {
		return dmodel;
	}
	
	public JLabel getTotalPriceLabel() {
		return totalPriceLabel;
	}
	
	public OrderListJPanel getOrderListPanel() {
		return orderListPanel;
	}
}
