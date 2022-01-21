package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import oneteampos.sales.containers.CalendarJFrame;

public class dayLableListener implements MouseListener {
	
	int y ;
	int m ;
	CalendarJFrame calendarJFrame ;
	public dayLableListener(int y, int m, CalendarJFrame calendarJFrame) {
		this.y = y;
		this.m = m ;
		this.calendarJFrame = calendarJFrame;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel mouseClick = (JLabel) e.getSource();
		String str = y + "";
		if(m < 10) {
			str += "-0"+m;
		} else {
			str += "-" + m;
		}
		if(mouseClick.getText().length() < 2) {
			str += "-0" +  mouseClick.getText() ;
		} else {
			str += "-" +  mouseClick.getText() ;
		}
		calendarJFrame.getSelectField().setText(str);
		calendarJFrame.dispose();
		
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
