package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.etc.ChangeStr;

public class Cart_deleteAction extends MouseAdapter {
	
	private MainFrame mainFrame;
	
	public Cart_deleteAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable cart = (JTable)e.getSource();
		
		if(rp.getIsDiscnt()) {
			JOptionPane.showMessageDialog(null, "할인&적립을 취소해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			DefaultTableModel dtm = (DefaultTableModel)cart.getModel();
			String delete = "";
			int row = cart.getSelectedRow();
			
			if(cart.getSelectedColumn() == 4 && cart.getRowCount() > 0) {
				
//				int total = Integer.parseInt(rp.getTotalPrice().getText().replaceAll("\\D", ""));
				int total = Integer.parseInt(ChangeStr.setErase(rp.getTotalPrice().getText()));
				
				delete = (String)cart.getValueAt(row, 2);
//				int current = Integer.parseInt(delete.substring(4));
				int current = Integer.parseInt(ChangeStr.setErase(delete));
				
				dtm.removeRow(row);
//				rp.getTotalPrice().setText("￦ " + (total-current));
				rp.getTotalPrice().setText(ChangeStr.setCashMark(total-current));
				
				
				rp.getMenuIdList().remove(row);
				rp.getMenuCntList().remove(row);
			}
		}
		

		if(cart.getRowCount() == 0) {
			rp.getDiscountCash().setText("0");
			rp.getDiscountCash().setVisible(false);
			rp.getTotalPrice().setVisible(false);
		}
	}
	
}
