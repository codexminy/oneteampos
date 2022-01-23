package oneteampos.order.cotainer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.order.compnents.BackBtn;
import oneteampos.order.compnents.OrderListBtn;
import oneteampos.order.compnents.StockListBtn;



public class OrderJPanel extends JPanel{
	
	
	MainFrame mainframe;
	StockListBtn stockListBtn;
	OrderListBtn orderListBtn;
	OrderListJPanel orderListPanel;
	StockListJPanel stockListPanel;
	
	public OrderJPanel(MainFrame mainframe) {
		this.mainframe = mainframe;
		this.stockListBtn = new StockListBtn(this);
		this.orderListBtn = new OrderListBtn(this);
		StockListJPanel stockListPanel = new StockListJPanel(this);
		orderListPanel = new OrderListJPanel(this);
		
		
		// 판넬 설정 
		this.setBounds(0, 0, 1280, 720);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		// 메인프레임에 판넬 추가
		mainframe.add(this);
		
		// 로그인 정보 : 사번 + 사원이름
		addLoginInfoLabel();
		
		// 버튼 추가
		add(new BackBtn(mainframe , this , "orderJPanel"));
		add(stockListBtn); //  재고 버튼
		add(orderListBtn); // 발주 버튼
		setStockListPanel(stockListPanel);
		setOrderListPanel(orderListPanel);
	
	}
	
	@Override
	public void paint(Graphics g) { // 선 긋기
		super.paint(g);
		
		g.drawLine(20, 60, 1240, 60);
		g.drawLine(20, 100, 1240, 100);
	}
	
	public void setVisibleTrue() {
		setVisible(true);
	}
	
	public void setVisibleFalse() {
		setVisible(false);
	}
	
	public void addLoginInfoLabel() { // 로그인 정보 라벨 (stf_id, stf_name)
		mainframe.getLoginStaffIDJLabel().setVisible(true);
		mainframe.getStaffNameJLabel().setVisible(true);
		
		add(mainframe.getLoginStaffIDJLabel());
		add(mainframe.getStaffNameJLabel());
	}
	
	public StockListBtn getStockListBtn() {
		return stockListBtn ;
	}
	
	public OrderListBtn getOrderListBtn() {
		return orderListBtn;
	}
	
	public StockListJPanel getStockListJPanel() {
		return stockListPanel;
	}

	public OrderListJPanel getOrderListJPanel() {
		return orderListPanel;
	}
	
	public void setStockListPanel(StockListJPanel stockListPanel) {
		this.stockListPanel = stockListPanel;
		add(stockListPanel);
	}
	
	public void setOrderListPanel(OrderListJPanel orderListPanel) {
		this.orderListPanel = orderListPanel;
		add(orderListPanel);
	}
	
	
}
