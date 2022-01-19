package oneteampos.menu.container;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuNextAction;
import oneteampos.menu.action.MenuPrevAction;
import oneteampos.menu.action.MenuSettingAction;
import oneteampos.menu.allLayouts.Gbl;
import oneteampos.menu.component.MenuButton;
import oneteampos.menu.component.MenuItemBtn;
import oneteampos.menu.data.CafeMenuData;
import oneteampos.menu.data.MenuData;
import oneteampos.receipt.actions.ReceiptBtnListener;

public class MenuLeftPanel extends JPanel {
	
	private final static String[] menuNames = new String[] {"세트메뉴", "신메뉴", "커피", "스무디&프라페", "에이드", "기타음료", "티", "주스", "디저트"};
	private final static String[] menuConditions = new String[] {"coffee", "frappuccino", "dessert", "temperature", "coffee", "frappuccino", "dessert", "temperature", "coffee"};
	private final static int xgap = 20;
	private final static int hgap = 10;
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
		this.prevBtn = new JButton(" ◀ ");
		this.nextBtn = new JButton(" ▶ ");
		this.infoId = new JLabel();
		this.infoName = new JLabel();
		
		JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, hgap));
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, hgap));
		JPanel settingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, hgap));
		JPanel receiptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, hgap));
		JPanel movePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, hgap));
		JButton menuSettingbtn = new JButton("메뉴 관리");
		JButton receiptBtn = new JButton("영수증");
		JButton homeBtn = new JButton("<");
		
		homeBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout card = (CardLayout)mainFrame.getMainPanel().getCardPanel().getLayout();
				card.first(mainFrame.getMainPanel().getCardPanel());
			}
			
		});
		
		receiptPanel.add(receiptBtn);
		movePanel.add(prevBtn);
		movePanel.add(nextBtn);
		infoPanel.add(infoId);
		infoPanel.add(infoName);
		settingPanel.add(menuSettingbtn);
		settingPanel.add(homeBtn);
		

		receiptBtn.addMouseListener(new ReceiptBtnListener(mainFrame));

		menuPanel.setBackground(Color.WHITE);
		infoPanel.setBackground(Color.WHITE);
		settingPanel.setBackground(Color.WHITE);
		receiptPanel.setBackground(Color.WHITE);
		movePanel.setBackground(Color.WHITE);
		
		menuSettingbtn.addActionListener(new MenuSettingAction(menuManagePanel));

		
		inputMenuPanel(menuNames, menuConditions);
		createSelectMenuBtn(menuPanel);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = Gbl.getGbc();
		
		setBorder(BorderFactory.createEmptyBorder(hgap, xgap, 0, xgap));
		setBackground(Color.WHITE);
		
		add(infoPanel, Gbl.setting(gbc, 0.1, 0.01, 0, 0, 1, 1));
		add(settingPanel, Gbl.setting(gbc, 0.1, 0.01, 1, 0, 1, 1));
		add(menuPanel, Gbl.setting(gbc, 0.1, 0.03, 0, 1, 2, 1));
		add(cardMenuPanel, Gbl.setting(gbc, 1.0, 1.0, 0, 3, 2, 1));
		add(receiptPanel, Gbl.setting(gbc, 0.1, 0.05, 0, 4, 1, 1));
		add(movePanel, Gbl.setting(gbc, 0.1, 0.05, 1, 4, 1, 1));
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
				gridPanel.add(new JPanel(new GridLayout(3, 4, hgap, hgap)));
				gridPanel.get(index).setBackground(Color.WHITE);
			}
			
			total = condition.equals(list.get(i).getMenuType()) ? total+=1 : total;
			cnt = condition.equals(list.get(i).getMenuType()) ? cnt+=1 : cnt;

			if(cnt > menuSize) {
				gridPanel.add(new JPanel(new GridLayout(3, 4, hgap, hgap)));
				index++;
				gridPanel.get(index).setBackground(Color.WHITE);
				cnt = 1;
			}

			if(condition.equals(list.get(i).getMenuType())) {
				JPanel innerPanel = new JPanel(new BorderLayout());
				JLabel menuName = new JLabel(list.get(i).getMenuName(), JLabel.CENTER);
				JLabel menuPrice = new JLabel("￦ " + list.get(i).getPrice(), JLabel.RIGHT);
				menuDetailsPanel = new MenuDetailsPanel(mainFrame, list.get(i), list);
				JButton btn = new MenuItemBtn(menuDetailsPanel);
				
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
					gridPanel.get(i).add(new JButton());
				}
			}
		}
		
		nextBtn.addActionListener(new MenuNextAction(innerCardPanel));
		prevBtn.addActionListener(new MenuPrevAction(innerCardPanel));
		
		return innerCardPanel;
	}
	
	public void createSelectMenuBtn(JPanel menuPanel) {
		for(int i=0; i<menuNames.length; ++i) {
			JToggleButton btn = new MenuButton(this, menuNames[i], Color.WHITE);
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
