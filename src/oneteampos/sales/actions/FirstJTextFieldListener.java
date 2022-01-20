package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import oneteampos.sales.containers.CalendarJFrame;
import oneteampos.sales.containers.CalendarJFrame2;
import oneteampos.sales.containers.DailySalesJPanel;
import oneteampos.sales.containers.MonthlySalesJPanel;
import oneteampos.sales.containers.SalesPanel;

public class FirstJTextFieldListener implements MouseListener {
	
	SalesPanel salesPanel;
	DailySalesJPanel dailySalesJPanel;
	MonthlySalesJPanel monthlySalesJPanel;

	public FirstJTextFieldListener(SalesPanel salesPanel, DailySalesJPanel dailySalesJPanel) {
		this.salesPanel = salesPanel;
		this.dailySalesJPanel = dailySalesJPanel;
		
	}

	public FirstJTextFieldListener(SalesPanel salesPanel, MonthlySalesJPanel monthlySalesJPanel) {
		this.salesPanel = salesPanel;
		this.monthlySalesJPanel = monthlySalesJPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!dailySalesJPanel.getSndField().getText().equals("")) {
			dailySalesJPanel.getSndField().setText("");
		}
		if(dailySalesJPanel != null) {
			JTextField fstfeild = (JTextField) e.getSource();
			new CalendarJFrame(dailySalesJPanel, fstfeild , 1);
		} else {
			JTextField fstfeild = (JTextField) e.getSource();
			new CalendarJFrame2(monthlySalesJPanel, fstfeild, 1);
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
