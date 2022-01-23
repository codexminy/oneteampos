package oneteampos.menu;

import java.util.TreeSet;

import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Cart_holdPanel;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuRightPanel;

public class MenuPanel extends JPanel {
	
	private TreeSet<Integer> list;
	private MenuLeftPanel leftPanel;
	private MenuRightPanel rightPanel;
	private Cart_holdPanel holdPanel;
	
	public MenuPanel(MainFrame mainFrame) {
		this.list = new TreeSet<>();
		this.leftPanel = new MenuLeftPanel(mainFrame);
		this.rightPanel = new MenuRightPanel(mainFrame, leftPanel);
		this.holdPanel = new Cart_holdPanel(mainFrame, leftPanel, rightPanel);
		
		add(leftPanel);
		add(holdPanel);
		add(rightPanel);
		
		setLayout(null);
	}
	
	public MenuLeftPanel getLeftPanel() {
		return this.leftPanel;
	}
	
	public MenuRightPanel getRightPanel() {
		return this.rightPanel;
	}
	
	public Cart_holdPanel getHoldPanel() {
		return this.holdPanel;
	}
	
	public TreeSet<Integer> getList() {
		return this.list;
	}
}
