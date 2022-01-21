package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import oneteampos.sales.containers.CalendarJFrame;
import oneteampos.sales.containers.CalendarJFrame2;
import oneteampos.sales.containers.DailySalesJPanel;
import oneteampos.sales.containers.MonthlySalesJPanel;
import oneteampos.sales.containers.SalesPanel;

public class SecondJTextFieldListener implements MouseListener {
	
	SalesPanel salesPanel;
	DailySalesJPanel dailySalesJPanel;
	MonthlySalesJPanel monthlySalesJPanel;
	
	public SecondJTextFieldListener(SalesPanel salesPanel, DailySalesJPanel dailySalesJPanel) {
		this.salesPanel = salesPanel;
		this.dailySalesJPanel = dailySalesJPanel;
	}

	public SecondJTextFieldListener(SalesPanel salesPanel, MonthlySalesJPanel monthlySalesJPanel) {
		this.salesPanel = salesPanel;
		this.dailySalesJPanel = dailySalesJPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(dailySalesJPanel != null) {
			JTextField sndfield = (JTextField) e.getSource();
			new CalendarJFrame(dailySalesJPanel, sndfield , 2);
		} else {
			JTextField sndfield = (JTextField) e.getSource();
			new CalendarJFrame2(monthlySalesJPanel, sndfield, 2);
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
