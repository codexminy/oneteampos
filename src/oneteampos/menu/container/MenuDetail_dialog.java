package oneteampos.menu.container;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

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
import oneteampos.menu.action.MenuDetail_cntMinusAction;
import oneteampos.menu.action.MenuDetail_cntPlusAction;
import oneteampos.menu.component.All_LinePanel;
import oneteampos.menu.component.All_boxPanel;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.All_opaquePanel;
import oneteampos.menu.component.MenuDetail_minusBtn;
import oneteampos.menu.component.MenuDetail_plusBtn;
import oneteampos.menu.component.MenuDetail_radioBtn;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeString;

public class MenuDetail_dialog extends JDialog {
	
	private final static int line = 5;
	private ArrayList<JRadioButton> tempBtns;
	private ArrayList<JRadioButton> sizeBtns;
	private ArrayList<JButton> minusBtns;
	private ArrayList<JButton> plusBtns;
	private ArrayList<JLabel> sizeLabels;
	private ArrayList<JLabel> amountLabels;
	private ArrayList<JLabel> extraLabels;
	private ArrayList<JLabel> extraAmountLabels;
	private ArrayList<MenuData> lists;
	private String[] titles;
	private ArrayList<JLabel> titleList;
	private int tempPrice;
	private boolean[] isSelect;
	private int order;
	private int originPrice;
	private JLabel cntName;
	private JLabel cnt;

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
		this.lists = lists;
		this.tempPrice = list.getPrice();
		this.titles = new String[] {"메뉴명", list.getMenuName(), "온도", "사이즈", "추가", ChangeString.setCashMark(tempPrice)};
		this.titleList = createLabel();
		this.order = 0;
		this.originPrice = list.getPrice();
		this.cntName = new All_label("수량");
		this.cnt = new All_label("1");

		All_boxPanel bgBoxPanel = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel menuNameBoxPanel = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel tempBoxPanel = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel sizeTitleBoxPanel = new All_boxPanel(LEFT_ALIGNMENT);
		All_boxPanel sizeSurroundBoxPanel = new All_boxPanel(LEFT_ALIGNMENT);
		All_boxPanel sizeBoxPanel = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel sizeNameBoxPanel = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel extraTitleBoxPanel = new All_boxPanel(LEFT_ALIGNMENT);
		All_boxPanel extraBoxPanel = new All_boxPanel(LEFT_ALIGNMENT);
		All_boxPanel extraBoxPanel1 = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel extraBoxPanel2 = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel extraBoxPanel3 = new All_boxPanel("Y", LEFT_ALIGNMENT);
		All_boxPanel cntSurroundPanel = new All_boxPanel(LEFT_ALIGNMENT);
		All_boxPanel totalBtnPanel = new All_boxPanel(LEFT_ALIGNMENT);
		
		JPanel cntPanel = new All_opaquePanel(new FlowLayout(FlowLayout.RIGHT,0,0));
		JPanel totalPanel = new All_opaquePanel(new FlowLayout(FlowLayout.LEFT,0,0));
		JPanel btnPanel = new All_opaquePanel(new FlowLayout(FlowLayout.RIGHT,0,0));
		JPanel chkPanel = new All_opaquePanel(new GridLayout(minusBtns.size(), 1, 0, 0));
		ArrayList<JPanel> linePanels = getLinePanel();
		
		JButton cntLeftBtn = new All_btn("◀");
		JButton cntRightBtn = new All_btn("▶");
		JButton addBtn = new All_btn("추가");

		ButtonGroup tempBtnGroup = new ButtonGroup();
		ButtonGroup sizeBtnGroup = new ButtonGroup();
		
		insertData("temperature", "size", "extra");
		
		inputArrowBtn(minusBtns, plusBtns, amountLabels, chkPanel);
		inputArrowBtn(minusBtns, plusBtns, amountLabels, extraBoxPanel3);
		
		tempBoxPanel.add(titleList.get(2));

		inputBtn(tempBoxPanel, tempBtns, tempBtnGroup);
		inputBtn(sizeBoxPanel, sizeBtns, sizeBtnGroup);
		inputRadioBtn(tempBtns, tempBtnGroup);
		inputRadioBtn(sizeBtns, sizeBtnGroup);
		
		inputLabel(sizeLabels, sizeNameBoxPanel, FlowLayout.LEFT);
		inputLabel(extraLabels, extraBoxPanel1, FlowLayout.LEFT);
		inputLabel(extraAmountLabels, extraBoxPanel2, FlowLayout.CENTER);

		this.isSelect = new boolean[sizeBtns.size()];
		
		addBtn.addActionListener(new Cart_addAction(mainFrame, this));
		cntLeftBtn.addMouseListener(new MenuDetail_cntPlusAction(this));
		cntRightBtn.addMouseListener(new MenuDetail_cntMinusAction(this));

		bgBoxPanel.setEmptyBorder(20, 20, 20, 20);
		cnt.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
		sizeNameBoxPanel.setEmptyBorder(9, 10, 0, 0);
		extraBoxPanel1.setEmptyBorder(8, 0, 0, 0);
		extraBoxPanel2.setEmptyBorder(8, 0, 0, 0);
		
		cntPanel.add(cntLeftBtn);
		cntPanel.add(cnt);
		cntPanel.add(cntRightBtn);
		
		sizeTitleBoxPanel.add(titleList.get(3));
		menuNameBoxPanel.add(titleList.get(1));
		extraTitleBoxPanel.add(titleList.get(4));
		
		extraBoxPanel.add(extraBoxPanel1);
		extraBoxPanel.add(extraBoxPanel2);
		extraBoxPanel.add(extraBoxPanel3);
		
		cntSurroundPanel.add(cntName);
		cntSurroundPanel.add(cntPanel);
		
		totalPanel.add(titleList.get(5));
		btnPanel.add(addBtn);
		totalBtnPanel.add(totalPanel);
		totalBtnPanel.add(btnPanel);
		
		sizeSurroundBoxPanel.add(sizeBoxPanel);
		sizeSurroundBoxPanel.add(sizeNameBoxPanel);
		
		bgBoxPanel.add(menuNameBoxPanel);
		bgBoxPanel.add(linePanels.get(0));
		bgBoxPanel.add(tempBoxPanel);
		bgBoxPanel.add(linePanels.get(1));
		bgBoxPanel.add(sizeTitleBoxPanel);
		bgBoxPanel.add(sizeSurroundBoxPanel);
		bgBoxPanel.add(linePanels.get(2));
		bgBoxPanel.add(extraTitleBoxPanel);
		bgBoxPanel.add(extraBoxPanel);
		bgBoxPanel.add(linePanels.get(3));
		bgBoxPanel.add(cntSurroundPanel);
		bgBoxPanel.add(linePanels.get(4));
		bgBoxPanel.add(totalBtnPanel);
		
		add(bgBoxPanel);
		addWindowListener(new Dialog_windowAction(mainFrame));
		setSize(400, 650);
		setLocationRelativeTo(null);
	}
	
	public void inputBtn(All_boxPanel panel, ArrayList<JRadioButton> btns, ButtonGroup bg) {
		for(int i=0; i<btns.size(); ++i) {
			bg.add(btns.get(i));
			panel.add(btns.get(i));
		}
	}

	private ArrayList<JLabel> createLabel() {
		ArrayList<JLabel> titleList = new ArrayList<>();
		for(int i=0; i<titles.length; ++i) {
			titleList.add(new All_label(titles[i], 13));
		}
		return titleList;
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
			JPanel bp = new All_opaquePanel(new FlowLayout(FlowLayout.RIGHT,0,0));
			JLabel label = amountLabels.get(i);
			label.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
			bp.add(minusbtns.get(i));
			bp.add(label);
			bp.add(plusbtns.get(i));
			panel.add(bp);
		}
	}
	
	private void inputRadioBtn(ArrayList<JRadioButton> btns, ButtonGroup btnGroup) {
		for(int i=0; i<btns.size(); ++i) {
			btnGroup.add(btns.get(i));
			if(i == 0) btns.get(i).setSelected(true);
			btns.get(i).setFocusable(false);
		}
	}

	private void inputLabel(ArrayList<JLabel> labels, JPanel panel, int position) {
		for(int i=0; i<labels.size(); ++i) {
			JPanel bp = new All_opaquePanel(new FlowLayout(position,0,0));
			bp.add(labels.get(i));
			panel.add(bp);
		}
	}

	private void insertData(String temperature, String size, String extra) {
		for(int i=0; i<lists.size(); ++i) {
			Object data = lists.get(i).getMenuType();
			MenuData list = lists.get(i);
			
			if(data.equals(temperature)) {
				tempBtns.add(new MenuDetail_radioBtn(list.getMenuName()));
			} else if(data.equals(size)) {
				sizeBtns.add(new MenuDetail_radioBtn(this, list.getMenuName(), list.getPrice()+""));
				sizeLabels.add(new All_label("+ " + list.getPrice(), list.getMenuName()));
			} else if(data.equals(extra)) {
				plusBtns.add(new MenuDetail_plusBtn(this, list.getMenuName()));
				minusBtns.add(new MenuDetail_minusBtn(this, list.getMenuName()));
				amountLabels.add(new All_label("0",13));
				extraLabels.add(new All_label(list.getMenuName(),13));
				extraAmountLabels.add(new All_label("+ " + list.getPrice(),13));
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

	public int getOriginPrice() {
		return this.originPrice;
	}

	public ArrayList<MenuData> getMenuData() {
		return this.lists;
	}
	
	public JLabel getCnt() {
		return this.cnt;
	}
	
	public void setOrder(int num) {
		this.order += num;
	}
	
	public void setCnt(String text) {
		this.cnt.setText(text);
	}
}
