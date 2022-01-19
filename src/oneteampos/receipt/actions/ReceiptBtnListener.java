package oneteampos.receipt.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.receipt.container.ReceiptJPanel;

public class ReceiptBtnListener implements MouseListener {
	
	MainFrame mainFrame;

	public ReceiptBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		mainFrame.getMainPanel().getMenuPanel().setVisible(false);
		mainFrame.getMainPanel().setVisibleFalse();
		mainFrame.add(new ReceiptJPanel(mainFrame));

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
