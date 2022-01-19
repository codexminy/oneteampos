package oneteampos.receipt.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.receipt.container.ReceiptJPanel;
import oneteampos.receipt.container.ReturnJPanel;

public class BackToReceiptBtnListener implements MouseListener {
	
	ReceiptJPanel receiptJPanel;
	ReturnJPanel returnJPanel;

	public BackToReceiptBtnListener(ReceiptJPanel receiptJPanel, ReturnJPanel returnJPanel) {
		this.receiptJPanel = receiptJPanel;
		this.returnJPanel = returnJPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		receiptJPanel.getReceiptTablePanel().setVisible(true);
		returnJPanel.setVisible(false);
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
