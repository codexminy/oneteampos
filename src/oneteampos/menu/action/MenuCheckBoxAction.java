package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuManagePanel;

public class MenuCheckBoxAction implements ActionListener {

	private MainFrame mainFrame;
	private JTable table;
	private JCheckBox box;
	
	public MenuCheckBoxAction(MainFrame mainFrame, JTable table, JCheckBox box) {
		this.mainFrame = mainFrame;
		this.table = table;
		this.box = box;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		int row = menuManagePanel.getMenuTable().getSelectedRow();
//		MenuManagePanel mmp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel().getMenuManagePanel();
//		
//		int row = mmp.getMenuTable().getSelectedRow();

		int row = table.getSelectedRow();
		
//		if(menuManagePanel.getChkBox().isSelected()) {
//			menuManagePanel.getList().add(row);
//		} else {
//			menuManagePanel.getList().remove(row);
//		}
		
//		if(mmp.getChkBox().isSelected()) {
//			mainFrame.getMainPanel().getMenuPanel().getList().add(row);
//			System.out.println(mainFrame.getMainPanel().getMenuPanel().getList());
//		} else {
//			mainFrame.getMainPanel().getMenuPanel().getList().remove(row);
//			System.out.println(mainFrame.getMainPanel().getMenuPanel().getList());
//		}
		
		if(box.isSelected()) {
			mainFrame.getMainPanel().getMenuPanel().getList().add(row);
		} else {
			mainFrame.getMainPanel().getMenuPanel().getList().remove(row);
		}
	}
}
