package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.menu.container.MenuManagePanel;

public class MenuCheckBoxAction implements ActionListener {

	private MenuManagePanel menuManagePanel;
	
	public MenuCheckBoxAction(MenuManagePanel menuManagePanel) {
		this.menuManagePanel = menuManagePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int row = menuManagePanel.getMenuTable().getSelectedRow();
		
		if(menuManagePanel.getChkBox().isSelected()) {
			menuManagePanel.getList().add(row);
		} else {
			menuManagePanel.getList().remove(row);
		}
	}
}
