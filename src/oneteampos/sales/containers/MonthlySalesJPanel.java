package oneteampos.sales.containers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.BorderUIResource;

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
//	JButton showBtn;
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
		MonthShowBtn showBtn = new MonthShowBtn(this);
		add(showBtn);
		NmonthBtn oneMonthBtn = new NmonthBtn(1);
		add(oneMonthBtn);
		NmonthBtn threeMonthBtn = new NmonthBtn(3);
		add(threeMonthBtn);
		NmonthBtn sixMonthBtn = new NmonthBtn(6);
		add(sixMonthBtn);
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

class MonthShowBtn extends RoundedButton {
	
	public MonthShowBtn(MonthlySalesJPanel monthlySalesPanel) {
		super("확인");
		setBounds(615, 28, 100, 30);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorderPainted(false);

		addMouseListener(new MonthlyShowBtn(monthlySalesPanel));
	
	}
}	

class NmonthBtn extends RoundedButton {
	
	public NmonthBtn(int month) {
		setText(month + "개월");
		if(month == 1) {
			setBounds(140, 100, 100, 50);
			
		} else if (month == 3) {
			setBounds(260, 100, 100, 50);
		} else {
			setBounds(380, 100, 100, 50);
		}
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JRadioButton btn = (JRadioButton)e.getSource();
				btn.setSelected(false);
				String fst = fstField.getText();
		
				if(fst.equals("")) {
					// 현재 
					Date now = Calendar.getInstance().getTime();
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
					sndField.setText(fm.format(now));
					// N개월 전 
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, -1 * month);
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
						
						// N개월 후
						Calendar cal = Calendar.getInstance();
						cal.setTime(now);
						cal.add(Calendar.MONTH, month);
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
	}
}

}
