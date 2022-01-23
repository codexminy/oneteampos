package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuManage_dialog;

public class MenuManage_turnOnAction implements ActionListener {
	
	private MainFrame mainFrame;
	private MenuLeftPanel leftPanel;
	
	public MenuManage_turnOnAction(MainFrame mainFrame, MenuLeftPanel leftPanel) {
		this.mainFrame = mainFrame;
		this.leftPanel = leftPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		leftPanel.setMenuManageDialog(new MenuManage_dialog(mainFrame, leftPanel));
		leftPanel.getMenuManage_dialog().setVisible(true);
	}
}
