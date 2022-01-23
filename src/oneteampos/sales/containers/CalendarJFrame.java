package oneteampos.sales.containers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oneteampos.sales.actions.dayLableListener;
import oneteampos.sales.components.FirstJTextField;

public class CalendarJFrame extends JFrame implements ActionListener, WindowListener {
	
	
	JPanel bar = new JPanel();
	JButton lastMonth = new JButton("◀");
	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	JLabel yLbl = new JLabel("년 ");
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
	JLabel mLbl = new JLabel("월");
	JButton nextMonth = new JButton("▶");
	// 중앙 지역
	JPanel center = new JPanel(new BorderLayout());
	// 중앙 상단 지역
	JPanel cntNorth = new JPanel(new GridLayout(0, 7));
	// 중앙 중앙 지역
	JPanel cntCenter = new JPanel(new GridLayout(0, 7));
	// 요일 입력
	String dw[] = { "일", "월", "화", "수", "목", "금", "토" };
	// 클릭한 텍스트 가져오는 라벨
	JTextField selectField;
	
	
	
	Calendar now = Calendar.getInstance();
	int year, month, date;

	

	public CalendarJFrame(DailySalesJPanel dailySalesJPanel, JTextField feild , int num) {
		selectField = feild;
		getCalendar(feild);
		
		if(num == 1) {
			setLocation(250, 250);
			
		} else {
			setLocation(400, 250);
		}
		
		Image image = null; 
		try { 
			image = ImageIO.read(new File("image\\icon4.png")); 
		} catch (IOException e1) { 
			e1.printStackTrace(); 
		} 
		setIconImage(image);
	}
	
	public void getCalendar(JTextField textField) { // 속성 메소드 
		// 버튼 속성
		lastMonth.setBackground(Color.WHITE);
		lastMonth.setBorderPainted(false);
		lastMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lastMonth.setFocusable(false);
		nextMonth.setBackground(Color.WHITE);
		nextMonth.setBorderPainted(false);
		nextMonth.setCursor(new Cursor(Cursor.HAND_CURSOR));
	
		// 판넬 속성
		cntNorth.setBackground(Color.WHITE);
		cntCenter.setBackground(Color.WHITE);
		
		// 라벨 속성
		selectField.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
	
		// 콤보 속성
		yearCombo.setBackground(Color.WHITE);
		monthCombo.setBackground(Color.WHITE);
	
		year = now.get(Calendar.YEAR);// 2022년
		month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
		date = now.get(Calendar.DATE);
		for (int i = year - 20; i <= year + 50; i++) {
			yearModel.addElement(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthModel.addElement(i);
		}
		//////////////////////////프레임///////////////////////////////////////////
		// 상단 지역
		add("North", bar);
		bar.setLayout(new FlowLayout());
		bar.setSize(300, 400);
		bar.add(lastMonth);
		////////////////////////// 달력/////////////////////////////////////////////
		bar.add(yearCombo);
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);
		bar.add(yLbl);
		bar.add(monthCombo);
		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month);
		bar.add(mLbl);
		bar.add(nextMonth);
		bar.setBackground(Color.WHITE);
		// 중앙 지역
		add("Center", center);
		// 중앙 상단 지역
		center.add("North", cntNorth);
		for (int i = 0; i < dw.length; i++) { // 요일 뿌리기
			JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
			if (i == 0) {
				dayOfWeek.setForeground(Color.red);
			} else if (i == 6) {
				dayOfWeek.setForeground(Color.blue);
			}
			cntNorth.add(dayOfWeek);
		}
		// 중앙 중앙 지역
		center.add("Center", cntCenter);
		// ※여기서 받아와 ※
		dayPrint(year, month);
		// 이벤트
		yearCombo.addActionListener(this);
		monthCombo.addActionListener(this);
		lastMonth.addActionListener(this);
		nextMonth.addActionListener(this);
		addWindowListener(this);
		// frame 기본 셋팅
		setSize(400, 300);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	// 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton) {
			JButton eventBtn = (JButton) obj;
			int yy = (Integer) yearCombo.getSelectedItem();
			int mm = (Integer) monthCombo.getSelectedItem();
			if (eventBtn.equals(lastMonth)) { // 전달
				if (mm == 1) {
					yy--;
					mm = 12;
				} else {
					mm--;
				}
			} else if (eventBtn.equals(nextMonth)) { // 다음달
				if (mm == 12) {
					yy++;
					mm = 1;
				} else {
					mm++;
				}
			}
			yearCombo.setSelectedItem(yy);
			monthCombo.setSelectedItem(mm);
		} else if (obj instanceof JComboBox) { // 콤보박스 이벤트 발생시
			createDayStart();
		}
	}
	
	private void createDayStart() {
		cntCenter.setVisible(false); // 패널 숨기기
		cntCenter.removeAll(); // 날짜 출력한 라벨 지우기
		dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
		cntCenter.setVisible(true); // 패널 재출력
	}
	
	// 날짜 출력
	public void dayPrint(int y, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
		for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
			cntCenter.add(new JLabel(""));
		}
		for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
			JLabel day = new JLabel();
			day.setHorizontalAlignment(JLabel.CENTER);
			day.setCursor(new Cursor(Cursor.HAND_CURSOR));
			if ((week + i) % 7 == 0) {
				cntCenter.add(day).setForeground(Color.blue);
				day.setText(1 + i + "");
			} else if ((week + i) % 7 == 1) {
				cntCenter.add(day).setForeground(Color.red);
				day.setText(1 + i + "");
			} else {
				cntCenter.add(day);
				day.setText(1 + i + "");
			}
	
			day.addMouseListener(new dayLableListener(y , m, this));
		}

}
	public JTextField getSelectField() {
		return selectField;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
