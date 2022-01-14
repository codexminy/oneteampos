package oneteampos.menu.container;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import oneteampos.menu.action.MenuWindowAction;
import oneteampos.menu.allLayouts.Gbl;
import oneteampos.menu.compoenet.MinusButton;
import oneteampos.menu.compoenet.PlusButton;
import oneteampos.menu.compoenet.SizeLabel;
import oneteampos.menu.compoenet.sizeRadioButton;
import oneteampos.menu.data.MenuData;

public class MenuDetailsPanel extends JDialog {
	
	private final static int line = 4;
	private final static int panelCnt = 7;
	private ArrayList<JRadioButton> tempBtns;
	private ArrayList<JRadioButton> sizeBtns;
//	private ArrayList<JCheckBox> extraBtns;
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
	private String tempPrice;
	private int[] cnt;
	
	public MenuDetailsPanel(MainFrame mainFrame, MenuData list, ArrayList<MenuData> lists) {
		super(mainFrame, "메뉴 상세 선택");
		this.tempBtns = new ArrayList<>();
		this.sizeBtns = new ArrayList<>();
//		this.extraBtns = new ArrayList<>();
		this.minusBtns = new ArrayList<>();
		this.plusBtns = new ArrayList<>();
		this.sizeLabels = new ArrayList<>();
		this.amountLabels = new ArrayList<>();
		this.extraLabels = new ArrayList<>();
		this.extraAmountLabels = new ArrayList<>();
		this.panels = getPanel();
		this.lists = lists;
		this.tempPrice = list.getPrice()+"";
		this.titles = new String[] {"메뉴명", list.getMenuName(), "온도", "사이즈", "추가", "￦   " + tempPrice};
		this.titleList = createLabel();
		
		setSize((int)(MainFrame.FRAME_WIDTH*0.35), (int)(MainFrame.FRAME_HEIGHT*0.9));
		setLocationRelativeTo(null);
		setResizable(true);
		
		final int upDown = this.getHeight()/20;
		final int leftRight = this.getWidth()/9;
		
		insertData("temperature", "size", "extra");

		ArrayList<JPanel> linePanels = getLinePanel();
		JPanel chkPanel = new JPanel(new GridLayout(minusBtns.size(), 1, 0, 0));
		
		ButtonGroup tempBtnGroup = new ButtonGroup();
		ButtonGroup sizeBtnGroup = new ButtonGroup();

		GridBagConstraints gbc = Gbl.getGbc();
		
		inputArrowBtn(minusBtns, plusBtns, amountLabels, chkPanel);
		
		inputRadioBtn(tempBtns, tempBtnGroup, panels.get(1), gbc);
		inputRadioBtn(sizeBtns, sizeBtnGroup, panels.get(2), gbc);
//		inputCheckBtn(extraBtns, panels.get(3), gbc);
		inputextraLabel(extraLabels, panels.get(3), gbc);
		inputextraLabel(extraAmountLabels, panels.get(4), gbc);
		panels.get(5).add(chkPanel, Gbl.getSetting(gbc, 0.1, 0.1, 0, 0));
		inputextraLabel(sizeLabels, panels.get(6), gbc);
		
		this.cnt = new int[sizeBtns.size()];
		
		panels.get(0).setBorder(BorderFactory.createEmptyBorder(upDown, leftRight, upDown, leftRight));

		panels.get(0).add(titleList.get(0), Gbl.getSetting(gbc, 0.1, 0.2, 0, 0, 3, 1));
		panels.get(0).add(titleList.get(1), Gbl.getSetting(gbc, 0.1, 0.2, 0, 1, 3, 1));
		panels.get(0).add(linePanels.get(0), Gbl.getSetting(gbc, 0.1, 0.1, 0, 2, 3, 1));
		panels.get(0).add(titleList.get(2), Gbl.getSetting(gbc, 0.1, 0.2, 0, 3, 3, 1));
		panels.get(0).add(panels.get(1), Gbl.getSetting(gbc, 0.1, 0.15, 0, 4, 3, 1));
		panels.get(0).add(linePanels.get(1), Gbl.getSetting(gbc, 0.1, 0.1, 0, 5, 3, 1));
		panels.get(0).add(titleList.get(3), Gbl.getSetting(gbc, 0.1, 0.2, 0, 6, 3, 1));
		panels.get(0).add(panels.get(2), Gbl.getSetting(gbc, 0.1, 0.15, 0, 7, 1, 1));
		panels.get(0).add(panels.get(6), Gbl.getSetting(gbc, 0.1, 0.15, 1, 7, 1, 1));
		panels.get(0).add(linePanels.get(2), Gbl.getSetting(gbc, 0.1, 0.1, 0, 8, 3, 1));
		panels.get(0).add(titleList.get(4), Gbl.getSetting(gbc, 0.1, 0.2, 0, 9, 3, 1));
		panels.get(0).add(panels.get(3), Gbl.getSetting(gbc, 0.1, 1.0, 0, 10, 1, 1));
		panels.get(0).add(panels.get(4), Gbl.getSetting(gbc, 0.1, 1.0, 1, 10, 1, 1));
		panels.get(0).add(panels.get(5), Gbl.getSetting(gbc, 0.01, 1.0, 2, 10, 1, 1));
		panels.get(0).add(linePanels.get(3), Gbl.getSetting(gbc, 0.1, 0.1, 0, 11, 3, 1));
		panels.get(0).add(titleList.get(5), Gbl.getSetting(gbc, 0.1, 0.1, 0, 12, 1, 1));

		add(panels.get(0));
		addWindowListener(new MenuWindowAction(mainFrame));
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
			linePanels.add(new LinePanel(this));
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
			panel.add(btns.get(i), Gbl.getSetting(gbc, 0.1, 0.1, 0, i));
		}
	}
	
//	private void inputCheckBtn(ArrayList<JCheckBox> btns, JPanel panel, GridBagConstraints gbc) {
//		for(int i=0; i<btns.size(); ++i) {
//			btns.get(i).setFocusable(false);
//			panel.add(btns.get(i), Gbl.getSetting(gbc, 0.1, 0.1, 0, i));
//		}
//	}
//	
	private void inputextraLabel(ArrayList<JLabel> labels, JPanel panel, GridBagConstraints gbc) {
		for(int i=0; i<labels.size(); ++i) {
			panel.add(labels.get(i), Gbl.getSetting(gbc, 0.1, 0.1, 0, i));
		}
	}
	
	private void insertData(String temperature, String size, String extra) {
		for(int i=0; i<lists.size(); ++i) {
			Object data = lists.get(i).getMenuType();
			
			if(data.equals(temperature)) {
				tempBtns.add(new JRadioButton(lists.get(i).getMenuName()));
			} else if(data.equals(size)) {
				sizeBtns.add(new sizeRadioButton(this, lists.get(i).getMenuName(), lists.get(i).getPrice()+""));
				sizeLabels.add(new SizeLabel("+ " + lists.get(i).getPrice(), lists.get(i).getMenuName()));
			} else if(data.equals(extra)) {
//				extraBtns.add(new MenuDetailsChkBox(lists.get(i).getMenuName(), this, lists));
				plusBtns.add(new PlusButton(this, lists.get(i).getMenuName()));
				minusBtns.add(new MinusButton(this, lists.get(i).getMenuName()));
				amountLabels.add(new JLabel("0"));
				extraLabels.add(new JLabel(lists.get(i).getMenuName()));
				extraAmountLabels.add(new JLabel("+ " + lists.get(i).getPrice()));
			}
		}
	}

	public ArrayList<JRadioButton> getSizeBtns() {
		return this.sizeBtns;
	}
	
//	public ArrayList<JCheckBox> getExtraBtns() {
//		return this.extraBtns;
//	}
	
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
	
	public int[] getSizeCnt() {
		return this.cnt;
	}
}













