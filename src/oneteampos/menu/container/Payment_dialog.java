package oneteampos.menu.container;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.action.Payment_cardProcessAction;
import oneteampos.menu.action.Payment_cashInputAction;
import oneteampos.menu.action.Payment_cashProcessAction;
import oneteampos.menu.action.Payment_clickAction;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_numberPad;
import oneteampos.menu.data.OrderDetailsData;
import oneteampos.menu.data.OrdersData;
import oneteampos.menu.etc.ChangeString;

public class Payment_dialog extends JDialog {
	
	public Vector<OrdersData> orders;
	public Vector<OrderDetailsData> orderDetail;
	public JLabel cardNum;
	public MenuRightPanel mrp;
	public JPanel cardPanel;
	public JPanel bg;
	public JButton card;
	public JButton cash;
	public JButton cashGo;
	public JTextField takeCash;
	public JLabel totalCash;
	public JLabel changeCash;
	
	public JPanel getCardPanel() {
		return this.cardPanel;
	}
	
	public Payment_dialog(MainFrame mainFrame, MenuRightPanel mrp) {
		super(mainFrame, "결제 수단", true);
		this.mrp = mrp;
		
		cardPanel = new JPanel(new CardLayout());

		bg = new JPanel(new GridLayout(1,1,20,20));
		bg.setBorder(BorderFactory.createEmptyBorder(80, 40, 80, 40));
		bg.setBackground(Color.WHITE);

		JButton card = new All_btn("카드");
		JButton cash = new All_btn("현금");

		cash.addMouseListener(new Payment_clickAction(this));
		card.addMouseListener(new Payment_clickAction(this));
		
		bg.add(card);
		bg.add(cash);
		
		JPanel cardBg = new JPanel(null);
		
		JLabel cardTitle = new JLabel("카드 결제 완료");
		
		int cardNum1 = (int)(Math.random()*8999+1000);
		int cardNum2 = (int)(Math.random()*8999+1000);
		int cardNum3 = (int)(Math.random()*8999+1000);
		
		JLabel cardName = new JLabel("카드번호");
		cardNum = new JLabel(String.format("%d-%d-%d", cardNum1, cardNum2, cardNum3));
		JLabel approval = new JLabel("승인금액");
		JLabel approvalCash = new JLabel(mainFrame.getMainPanel().getMenuPanel().getRightPanel().getTotalPrice().getText());
		
		JButton yes = new JButton("확인");
		yes.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
		});
		
		card.addMouseListener(new Payment_cardProcessAction(this, mrp));


		cardTitle.setBounds(150, 20, 100, 30);
		cardName.setBounds(80, 150, 50, 30);
		cardNum.setBounds(160, 150, 200, 30);
		approval.setBounds(100, 200, 200, 30);
		approvalCash.setBounds(100, 230, 200, 30);
		yes.setBounds(100, 280, 100, 30);
		
		cardBg.add(cardTitle);
		cardBg.add(cardName);
		cardBg.add(cardNum);
		cardBg.add(approval);
		cardBg.add(approvalCash);
		cardBg.add(yes);

		JPanel cashBg = new JPanel(null);
		
		JLabel cashTitle = new JLabel("현금결제");
		cashTitle.setBounds(100, 20, 100, 30);
		
		JLabel takeName = new JLabel("받은 금액");
		takeName.setBounds(30, 70, 50, 30);
		takeCash = new JTextField();
		takeCash.setBounds(100, 70, 70, 30);
		JLabel takeWon = new JLabel("원");
		takeWon.setBounds(180, 70, 50, 30);
		
		JButton enter = new JButton("입력");
		enter.setBounds(230, 70, 100, 30);
		
		JLabel totalName = new JLabel("결제 금액");
		totalName.setBounds(30, 110, 50, 30);
		totalCash = new JLabel(ChangeString.setErase(mrp.getTotalPrice().getText()));
		totalCash.setBounds(100, 110, 70, 30);
		JLabel totalWon = new JLabel("원");
		totalWon.setBounds(180, 110, 50, 30);
		
		
		cashGo = new JButton("결제");
		cashGo.setBounds(50, 300, 100, 30);
		cashGo.setEnabled(false);
		
		JLabel change = new JLabel("거스름돈");
		change.setBounds(30, 200, 50, 30);
		changeCash = new JLabel("0");
		changeCash.setBounds(90, 200, 50, 30);
		
		enter.addMouseListener(new Payment_cashInputAction(this));
		cashGo.addMouseListener(new Payment_cashProcessAction(this, mrp));

		All_numberPad numPadPanel = new All_numberPad(takeCash);
		
		numPadPanel.setBounds(200, 200, 150, 150);

		cashBg.add(cashTitle);
		cashBg.add(takeName);
		cashBg.add(takeCash);
		cashBg.add(takeWon);
		cashBg.add(enter);
		cashBg.add(totalName);
		cashBg.add(totalCash);
		cashBg.add(totalWon);
		cashBg.add(numPadPanel);
		cashBg.add(cashGo);
		cashBg.add(change);
		cashBg.add(changeCash);

		cardPanel.add("첫화면", bg);
		cardPanel.add("카드", cardBg);
		cardPanel.add("현금", cashBg);

		add(cardPanel);
		
		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
