package oneteampos.menu.action;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.data.CafeMenuData;

public class MenuManage_updateAction {
	
	public MenuManage_updateAction(MainFrame mainFrame) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		
		mlp.getCafeMenuData().getMenuData().removeAll(mlp.getCafeMenuData().getMenuData());
		new CafeMenuData();
		mlp.getMenuManage_dialog().getRow().clear();
		mlp.getMenuManage_dialog().inputMenuData();
		mlp.getCardMenuPanel().removeAll();
		mlp.inputMenuPanel(mlp.getMenuNames(), mlp.getMenuConditions());
		mlp.getCardMenuPanel().revalidate();
		mlp.getMenuManage_dialog().getmenuManageDialog().revalidate();
		mainFrame.getMainPanel().getMenuPanel().getList().clear();
		mlp.getMenuManage_dialog().getMenuTable().revalidate();
	}
}
