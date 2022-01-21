package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.sales.components.DailyBtn;
import oneteampos.sales.containers.DailySalesJPanel;
import oneteampos.sales.containers.MonthlySalesJPanel;
import oneteampos.sales.containers.SalesPanel;

public class DailyBtnListener implements MouseListener {
	
	MainFrame mainFrame;
	SalesPanel salesPanel;
	
	

	public DailyBtnListener(MainFrame mainframe, SalesPanel salesPanel) {
		this.mainFrame = mainframe;
		this.salesPanel = salesPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DailyBtn dailyBtn = (DailyBtn)e.getSource();
	
		salesPanel.getMonthlyBtn().setSelected(false);
	
		// 프레임 바꾸기
		if(!salesPanel.getDailySalesPanel().getFstField().getText().equals("")) {
			salesPanel.getDailySalesPanel().setVisibleFalse();
			salesPanel.getMonthlySalesPanel().setVisibleFalse();
			salesPanel.setVisible(false);
			SalesPanel newSalesPanel = new SalesPanel(mainFrame);
			mainFrame.add(newSalesPanel);
			newSalesPanel.setVisible(true);
		} else {
			salesPanel.getDailySalesPanel().setVisibleTrue();
			salesPanel.getMonthlySalesPanel().setVisibleFalse();
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
