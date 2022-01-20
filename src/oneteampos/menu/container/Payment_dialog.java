package oneteampos.menu.container;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.data.OrderDetailsData;
import oneteampos.menu.data.OrdersData;
import oneteampos.menu.etc.ChangeStr;

public class Payment_dialog extends JDialog {
	
	private Vector<OrdersData> orders;
	private Vector<OrderDetailsData> orderDetail;
	private JLabel cardNum;
	private MenuRightPanel mrp;
	
	public Payment_dialog(MainFrame mainFrame, MenuRightPanel mrp) {
		super(mainFrame, "결제 방법", true);
		this.mrp = mrp;
		
		JPanel cardPanel = new JPanel(new CardLayout());
		
		JPanel bg = new JPanel(null);

		JButton card = new JButton("카드");
		JButton cash = new JButton("현금");

		card.setBounds(70, 80, 100, 100);
		cash.setBounds(200, 80, 100, 100);
		
		bg.add(card);
		bg.add(cash);

		cash.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout)cardPanel.getLayout();
				card.show(cardPanel, "현금");
			}
			
		});

		
		JPanel cardBg = new JPanel(null);
		
		JLabel cardTitle = new JLabel("카드 결제 완료");
		
		int cardNum1 = (int)(Math.random()*8999+1000);
		int cardNum2 = (int)(Math.random()*8999+1000);
		int cardNum3 = (int)(Math.random()*8999+1000);
		
		JLabel cardName = new JLabel("카드번호");
		cardNum = new JLabel(String.format("%d-%d-%d", cardNum1, cardNum2, cardNum3));
		JLabel approval = new JLabel("승인금액");
		JLabel approvalCash = new JLabel(mainFrame.getMainPanel().getMenuPanel().getRightPanel().getTotalPrice().getText());
		
		JButton yes = new JButton("확인");
		yes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
		});
		
		card.addMouseListener(new MouseAdapter() {
				
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout)cardPanel.getLayout();
				card.show(cardPanel, "카드");
				
//				MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
				
//				int row = 0;
//				int total = Integer.parseInt(rp.getTotalPrice().getText().substring(2).equals("") ? "0" : rp.getTotalPrice().getText().substring(2));
//				int discnt;
//				int save;
				
				int row = 0;
//				int total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2).equals("") ? "0" : mrp.getTotalPrice().getText().substring(2));
				int total = Integer.parseInt(ChangeStr.setErase(mrp.getTotalPrice().getText()));
				int discnt;
				int save;
				
				Object memberId = 0;
				
				Member_inquiryDialog mcd = mrp.getMemeberCheckDialog();

				if(mcd != null) {
					row = mcd.getTable().getSelectedRow();
//					total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2).equals("") ? "0" : mrp.getTotalPrice().getText().substring(2));
//					discnt = Integer.parseInt(mrp.getDiscountCash().getText().equals("") ? "0" : mrp.getDiscountCash().getText());
					discnt = Integer.parseInt(ChangeStr.setErase(mrp.getDiscountCash().getText()));
//					save = Integer.parseInt(mcd.getSaveCash().getText().equals("") ? "0" : mcd.getSaveCash().getText());
					save = Integer.parseInt(ChangeStr.setErase(mcd.getSaveCash().getText()));
					memberId = mcd.getTable().getValueAt(row, 1);
					updatePointData(mcd.getTable().getColumnName(5), mcd.getPoint(), (String)mcd.getTable().getValueAt(row, 2));
					mcd.setPoint(0);
					mcd.getSaveCash().setText("");
//					updateOrderData(memberId, total + discnt, save, discnt, total);
//					orders = insertOrderData();
//					updateOrderDetailsData();
//					orderDetail = insertOrderDetailData();
				} else {
					memberId = null;
					discnt = 0;
					save = 0;
				}

//				if(rp.d != null) {
//					row = rp.d.table.getSelectedRow();
//					total = Integer.parseInt(rp.getTotalPrice().getText().substring(2).equals("") ? "0" : rp.getTotalPrice().getText().substring(2));
//					discnt = Integer.parseInt(rp.getDiscountCash().getText().equals("") ? "0" : rp.getDiscountCash().getText());
//					save = Integer.parseInt(rp.d.saveCash.getText().equals("") ? "0" : rp.d.saveCash.getText());
//					memberId = rp.d.table.getValueAt(row, 1);
//					updatePointData(rp.d.table.getColumnName(5), rp.d.point, (String)rp.d.table.getValueAt(row, 2));
//					rp.d.point = 0;
//					rp.d.saveCash.setText("");
////					updateOrderData(memberId, total + discnt, save, discnt, total);
////					orders = insertOrderData();
////					updateOrderDetailsData();
////					orderDetail = insertOrderDetailData();
//				} else {
//					memberId = null;
//					discnt = 0;
//					save = 0;
//				}
				
				updateOrderData(memberId, total + discnt, save, discnt, total);
				orders = insertOrderData();
				updateOrderDetailsData();
				orderDetail = insertOrderDetailData();
				updatePaymentData("card", cardNum.getText().replaceAll("-", ""));
				excute();
				mrp.getDiscountCash().setText("0");
				mrp.getTotalPrice().setText("0");
				mrp.getDiscountCash().setVisible(false);
				mrp.getTotalPrice().setVisible(false);
				
				Vector<Vector<Object>> menuIdList = mrp.getMenuIdList();
				Vector<Vector<Object>> menuCntList = mrp.getMenuCntList();
				
//				System.out.println("결제 전" + menuIdList);
//				System.out.println("결제 전" + menuCntList);
				
				menuIdList.removeAll(menuIdList);
				menuCntList.removeAll(menuCntList);
				
//				System.out.println("결제 후" + menuIdList);
//				System.out.println("결제 후" + menuCntList);
				
				
				DefaultTableModel model = mrp.getModel();
				
				while(model.getRowCount() > 0) {
					model.removeRow(0);
				}
				
//				for(int i=0; i<=model.getRowCount(); ++i) {
//					System.out.println(model.getRowCount());
//					model.removeRow(0);
//				}
				mrp.getCancelBtn().setVisible(false);
				mrp.setIsDisCnt(false);

//				rp.cancelBtn.setVisible(false);
//				rp.isDisCnt = false;
//				for(int i=0; i<mdp.getMenuIdList().size(); ++i) {
//					for(int j=0; j<mdp.getMenuIdList().get(i).size(); ++j) {
//						updateCartData(mdp.getMenuIdList().get(i).get(j), mdp.getMenuCntList().get(i).get(j));
//					}
//				}
			}
			
		});

		cardTitle.setBounds(150, 20, 100, 30);
		cardName.setBounds(80, 150, 50, 30);
		cardNum.setBounds(160, 150, 200, 30);
		approval.setBounds(100, 200, 200, 30);
		approvalCash.setBounds(100, 230, 200, 30);
		yes.setBounds(100, 280, 100, 30);
		
		cardBg.add(cardTitle);
		cardBg.add(cardName);
		cardBg.add(cardNum);
		cardBg.add(approval);
		cardBg.add(approvalCash);
		cardBg.add(yes);

		JPanel cashBg = new JPanel(null);
		
		JLabel cashTitle = new JLabel("현금결제");
		cashTitle.setBounds(100, 20, 100, 30);
		
		JLabel takeName = new JLabel("받은 금액");
		takeName.setBounds(30, 70, 50, 30);
		JTextField takeCash = new JTextField();
		takeCash.setBounds(100, 70, 70, 30);
		JLabel takeWon = new JLabel("원");
		takeWon.setBounds(180, 70, 50, 30);
		
		JButton enter = new JButton("입력");
		enter.setBounds(230, 70, 100, 30);
		
		JLabel totalName = new JLabel("결제 금액");
		totalName.setBounds(30, 110, 50, 30);
		JLabel totalCash = new JLabel(mrp.getTotalPrice().getText().substring(2));
		totalCash.setBounds(100, 110, 70, 30);
		JLabel totalWon = new JLabel("원");
		totalWon.setBounds(180, 110, 50, 30);
		
		
		JButton cashGo = new JButton("결제");
		cashGo.setBounds(50, 300, 100, 30);
		cashGo.setEnabled(false);
		
		JLabel change = new JLabel("거스름돈");
		change.setBounds(30, 200, 50, 30);
		JLabel changeCash = new JLabel("0");
		changeCash.setBounds(90, 200, 50, 30);
		
		enter.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int current = 0;
				int total = Integer.parseInt(totalCash.getText());
				
				if(takeCash.getText().length() != 0) {
					current = Integer.parseInt(takeCash.getText());
				}

				if(takeCash.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "받은 금액을 적어주세요!", "Message", JOptionPane.WARNING_MESSAGE);
				} else if(current < total) {
					JOptionPane.showMessageDialog(null, "받은 금액이 적습니다!", "Message", JOptionPane.WARNING_MESSAGE);
				} else {
					changeCash.setText((current - total)+"");
					cashGo.setEnabled(true);
				}
			}
			
		});

		cashGo.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cashGo.isEnabled()) {
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
//					MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
					
//					int row = 0;
//					int total = Integer.parseInt(rp.getTotalPrice().getText().substring(2).equals("") ? "0" : rp.getTotalPrice().getText().substring(2));
//					int discnt;
//					int save;
					
					int row = 0;
//					int total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2).equals("") ? "0" : mrp.getTotalPrice().getText().substring(2));
					int total = Integer.parseInt(ChangeStr.setErase(mrp.getTotalPrice().getText()));
					int discnt;
					int save;
					
					Object memberId = 0;
					
					Member_inquiryDialog mcd = mrp.getMemeberCheckDialog();
					
					if(mcd != null) {
						row = mcd.getTable().getSelectedRow();
//						total = Integer.parseInt(mrp.getTotalPrice().getText().substring(2).equals("") ? "0" : mrp.getTotalPrice().getText().substring(2));
						discnt = Integer.parseInt(ChangeStr.setErase(mrp.getDiscountCash().getText()));
//						discnt = Integer.parseInt(mrp.getDiscountCash().getText().equals("") ? "0" : mrp.getDiscountCash().getText());
//						save = Integer.parseInt(mcd.getSaveCash().getText().equals("") ? "0" : mcd.getSaveCash().getText());
						save = Integer.parseInt(ChangeStr.setErase(mcd.getSaveCash().getText()));
						memberId = mcd.getTable().getValueAt(row, 1);
						updatePointData(mcd.getTable().getColumnName(5), mcd.getPoint(), (String)mcd.getTable().getValueAt(row, 2));
						mcd.setPoint(0);
						mcd.getSaveCash().setText("");
//						updateOrderData(memberId, total + discnt, save, discnt, total);
//						orders = insertOrderData();
//						updateOrderDetailsData();
//						orderDetail = insertOrderDetailData();
					} else {
						memberId = null;
						discnt = 0;
						save = 0;
						
					}
					
//					if(rp.d != null) {
//						row = rp.d.table.getSelectedRow();
//						total = Integer.parseInt(rp.getTotalPrice().getText().substring(2).equals("") ? "0" : rp.getTotalPrice().getText().substring(2));
//						discnt = Integer.parseInt(rp.getDiscountCash().getText().equals("") ? "0" : rp.getDiscountCash().getText());
//						save = Integer.parseInt(rp.d.saveCash.getText().equals("") ? "0" : rp.d.saveCash.getText());
//						memberId = rp.d.table.getValueAt(row, 1);
//						updatePointData(rp.d.table.getColumnName(5), rp.d.point, (String)rp.d.table.getValueAt(row, 2));
//						rp.d.point = 0;
//						rp.d.saveCash.setText("");
////						updateOrderData(memberId, total + discnt, save, discnt, total);
////						orders = insertOrderData();
////						updateOrderDetailsData();
////						orderDetail = insertOrderDetailData();
//					} else {
//						memberId = null;
//						discnt = 0;
//						save = 0;
//						
//					}
					
					updateOrderData(memberId, total + discnt, save, discnt, total);
					orders = insertOrderData();
					updateOrderDetailsData();
					orderDetail = insertOrderDetailData();
					updatePaymentData("cash", null);
					excute();
					
					mrp.getDiscountCash().setText("0");
					mrp.getTotalPrice().setText("0");
					mrp.getDiscountCash().setVisible(false);
					mrp.getTotalPrice().setVisible(false);
					
					mrp.getCancelBtn().setVisible(false);
					mrp.setIsDisCnt(false);
					
					
					Vector<Vector<Object>> menuIdList = mrp.getMenuIdList();
					Vector<Vector<Object>> menuCntList = mrp.getMenuCntList();
					
//					System.out.println("결제 전" + menuIdList);
//					System.out.println("결제 전" + menuCntList);
					
					menuIdList.removeAll(menuIdList);
					menuCntList.removeAll(menuCntList);
					
//					System.out.println("결제 후" + menuIdList);
//					System.out.println("결제 후" + menuCntList);
					
//					rp.isDisCnt = false;
					
					DefaultTableModel model = mrp.getModel();

					while(model.getRowCount() > 0) {
						model.removeRow(0);
					}
					
//					for(int i=0; i<=model.getRowCount(); ++i) {
//						System.out.println(model.getRowCount());
//						model.removeRow(0);
//					}
					dispose();
//					rp.cancelBtn.setVisible(false);
//					MenuDetailsPanel mdp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel().getMenuDetailsPanel();
//					System.out.println(mdp.getMenuIdList().size());
//					for(int i=0; i<mdp.getMenuIdList().size(); ++i) {
//						for(int j=0; j<mdp.getMenuIdList().get(i).size(); ++j) {
//							updateCartData(mdp.getMenuIdList().get(i).get(j), mdp.getMenuCntList().get(i).get(j));
//						}
//					}
				}
			}
			
		});
		
		
		
		JPanel g = new JPanel(new GridLayout(4,3));
		String[] num = new String[] {"7", "8", "9", "4", "5", "6", "1", "2", "3", "clear", "0", "<"};
		
		for(int i=0; i<num.length; ++i) {
			JButton btn = new JButton(num[i]);
			g.add(btn);
			btn.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					String text = takeCash.getText();

					if(btn.getText().equals("clear")) {
						takeCash.setText("");
					} else if(text.length() != 0 && btn.getText().equals("<")) {
						takeCash.setText(text.substring(0, text.length()-1));
					} else if(!btn.getText().equals("<")) {
						takeCash.setText(text + btn.getText());
					}
				}
				
			});
		}
		
		g.setBounds(200, 200, 150, 150);

		cashBg.add(cashTitle);
		cashBg.add(takeName);
		cashBg.add(takeCash);
		cashBg.add(takeWon);
		cashBg.add(enter);
		cashBg.add(totalName);
		cashBg.add(totalCash);
		cashBg.add(totalWon);
		cashBg.add(g);
		cashBg.add(cashGo);
		cashBg.add(change);
		cashBg.add(changeCash);

		cardPanel.add("첫화면", bg);
		cardPanel.add("카드", cardBg);
		cardPanel.add("현금", cashBg);

		add(cardPanel);
		
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
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
	
	private void updateOrderData(Object member_id, int total, int point_save, int discount_amount, int payment_amount) {
		String sql = String.format("INSERT INTO orders VALUES(orders_order_id_seq.nextval, sysdate, %s, %d, %d, %d, %d)", member_id, total, point_save, discount_amount, payment_amount);

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateOrderDetailsData() {
		String sql = String.format("INSERT INTO order_details VALUES(order_details_history_id_seq.nextval, %d, '%s')", orders.get(orders.size()-1).getOrderId(), "Y");

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updatePaymentData(String pay_type, Object card) {
		String sql = String.format("INSERT INTO payment VALUES(payment_pay_id_seq.nextval, %d, '%s', %s, sysdate)", orderDetail.get(orderDetail.size()-1).getOrderHistoryId(), pay_type, card);

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateCartData(Object menu_id, Object menu_amount) {
		String sql = String.format("INSERT INTO cart VALUES(cart_cart_id_seq.nextval, %d, %s, %s)", orderDetail.get(orderDetail.size()-1).getOrderHistoryId(), menu_id, menu_amount);

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updatePointData(String column, int point, String phone_number) {
		String sql = String.format("UPDATE members SET %s=%d WHERE phone_number='%s'", column, point, phone_number.replaceAll("-", ""));
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}











