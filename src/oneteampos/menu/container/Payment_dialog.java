package oneteampos.menu.container;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.Payment_cardProcessAction;
import oneteampos.menu.action.Payment_cashInputAction;
import oneteampos.menu.action.Payment_cashProcessAction;
import oneteampos.menu.action.Payment_clickAction;
import oneteampos.menu.component.All_boxPanel;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.All_numberPad;
import oneteampos.menu.component.All_opaquePanel;
import oneteampos.menu.data.OrderDetailsData;
import oneteampos.menu.data.OrdersData;
import oneteampos.menu.etc.ChangeString;

public class Payment_dialog extends JDialog {
	
	public Vector<OrdersData> orders;
	public Vector<OrderDetailsData> orderDetail;
	public JLabel cardNumLabel;
	private JPanel cardPanel;
	private JButton cashGo;
	private JTextField takeCash;
	private JLabel totalCash;
	private JLabel changeCash;
	private ArrayList<Integer> cardNum;

	public Payment_dialog(MainFrame mainFrame, MenuRightPanel mrp) {
		super(mainFrame, "결제 수단", true);
		this.cardPanel = new JPanel(new CardLayout());
		this.cardNum = new ArrayList<>();
		createCardNumber(3);
		this.cardNumLabel = new All_label(String.format("%d-%d-%d", cardNum.get(0), cardNum.get(1), cardNum.get(2)), JLabel.RIGHT);
		
		JPanel backGroundPanel = new JPanel(new GridLayout(1,1,20,20));
		JPanel cardBackGroundPanel = new JPanel(new GridLayout(4,1,0,0));
		JPanel cardBoxPanel = new All_opaquePanel(new GridLayout());
		JPanel approvalBoxPanel = new All_opaquePanel(new GridLayout());
		JPanel confirmBoxPanel = new All_opaquePanel(new FlowLayout(FlowLayout.CENTER, 0, 20));

		JButton card = new All_btn("카드");
		JButton cash = new All_btn("현금");
		JButton confirm = new All_btn("확인");

		JLabel cardTitle = new All_label("카드 결제 완료");
		JLabel cardName = new All_label("카드번호");
		JLabel approval = new All_label("승인금액");
		JLabel approvalCash = new All_label(mrp.getTotalPrice().getText(), JLabel.RIGHT);

		cash.addMouseListener(new Payment_clickAction(this));
		card.addMouseListener(new Payment_clickAction(this));
		card.addMouseListener(new Payment_cardProcessAction(this, mrp));
		confirm.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) { dispose(); } });
		
		backGroundPanel.setBorder(BorderFactory.createEmptyBorder(80, 40, 80, 40));
		backGroundPanel.setBackground(Color.WHITE);
		
		cardBackGroundPanel.setBackground(Color.WHITE);
		cardBackGroundPanel.setBorder(BorderFactory.createEmptyBorder(40, 120, 40, 120));
		
		cardTitle.setHorizontalAlignment(JLabel.CENTER);
		confirm.setPreferredSize(new Dimension(90, 30));
		
		backGroundPanel.add(card);
		backGroundPanel.add(cash);
		
		cardBoxPanel.add(cardName);
		cardBoxPanel.add(cardNumLabel);

		approvalBoxPanel.add(approval);
		approvalBoxPanel.add(approvalCash);

		confirmBoxPanel.add(confirm);

		cardBackGroundPanel.add(cardTitle);
		cardBackGroundPanel.add(cardBoxPanel);
		cardBackGroundPanel.add(approvalBoxPanel);
		cardBackGroundPanel.add(confirmBoxPanel);

		JPanel cashSurroundPanel = new All_boxPanel("Y", LEFT_ALIGNMENT);
		JPanel cashBackgroundPanel = new All_opaquePanel(new GridLayout());
		JPanel leftBoxPanel = new All_opaquePanel(new GridLayout(3,1));
		All_boxPanel rightBoxPanel = new All_boxPanel(LEFT_ALIGNMENT);
		JPanel titleBoxPanel = new All_opaquePanel(new GridLayout());
		JPanel takeBoxPanel = new All_opaquePanel(null);
		JPanel totalBoxPanel = new All_opaquePanel(new GridLayout());
		JPanel changeBoxPanel = new All_opaquePanel(new GridLayout());
		JPanel enterBoxPanel = new All_opaquePanel(null);
		
		JLabel cashTitle = new All_label("현금결제", JLabel.CENTER);
		JLabel takeName = new All_label("받은금액 ￦ ");
		this.takeCash = new JTextField();
		JButton enter = new All_btn("입력");
		JLabel totalName = new All_label("결제금액");
		this.totalCash = new All_label(mrp.getTotalPrice().getText(), JLabel.RIGHT);
		this.cashGo = new All_btn("결제");
		this.cashGo.setEnabled(false);
		JLabel change = new All_label("거스름돈");
		this.changeCash = new All_label(ChangeString.setCashMark(Integer.parseInt("0")), JLabel.RIGHT);
		All_numberPad numPadPanel = new All_numberPad(takeCash);

		enter.addMouseListener(new Payment_cashInputAction(this));
		this.cashGo.addMouseListener(new Payment_cashProcessAction(this, mrp));
//		cashTitle.setHorizontalAlignment(JLabel.CENTER);
		titleBoxPanel.add(cashTitle);
		
		takeName.setBounds(0, 20, 75, 20);
		takeCash.setBounds(takeName.getWidth(), 20, 95, 20);
		enter.setBounds(takeName.getWidth()+takeCash.getWidth()+5, 20, 45, 20);
		
		takeBoxPanel.add(takeName);
		takeBoxPanel.add(takeCash);
		takeBoxPanel.add(enter);
		
		totalBoxPanel.add(totalName);
		totalBoxPanel.add(totalCash);
		
		changeBoxPanel.add(change);
		changeBoxPanel.add(changeCash);
		
		leftBoxPanel.add(takeBoxPanel);
		leftBoxPanel.add(totalBoxPanel);
		leftBoxPanel.add(changeBoxPanel);
		
		rightBoxPanel.add(numPadPanel);
		numPadPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

		cashGo.setPreferredSize(new Dimension(90, 35));
		
		enterBoxPanel.add(cashGo);
		cashGo.setBounds(170, 20, 90, 35);
		
		cashBackgroundPanel.add(leftBoxPanel);
		cashBackgroundPanel.add(rightBoxPanel);
		
		cashSurroundPanel.add(titleBoxPanel);
		cashSurroundPanel.add(cashBackgroundPanel);
		cashSurroundPanel.add(enterBoxPanel);
		cashSurroundPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		leftBoxPanel.setBackground(Color.WHITE);

		this.cardPanel.add("첫화면", backGroundPanel);
		this.cardPanel.add("카드", cardBackGroundPanel);
		this.cardPanel.add("현금", cashSurroundPanel);

		add(cardPanel);
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void createCardNumber(int num) {
		for(int i=0; i<num; ++i) {
			cardNum.add((int)(Math.random()*8999+1000));
		}
	}
	
	public Vector<OrdersData> getOrders(){
		return this.orders;
	}
	
	public Vector<OrderDetailsData> getOrderDetail(){
		return this.orderDetail;
	}
	
	public JButton getCashGo() {
		return this.cashGo;
	}
	
	public JPanel getCardPanel() {
		return this.cardPanel;
	}
	
	public JTextField getTakeCash() {
		return this.takeCash;
	}
	
	public JLabel getTotalCash() {
		return this.totalCash;
	}
	
	public JLabel getChangeCash() {
		return this.changeCash;
	}
}
