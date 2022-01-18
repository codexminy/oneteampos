package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuRightPanel;

public class DeleteCartAction extends MouseAdapter {
	
	private MainFrame mainFrame;
	
	public DeleteCartAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		JTable cart = (JTable)e.getSource();
		
		if(rp.isDisCnt) {
			JOptionPane.showMessageDialog(null, "할인&적립을 취소해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			DefaultTableModel dtm = (DefaultTableModel)cart.getModel();
			String delete = "";
			int row = cart.getSelectedRow();
			
			if(cart.getSelectedColumn() == 4 && cart.getRowCount() > 0) {
				
				int total = Integer.parseInt(rp.getTotalPrice().getText().substring(2));
				
				delete = (String) cart.getValueAt(row, 2);
				int current = Integer.parseInt(delete.substring(4));
				
				dtm.removeRow(row);
				rp.getTotalPrice().setText("￦ " + (total-current));
				
				rp.menuIdList.remove(row);
				rp.menuCntList.remove(row);
				
				
				
//			System.out.println(rp.menuIdList);
//			System.out.println(rp.menuCntList);
			}
		}
		

		if(cart.getRowCount() == 0) {
			rp.getDiscountCash().setText("");
			rp.getDiscountCash().setVisible(false);
			rp.getTotalPrice().setVisible(false);
		}
	}
	
}
