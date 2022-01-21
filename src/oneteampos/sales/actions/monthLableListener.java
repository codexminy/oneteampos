package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import oneteampos.sales.containers.CalendarJFrame2;

public class monthLableListener implements MouseListener {
	
	int y;
	CalendarJFrame2 calendarJFrame2;
	public monthLableListener(int y, CalendarJFrame2 calendarJFrame2) {
		this.y = y;
		this.calendarJFrame2 = calendarJFrame2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel mouseClick = (JLabel) e.getSource();
		int num = Integer.parseInt(mouseClick.getText().replace("월", ""));
		String str = "";
		if(num < 10) {
			
			str = y + "-0"+mouseClick.getText().replace("월", "") ;
		} else {
			str = y + "-"+mouseClick.getText().replace("월", "") ;
		}
		
		calendarJFrame2.getSelectField().setText(str);
		calendarJFrame2.dispose();
				
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
