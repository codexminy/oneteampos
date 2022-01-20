package oneteampos.sales.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.main.MainFrame;
import oneteampos.sales.components.MonthlyBtn;
import oneteampos.sales.containers.SalesPanel;

public class MontylyBtnListener implements MouseListener {
	
	MainFrame mainFrame;
	SalesPanel salesPanel;

	public MontylyBtnListener(MainFrame mainFrame,SalesPanel salesPanel) {
		this.mainFrame = mainFrame;
		this.salesPanel = salesPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MonthlyBtn monthlyBtn = (MonthlyBtn)e.getSource();
		
		// 버튼 강조
		monthlyBtn.setFocus();
		salesPanel.getDailyBtn().setClearFocus();
		// 패널 바꾸기
		if(!salesPanel.getMonthlySalesPanel().getFstField().getText().equals("")) {
			salesPanel.getMonthlySalesPanel().setVisibleFalse();
			salesPanel.getDailySalesPanel().setVisibleFalse();
			salesPanel.setVisible(false);
			SalesPanel newSalesPanel = new SalesPanel(mainFrame);
			mainFrame.add(newSalesPanel);
			newSalesPanel.setVisible(true);
		} else {
			salesPanel.getDailySalesPanel().setVisibleFalse();
			salesPanel.getMonthlySalesPanel().setVisibleTrue();
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
