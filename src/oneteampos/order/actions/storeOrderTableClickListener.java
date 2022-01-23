package oneteampos.order.actions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Order_list;
import oneteampos.datamodel.StoreOrderDetails;
import oneteampos.order.cotainer.JMenuItemTemplete;
import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.sales.containers.RoundedButton;

public class storeOrderTableClickListener implements MouseListener {
	
	OrderJPanel orderPanel;
	JTable selectTable;

	public storeOrderTableClickListener(OrderJPanel orderPanel) {
		this.orderPanel = orderPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable stockTable = orderPanel.getOrderListJPanel().getStoreOrderTable();
		
		
		if(e.getButton() == 1) {
			
		} 
		
		if(e.getClickCount() == 2) {
			
		}
		
		if(e.getButton() == 3) {
			selectTable = (JTable)e.getSource();
			
			if(selectTable.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(orderPanel, "한 행만 선택해주세요");
			} else {
				if(selectTable.rowAtPoint(e.getPoint()) == selectTable.getSelectedRow() ) { 
					JPopupMenu popup = new JPopupMenu();
					popup.setBackground(new Color(135, 136, 138));
					JMenuItem  details = new JMenuItemTemplete("    상세내역보기   ");
					int x = e.getX();
					int y = e.getY();
					// JMenuItem 클릭시 
					details.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							
							if(orderPanel.getOrderListJPanel().getDetailsFrame() != null) {
								orderPanel.getOrderListJPanel().getDetailsFrame().dispose();
							}
							
							// 프레임 생성 
							JFrame detailsFrame = new JFrame("발주 상세내역");
							detailsFrame.setLayout(null);
							detailsFrame.getContentPane().setBackground(Color.WHITE);
						
							// 테이블 생성 
							// order_list 아이디로 목록 가져오기
							ArrayList<StoreOrderDetails> orderList = new ArrayList<>();
							String sql = "SELECT o.order_id AS order_id ,\r\n"
									+ "o.item_name AS item_name,\r\n"
									+ "o.item_amount AS item_amount,\r\n"
									+ "i.item_price AS price,\r\n"
									+ "i.item_price * o.item_amount AS totalprice\r\n"
									+ "FROM order_list o INNER JOIN store_order s ON o.order_id = s.order_id\r\n"
									+ "INNER JOIN items i ON o.item_name = i.item_name WHERE o.order_id =" + selectTable.getValueAt(selectTable.getSelectedRow(), 0);
							
							try (
									Connection conn = DBConnector.getConnection();
									PreparedStatement pstmt = conn.prepareStatement(sql);
									ResultSet rs = pstmt.executeQuery();
							) {
								while (rs.next()) {
									orderList.add(new StoreOrderDetails(rs));
								}
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							// 테이블 만들기 
							// 재고 테이블 만들기 
							String[] columnNames = { "품목 이름", "수량" , "가격" , "품목 주문 가격"};
							String[][] rowData = new String[orderList.size()][columnNames.length];
							int detailsTotalPrice = 0;
							for(int i = 0; i < orderList.size(); ++i) {
								for(int j = 0; j < columnNames.length; ++j) {
									rowData[i][j] = orderList.get(i).getRowdData(j) ;
									if(j == 3) {
										detailsTotalPrice += Integer.parseInt(orderList.get(i).getRowdData(j));
									}
								}
							}
							
							DefaultTableModel model = new DefaultTableModel(rowData, columnNames){
								public boolean isCellEditable(int i, int c) {
									return false;
								}
							};
							
							JTable detailsTable = new JTable(model);
							
							DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
							celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
							
							for(int i =0; i < detailsTable.getColumnCount(); ++i) {
								detailsTable.getColumn(detailsTable.getColumnName(i)).setCellRenderer(celAlignLeft);
							}
							
							
							JScrollPane sp = new JScrollPane(detailsTable);
							
							sp.setBounds(20, 90, 900, 33+20 * (detailsTable.getRowCount() + 2 ));
							sp.setBackground(new Color(135, 136, 138));
//							sp.setBorder(BorderFactory.createEmptyBorder());
							
							detailsTable.setRowHeight(30);
							detailsTable.setBackground(Color.WHITE);
							detailsTable.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
							detailsTable.getTableHeader().setFont(new Font("나눔스퀘어", Font.BOLD, 15));
							detailsTable.getTableHeader().setBackground(new Color(135, 136, 138));
							detailsTable.getTableHeader().setForeground(Color.WHITE);
							detailsTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
							// 선 비활성화
							detailsTable.setShowHorizontalLines(false);
							detailsTable.setGridColor(new Color(135, 136, 138));
							detailsFrame.add(sp);
							
							
							// 라벨 생성
							JLabel detailsLabel = new JLabel("발주 상세내역 ( 발주ID : " + orderList.get(0).getListID() + " )");
							detailsLabel.setBounds(30, 20, 400, 50);
							detailsLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 17));
							detailsFrame.add(detailsLabel);
							
							JLabel priceLabel = new JLabel("총 주문 가격 : " + detailsTotalPrice);
							priceLabel.setBounds(20, 130 + 20* (detailsTable.getRowCount() + 2 ), 400, 40);
							priceLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
							detailsFrame.add(priceLabel);
							
							// 확인 버튼
							RoundedButton exitBtn = new RoundedButton("확인");
							exitBtn.setBounds(820, 140+20 * (detailsTable.getRowCount() + 2 ), 100, 40);
							exitBtn.addMouseListener(new MouseAdapter() {
								@Override
								public void mouseClicked(MouseEvent e) {
									detailsFrame.dispose();
								}
							});
							detailsFrame.add(exitBtn);
							
							// 프레임 속성 
							detailsFrame.setSize(1000, 270 + detailsTable.getRowCount() * 20 );
							detailsFrame.setLocationRelativeTo(null);
							orderPanel.getOrderListJPanel().setDetailsFrame(detailsFrame);
							orderPanel.getOrderListJPanel().getDetailsFrame().setVisible(true);
							
						}
					});
				
					popup.add(details);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
