package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuManagePanel;

public class MenuSettingAction implements ActionListener {
	
	private MainFrame mainFrame;
	private MenuManagePanel menuManagePanel;
	private boolean isOn;
	
	public MenuSettingAction(MainFrame mainFrame, MenuManagePanel menuManagePanel) {
		this.mainFrame = mainFrame;
		this.menuManagePanel = menuManagePanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		menuManagePanel.getmenuManageDialog().setVisible(true);
	}
}
