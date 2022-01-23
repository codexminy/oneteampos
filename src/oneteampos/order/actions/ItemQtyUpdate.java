package oneteampos.order.actions;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import oneteampos.database.DBConnector;
import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.order.cotainer.StockListJPanel;
import oneteampos.sales.containers.RoundedButton;
import oneteampos.sales.containers.SalesPanel;

public class ItemQtyUpdate implements MouseListener{
	
	StockListJPanel stockListPanel;
	int x;
	int y;

	public ItemQtyUpdate(StockListJPanel stockListPanel, int x , int y) {
		this.stockListPanel = stockListPanel;
		this.x = x;
		this.y = y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTable stockTable = stockListPanel.getStockTable();
		
		if(stockListPanel.getQtyframe() != null) {
			stockListPanel.getQtyframe().dispose();
		}
		
		int row = stockTable.getSelectedRow();
		int column = 1; // 수량 컬럼
		// 프레임 띄우기
		JFrame qtyframe = new JFrame(stockTable.getValueAt(row, 2)+"수량 관리");
		qtyframe.setSize(300, 200);
		qtyframe.setLocationRelativeTo(null);
		
		JPanel qtypanel = new JPanel();
		qtypanel.setBounds(0, 0, 300, 200);
		qtypanel.setLayout(null);
		qtypanel.setBackground(Color.WHITE);
		qtyframe.add(qtypanel);
		
		JLabel itemName = new JLabel(stockTable.getValueAt(row, 2) + "");
		itemName.setBounds(70, 20, 150, 50);
		itemName.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		
		JLabel qtyLable = new JLabel(stockTable.getValueAt(row, column) + "");
		qtyLable.setBounds(130, 70, 50, 50);
		qtyLable.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		
	
		
		RoundedButton leftBtn = new RoundedButton("◀");
		leftBtn.setBounds(70, 80, 40, 30);
		leftBtn.setFont(new Font("돋움", Font.BOLD, 15));
		leftBtn.setBackground(new Color(228, 227, 230));
		leftBtn.setBorderPainted(false);
		leftBtn.setRequestFocusEnabled(false);
		leftBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leftBtn.setSelected(false);
		leftBtn.setFocusPainted(false);
		
		leftBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(Integer.parseInt(qtyLable.getText()) == 0){
					
				} else {
					int qty = Integer.parseInt(qtyLable.getText());
					qtyLable.setText((qty-1)+"");
				}
			}
		});
	
		
		RoundedButton rightBtn = new RoundedButton("▶");
		rightBtn.setBounds(190, 80, 40, 30);
		rightBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		rightBtn.setBackground(new Color(228, 227, 230));
		rightBtn.setBorderPainted(false);
		rightBtn.setRequestFocusEnabled(false);
		rightBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rightBtn.setFocusPainted(false);
		rightBtn.setSelected(false);
		rightBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int qty = Integer.parseInt(qtyLable.getText());
				qtyLable.setText((qty+1)+"");
			}
		});
		
		rightBtn.addMouseListener(new MouseAdapter() {
			
		});
		
		RoundedButton qtyUpdateBtn = new RoundedButton("확인");
		qtyUpdateBtn.setBounds(220, 120, 60, 30);
		qtyUpdateBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
		qtyUpdateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		qtyUpdateBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String sql = "UPDATE stock SET stock_count = ? WHERE item_name = ?";
				int row = 0;
				try (
						Connection conn = DBConnector.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
					pstmt.setString(1, qtyLable.getText());
					pstmt.setString(2, itemName.getText());
					
					row = pstmt.executeUpdate();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				if(row >= 1) {
					JOptionPane.showMessageDialog(stockListPanel, "재고 수량을 수정했습니다.");
					OrderJPanel orderPanel = stockListPanel.getOrderPanel();
					stockListPanel.setVisible(false);
					orderPanel.setStockListPanel(new StockListJPanel(orderPanel));
					orderPanel.getStockListJPanel().setVisible(true);
					
					qtyframe.dispose();
				} else {
					JOptionPane.showMessageDialog(stockListPanel, "재고 수량을 수정에 실패했습니다.");
				}
			}
		
		});
		
		
		qtypanel.add(itemName);
		qtypanel.add(leftBtn);
		qtypanel.add(qtyLable);
		qtypanel.add(rightBtn);
		qtypanel.add(qtyUpdateBtn);
		

		
		stockListPanel.setQtyframe(qtyframe);
		qtyframe.setVisible(true);
		

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
