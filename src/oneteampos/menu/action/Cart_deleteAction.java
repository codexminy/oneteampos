package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.etc.ChangeString;

public class Cart_deleteAction extends MouseAdapter {
	
	private MainFrame mainFrame;
	
	public Cart_deleteAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable cart = (JTable)e.getSource();
		JLabel totalPrice = rp.getTotalPrice();
		JLabel discountCash = rp.getDiscountCash();
		
		if(rp.getIsDiscnt()) {
			JOptionPane.showMessageDialog(null, "할인&적립을 취소해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			DefaultTableModel dtm = (DefaultTableModel)cart.getModel();
			int row = cart.getSelectedRow();
			
			if(cart.getSelectedColumn() == 4 && cart.getRowCount() > 0) {
				int total = Integer.parseInt(ChangeString.setErase(totalPrice.getText()));
				String delete = (String)cart.getValueAt(row, 2);
				int current = Integer.parseInt(ChangeString.setErase(delete));
				
				dtm.removeRow(row);
				totalPrice.setText(ChangeString.setCashMark(total-current));

				rp.getMenuIdList().remove(row);
				rp.getMenuCntList().remove(row);
			}
		}

		if(cart.getRowCount() == 0) {
			discountCash.setText("0");
			discountCash.setVisible(false);
			totalPrice.setVisible(false);
		}
	}
	
}
