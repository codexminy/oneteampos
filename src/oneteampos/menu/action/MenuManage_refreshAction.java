package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuManage_refreshDialog;

public class MenuManage_refreshAction implements ActionListener {

	private MainFrame mainFrame;
	
	public MenuManage_refreshAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MenuManage_refreshDialog mrd = new MenuManage_refreshDialog(mainFrame);
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		DefaultTableModel tm = (DefaultTableModel)mlp.getMenuManage_dialog().getMenuTable().getModel();
		TreeSet<Integer> list = mainFrame.getMainPanel().getMenuPanel().getList();
		
		if(list.size() == 0) {
			JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요!", "Message", JOptionPane.CANCEL_OPTION);
			return;
		} else if(list.size() > 1) {
			JOptionPane.showMessageDialog(null, "메뉴를 1개만 선택해주세요!", "Message", JOptionPane.CANCEL_OPTION);
			return;
		}
		
		int row = list.first();
		
		mrd.getNameField().setText(tm.getValueAt(row, 2).toString());
		mrd.getPriceField().setText(tm.getValueAt(row, 3).toString());
		mrd.getTypeField().setText(tm.getValueAt(row, 4).toString());

		mrd.setVisible(true);
	}
}
