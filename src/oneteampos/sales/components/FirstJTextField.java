package oneteampos.sales.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JTextField;

import oneteampos.sales.actions.FirstJTextFieldListener;
import oneteampos.sales.containers.DailySalesJPanel;
import oneteampos.sales.containers.MonthlySalesJPanel;
import oneteampos.sales.containers.SalesPanel;

public class FirstJTextField extends JTextField{
	
	SalesPanel salesPanel;

	public FirstJTextField(SalesPanel salesPanel, DailySalesJPanel dailySalesJPanel) {
		this.salesPanel = salesPanel;
		setBounds(260, 28, 130, 30);
		setBackground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setEditable(false);
		setFont(new Font("돋움", Font.BOLD, 18));
		
		addMouseListener(new FirstJTextFieldListener(salesPanel , dailySalesJPanel));
	}

	public FirstJTextField(SalesPanel salesPanel2, MonthlySalesJPanel monthlySalesJPanel) {
		this.salesPanel = salesPanel;
		setBounds(260, 28, 130, 30);
		setBackground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setEditable(false);
		setFont(new Font("돋움", Font.BOLD, 18));
		
		addMouseListener(new FirstJTextFieldListener(salesPanel , monthlySalesJPanel));
	}
}
