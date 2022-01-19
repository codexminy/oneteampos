package oneteampos.menu;

import java.util.TreeSet;

import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuRightPanel;

public class MenuPanel extends JPanel {
	
	private TreeSet<Integer> list;
	private MenuLeftPanel leftPanel; // 메뉴 왼쪽 패널
	private MenuRightPanel rightPanel; // 메뉴 오른쪽 패널(카트)

	public MenuPanel(MainFrame mainFrame) {
		this.list = new TreeSet<>();
		this.leftPanel = new MenuLeftPanel(mainFrame);
		this.rightPanel = new MenuRightPanel(mainFrame, leftPanel);
		
		add(leftPanel);
		add(rightPanel);
		
		setLayout(null);
	}
	
	public MenuLeftPanel getLeftPanel() {
		return this.leftPanel;
	}
	
	public MenuRightPanel getRightPanel() {
		return this.rightPanel;
	}
	
	public TreeSet<Integer> getList() {
		return this.list;
	}
}
