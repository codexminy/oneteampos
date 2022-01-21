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
import javax.swing.JRadioButton;

import oneteampos.sales.actions.DailyShowBtn;
import oneteampos.sales.actions.MonthlyShowBtn;
import oneteampos.sales.components.DashJLabel;
import oneteampos.sales.components.FirstJTextField;
import oneteampos.sales.components.PeriodJLabel;
import oneteampos.sales.components.SecondJTextField;

public class DailySalesJPanel extends JPanel{
	
	DailySalesJPanel dailySalesJPanel;
	SalesPanel salesPanel;
	FirstJTextField fstField;
	SecondJTextField sndField;
	JButton showBtn;
	DailyResultPanel resultPanel;

	public DailySalesJPanel(SalesPanel salesPanel) {
		this.setBounds(0, 100, 1280, 680);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setVisible(false);
		
		this.salesPanel  = salesPanel;
		this.dailySalesJPanel = this;
		
		// 라벨 추가 
		add(new PeriodJLabel());
		add(new DashJLabel());
		
		// TextField 추가
		fstField = new FirstJTextField(salesPanel, this);
		add(fstField);
		sndField = new SecondJTextField(salesPanel, this);
		add(sndField);
		
		// 버튼 추가
		DayShowBtn dayShowBtn = new DayShowBtn(this);
		add(dayShowBtn);
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
	
	public DailyResultPanel getResultPanel() {
		return resultPanel;
	}
	
class DayShowBtn extends RoundedButton {
	
	public DayShowBtn(DailySalesJPanel dailySalesPanel) {
		super("확인");
		setBackground(new Color(247, 245, 247));
		setBounds(615, 28, 100, 30);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new DailyShowBtn(dailySalesPanel));
	}
}	

class NmonthBtn extends RoundedButton {

	public NmonthBtn(int month) {
		super(month + "개월");
		if (month == 1) {
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
				JRadioButton btn = (JRadioButton) e.getSource();
				btn.setSelected(false);
				String fst = fstField.getText();
				if (fst.equals("")) {
					// 현재
					Date now = Calendar.getInstance().getTime();
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					sndField.setText(fm.format(now));
					// 1개월 전
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, -1 * month);
					fstField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date startDate = cal.getTime();

					long calDate = now.getTime() - startDate.getTime();
					long calDateDays = calDate / (24 * 60 * 60 * 1000);
					calDateDays = Math.abs(calDateDays);
					resultPanel = new DailyResultPanel(startDate, calDateDays);
					add(resultPanel);
				} else if (!fst.equals("")) {
					// 현재
					SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
					Date now = null;
					try {
						now = fm.parse(fstField.getText());
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					// 1개월 후
					Calendar cal = Calendar.getInstance();
					cal.setTime(now);
					cal.add(Calendar.MONTH, month);
					sndField.setText(fm.format(cal.getTime()));
					// 날짜 차이 계산
					Date AfterDate = cal.getTime();

					long calDate = now.getTime() - AfterDate.getTime();
					long calDateDays = calDate / (24 * 60 * 60 * 1000);
					calDateDays = Math.abs(calDateDays);
					resultPanel = new DailyResultPanel(now, calDateDays);
					add(resultPanel);
				} else {
					JOptionPane.showMessageDialog(dailySalesJPanel, "앞쪽에 날짜를 선택해주세요");
					sndField.setText("");
				}
			}
		});

	}
}

}
