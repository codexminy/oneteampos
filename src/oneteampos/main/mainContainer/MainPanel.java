package oneteampos.main.mainContainer;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.main.mainComponent.MainButton;
import oneteampos.members.MemberJPanel;
import oneteampos.menu.MenuPanel;
import oneteampos.staff.containers.StaffJPanel;

public class MainPanel {
	
	private final static String[] mainNames = new String[] {"메뉴", "매출", "발주", "사원", "회원"};
	private MainFrame mainFrame;
	private MenuPanel menuPanel;
	private JPanel cardPanel;
	private JPanel gridPanel;
	private MemberJPanel member;
	private StaffJPanel staffPanel;
	
	public MainPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.cardPanel = new JPanel(new CardLayout());
		this.gridPanel = new JPanel(new GridLayout());
		this.menuPanel = new MenuPanel(mainFrame);
		this.member = new MemberJPanel(mainFrame);
		
		createMainBtns();

		cardPanel.add(gridPanel);
		cardPanel.add(mainNames[0], menuPanel);
		cardPanel.add(mainNames[4], member);
		cardPanel.setVisible(false);
	
	}
	
	private void createMainBtns() {
		for(int i=0; i<mainNames.length; ++i) {
			gridPanel.add(new MainButton(mainNames[i], mainFrame));
		}
	}
	
	public MenuPanel getMenuPanel() {
		return this.menuPanel;
	}
	
	public MemberJPanel getMember() {
		return this.member;
	}
	
	public JPanel getCardPanel() {
		return this.cardPanel;
	}

	public void setVisibleTrue() {
		cardPanel.setVisible(true);
	}
	
	public void setVisibleFalse() {
		cardPanel.setVisible(false);
	}
}
