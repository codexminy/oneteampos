package oneteampos.receipt.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.receipt.actions.BackToReceiptBtnListener;
import oneteampos.receipt.container.ReceiptJPanel;
import oneteampos.receipt.container.ReturnJPanel;
import oneteampos.sales.containers.RoundedButton;

public class BackToReceiptBtn extends RoundedButton{
	
	ReceiptJPanel receiptJPanel;
	
	public BackToReceiptBtn(ReceiptJPanel receiptJPanel, ReturnJPanel returnJPanel) {
		super("X");
		setFont(new Font("고딕", Font.BOLD, 20));
		setBounds(1180, 10, 48, 40);
		setBackground(Color.WHITE);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLayout(null);
		
		this.receiptJPanel = receiptJPanel;
		
		addMouseListener(new BackToReceiptBtnListener(receiptJPanel , returnJPanel));
	}
}
