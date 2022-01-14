package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Order_list;
import oneteampos.order.cotainer.CartJPanel;
import oneteampos.order.cotainer.OrderListJPanel;

public class DeleteCartListener implements MouseListener {
	
	CartJPanel cartPanel;
	
	public DeleteCartListener(CartJPanel cartPanel) {
		this.cartPanel = cartPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*카트에서 목록 삭제 리스너*/

		// 테이블에서 발주 리스트 지우기
		JTable deletetable = cartPanel.getDeleteJTable();
		DefaultTableModel dmodel = (DefaultTableModel) deletetable.getModel();
		int idx1 = deletetable.getSelectedRow();
		int idx2 = deletetable.getSelectedColumn();

		JTable cartTable = cartPanel.getOrderTable();
		DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
		Object item_name = cartTable.getValueAt(idx1, 0);
		Object item_amount = cartTable.getValueAt(idx1, 1);

		model.removeRow(idx1);
		dmodel.removeRow(idx1);

		// 지울 행 데이터 가져오기
		ArrayList<Order_list> orderList = cartPanel.getOrderList();

		// 총 결제 금액 차감
		int item_price = 0;

		String sql = "SELECT item_price FROM items where item_name = '" + item_name.toString() + "'";

		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				item_price = rs.getInt("item_price");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		int totalPrice = Integer.parseInt(cartPanel.getTotalPriceLabel().getText());
		totalPrice -= (Integer.parseInt(item_amount.toString()) * item_price);
		cartPanel.getTotalPriceLabel().setText(totalPrice + "");

		// 발주 리스트 데이터 지우기
		orderList.remove(idx1);

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
