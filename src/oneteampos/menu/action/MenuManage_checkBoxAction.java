package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuManage_dialog;

public class MenuManage_checkBoxAction implements ActionListener {

	private MainFrame mainFrame;
	private JTable table;
	private JCheckBox box;
	
	public MenuManage_checkBoxAction(MainFrame mainFrame, JTable table, JCheckBox box) {
		this.mainFrame = mainFrame;
		this.table = table;
		this.box = box;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();

		if(box.isSelected()) {
			mainFrame.getMainPanel().getMenuPanel().getList().add(row);
		} else {
			mainFrame.getMainPanel().getMenuPanel().getList().remove(row);
		}
	}
}
