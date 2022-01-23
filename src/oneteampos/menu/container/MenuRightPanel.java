package oneteampos.menu.container;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.Cart_discountSaveCancelAction;
import oneteampos.menu.action.Member_turnOnAction;
import oneteampos.menu.action.Payment_turnOnAcion;
import oneteampos.menu.component.All_ScrollPane;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.All_opaquePanel;
import oneteampos.menu.component.Cart_btn;
import oneteampos.menu.component.Cart_table;
import oneteampos.menu.etc.CommonVariable;

public class MenuRightPanel extends JPanel {
	
	private Vector<Vector<Object>> menuIdList;
	private Vector<Vector<Object>> menuCntList;
	private DefaultTableModel model;
	private Member_inquiryDialog mcd;
	private JPanel totalPanel;
	private JPanel totalPricePanel;
	private JPanel payPanel;
	private JPanel disPanel;
	private JLabel totalPrice;
	private JLabel discountCash;
	private JTable cart;
	private boolean isDiscnt;
	private JButton cancelBtn;
	private ArrayList<Integer> prevSum;

	public MenuRightPanel(MainFrame mainFrame, MenuLeftPanel leftPanel) {
		this.totalPanel = new All_opaquePanel();
		this.totalPricePanel = new All_opaquePanel(new GridLayout());
		this.totalPrice = new All_label("0", JLabel.RIGHT);
		this.payPanel = new All_opaquePanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		this.model = new DefaultTableModel() {@Override public boolean isCellEditable(int row, int column) {return false;}};
		this.disPanel = new All_opaquePanel(new GridLayout());
		this.discountCash = new All_label("0", JLabel.RIGHT);
		this.menuIdList = new Vector<>();
		this.menuCntList = new Vector<>();
		this.prevSum = new ArrayList<>();

		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		model.setDataVector(new Object[][] {{"","","","",""}}, new Object[] {"메뉴이름","사이즈","금액","수량","선택"});
		model.removeRow(0);
		cart = new Cart_table(mainFrame, model);

		JScrollPane sc = new All_ScrollPane(cart);

		JLabel discount = new All_label("할인 금액");
		JLabel total = new All_label("결제 금액");
		
		JButton payBtn = new Cart_btn("결제");
		JButton memberCheck = new Cart_btn("회원조회");
		cancelBtn = new Cart_btn("할인&적립취소");

		cancelBtn.setVisible(false);
		discountCash.setVisible(false);
		totalPrice.setVisible(false);

		disPanel.add(discount);
		disPanel.add(discountCash);
		
		discount.setVerticalAlignment(JLabel.BOTTOM);
		discountCash.setVerticalAlignment(JLabel.BOTTOM);
		
		payPanel.add(cancelBtn);
		payPanel.add(memberCheck);
		payPanel.add(payBtn);
		
		totalPricePanel.add(total);
		totalPricePanel.add(totalPrice);
		
		totalPanel.add(disPanel);
		totalPanel.add(totalPricePanel);
		totalPanel.add(payPanel);

		cancelBtn.addMouseListener(new Cart_discountSaveCancelAction(this));
		payBtn.addMouseListener(new Payment_turnOnAcion(mainFrame, this));
		memberCheck.addMouseListener(new Member_turnOnAction(mainFrame, this));

		sc.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		totalPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 15));
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
//		setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		setBounds(leftPanel.getWidth(), 60, MainFrame.FRAME_WIDTH-leftPanel.getWidth()-15, MainFrame.FRAME_HEIGHT-88);
		
		add(sc);
		add(totalPanel);
	}

	public JLabel getTotalPrice() {
		return this.totalPrice;
	}

	public DefaultTableModel getModel() {
		return this.model;
	}
	
	public JLabel getDiscountCash() {
		return this.discountCash;
	}
	
	public Member_inquiryDialog getMemberInquiryDialog() {
		return this.mcd;
	}

	public boolean getIsDiscnt() {
		return this.isDiscnt;
	}
	
	public JButton getCancelBtn() {
		return this.cancelBtn;
	}
	
	public JTable getCart() {
		return this.cart;
	}
	
	public Vector<Vector<Object>> getMenuIdList() {
		return this.menuIdList;
	}
	
	public Vector<Vector<Object>> getMenuCntList() {
		return this.menuCntList;
	}
	
	public ArrayList<Integer> getPrevSum() {
		return this.prevSum;
	}
	
	public void setIsDisCnt(boolean b) {
		this.isDiscnt = b;
	}
	
	public void setMemberInquiryDialog(Member_inquiryDialog mcd) {
		this.mcd = mcd;
	}
}
