package oneteampos.menu.action;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JRadioButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;

public class MenuManage_updateAction {
	
	public MenuManage_updateAction(MainFrame mainFrame) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		ArrayList<JRadioButton> categoryBtns = mlp.getCategoryBtns();
		
		mlp.getCardMenuPanel().removeAll();
		mlp.inputMenuPanel(mlp.getMenuNames(), mlp.getMenuConditions());
		mlp.getCardMenuPanel().revalidate();
		
		for(int i=0; i<categoryBtns.size(); ++i) {
			if(categoryBtns.get(i).isSelected()) {
				CardLayout c = (CardLayout)mlp.getCardMenuPanel().getLayout();
				c.show(mlp.getCardMenuPanel(), mlp.getMenuNames()[i]);
			}
		}
	}
}
