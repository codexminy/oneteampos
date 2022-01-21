package oneteampos.sales.containers;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import oneteampos.sales.actions.DailyShowBtn;
import oneteampos.sales.actions.MonthlyShowBtn;
import oneteampos.sales.components.DashJLabel;
import oneteampos.sales.components.FirstJTextField;
import oneteampos.sales.components.PeriodJLabel;
import oneteampos.sales.components.SecondJTextField;

public class MonthlySalesJPanel extends JPanel{
	
	MonthlySalesJPanel monthlySalesPanel;
	SalesPanel salesPanel;
	FirstJTextField fstField;
	SecondJTextField sndField;
	JButton showBtn;
	MonthlyResultJPanel resultPanel;

	public MonthlySalesJPanel(SalesPanel salesPanel) {
		this.setBounds(0, 100, 1280, 680);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setVisible(false);
		
		this.salesPanel  = salesPanel;
		
		// 라벨 추가 
		add(new PeriodJLabel());
		add(new DashJLabel());
		
		// TextField 추가
		fstField = new FirstJTextField(salesPanel, this);
		add(fstField);
		sndField = new SecondJTextField(salesPanel, this);
		add(sndField);
		
		// 버튼 추가
		addShowBtn();
		addOneMonthBtn();
		addThreeMonthBtn();
		addSixMonthBtn();
	}

	public void addShowBtn() {
		this.showBtn = new JButton("확인");
		showBtn.setBackground(new Color(247, 245, 247));
		showBtn.setBounds(615, 28, 100, 30);
		showBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		showBtn.addMouseListener(new MonthlyShowBtn(this));
		
		add(showBtn);
	}
	
	public void addOneMonthBtn() {
		JButton oneMonthBtn = new JButton("1개월"); 
		oneMonthBtn.setBounds(140, 100, 100, 50);
		oneMonthBtn.setBackground(new Color(247, 245, 247));
		oneMonthBtn.setFont(new Font("돋움", Font.BOLD, 18));
		oneMonthBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		oneMonthBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fst = fstField.getText();
				if(fst.equals("")) {
					// 현재 
					Date now = Calendar.getInstance().getTime();
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					sndField.setText(fm.format(now));
					// 1개월 전 
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, -1);
					fstField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date startDate = cal.getTime();
					long calDateDays = 1;
					resultPanel = new MonthlyResultJPanel(startDate,calDateDays);
					add(resultPanel);
				} else if(!fst.equals("")){
					// 현재 
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					Date now = null; 
					try {
						now = fm.parse(fstField.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					// 1개월 후
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, 1);
					sndField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date AfterDate = cal.getTime();
				    long calDateDays = 1;
					resultPanel = new MonthlyResultJPanel(now,calDateDays);
					add(resultPanel);
				} else {
					JOptionPane.showMessageDialog(monthlySalesPanel, "앞쪽에 날짜를 선택해주세요");
					sndField.setText("");
				}
			}
		});
		
		add(oneMonthBtn);
	}
	

	private void addSixMonthBtn() {
		JButton sixMonthBtn = new JButton("6개월"); 
		sixMonthBtn.setBounds(380, 100, 100, 50);
		sixMonthBtn.setBackground(new Color(247, 245, 247));
		sixMonthBtn.setFont(new Font("돋움", Font.BOLD, 18));
		sixMonthBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		sixMonthBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fst = fstField.getText();
				if(fst.equals("")) {
					// 현재 
					Date now = Calendar.getInstance().getTime();
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					sndField.setText(fm.format(now));
					// 1개월 전 
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, -6);
					fstField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date startDate = cal.getTime();
					long calDateDays = 1;
					resultPanel = new MonthlyResultJPanel(startDate,calDateDays);
					add(resultPanel);
				} else if(!fst.equals("")){
					// 현재 
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					Date now = null; 
					try {
						now = fm.parse(fstField.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					// 1개월 후
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, 6);
					sndField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date AfterDate = cal.getTime();
				    long calDateDays = 1;
					resultPanel = new MonthlyResultJPanel(now,calDateDays);
					add(resultPanel);
				} else {
					JOptionPane.showMessageDialog(monthlySalesPanel, "앞쪽에 날짜를 선택해주세요");
					sndField.setText("");
				}
			}
		});
		
		add(sixMonthBtn);
	}

	private void addThreeMonthBtn() {
		JButton threeMonthBtn = new JButton("3개월"); 
		threeMonthBtn.setBounds(260, 100, 100, 50);
		threeMonthBtn.setBackground(new Color(247, 245, 247));
		threeMonthBtn.setFont(new Font("돋움", Font.BOLD, 18));
		threeMonthBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		threeMonthBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fst = fstField.getText();
				if(fst.equals("")) {
					// 현재 
					Date now = Calendar.getInstance().getTime();
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					sndField.setText(fm.format(now));
					// 1개월 전 
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, -3);
					fstField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date startDate = cal.getTime();
					long calDateDays = 1;
					resultPanel = new MonthlyResultJPanel(startDate,calDateDays);
					add(resultPanel);
				} else if(!fst.equals("")){
					// 현재 
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					Date now = null; 
					try {
						now = fm.parse(fstField.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					// 1개월 후
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, 3);
					sndField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date AfterDate = cal.getTime();
				    long calDateDays = 1;
					resultPanel = new MonthlyResultJPanel(now,calDateDays);
					add(resultPanel);
				} else {
					JOptionPane.showMessageDialog(monthlySalesPanel, "앞쪽에 날짜를 선택해주세요");
					sndField.setText("");
				}
			}
		});
		
		add(threeMonthBtn);
	}
	public void setVisibleFalse() {
		this.setVisible(false);
	}
	public void setVisibleTrue() {
		this.setVisible(true);
	}
	
	public FirstJTextField getFstField() {
		return fstField;
	}
	
	public SecondJTextField getSndField() {
		return sndField;
	}
	
}
