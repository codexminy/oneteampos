package oneteampos.menu.container;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuHomeAction;
import oneteampos.menu.action.MenuNextAction;
import oneteampos.menu.action.MenuPrevAction;
import oneteampos.menu.action.MenuSettingAction;
import oneteampos.menu.component.MenuBtns;
import oneteampos.menu.component.MenuChoiceBtn;
import oneteampos.menu.component.MenuEmptyBtn;
import oneteampos.menu.component.MenuInfoLabel;
import oneteampos.menu.component.MenuItemBtn;
import oneteampos.menu.component.MenuLabel;
import oneteampos.menu.data.CafeMenuData;
import oneteampos.menu.data.MenuData;

public class MenuLeftPanel extends JPanel {
	
	private final static String[] menuNames = new String[] {"세트메뉴", "신메뉴", "커피", "스무디&프라페", "에이드", "기타음료", "티", "주스", "디저트"};
	private final static String[] menuConditions = new String[] {"coffee", "frappuccino", "dessert", "temperature", "coffee", "frappuccino", "dessert", "temperature", "coffee"};
	private final static int xgap = 10;
	private final static int hgap = 10;
	private final static int gap = 20;
	private final static int menuSize = 12;
	private MainFrame mainFrame;
	private MenuManagePanel menuManagePanel;
	private MenuDetailsPanel menuDetailsPanel;
	private JPanel mainMenuPanel;
	private JPanel cardMenuPanel;
	private ButtonGroup buttonGroup;
	private CafeMenuData cafeMenuData;
	private JButton nextBtn;
	private JButton prevBtn;
	private JLabel infoId;
	private JLabel infoName;

	public MenuLeftPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.menuManagePanel = new MenuManagePanel(mainFrame, this);
		this.cardMenuPanel = new JPanel(new CardLayout());
		this.buttonGroup = new ButtonGroup();
		this.cafeMenuData = new CafeMenuData();
		this.prevBtn = new MenuBtns(" ◀ ");
		this.nextBtn = new MenuBtns(" ▶ ");
		this.infoId = new MenuInfoLabel();
		this.infoName = new MenuInfoLabel();

		JPanel menuPanel = new JPanel(new GridLayout(1,1,5,5));
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		JPanel settingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));
		JPanel receiptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		JPanel movePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));
		
		JButton menuSettingBtn = new MenuBtns("메뉴 관리");
		JButton receiptBtn = new MenuBtns("영수증");
		JButton homeBtn = new MenuBtns("<");

		menuPanel.setBackground(Color.WHITE);
		infoPanel.setBackground(Color.WHITE);
		settingPanel.setBackground(Color.WHITE);
		receiptPanel.setBackground(Color.WHITE);
		movePanel.setBackground(Color.WHITE);
		
		receiptPanel.add(receiptBtn);
		movePanel.add(prevBtn);
		movePanel.add(nextBtn);
		infoPanel.add(infoId);
		infoPanel.add(infoName);
		settingPanel.add(menuSettingBtn);
		settingPanel.add(homeBtn);

		homeBtn.addMouseListener(new MenuHomeAction(mainFrame));
		menuSettingBtn.addActionListener(new MenuSettingAction(menuManagePanel));
		
		inputMenuPanel(menuNames, menuConditions);
		createSelectMenuBtn(menuPanel);

		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, MainFrame.FRAME_WIDTH-365, MainFrame.FRAME_HEIGHT-38);
		
		infoPanel.setBounds(gap, gap, getWidth()/4, gap*2);
		settingPanel.setBounds(getWidth()-infoPanel.getWidth()-gap+5, gap, getWidth()/4, gap*2);
		menuPanel.setBounds(gap-5, infoPanel.getHeight()+gap, getWidth()-gap*2+xgap, gap*2);
		cardMenuPanel.setBounds(gap, infoPanel.getHeight()+menuPanel.getHeight()+gap*2, getWidth()-gap*2, (int)(getHeight()*0.72));
		receiptPanel.setBounds(gap, infoPanel.getHeight()+menuPanel.getHeight()+cardMenuPanel.getHeight()+gap*3, getWidth()/4, gap*2);
		movePanel.setBounds(getWidth()-receiptPanel.getWidth()-gap+5, infoPanel.getHeight()+menuPanel.getHeight()+cardMenuPanel.getHeight()+receiptPanel.getHeight()+gap, getWidth()/4, gap*2);
		
		add(infoPanel);
		add(settingPanel);
		add(menuPanel);
		add(cardMenuPanel);
		add(receiptPanel);
		add(movePanel);
	}

	public void inputMenuPanel(String[] menuNames, String[] menuConditions) {
		for(int i=0; i<menuNames.length; ++i) {
			cardMenuPanel.add(menuNames[i], createMenuPanel(cafeMenuData.getMenuData(), menuConditions[i]));
		}
	}
	
	public JPanel createMenuPanel(ArrayList<MenuData> list, String condition) {
		JPanel innerCardPanel = new JPanel(new CardLayout());
		ArrayList<JPanel> gridPanel = new ArrayList<>();
		
		list.clear();
		list = cafeMenuData.insertMenuData();
		
		int cnt = 0;
		int index = 0;
		int loop = 1;
		int total = 0;
		
		for(int i=0; i<list.size(); ++i) {
			if(i == 0 && cnt == 0) {
				gridPanel.add(new JPanel(new GridLayout(3, 4, xgap, hgap)));
				gridPanel.get(index).setBackground(Color.WHITE);
			}
			
			total = condition.equals(list.get(i).getMenuType()) ? total+=1 : total;
			cnt = condition.equals(list.get(i).getMenuType()) ? cnt+=1 : cnt;

			if(cnt > menuSize) {
				gridPanel.add(new JPanel(new GridLayout(3, 4, xgap, hgap)));
				index++;
				gridPanel.get(index).setBackground(Color.WHITE);
				cnt = 1;
			}

			if(condition.equals(list.get(i).getMenuType())) {
				JPanel innerPanel = new JPanel(new BorderLayout());
				JLabel menuName = new MenuLabel(list.get(i).getMenuName(), JLabel.CENTER);
				JLabel menuPrice = new MenuLabel("￦ " + list.get(i).getPrice(), JLabel.RIGHT);
				menuDetailsPanel = new MenuDetailsPanel(mainFrame, list.get(i), list);
				JButton btn = new MenuItemBtn(menuDetailsPanel, menuName, menuPrice);
				
				innerPanel.setOpaque(false);
				innerPanel.add(menuName, "Center");
				innerPanel.add(menuPrice, "South");
				btn.add(innerPanel);
				gridPanel.get(index).add(btn);
			}
		}

		while(true) {
			if(12*loop >= total) {
				total = 12*loop-total;
				break;
			}
			loop++;
		}
		
		for(int i=0; i<=index; ++i) {
			innerCardPanel.add(condition, gridPanel.get(i));
			if(i == index) {
				for(int j=0; j<total; ++j) {
					gridPanel.get(i).add(new MenuEmptyBtn());
				}
			}
		}
		
		nextBtn.addActionListener(new MenuNextAction(innerCardPanel));
		prevBtn.addActionListener(new MenuPrevAction(innerCardPanel));
		
		return innerCardPanel;
	}
	
	public void createSelectMenuBtn(JPanel menuPanel) {
		for(int i=0; i<menuNames.length; ++i) {
			JRadioButton btn = new MenuChoiceBtn(this, menuNames[i], mainFrame);
			if(i == 0) btn.setSelected(true);
			if(btn.isSelected()) {
				CardLayout c = (CardLayout)cardMenuPanel.getLayout();
				c.show(cardMenuPanel, menuNames[i]);
			}
			buttonGroup.add(btn);
			menuPanel.add(btn);
		}
	}

	public MenuManagePanel getMenuManagePanel() {
		return this.menuManagePanel;
	}
	
	public String[] getMenuNames() {
		return menuNames;
	}
	
	public String[] getMenuConditions() {
		return menuConditions;
	}
	
	public JPanel getMainMenuPanel() {
		return this.mainMenuPanel;
	}
	
	public JPanel getCardMenuPanel() {
		return this.cardMenuPanel;
	}

	public JLabel getInfoIdLabel() {
		return this.infoId;
	}
	
	public JLabel getInfoNameLabel() {
		return this.infoName;
	}
	
	public MenuDetailsPanel getMenuDetailsPanel() {
		return this.menuDetailsPanel;
	}
	
	public CafeMenuData getCafeMenuData() {
		return this.cafeMenuData;
	}
}
