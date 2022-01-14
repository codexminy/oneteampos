package oneteampos.menu.action;

import oneteampos.menu.container.MenuLeftPanel;

public class MenuUpdateAction {
	
	public MenuUpdateAction(MenuLeftPanel menuLeftPanel) {
		menuLeftPanel.getMenuManagePanel().getRow().clear();
		menuLeftPanel.getMenuManagePanel().inputMenuData();
		menuLeftPanel.getCardMenuPanel().removeAll();
		menuLeftPanel.inputMenuPanel(menuLeftPanel.getMenuNames(), menuLeftPanel.getMenuConditions());
		menuLeftPanel.getCardMenuPanel().revalidate();
		menuLeftPanel.getMenuManagePanel().getmenuManageDialog().revalidate();
		menuLeftPanel.getMenuManagePanel().getList().clear();
		menuLeftPanel.getMenuManagePanel().getMenuTable().revalidate();
	}
}
