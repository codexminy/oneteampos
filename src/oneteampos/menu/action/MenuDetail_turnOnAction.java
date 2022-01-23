package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeString;

public class MenuDetail_turnOnAction implements ActionListener {

	private MainFrame mainFrame;
	private JLabel menuName;
	private JLabel menuPrice;
	private ArrayList<MenuData> arrayList;
	
	public MenuDetail_turnOnAction(MainFrame mainFrame, JLabel menuName, JLabel menuPrice, ArrayList<MenuData> arrayList) {
		this.mainFrame = mainFrame;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.arrayList = arrayList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MenuLeftPanel mlp = mainFrame.getMainPanel().getMenuPanel().getLeftPanel();
		
		mlp.setMenuDetailDialog(new MenuDetail_dialog(mainFrame, menuName.getText(), ChangeString.setErase(menuPrice.getText()), arrayList));
		mlp.getMenuDetail_dialog().setVisible(true);
	}
	
}
