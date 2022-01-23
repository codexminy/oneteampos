package oneteampos.menu.action;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.data.CafeMenuData;

public class MenuManage_updateAction {
	
	public MenuManage_updateAction(MainFrame mainFrame) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		
		mlp.getCardMenuPanel().removeAll();
		mlp.inputMenuPanel(mlp.getMenuNames(), mlp.getMenuConditions());
		mlp.getCardMenuPanel().revalidate();
	}
}
