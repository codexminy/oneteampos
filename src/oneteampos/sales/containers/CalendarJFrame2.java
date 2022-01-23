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
import oneteampos.sales.actions.monthLableListener;

public class CalendarJFrame2 extends JFrame implements ActionListener, WindowListener{
	
	JPanel bar = new JPanel();
	JButton lastyear = new JButton("◀");
	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	JLabel yLbl = new JLabel("년 ");
	JButton nextyear = new JButton("▶");
	// 중앙 지역
	JPanel center = new JPanel(new BorderLayout());
	// 중앙 상단 지역
	JPanel cntNorth = new JPanel(new GridLayout(0, 7));
	// 중앙 중앙 지역
	JPanel cntCenter = new JPanel(new GridLayout(0, 7));

	// 클릭한 텍스트 가져오는 라벨
	JTextField selectField;
	
	
	
	Calendar now = Calendar.getInstance();
	int year, month, date;

	

	public CalendarJFrame2(MonthlySalesJPanel monthlySalesJPanel, JTextField feild, int num) {
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
	
	public void getCalendar(JTextField feild) { // 속성 메소드 
		// 버튼 속성
		lastyear.setBackground(Color.WHITE);
		lastyear.setBorderPainted(false);
		lastyear.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lastyear.setFocusable(false);
		nextyear.setBackground(Color.WHITE);
		nextyear.setBorderPainted(false);
		nextyear.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nextyear.setFocusable(false);
		lastyear.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		nextyear.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		lastyear.setForeground(Color.BLACK);
		nextyear.setForeground(Color.BLACK);
	
		// 판넬 속성
		cntNorth.setBackground(Color.WHITE);
		cntCenter.setBackground(Color.WHITE);
		
		// 라벨 속성
		selectField.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
	
		// 콤보 속성
		yearCombo.setBackground(Color.WHITE);
		
	
		year = now.get(Calendar.YEAR);// 2022년
		for (int i = year - 20; i <= year + 50; i++) {
			yearModel.addElement(i);
		}
	
		//////////////////////////프레임///////////////////////////////////////////
		// 상단 지역
		add("North", bar);
		bar.setLayout(new FlowLayout());
		bar.setSize(300, 400);
		bar.add(lastyear);
		////////////////////////// 달력/////////////////////////////////////////////
		bar.add(yearCombo);
		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);
		bar.add(yLbl);
		bar.add(nextyear);
		bar.setBackground(Color.WHITE);
		// 중앙 지역
		add("Center", center);
		// 중앙 상단 지역
		center.add("North", cntNorth);
	
		// 중앙 중앙 지역
		center.add("Center", cntCenter);
		// ※여기서 받아와 ※
		dayPrint(year);
		// 이벤트
		yearCombo.addActionListener(this);
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
			if (eventBtn.equals(lastyear)) { // 전년도
					yy--;
				
			} else if (eventBtn.equals(nextyear)) { // 내년
					yy++;
			}
			yearCombo.setSelectedItem(yy);
		}
	}
	

	
	// 요일 출력
	public void dayPrint(int y) {
		for (int i = 0; i < 12; i++) { 
			JLabel month = new JLabel();
			month.setHorizontalAlignment(JLabel.CENTER);
			month.setCursor(new Cursor(Cursor.HAND_CURSOR));
			month.setText(1 + i + "월");
			cntCenter.add(month);
			
			month.addMouseListener(new monthLableListener(y , this));
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
