package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Order_list;
import oneteampos.datamodel.Stock;
import oneteampos.order.cotainer.AddItemJPanel;
import oneteampos.order.cotainer.CartJPanel;
import oneteampos.order.cotainer.OrderListJPanel;

public class AddOrderBtnListener implements MouseListener{
	
	OrderListJPanel orderListPanel;
	AddItemJPanel addItemPanel;
	CartJPanel cartPanel;
	
	public AddOrderBtnListener(OrderListJPanel orderListPanel) {
		this.orderListPanel = orderListPanel;
		this.addItemPanel = orderListPanel.getaddItemPanel();
		this.cartPanel = orderListPanel.getCartPanel();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
		/*카트 추가 리스너*/
		
		// 담을 품목의 개수 
		Integer item_amount = Integer.parseInt(orderListPanel.getaddItemPanel().getCountLabel().getText());
		
		if(item_amount == 0) { // 담을 품목이 없을때
			
			// 알림 팝업창 띄우기
			JOptionPane.showMessageDialog(addItemPanel, "수량이 0이므로 추가할 수 없습니다.");
			
		} else { // 담을 품목이 있을때
		
		// Order_list의 변수들 만들어서 채우주기 
		String list_num = "order_list_list_id_seq";
		Integer order_id = 0;
		String item_name = orderListPanel.getaddItemPanel().getItemNameLabel().getText();
		
		// 마지막 발주번호 꺼내오기
		String sql = "SELECT MAX(order_id) AS max_order FROM store_order";

		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				order_id = rs.getInt("max_order");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		// Order_List 추가 
		Order_list order_list = new Order_list(list_num, order_id, item_name, item_amount);
		orderListPanel.getCartPanel().getOrderList().add(order_list);
		// 테이블 행 추가 
		JTable table = orderListPanel.getCartPanel().getOrderTable();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		String[] strArr  = new String[2];
		strArr[0] = item_name; 
		strArr[1] = item_amount + "";
		model.addRow(strArr);
		
		JTable deleteTable = orderListPanel.getCartPanel().getDeleteJTable();
		DefaultTableModel dmodel = (DefaultTableModel)deleteTable.getModel();
		String[] xArr = new String[1];
		xArr[0] =  "X";
		dmodel.addRow(xArr);
		
		// 총 가격 계산 
		// CartJPanel에 있는 총가격 라벨 
		JLabel totalPriceLabel = orderListPanel.getCartPanel().getTotalPriceLabel() ;
		int totalPrice = Integer.parseInt(totalPriceLabel.getText());
		totalPrice += (item_amount * Integer.parseInt(orderListPanel.getaddItemPanel().getItemRealPriceLabel().getText()));
		totalPriceLabel.setText(totalPrice + "");
		orderListPanel.getCartPanel().getTotalPriceLabel().setText(totalPrice + "");
		
		// 판넬 닫기
		orderListPanel.getaddItemPanel().setVisibleFalse();
		
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
