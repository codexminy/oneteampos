package oneteampos.menu;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.TreeSet;

import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.menu.allLayouts.Gbl;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuRightPanel;

public class MenuPanel extends JPanel {
	
	private TreeSet<Integer> list;
	private MenuLeftPanel leftPanel; // 메뉴 왼쪽 패널
	private MenuRightPanel rightPanel; // 메뉴 오른쪽 패널(장바구니)

	public MenuPanel(MainFrame mainFrame) {
		this.list = new TreeSet<>();
		this.leftPanel = new MenuLeftPanel(mainFrame);
		this.rightPanel = new MenuRightPanel(mainFrame);
		
		GridBagConstraints gbc = Gbl.getGbc(); // 그리드백 레이아웃을 설정하기 위한 컨스트레인트

		setLayout(new GridBagLayout()); // 그리드백 레이아웃으로 설정
		
		add(leftPanel, Gbl.setting(gbc, 0.6, 0.1, 0, 0));
		add(rightPanel, Gbl.setting(gbc, 0.4, 0.1, 1, 0));
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
