package oneteampos.menu.action;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.data.CafeMenuData;

public class MenuUpdateAction {
	
	public MenuUpdateAction(MainFrame mainFrame) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		
//		menuLeftPanel.getMenuManagePanel().getRow().clear();
//		menuLeftPanel.getMenuManagePanel().inputMenuData();
//		menuLeftPanel.getCardMenuPanel().removeAll();
//		menuLeftPanel.inputMenuPanel(menuLeftPanel.getMenuNames(), menuLeftPanel.getMenuConditions());
//		menuLeftPanel.getCardMenuPanel().revalidate();
//		menuLeftPanel.getMenuManagePanel().getmenuManageDialog().revalidate();
//		menuLeftPanel.getMenuManagePanel().getList().clear();
//		menuLeftPanel.getMenuManagePanel().getMenuTable().revalidate();
		mlp.getCafeMenuData().getMenuData().removeAll(mlp.getCafeMenuData().getMenuData());
		new CafeMenuData();
		mlp.getMenuManagePanel().getRow().clear();
		mlp.getMenuManagePanel().inputMenuData();
		mlp.getCardMenuPanel().removeAll();
		mlp.inputMenuPanel(mlp.getMenuNames(), mlp.getMenuConditions());
		mlp.getCardMenuPanel().revalidate();
		mlp.getMenuManagePanel().getmenuManageDialog().revalidate();
		mainFrame.getMainPanel().getMenuPanel().getList().clear();
		mlp.getMenuManagePanel().getMenuTable().revalidate();
	}
}
