package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Order_list;
import oneteampos.datamodel.Store_order;
import oneteampos.order.compnents.StoreOrderBtn;
import oneteampos.order.cotainer.AddItemJFrame;
import oneteampos.order.cotainer.CartJPanel;
import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.order.cotainer.OrderListJPanel;

public class StoreOrderBtnListener implements MouseListener{
	
	CartJPanel cartPanel;
	
	public StoreOrderBtnListener(CartJPanel cartPanel) {
		this.cartPanel = cartPanel;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/* 발주 버튼 눌렀을 때 리스너 */
		StoreOrderBtn storeOrderBtn = (StoreOrderBtn)e.getSource();
		storeOrderBtn.setSelected(false);
		
		// 토탈 금액 받아오기
		JLabel totalPriceLabel = cartPanel.getTotalPriceLabel();
		int totalPrice = Integer.parseInt(totalPriceLabel.getText());

		if (totalPrice == 0) {
			// 알림 팝업창 띄우기
			JOptionPane.showMessageDialog(cartPanel.getOrderListPanel(), "발주할 품목이 없습니다.");
		} else {
			// 1. 발주 테이블(store_order) 데이터 넣기
			// store_order_id_seq 시퀀스

			String sql1 = "INSERT INTO store_order VALUES (STORE_ORDER_ID_SEQ.NEXTVAL , ? , sysdate)";

			try (Connection conn = DBConnector.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql1);

			) {

				pstmt.setInt(1, totalPrice);
				int rows = pstmt.executeUpdate();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// 2. 발주 테이블(store_order) 데이터에서 발주ID 얻기

			// 마지막 발주ID 받아 오기
			Integer order_id = 0;
			String sql2 = "SELECT MAX(order_id) AS max_order FROM store_order";

			try (Connection conn = DBConnector.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql2);
					ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					order_id = rs.getInt("max_order");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// 3. 발주리스트 테이블(order_list)에 데이터 넣기

			// list 확인
			ArrayList<Order_list> orderList = cartPanel.getOrderList();

			String sql3 = "INSERT INTO order_list VALUES (order_list_list_id_seq.NEXTVAL , ?, ?, ?)";

			try (
				Connection conn = DBConnector.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql3);

			) {

				for (int i = 0; i < orderList.size(); ++i) {
					// 발주ID
					pstmt.setInt(1, order_id);
					// 품목이름
					pstmt.setString(2, orderList.get(i).getItemName());
					// 품목 수량
					pstmt.setInt(3, Integer.parseInt(orderList.get(i).getItemAmount()));
					int rows = pstmt.executeUpdate();
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// 4. 장바구니 테이블 비우기
			JTable deletetable = cartPanel.getDeleteJTable();
			DefaultTableModel dmodel = (DefaultTableModel) deletetable.getModel();
			int rowCount = dmodel.getRowCount();

			JTable cartTable = cartPanel.getOrderTable();
			DefaultTableModel model = (DefaultTableModel) cartTable.getModel();

			for (int i = 0; i < rowCount; ++i) {
				// 테이블 내용 지우기
				dmodel.removeRow(0);
				model.removeRow(0);
				// 발주 리스트 지우기
				orderList.remove(0);
			}

			// 5. 결제금액 0으로 초기화
			totalPriceLabel.setText("0");

			// 6. 발주 성공 팝업
			// 알림 팝업창 띄우기
			JOptionPane.showMessageDialog(cartPanel.getOrderListPanel(), "발주가 완료 되었습니다.");	

			// 7. 발주목록 테이블 갱신
			OrderListJPanel orderListPanel = cartPanel.getOrderListPanel();
			DefaultTableModel smodel = orderListPanel.getSmodel();
			JTable storeListTabel = orderListPanel.getStoreOrderTable();

			// 최신 발주 데이터 저장
			ArrayList<Store_order> soList = new ArrayList<>();

			String sql = "SELECT order_id, total_pay, TO_CHAR(order_date, 'YYYY-MM-DD') AS order_date FROM store_order WHERE order_id = (SELECT max(order_id) FROM store_order)";

			try (Connection conn = DBConnector.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					soList.add(new Store_order(rs));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			String[] strArr = new String[3];
			strArr[0] = soList.get(0).getOrderId() + "";
			strArr[1] = soList.get(0).getTotalPay() + "";
			strArr[2] = soList.get(0).getOrderDate();
			smodel.addRow(strArr);
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
