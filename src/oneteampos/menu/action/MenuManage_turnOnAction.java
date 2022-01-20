package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuManage_dialog;

public class MenuManage_turnOnAction implements ActionListener {
	
	private MenuManage_dialog menuManagePanel;
	
	public MenuManage_turnOnAction(MenuManage_dialog menuManagePanel) {
		this.menuManagePanel = menuManagePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		menuManagePanel.getmenuManageDialog().setVisible(true);
	}
}
