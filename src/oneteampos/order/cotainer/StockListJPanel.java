package oneteampos.order.cotainer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Stock;
import oneteampos.order.actions.StockTableClickListener;

public class StockListJPanel extends JPanel{
	
	private JTable stock_table;
	private JFrame qtyframe;
	private OrderJPanel orderPanel;
	
	public StockListJPanel(OrderJPanel orderPanel) {
		this.orderPanel = orderPanel;
		
		setBounds(0, 100, 1280, 680);
		setBackground(Color.WHITE);
		setLayout(null);
		setVisible(false);
		
		getStockListTable();
	}
	
	public void setVisibleTrue() {
		setVisible(true);
	}
	
	public void setVisibleFalse() {
		setVisible(false);
	}
	
	public void getStockListTable() {
		
		// 재고 데이터 가져오기
		ArrayList<Stock> stock = new ArrayList<>();

		String sql = "SELECT * FROM stock";

		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				stock.add(new Stock(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 재고 라벨
		setStockLabel();
		
		// 재고 테이블 만들기 
		String[] columnNames = {"재고ID", "수량", "품목이름"};
		String[][] rowData = new String[stock.size()][columnNames.length];
		
		for(int i = 0; i < stock.size(); ++i) {
			
			for(int j = 0; j < columnNames.length; ++j) {
				rowData[i][j] = stock.get(i).getRowdData(j) ;
			}
		}
		
		DefaultTableModel model = new DefaultTableModel(rowData, columnNames){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
		stock_table = new JTable(model);
		stock_table.addMouseListener(new StockTableClickListener(this));
		
		setSizeColumnWidth(stock_table);
		
		
		// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
		JScrollPane sp = new JScrollPane(stock_table);
		
		sp.setBounds(100, 180, 800, 360);
		sp.setBackground(new Color(135, 136, 138));
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(sp);
	
	}
	
	public void setStockLabel() {
		JLabel stockLabel = new JLabel("재고 목록");
		
		stockLabel.setBounds(120, 60, 400, 100);
		stockLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 35));
		
		add(stockLabel);
	}
	
	public void setSizeColumnWidth(JTable table) {
		// 셀 간격 조정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		for(int i =0; i < table.getColumnCount(); ++i) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignCenter);
		}
		
		table.setRowHeight(table.getRowHeight()+30);
		table.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		table.getTableHeader().setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		table.getTableHeader().setBackground(new Color(135, 136, 138));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setPreferredSize(new Dimension(0, 40));
	}
	
	public JTable getStockTable() {
		return stock_table;
	}
	
	public void setQtyframe(JFrame frame) {
		this.qtyframe = frame;
	}
	
	public JFrame getQtyframe() {
		return this.qtyframe;
	}
	
	public OrderJPanel getOrderPanel() {
		return this.orderPanel;
	}
}

