package oneteampos.sales.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.order.actions.StockBtnListener;
import oneteampos.sales.actions.DailyBtnListener;
import oneteampos.sales.containers.SalesPanel;

public class DailyBtn extends JButton{

	SalesPanel salesPanel;
	
	public DailyBtn(MainFrame mainframe, SalesPanel salesPanel) {
		super("일매출");
		this.salesPanel = salesPanel;
		setBounds(30, 65, 300, 30);
		setClearFocus();
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		addMouseListener(new DailyBtnListener(mainframe, salesPanel));
	}
	
	public void setFocus() {
		setFont(new Font("고딕", Font.BOLD, 17));
		setBackground(Color.GRAY);
		setForeground(Color.WHITE);
	}
	
	public void setClearFocus() {
		setFont(new Font("고딕", Font.BOLD, 15));
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLACK);
	}
	
}
