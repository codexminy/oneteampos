package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import oneteampos.sales.containers.DailyResultPanel;
import oneteampos.sales.containers.DailySalesJPanel;

public class DailyShowBtn implements MouseListener {
	
	DailySalesJPanel dailySalesJPanel;
	
	public DailyShowBtn(DailySalesJPanel dailySalesJPanel) {
		this.dailySalesJPanel = dailySalesJPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 날짜 계산
		String fst = dailySalesJPanel.getFstField().getText();
		String snd = dailySalesJPanel.getSndField().getText();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		Date fstDate = null;
		Date sndDate = null;
		
		if(fst.equals("")) {
			JOptionPane.showMessageDialog(dailySalesJPanel, "날짜를 선택해주세요");
		} else {
		
			try {
				fstDate = fm.parse(fst);
				sndDate = fm.parse(snd);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			// 날짜 차이 계산 
			long calDate = fstDate.getTime() - sndDate.getTime();
			long calDateDays = calDate/ (24*60*60*1000);
			calDateDays = Math.abs(calDateDays);
			
			if(fstDate.after(sndDate)) {
				dailySalesJPanel.add(new DailyResultPanel(sndDate,calDateDays));
			} else if (fstDate.before(sndDate)) {
				dailySalesJPanel.add(new DailyResultPanel(fstDate,calDateDays));
			} else {
				dailySalesJPanel.add(new DailyResultPanel(fstDate,calDateDays));
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
