package oneteampos.order.actions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTextField;

import oneteampos.database.DBConnector;
import oneteampos.order.cotainer.JMenuItemTemplete;
import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.order.cotainer.StockListJPanel;

public class StockTableClickListener implements MouseListener {
	
	StockListJPanel stockListPanel;
	JTable selectTable;

	public StockTableClickListener(StockListJPanel stockListPanel) {
		this.stockListPanel = stockListPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		JTable stockTable = stockListPanel.getStockTable();
		
		
		if(e.getButton() == 1) {
			
		} 
		
		if(e.getClickCount() == 2) {
			
		}
		
		if(e.getButton() == 3) {
			selectTable = (JTable)e.getSource();
			
			if(selectTable.getSelectedRowCount() > 1) {
				JOptionPane.showMessageDialog(stockListPanel, "한 행만 선택해주세요");
			} else {
				if(selectTable.rowAtPoint(e.getPoint()) == selectTable.getSelectedRow() ) { 
					JPopupMenu popup = new JPopupMenu("수정");
					popup.setBackground(new Color(135, 136, 138));
					JMenuItem edit = new JMenuItemTemplete("    수정   ");
					JMenuItem delete = new JMenuItemTemplete("    삭제   ");
					// JMenuItem 클릭시 
					edit.addMouseListener(new ItemQtyUpdate(stockListPanel, e.getX(), e.getY()));
					delete.addMouseListener(new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							String sql = "DELETE FROM stock WHERE item_name = ?";
							int row = 0;
							try (
									Connection conn = DBConnector.getConnection();
									PreparedStatement pstmt = conn.prepareStatement(sql);
							) {
								pstmt.setString(1, selectTable.getValueAt(selectTable.getSelectedRow(), 2) + "");
								
								row = pstmt.executeUpdate();
								
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							if(row >= 1) {
								JOptionPane.showMessageDialog(stockListPanel, "품목을 삭제 했습니다.");
								OrderJPanel orderPanel = stockListPanel.getOrderPanel();
								stockListPanel.setVisible(false);
								orderPanel.setStockListPanel(new StockListJPanel(orderPanel));
								orderPanel.getStockListJPanel().setVisible(true);
							
							} else {
								JOptionPane.showMessageDialog(stockListPanel, "품목 삭제에 실패 했습니다.");
							}
						}
					});
					popup.add(edit);
					popup.add(delete);
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
