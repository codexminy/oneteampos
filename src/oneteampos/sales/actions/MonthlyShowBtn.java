package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import oneteampos.sales.containers.DailyResultPanel;
import oneteampos.sales.containers.MonthlyResultJPanel;
import oneteampos.sales.containers.MonthlySalesJPanel;

public class MonthlyShowBtn implements MouseListener {
	
	MonthlySalesJPanel monthlySalesJPanel;

	public MonthlyShowBtn(MonthlySalesJPanel monthlySalesJPanel) {
		this.monthlySalesJPanel = monthlySalesJPanel;
	}
	
	public long calDateMonth(String fst, String snd) {
		long calDateMonth = 0;
		String[] fstArr = fst.split("-");
		String[] sndArr = snd.split("-");
		int calYear;
		int claMonth;
		
		if(Integer.parseInt(fstArr[0]) == Integer.parseInt(sndArr[0])) {
			calDateMonth = Math.abs(Integer.parseInt(fstArr[1]) - Integer.parseInt(sndArr[1]));
		} else if (Integer.parseInt(fstArr[0]) < Integer.parseInt(sndArr[0])) {
			calYear = (Integer.parseInt(sndArr[0]) - Integer.parseInt(fstArr[0])) *12;
			claMonth = 12 - Integer.parseInt(fstArr[1])+ 1 + Integer.parseInt(sndArr[1]) ;
		} else {
			calYear = (Integer.parseInt(fstArr[0]) - Integer.parseInt(sndArr[0])) * 12;
			claMonth = 12 - Integer.parseInt(sndArr[1])+ 1 + Integer.parseInt(fstArr[1]) ;
		}
		
		return calDateMonth;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		JRadioButton btn = (JRadioButton)e.getSource();
		btn.setSelected(false);
		
		// 년도와 달 가져오기
				String fst = monthlySalesJPanel.getFstField().getText();
				String snd = monthlySalesJPanel.getSndField().getText();
				SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
				Date fstDate = null;
				Date sndDate = null;
				
				if(fst.equals("")) {
					JOptionPane.showMessageDialog(monthlySalesJPanel, "날짜를 선택해주세요");
				} else {
				
					try {
						fstDate = fm.parse(fst);
						sndDate = fm.parse(snd);
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					// 날짜 차이 계산 
					long calDateMonth = 0;
					String[] fstArr = fst.split("-");
					String[] sndArr = snd.split("-");
					int calYear;
					int claMonth;
					
					if(Integer.parseInt(fstArr[0]) == Integer.parseInt(sndArr[0])) {
						calDateMonth = Math.abs(Integer.parseInt(fstArr[1]) - Integer.parseInt(sndArr[1]));
					} else if (Integer.parseInt(fstArr[0]) < Integer.parseInt(sndArr[0])) {
						calYear = (Integer.parseInt(sndArr[0]) - Integer.parseInt(fstArr[0])) *12;
						claMonth = 12 - Integer.parseInt(fstArr[1])+ 1 + Integer.parseInt(sndArr[1]) ;
						calDateMonth = calYear + claMonth;
					} else {
						calYear = (Integer.parseInt(fstArr[0]) - Integer.parseInt(sndArr[0])) * 12;
						claMonth = 12 - Integer.parseInt(sndArr[1])+ 1 + Integer.parseInt(fstArr[1]) ;
						calDateMonth = calYear + claMonth;
					}
		
					if(fstDate.after(sndDate)) {
						monthlySalesJPanel.add(new MonthlyResultJPanel(sndDate,calDateMonth));
					} else if (fstDate.before(sndDate)) {
						monthlySalesJPanel.add(new MonthlyResultJPanel(fstDate,calDateMonth));
					} else {
						monthlySalesJPanel.add(new MonthlyResultJPanel(fstDate,calDateMonth));
					}
			}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
