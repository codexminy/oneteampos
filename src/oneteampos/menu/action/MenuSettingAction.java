package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuManagePanel;

public class MenuSettingAction implements ActionListener {
	
	private MenuManagePanel menuManagePanel;
	
	public MenuSettingAction(MenuManagePanel menuManagePanel) {
		this.menuManagePanel = menuManagePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		menuManagePanel.getmenuManageDialog().setVisible(true);
	}
}
