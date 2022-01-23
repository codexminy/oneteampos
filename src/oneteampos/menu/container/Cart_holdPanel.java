package oneteampos.menu.container;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.Cart_holdBtnAction;
import oneteampos.menu.action.Cart_holdListBtnAction;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_opaquePanel;

public class Cart_holdPanel extends All_opaquePanel {
	
	private Cart_holdDialog holdDialog;
	private JButton holdBtn;
	private JButton holdList;
	private ArrayList<MenuRightPanel> mrp;
	
	public Cart_holdPanel(MainFrame mainFrame, MenuLeftPanel leftPanel) {
		this.holdDialog = new Cart_holdDialog(mainFrame);
		this.holdBtn = new All_btn("보류");
		this.holdList = new All_btn("보류 목록");
		this.mrp = new ArrayList<>();
		
		holdBtn.addMouseListener(new Cart_holdBtnAction(mainFrame, leftPanel, this));
		holdList.addMouseListener(new Cart_holdListBtnAction(this, holdDialog));
		
		add(holdList);
		add(holdBtn);
		
		setLayout(new FlowLayout(FlowLayout.RIGHT,5,20));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		setBounds(leftPanel.getWidth(), 0, MainFrame.FRAME_WIDTH-leftPanel.getWidth()-15, 60);
	}
	
	public Cart_holdDialog getHoldDialog() {
		return this.holdDialog;
	}
	
	public ArrayList<MenuRightPanel> getMrpList() {
		return this.mrp;
	}
	
}
