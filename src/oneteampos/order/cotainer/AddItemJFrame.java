package oneteampos.order.cotainer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import oneteampos.order.actions.AddItemJPanelExit;
import oneteampos.order.actions.QtyControlListener;
import oneteampos.order.compnents.AddOrderBtn;
import oneteampos.sales.containers.RoundedButton;

public class AddItemJFrame extends JFrame {
	
	OrderListJPanel orderListPanel;
	JLabel countLabel;
	JLabel itemNameLabel;
	JLabel itemRealPriceLable;
	AddOrderBtn addOrderBtn;
	
	public AddItemJFrame(OrderListJPanel orderListPanel) {
		super("발주 아이템 추가");
		setBounds(650, 200, 290, 240);
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		setVisible(true);
		
		this.orderListPanel = orderListPanel;
		this.countLabel = countLabel();
		
		addItmeLabel(); // 품목이름 라벨
		addItemPriceLabel(); // 가격 라벨
		leftBtn(); // 수량 줄이는 버튼
		rightBtn(); // 수량 올리는 버튼 
		this.itemNameLabel = addItemNameLabel(); // 클릭한 품목 라벨
		addItemRealPriceLabel(); // 품목 가격 나오는 라벨
		add(itemNameLabel);
		add(countLabel); // 추가할 수량 라벨 
		this.addOrderBtn = new AddOrderBtn(orderListPanel, this); // 추가 버튼
		add(addOrderBtn);

	}
	
	public JLabel countLabel() { // 수량표시 라벨
		JLabel countLabel = new JLabel("0");
		countLabel.setBounds(73, 98, 50, 30);
		countLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 23));
		countLabel.setBackground(new Color(228, 227, 230));

		return countLabel;
	}
	
	public JLabel getCountLabel() {
		return countLabel;
	}
	
	public void addItmeLabel() { // 품목이름 : 라벨
		JLabel itemLabel = new JLabel("품목이름 : ");
		itemLabel.setBounds(20, 20, 100, 50);
		itemLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		itemLabel.setBackground(new Color(228, 227, 230));
		
		add(itemLabel);
	}
	
	public JLabel addItemNameLabel() { // 선택한 품목이름 
		JLabel itemNameLabel = new JLabel();
		itemNameLabel.setBounds(105, 20, 150, 50);
		itemNameLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		itemNameLabel.setBackground(new Color(228, 227, 230));
		
		return itemNameLabel;
	}
	
	public void addItemPriceLabel() { // 가격 :
		JLabel itemPriceLabel = new JLabel("가격 :");
		itemPriceLabel.setBounds(20, 50, 100, 50);
		itemPriceLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		itemPriceLabel.setBackground(new Color(228, 227, 230));
		
		add(itemPriceLabel);
	}
	
	public void addItemRealPriceLabel() { // 선택한 품목의 가격 
		itemRealPriceLable = new JLabel();
		itemRealPriceLable.setBounds(80, 50, 100, 50);
		itemRealPriceLable.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		itemRealPriceLable.setBackground(new Color(228, 227, 230));
		
		add(itemRealPriceLable);
	}
	
	public JLabel getItemRealPriceLabel() {
		return itemRealPriceLable;
	}
	
	public void rightBtn( ) { // 수량 증가버튼
		RoundedButton rightBtn = new RoundedButton("▶");
		rightBtn.setBounds(100, 100, 50, 30);
		rightBtn.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		rightBtn.setBackground(new Color(228, 227, 230));
		rightBtn.setBorderPainted(false);
		rightBtn.setRequestFocusEnabled(false);
		rightBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rightBtn.setFocusPainted(false);
		
		rightBtn.addMouseListener(new QtyControlListener(this));
		
		add(rightBtn);
		
		
	}
	
	public void leftBtn() { // 수량 감소버튼
		RoundedButton leftBtn = new RoundedButton("◀");
		leftBtn.setBounds(10, 100, 50, 30);
		leftBtn.setFont(new Font("돋움", Font.BOLD, 15));
		leftBtn.setBackground(new Color(228, 227, 230));
		leftBtn.setBorderPainted(false);
		leftBtn.setRequestFocusEnabled(false);
		leftBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		leftBtn.setFocusPainted(false);
		
		leftBtn.addMouseListener(new QtyControlListener(this));
		
		add(leftBtn);
	}
	
	public JLabel getItemNameLabel() {
		return itemNameLabel;
	}
	
	public void setItemNameLabelVisibleTrue() {
		itemNameLabel.setVisible(true);
	}
	
	public void setItemNameLabelVisibleFalse() {
		itemNameLabel.setVisible(false);
	}
	
	public void setVisibleTrue() {
		this.setVisible(true);
	}
	
	public void setVisibleFalse() {
		this.setVisible(false);
	}

}
