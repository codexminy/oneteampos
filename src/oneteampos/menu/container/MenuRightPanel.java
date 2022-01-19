package oneteampos.menu.container;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MemberCheckAction;
import oneteampos.menu.action.MenuCancelAction;
import oneteampos.menu.action.PayAcion;
import oneteampos.menu.component.CartBtns;
import oneteampos.menu.component.CartLabel;
import oneteampos.menu.component.MemberCheckDialog;
import oneteampos.menu.component.MenuCart;
import oneteampos.menu.component.RightPanels;

public class MenuRightPanel extends JPanel {
	
	private final static int gap = 20;
	public Vector<Vector<Object>> menuIdList;
	public Vector<Vector<Object>> menuCntList;
	public DefaultTableModel model;
	private MemberCheckDialog mcd;
	private JPanel totalPanel;
	private JPanel totalPricePanel;
	private JPanel payPanel;
	private JPanel disPanel;
	private JLabel totalPrice;
	private JLabel discountCash;
	private JTable cart;
	private boolean isDiscnt;
	private JButton cancelBtn;
	
	public void setMemberCheckDialog(MemberCheckDialog mcd) {
		this.mcd = mcd;
	}
	
	public MenuRightPanel(MainFrame mainFrame, MenuLeftPanel leftPanel) {
		this.totalPanel = new RightPanels();
		this.totalPricePanel = new RightPanels(new GridLayout());
		this.totalPrice = new CartLabel("￦ 0");
		this.payPanel = new RightPanels(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		this.model = new DefaultTableModel() {@Override public boolean isCellEditable(int row, int column) {return false;}};
		this.disPanel = new RightPanels(new GridLayout());
		this.discountCash = new JLabel();
		this.menuIdList = new Vector<>();
		this.menuCntList = new Vector<>();
//		this.mcd = new MemberCheckDialog(mainFrame);
		
		totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
		model.setDataVector(new Object[][] {{"","","","",""}}, new Object[] {"메뉴이름","사이즈","금액","수량","선택"});
		model.removeRow(0);
		cart = new MenuCart(mainFrame, model);

		JLabel title = new CartLabel("선택한 메뉴");
		JLabel discount = new CartLabel("할인 금액");
		JLabel total = new CartLabel("결제 금액");
		
		JButton payBtn = new CartBtns("결제");
		JButton memberCheck = new CartBtns("회원조회");
		cancelBtn = new CartBtns("할인&적립취소");
		
		cancelBtn.setVisible(false);
		discountCash.setVisible(false);
		totalPrice.setVisible(false);

		totalPrice.setHorizontalAlignment(JLabel.RIGHT);
		discountCash.setHorizontalAlignment(JLabel.RIGHT);

		disPanel.add(discount);
		disPanel.add(discountCash);
		
		payPanel.add(cancelBtn);
		payPanel.add(memberCheck);
		payPanel.add(payBtn);
		
		totalPricePanel.add(total);
		totalPricePanel.add(totalPrice);
		
		totalPanel.add(disPanel);
		totalPanel.add(totalPricePanel);
		totalPanel.add(payPanel);

		cancelBtn.addMouseListener(new MenuCancelAction(this));
		payBtn.addMouseListener(new PayAcion(mainFrame, this));
		memberCheck.addMouseListener(new MemberCheckAction(mainFrame, this));
		
		
		
		
		
		
		
		
		
		
		setLayout(null);
		setBackground(Color.DARK_GRAY);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 13, 20));
		setBounds(leftPanel.getWidth(), 0, MainFrame.FRAME_WIDTH-leftPanel.getWidth()-15, MainFrame.FRAME_HEIGHT-38);
		
		title.setBounds(gap, gap, getWidth()/2, gap);
		cart.setBounds(gap, title.getHeight()+gap*2, getWidth()-gap*2, (int)(getHeight()*0.7));
		totalPanel.setBounds(gap, title.getHeight()+cart.getHeight()+gap*3, getWidth()-gap*2, getHeight()-(title.getHeight()+cart.getHeight()+gap*3));
		
		add(title);
		add(cart);
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
	
	public MemberCheckDialog getMemeberCheckDialog() {
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
	
	public void setIsDisCnt(boolean b) {
		this.isDiscnt = b;
	}
}




























