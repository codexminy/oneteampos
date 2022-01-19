package oneteampos.receipt.components;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

import oneteampos.receipt.actions.ReturnBtnListener;
import oneteampos.receipt.container.ReceiptJPanel;
import oneteampos.receipt.container.ReturnJPanel;

public class ReturnBtn extends JButton{

	public ReturnBtn(ReceiptJPanel receiptJPanel, boolean func) {
		super("반품");
		setBounds(100, 430, 100, 50);
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setLayout(null);
	
		addMouseListener(new ReturnBtnListener(receiptJPanel, func));
	}

	public ReturnBtn(ReturnJPanel returnPanel, boolean func) {
		super("반품");
		setBounds(100, 500, 100, 50);
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setLayout(null);
		addMouseListener(new ReturnBtnListener(returnPanel, func));
	}
	
	

}
