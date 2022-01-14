package oneteampos.order.compnents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.login.actions.LoginNumberkeyAction;
import oneteampos.main.MainFrame;
import oneteampos.order.actions.BackBtnListener;
import oneteampos.order.cotainer.OrderJPanel;

public class BackBtn extends JButton{
	
	MainFrame mainframe;
	OrderJPanel orderPanel;
	
	public BackBtn(MainFrame mainframe , OrderJPanel orderPanel ) {
		super("<");
		setFont(new Font("고딕", Font.BOLD, 20));

		setBorderPainted(true);
		setBounds(1180, 10, 48, 40);
		setBackground(Color.WHITE);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// 뒤로 가기 액션 (MainPanel로 돌아감)
		addMouseListener(new BackBtnListener(mainframe , orderPanel));
	
	}
}
