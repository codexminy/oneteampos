package oneteampos.menu.container;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuManage_turnOnAction;
import oneteampos.menu.action.Menu_homeAction;
import oneteampos.menu.action.Menu_nextPageAction;
import oneteampos.menu.action.Menu_prevPageAction;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.Menu_btn;
import oneteampos.menu.component.Menu_categoryBtn;
import oneteampos.menu.component.Menu_emptyBtn;
import oneteampos.menu.component.Menu_itemBtn;
import oneteampos.menu.data.CafeMenuData;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeString;
import oneteampos.menu.etc.CommonVariable;
import oneteampos.receipt.actions.ReceiptBtnListener;

public class MenuLeftPanel extends JPanel implements CommonVariable {
	
	private final static String[] menuNames = new String[] {"세트메뉴", "신메뉴", "커피", "스무디&프라페", "에이드", "기타음료", "티", "주스", "디저트"};
	private final static String[] menuConditions = new String[] {"coffee", "frappuccino", "dessert", "temperature", "coffee", "frappuccino", "dessert", "temperature", "coffee"};
	private final static int menuSize = 12;
	private MainFrame mainFrame;
	private MenuManage_dialog menuManage_dialog;
	private MenuDetail_dialog menuDetail_dialog;
	private JPanel mainMenuPanel;
	private JPanel cardMenuPanel;
	private ButtonGroup buttonGroup;
	private CafeMenuData cafeMenuData;
	private JButton nextBtn;
	private JButton prevBtn;
	private JLabel infoId;
	private JLabel infoName;
	private HashMap<String, String> cartData;

	public void setMenuManageDialog(MenuManage_dialog menuManage_dialog) {
		this.menuManage_dialog = menuManage_dialog;
	}
	
	public MenuLeftPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.menuManage_dialog = new MenuManage_dialog(mainFrame, this);
		this.cardMenuPanel = new JPanel(new CardLayout());
		this.buttonGroup = new ButtonGroup();
		this.cafeMenuData = new CafeMenuData();
		this.prevBtn = new Menu_btn(" ◀ ");
		this.nextBtn = new Menu_btn(" ▶ ");
		this.infoId = new All_label();
		this.infoName = new All_label();
		this.cartData = new HashMap<>();

//		UIManager.put("Button.background", new Color(135, 136, 138));
//		UIManager.put("Button.foreground", new Color(135, 136, 138));
		
		JPanel menuPanel = new JPanel(new GridLayout(1,1,5,5));
		JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		JPanel settingPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,6,0));
		JPanel receiptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		JPanel movePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT,5,0));

		JButton menuSettingBtn = new Menu_btn("메뉴 관리");
		JButton receiptBtn = new Menu_btn("영수증");
		JButton homeBtn = new Menu_btn("<");

		receiptPanel.add(receiptBtn);
		movePanel.add(prevBtn);
		movePanel.add(nextBtn);
		infoPanel.add(infoId);
		infoPanel.add(infoName);
		settingPanel.add(menuSettingBtn);
		settingPanel.add(homeBtn);

		menuPanel.setBackground(Color.WHITE);
		infoPanel.setBackground(Color.WHITE);
		settingPanel.setBackground(Color.WHITE);
		receiptPanel.setBackground(Color.WHITE);
		movePanel.setBackground(Color.WHITE);

		homeBtn.addMouseListener(new Menu_homeAction(mainFrame));
		menuSettingBtn.addActionListener(new MenuManage_turnOnAction(mainFrame, this));
		receiptBtn.addMouseListener(new ReceiptBtnListener(mainFrame));

		inputMenuPanel(menuNames, menuConditions);
		createSelectMenuBtn(menuPanel);

		setLayout(null);
		setBackground(Color.WHITE);
		setBounds(0, 0, MainFrame.FRAME_WIDTH-365, MainFrame.FRAME_HEIGHT-38);
		
		infoPanel.setBounds(GAP, GAP, getWidth()/4, GAP*2);
		settingPanel.setBounds(getWidth()-infoPanel.getWidth()-GAP+6, GAP, getWidth()/4, GAP*2);
		menuPanel.setBounds(GAP-5, infoPanel.getHeight()+GAP, getWidth()-GAP*2+XGAP, GAP+10);
		cardMenuPanel.setBounds(GAP, infoPanel.getHeight()+menuPanel.getHeight()+GAP*2, getWidth()-GAP*2, (int)(getHeight()*0.72));
		receiptPanel.setBounds(GAP, infoPanel.getHeight()+menuPanel.getHeight()+cardMenuPanel.getHeight()+GAP*3, getWidth()/4, GAP*2);
		movePanel.setBounds(getWidth()-receiptPanel.getWidth()-GAP+5, infoPanel.getHeight()+menuPanel.getHeight()+cardMenuPanel.getHeight()+receiptPanel.getHeight()+GAP, getWidth()/4, GAP*2);
		
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
	
	public JPanel createMenuPanel(ArrayList<MenuData> arrayList, String condition) {
		JPanel innerCardPanel = new JPanel(new CardLayout());
		ArrayList<JPanel> gridPanel = new ArrayList<>();
		
		arrayList.clear();
		cafeMenuData.insertMenuData();
		arrayList = cafeMenuData.getMenuData();
		
		int cnt = 0;
		int index = 0;
		int loop = 1;
		int total = 0;
		
		for(int i=0; i<arrayList.size(); ++i) {
			if(i == 0 && cnt == 0) {
				gridPanel.add(new JPanel(new GridLayout(3, 4, XGAP, HGAP)));
				gridPanel.get(index).setBackground(Color.WHITE);
			}
			
			total = condition.equals((arrayList.get(i)).getMenuType()) ? total+=1 : total;
			cnt = condition.equals((arrayList.get(i)).getMenuType()) ? cnt+=1 : cnt;

			if(cnt > menuSize) {
				gridPanel.add(new JPanel(new GridLayout(3, 4, XGAP, HGAP)));
				index++;
				gridPanel.get(index).setBackground(Color.WHITE);
				cnt = 1;
			}

			if(condition.equals((arrayList.get(i)).getMenuType())) {
				JPanel innerPanel = new JPanel(new BorderLayout());
				JLabel menuName = new All_label((arrayList.get(i)).getMenuName(), JLabel.CENTER);
				JLabel menuPrice = new All_label(ChangeString.setCashMark((arrayList.get(i)).getPrice()), JLabel.RIGHT);
				cartData.put(menuName.getText(), menuPrice.getText());
				menuDetail_dialog = new MenuDetail_dialog(mainFrame, menuName.getText(), ChangeString.setErase(menuPrice.getText()), arrayList);
				JButton btn = new Menu_itemBtn(mainFrame, menuName, menuPrice, arrayList);
				
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
					gridPanel.get(i).add(new Menu_emptyBtn());
				}
			}
		}
		
		nextBtn.addActionListener(new Menu_nextPageAction(innerCardPanel));
		prevBtn.addActionListener(new Menu_prevPageAction(innerCardPanel));
		
		return innerCardPanel;
	}
	
	public void createSelectMenuBtn(JPanel menuPanel) {
		for(int i=0; i<menuNames.length; ++i) {
			JRadioButton btn = new Menu_categoryBtn(this, menuNames[i], mainFrame);
			if(i == 0) btn.setSelected(true);
			if(btn.isSelected()) {
				CardLayout c = (CardLayout)cardMenuPanel.getLayout();
				c.show(cardMenuPanel, menuNames[i]);
			}
			buttonGroup.add(btn);
			menuPanel.add(btn);
		}
	}

	public MenuManage_dialog getMenuManage_dialog() {
		return this.menuManage_dialog;
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
	
	public void setMenuDetailDialog(MenuDetail_dialog menuDetail_dialog) {
		this.menuDetail_dialog = menuDetail_dialog;
	}
	
	public MenuDetail_dialog getMenuDetail_dialog() {
		return this.menuDetail_dialog;
	}
	
	public CafeMenuData getCafeMenuData() {
		return this.cafeMenuData;
	}
	
	public HashMap<String, String> getCartData() {
		return this.cartData;
	}
}
