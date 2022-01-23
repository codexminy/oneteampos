package oneteampos.receipt.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.receipt.components.ReturnBtn;
import oneteampos.receipt.container.ReceiptJPanel;
import oneteampos.receipt.container.ReturnJPanel;

public class ReturnBtnListener implements MouseListener {
	
	ReceiptJPanel receiptJPanel;
	ReturnJPanel returnJPanel;
	boolean func;
	
	public ReturnBtnListener(ReceiptJPanel receiptJPanel, boolean func) {
		this.receiptJPanel = receiptJPanel;
		this.func = func;
	}

	public ReturnBtnListener(ReturnJPanel returnJPanel, boolean func) {
		this.returnJPanel = returnJPanel;
		this.func = func;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		
		if(func) { // 반품 누르고 들어온 ReturnJPanel서의 반품버튼
			
			this.receiptJPanel = returnJPanel.getReceiptJPanel();
			JTable receiptTable = receiptJPanel.getReceiptTablePanel().getReceiptInfoTable();
			int rowSelected = receiptTable.getSelectedRow();
			if(receiptTable.getValueAt(rowSelected, 5).equals("결제완료")) {
		
				// 주문내역ID로 주문확정 바꾸기
				
				// 주문ID 가져오기
				int id = returnJPanel.getReturnList().get(0).getID();
				
				// 주문내역테이블 주문확정 변경
				int row = 0;
				String sql = "UPDATE order_details SET order_confirmation = 'N' WHERE order_history_id =" + id;
				 
	
				try (
						Connection conn = DBConnector.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
					row = pstmt.executeUpdate();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				// 적립금 받아오기 
				
//				sql = "";
//				
//				try (
//						Connection conn = DBConnector.getConnection();
//						PreparedStatement pstmt = conn.prepareStatement(sql);
//						ResultSet rs = pstmt.executeQuery();
//				) {
//					row = pstmt.executeUpdate();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
				
				// 포인트 받아오기
//				
//				sql = "";
//				
//				try (
//						Connection conn = DBConnector.getConnection();
//						PreparedStatement pstmt = conn.prepareStatement(sql);
//				) {
//					row = pstmt.executeUpdate();
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
				
				
				// 포인트 복구
				
				
				
				
				
				if(row < 0) {
					// 반품 실패
					JOptionPane.showMessageDialog(returnJPanel, "반품 실패");
				} else {
					// 반품 완료
					JOptionPane.showMessageDialog(returnJPanel, "반품 완료");
					
					// 레시피 판넬 새로 넣어주기
					MainFrame mainFrame = receiptJPanel.getMainFrame();
					receiptJPanel.setVisible(false);
					mainFrame.add(new ReceiptJPanel(mainFrame));
				}
			} else {
				JOptionPane.showMessageDialog(receiptJPanel, "이미 반품된 상품입니다.");
			}
			
		} else { 
			if(receiptJPanel.getReceiptTablePanel().getReceiptInfoTable().getSelectedRow() >= 0) {
				receiptJPanel.getReceiptTablePanel().setVisible(false);
				// 라벨 이름 바꿔주기
				receiptJPanel.getReceiptLabel().setText("영수증 (반품)");
				ReturnJPanel returnPanel = new ReturnJPanel(receiptJPanel);
				returnPanel.add(new ReturnBtn(returnPanel, true));
				receiptJPanel.add(returnPanel);
			} else {
				JOptionPane.showMessageDialog(receiptJPanel, "반품을 선택해주세요.");
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
