package oneteampos.sales.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.sales.actions.MontylyBtnListener;
import oneteampos.sales.containers.SalesPanel;

public class MonthlyBtn extends JButton{
	
	SalesPanel salesPanel;

	public MonthlyBtn(MainFrame mainFrame, SalesPanel salesPanel) {
		super("월매출");
		this.salesPanel = salesPanel;
		setFocusable(false);
		setClearFocus();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new MontylyBtnListener(mainFrame,salesPanel));
	}
	
	public void setFocus() {
		setFont(new Font("고딕", Font.BOLD, 17));
		setBounds(340, 65, 300, 30);
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
	}
	
	public void setClearFocus() {
		setFont(new Font("고딕", Font.BOLD, 15));
		setBounds(340, 65, 300, 30);
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLACK);
	}
	
}
