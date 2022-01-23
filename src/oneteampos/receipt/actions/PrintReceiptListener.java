package oneteampos.receipt.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import oneteampos.receipt.components.PrintBtn;
import oneteampos.receipt.components.ReturnBtn;
import oneteampos.receipt.container.ReceiptJPanel;
import oneteampos.receipt.container.ReceiptTableJPanel;
import oneteampos.receipt.container.ReturnJPanel;
import oneteampos.sales.containers.RoundedButton;

public class PrintReceiptListener implements MouseListener {
	
	ReceiptJPanel receiptJPanel;
	boolean func;
	ReturnJPanel returnJPanel;
	
	public PrintReceiptListener(ReceiptJPanel receiptJPanel, boolean func) {
		this.receiptJPanel = receiptJPanel;
		this.func = func;
	}

	public PrintReceiptListener(ReturnJPanel returnJPanel, boolean func) {
		this.returnJPanel = returnJPanel;
		this.func = func;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		if(func) {
			RoundedButton btn = (RoundedButton)e.getSource();
			btn.setVisible(false);
			this.receiptJPanel = returnJPanel.getReceiptJPanel();
			returnJPanel.setVisible(false);
			receiptJPanel.getReceiptLabel().setText("영수증");
			receiptJPanel.getReceiptTablePanel().setVisible(true);
			JOptionPane.showMessageDialog(receiptJPanel, "출력 완료");
		}else {
			if(receiptJPanel.getReceiptTablePanel().getReceiptInfoTable().getSelectedRow() >= 0) {
				receiptJPanel.getReceiptTablePanel().setVisible(false);
				// 라벨 이름 바꿔주기
				receiptJPanel.getReceiptLabel().setText("영수증 (결제)");
				ReturnJPanel returnPanel = new ReturnJPanel(receiptJPanel);
				returnPanel.add(new PrintBtn(returnPanel, true));
				receiptJPanel.add(returnPanel);
			} else {
				JOptionPane.showMessageDialog(receiptJPanel, "출력물을 선택해주세요.");
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
