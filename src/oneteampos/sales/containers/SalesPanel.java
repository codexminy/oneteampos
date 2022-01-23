package oneteampos.sales.containers;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import oneteampos.datamodel.Clock;
import oneteampos.main.MainFrame;
import oneteampos.order.compnents.BackBtn;
import oneteampos.sales.components.DailyBtn;
import oneteampos.sales.components.MonthlyBtn;

public class SalesPanel extends JPanel{

	MainFrame mainframe;
	DailyBtn daliyBtn;
	MonthlyBtn monthlyBtn;
	DailySalesJPanel dailySalesPanel;
	MonthlySalesJPanel monthlySalesPanel;
	
	public SalesPanel(MainFrame mainframe) {
		// 판넬 설정 
		this.setBounds(0, 0, 1280, 720);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		this.mainframe = mainframe;
		
		// 메인 프레임에 추가
		mainframe.add(this);
		
		// 로그인 정보 : 사번 + 사원이름
		addLoginInfoLabel();
		
		//시계
		Clock clock = new Clock();
		add(clock);
		
		//	버튼추가
		daliyBtn = new DailyBtn(mainframe,this);
		add(daliyBtn);
		monthlyBtn = new MonthlyBtn(mainframe,this);
		add(monthlyBtn);
		add(new BackBtn(mainframe , this , "salesPanel"));
		
		//패널추가
		dailySalesPanel = new DailySalesJPanel(this);
		add(dailySalesPanel);
		monthlySalesPanel = new MonthlySalesJPanel(this);
		add(monthlySalesPanel);
		
		
	}
	
	@Override
	public void paint(Graphics g) { // 선 긋기
		super.paint(g);
		
		g.drawLine(20, 60, 1240, 60);
		g.drawLine(20, 100, 1240, 100);
	}
	
	public void addLoginInfoLabel() { // 로그인 정보 라벨 (stf_id, stf_name)
		mainframe.getLoginStaffIDJLabel().setVisible(true);
		mainframe.getStaffNameJLabel().setVisible(true);
		
		add(mainframe.getLoginStaffIDJLabel());
		add(mainframe.getStaffNameJLabel());
	}
	
	public DailyBtn getDailyBtn() {
		return daliyBtn;
	}
	
	public MonthlyBtn getMonthlyBtn() {
		return monthlyBtn;
	}

	public DailySalesJPanel getDailySalesPanel() {
		return dailySalesPanel;
	}
	
	public MonthlySalesJPanel getMonthlySalesPanel() {
		return monthlySalesPanel;
	}
}
