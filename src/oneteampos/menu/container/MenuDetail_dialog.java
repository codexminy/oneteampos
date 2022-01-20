package oneteampos.menu.container;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.Cart_addAction;
import oneteampos.menu.action.Dialog_windowAction;
import oneteampos.menu.component.MenuDetail_minusBtn;
import oneteampos.menu.component.MenuDetail_plusBtn;
import oneteampos.menu.component.MenuDetail_sizeLabel;
import oneteampos.menu.component.MenuDetail_sizeRadioBtn;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeStr;
import oneteampos.menu.etc.Gbl;

public class MenuDetail_dialog extends JDialog {
	
	private final static int line = 5;
	private final static int panelCnt = 7;
	public final static String[] OPTIONS= new String[] {"예", "아니오"};
	public final static String MESSAGE = "메뉴를 추가하겠습니까?";
	public final static String TITLE = "information";
	private ArrayList<JRadioButton> tempBtns;
	private ArrayList<JRadioButton> sizeBtns;
	private ArrayList<JButton> minusBtns;
	private ArrayList<JButton> plusBtns;
	private ArrayList<JLabel> sizeLabels;
	private ArrayList<JLabel> amountLabels;
	private ArrayList<JLabel> extraLabels;
	private ArrayList<JLabel> extraAmountLabels;
	private ArrayList<JPanel> panels;
	private ArrayList<MenuData> lists;
	private String[] titles;
	private ArrayList<JLabel> titleList;
	private int tempPrice;
	private boolean[] isSelect;
	private int order;
	private int originPrice;
	private JLabel cntName;
	public JLabel cnt;
	
	public MenuDetail_dialog(MainFrame mainFrame, MenuData list, ArrayList<MenuData> lists) {
		super(mainFrame, "메뉴 상세 선택", true);
		this.tempBtns = new ArrayList<>();
		this.sizeBtns = new ArrayList<>();
		this.minusBtns = new ArrayList<>();
		this.plusBtns = new ArrayList<>();
		this.sizeLabels = new ArrayList<>();
		this.amountLabels = new ArrayList<>();
		this.extraLabels = new ArrayList<>();
		this.extraAmountLabels = new ArrayList<>();
		this.panels = getPanel();
		this.lists = lists;
		this.tempPrice = list.getPrice();
		this.titles = new String[] {"메뉴명", list.getMenuName(), "온도", "사이즈", "추가", ChangeStr.setCashMark(tempPrice)};
		this.titleList = createLabel();
		this.order = 0;
		this.originPrice = list.getPrice();
		this.cntName = new JLabel("수량");
		this.cnt = new JLabel("1");
		
		setSize((int)(MainFrame.FRAME_WIDTH*0.35), (int)(MainFrame.FRAME_HEIGHT*0.9));
		setLocationRelativeTo(null);
		setResizable(true);
		
		final int upDown = this.getHeight()/20;
		final int leftRight = this.getWidth()/9;
		
		insertData("temperature", "size", "extra");

		ArrayList<JPanel> linePanels = getLinePanel();
		JPanel chkPanel = new JPanel(new GridLayout(minusBtns.size(), 1, 0, 0));
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton addBtn = new JButton("추가");
		btnPanel.add(addBtn);
		
		ButtonGroup tempBtnGroup = new ButtonGroup();
		ButtonGroup sizeBtnGroup = new ButtonGroup();

		GridBagConstraints gbc = Gbl.getGbc();
		
		inputArrowBtn(minusBtns, plusBtns, amountLabels, chkPanel);
		
		inputRadioBtn(tempBtns, tempBtnGroup, panels.get(1), gbc);
		inputRadioBtn(sizeBtns, sizeBtnGroup, panels.get(2), gbc);
		inputextraLabel(extraLabels, panels.get(3), gbc);
		inputextraLabel(extraAmountLabels, panels.get(4), gbc);
		panels.get(5).add(chkPanel, Gbl.setting(gbc, 0.1, 0.1, 0, 0));
		inputextraLabel(sizeLabels, panels.get(6), gbc);
		
		JPanel cntPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
		
		JButton cntLeftBtn = new JButton("◀");
		cntLeftBtn.setContentAreaFilled(false);
		cntLeftBtn.setBorderPainted(false);
		JButton cntRightBtn = new JButton("▶");
		cntRightBtn.setContentAreaFilled(false);
		cntRightBtn.setBorderPainted(false);
		
		cntPanel.add(cntLeftBtn);
		cntPanel.add(cnt);
		cntPanel.add(cntRightBtn);
		cnt.setBorder(BorderFactory.createEmptyBorder(0, 17, 0, 17));
		
		cntLeftBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					int cnt2 = Integer.parseInt(cnt.getText());
//					int sum = Integer.parseInt(titleList.get(5).getText().substring(4));
					int sum = Integer.parseInt(ChangeStr.setErase(titleList.get(5).getText()));
					if(cnt2 > 1) {
//						titleList.get(5).setText("￦   " + (sum - originPrice));
						titleList.get(5).setText(ChangeStr.setCashMark(sum-originPrice));
						cnt.setText((cnt2 - 1) + "");
					}

			}
			
		});
		
		cntRightBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
//				int sum = Integer.parseInt(titleList.get(5).getText().substring(4));
				int sum = Integer.parseInt(ChangeStr.setErase(titleList.get(5).getText()));
				int cnt2 = Integer.parseInt(cnt.getText());
				cnt.setText((cnt2 + 1) + "");
//				titleList.get(5).setText("￦   " + (sum + originPrice));
				titleList.get(5).setText(ChangeStr.setCashMark(sum+originPrice));
			}
			
		});
		
		this.isSelect = new boolean[sizeBtns.size()];
		
		panels.get(0).setBorder(BorderFactory.createEmptyBorder(upDown, leftRight, upDown, leftRight));

		panels.get(0).add(titleList.get(0), Gbl.setting(gbc, 0.1, 0.2, 0, 0, 3, 1));
		panels.get(0).add(titleList.get(1), Gbl.setting(gbc, 0.1, 0.2, 0, 1, 3, 1));
		panels.get(0).add(linePanels.get(0), Gbl.setting(gbc, 0.1, 0.1, 0, 2, 3, 1));
		panels.get(0).add(titleList.get(2), Gbl.setting(gbc, 0.1, 0.2, 0, 3, 3, 1));
		panels.get(0).add(panels.get(1), Gbl.setting(gbc, 0.1, 0.15, 0, 4, 3, 1));
		panels.get(0).add(linePanels.get(1), Gbl.setting(gbc, 0.1, 0.1, 0, 5, 3, 1));
		panels.get(0).add(titleList.get(3), Gbl.setting(gbc, 0.1, 0.2, 0, 6, 3, 1));
		panels.get(0).add(panels.get(2), Gbl.setting(gbc, 0.1, 0.15, 0, 7, 1, 1));
		panels.get(0).add(panels.get(6), Gbl.setting(gbc, 0.1, 0.15, 1, 7, 1, 1));
		panels.get(0).add(linePanels.get(2), Gbl.setting(gbc, 0.1, 0.1, 0, 8, 3, 1));
		panels.get(0).add(titleList.get(4), Gbl.setting(gbc, 0.1, 0.2, 0, 9, 3, 1));
		panels.get(0).add(panels.get(3), Gbl.setting(gbc, 0.1, 1.0, 0, 10, 1, 1));
		panels.get(0).add(panels.get(4), Gbl.setting(gbc, 0.1, 1.0, 1, 10, 1, 1));
		panels.get(0).add(panels.get(5), Gbl.setting(gbc, 0.01, 1.0, 2, 10, 1, 1));
		panels.get(0).add(linePanels.get(3), Gbl.setting(gbc, 0.1, 0.1, 0, 11, 3, 1));
		panels.get(0).add(cntName, Gbl.setting(gbc, 0.1, 0.1, 0, 12, 1, 1));
		panels.get(0).add(cntPanel, Gbl.setting(gbc, 0.1, 0.1, 1, 12, 2, 1));
		panels.get(0).add(linePanels.get(4), Gbl.setting(gbc, 0.1, 0.1, 0, 13, 3, 1));
		panels.get(0).add(titleList.get(5), Gbl.setting(gbc, 0.1, 0.1, 0, 14, 1, 1));
		panels.get(0).add(btnPanel, Gbl.setting(gbc, 0.1, 0.1, 1, 14, 2, 1));
		
		
		addBtn.addActionListener(new Cart_addAction(mainFrame, this));
		
		add(panels.get(0));
		addWindowListener(new Dialog_windowAction(mainFrame));
	}
	
	private ArrayList<JLabel> createLabel() {
		ArrayList<JLabel> titleList = new ArrayList<>();
		for(int i=0; i<titles.length; ++i) {
			titleList.add(new JLabel(titles[i]));
		}
		return titleList;
	}
	
	private ArrayList<JPanel> getPanel() {
		ArrayList<JPanel> panels = new ArrayList<>();
		for(int i=0; i<panelCnt; ++i) {
			panels.add(new JPanel(new GridBagLayout()));
		}
		return panels;
	}
	
	private ArrayList<JPanel> getLinePanel() {
		ArrayList<JPanel> linePanels = new ArrayList<>();
		for(int i=0; i<line; ++i) {
			linePanels.add(new All_LinePanel((int)(MainFrame.FRAME_WIDTH*0.35)));
		}
		return linePanels;
	}

	private void inputArrowBtn(ArrayList<JButton> minusbtns, ArrayList<JButton> plusbtns, ArrayList<JLabel> amountLabels, JPanel panel) {
		for(int i=0; i<minusbtns.size(); ++i) {
			minusbtns.get(i).setFocusable(false);
			minusbtns.get(i).setContentAreaFilled(false);
			minusbtns.get(i).setBorderPainted(false);
			plusbtns.get(i).setFocusable(false);
			plusbtns.get(i).setContentAreaFilled(false);
			plusbtns.get(i).setBorderPainted(false);
			panel.add(minusbtns.get(i));
			JLabel la = amountLabels.get(i);
			la.setHorizontalAlignment(JLabel.CENTER);
			panel.add(la);
			panel.add(plusbtns.get(i));
		}
	}
	
	private void inputRadioBtn(ArrayList<JRadioButton> btns, ButtonGroup btnGroup, JPanel panel, GridBagConstraints gbc) {
		for(int i=0; i<btns.size(); ++i) {
			btnGroup.add(btns.get(i));
			if(i == 0) btns.get(i).setSelected(true);
			btns.get(i).setFocusable(false);
			panel.add(btns.get(i), Gbl.setting(gbc, 0.1, 0.1, 0, i));
		}
	}

	private void inputextraLabel(ArrayList<JLabel> labels, JPanel panel, GridBagConstraints gbc) {
		for(int i=0; i<labels.size(); ++i) {
			panel.add(labels.get(i), Gbl.setting(gbc, 0.1, 0.1, 0, i));
		}
	}
	
	private void insertData(String temperature, String size, String extra) {
		for(int i=0; i<lists.size(); ++i) {
			Object data = lists.get(i).getMenuType();
			
			if(data.equals(temperature)) {
				tempBtns.add(new JRadioButton(lists.get(i).getMenuName()));
			} else if(data.equals(size)) {
				sizeBtns.add(new MenuDetail_sizeRadioBtn(this, lists.get(i).getMenuName(), lists.get(i).getPrice()+""));
				sizeLabels.add(new MenuDetail_sizeLabel("+ " + lists.get(i).getPrice(), lists.get(i).getMenuName()));
			} else if(data.equals(extra)) {
				plusBtns.add(new MenuDetail_plusBtn(this, lists.get(i).getMenuName()));
				minusBtns.add(new MenuDetail_minusBtn(this, lists.get(i).getMenuName()));
				amountLabels.add(new JLabel("0"));
				extraLabels.add(new JLabel(lists.get(i).getMenuName()));
				extraAmountLabels.add(new JLabel("+ " + lists.get(i).getPrice()));
			}
		}
	}

	public ArrayList<JRadioButton> getSizeBtns() {
		return this.sizeBtns;
	}
	
	public ArrayList<JRadioButton> getTempBtns() {
		return this.tempBtns;
	}
	
	public ArrayList<JLabel> getExtraAmountLabels() {
		return this.extraAmountLabels;
	}
	
	public ArrayList<JLabel> getTitleList() {
		return this.titleList;
	}
	
	public ArrayList<JLabel> getExtraLabels() {
		return this.extraLabels;
	}
	
	public ArrayList<JLabel> getAmountLabels() {
		return this.amountLabels;
	}

	public ArrayList<JLabel> getSizeLabels() {
		return this.sizeLabels;
	}
	
	public boolean[] getisSelect() {
		return this.isSelect;
	}
	
	public int getOrder() {
		return this.order;
	}
	
	public void setOrder(int num) {
		this.order += num;
	}
	
	public int getOriginPrice() {
		return this.originPrice;
	}
	
//	public Vector<Vector<Object>> getMenuIdList() {
//		return this.menuIdList;
//	}
//	
//	public Vector<Vector<Object>> getMenuCntList() {
//		return this.menuCntList;
//	}
	
	public ArrayList<MenuData> getMenuData() {
		return this.lists;
	}
}













