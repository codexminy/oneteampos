package oneteampos.receipt.components;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

import oneteampos.receipt.actions.PrintReceiptListener;
import oneteampos.receipt.actions.ReturnBtnListener;
import oneteampos.receipt.container.ReceiptJPanel;
import oneteampos.receipt.container.ReturnJPanel;
import oneteampos.sales.containers.RoundedButton;

public class PrintBtn extends RoundedButton{

	public PrintBtn(ReceiptJPanel receiptJPanel, boolean b) {
		super("출력");
		setBounds(230, 430, 100, 50);
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setLayout(null);
		
		addMouseListener(new PrintReceiptListener(receiptJPanel, false));
	}

	public PrintBtn(ReturnJPanel returnPanel, boolean func) {
		super("출력");
		setBounds(100, 500, 100, 50);
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setLayout(null);
		addMouseListener(new PrintReceiptListener(returnPanel, func));
	}
	
}
