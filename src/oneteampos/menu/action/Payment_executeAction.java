package oneteampos.menu.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.menu.container.Member_inquiryDialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.container.Payment_dialog;
import oneteampos.menu.data.OrderDetailsData;
import oneteampos.menu.data.OrdersData;
import oneteampos.menu.etc.ChangeString;

public class Payment_executeAction {

	private Payment_dialog pd;
	private MenuRightPanel mrp;
	
	public Payment_executeAction(Payment_dialog pd, MenuRightPanel mrp, String money) {
		this.pd = pd;
		this.mrp = mrp;
		
		int row = 0;
		int total = ChangeString.setErase(mrp.getTotalPrice().getText());
		int discnt;
		int save;
		
		Object memberId = 0;
		Member_inquiryDialog mcd = mrp.getMemberInquiryDialog();

		if(mcd != null) {
			row = mcd.getTable().getSelectedRow();
			discnt = ChangeString.setErase(mrp.getDiscountCash().getText());
			save = ChangeString.setErase(mcd.getSaveCash().getText());
			memberId = mcd.getTable().getValueAt(row, 1);
			updatePointData(mcd.getTable().getColumnName(5), mcd.getPoint(), (String)mcd.getTable().getValueAt(row, 2));
			mcd.setPoint(0);
			mcd.getSaveCash().setText("");
		} else {
			memberId = null;
			discnt = 0;
			save = 0;
		}
		
		updateOrderData(memberId, total + discnt, save, discnt, total);
		pd.orders = insertOrderData();
		updateOrderDetailsData();
		pd.orderDetail = insertOrderDetailData();
		updatePaymentData(money, pd.cardNumLabel.getText().replaceAll("-", ""));
		excute();
		
		mrp.getDiscountCash().setText("0");
		mrp.getTotalPrice().setText("0");
		mrp.getDiscountCash().setVisible(false);
		mrp.getTotalPrice().setVisible(false);
		
		Vector<Vector<Object>> menuIdList = mrp.getMenuIdList();
		Vector<Vector<Object>> menuCntList = mrp.getMenuCntList();

		menuIdList.clear();
		menuCntList.clear();
		mrp.getPrevSum().clear();
		
		DefaultTableModel model = mrp.getModel();
		
		while(model.getRowCount() > 0) {
			model.removeRow(0);
		}

		mrp.getCancelBtn().setVisible(false);
		mrp.setIsDisCnt(false);
	}
	
	private void excute() {
		for(int i=0; i<mrp.getMenuIdList().size(); ++i) {
			for(int j=0; j<mrp.getMenuIdList().get(i).size(); ++j) {
				updateCartData(mrp.getMenuIdList().get(i).get(j), mrp.getMenuCntList().get(i).get(j));
			}
		}
	}
	
	private Vector<OrdersData> insertOrderData() {
		Vector<OrdersData> orders = new Vector<>();
		String sql = "SELECT * FROM orders ORDER BY order_id";

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				orders.add(new OrdersData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	private Vector<OrderDetailsData> insertOrderDetailData() {
		Vector<OrderDetailsData> orderDetail = new Vector<>();
		String sql = "SELECT * FROM order_details ORDER BY order_history_id";

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				orderDetail.add(new OrderDetailsData(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetail;
	}
	
	private void tryCatchData(String sql) {
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateOrderData(Object member_id, int total, int point_save, int discount_amount, int payment_amount) {
		String sql = String.format("INSERT INTO orders VALUES(orders_order_id_seq.nextval, sysdate, %s, %d, %d, %d, %d)", member_id, total, point_save, discount_amount, payment_amount);
		tryCatchData(sql);
	}

	private void updateOrderDetailsData() {
		String sql = String.format("INSERT INTO order_details VALUES(order_details_history_id_seq.nextval, %d, '%s')", pd.orders.get(pd.orders.size()-1).getOrderId(), "Y");
		tryCatchData(sql);
	}
	
	private void updatePaymentData(String pay_type, Object card) {
		String sql = String.format("INSERT INTO payment VALUES(payment_pay_id_seq.nextval, %d, '%s', %s, sysdate)", pd.orderDetail.get(pd.orderDetail.size()-1).getOrderHistoryId(), pay_type, card);
		tryCatchData(sql);
	}
	
	private void updateCartData(Object menu_id, Object menu_amount) {
		String sql = String.format("INSERT INTO cart VALUES(cart_cart_id_seq.nextval, %d, %s, %s)", pd.orderDetail.get(pd.orderDetail.size()-1).getOrderHistoryId(), menu_id, menu_amount);
		tryCatchData(sql);
	}
	
	private void updatePointData(String column, int point, String phone_number) {
		String sql = String.format("UPDATE members SET %s=%d WHERE phone_number='%s'", column, point, phone_number.replaceAll("-", ""));
		tryCatchData(sql);
	}
	
}
