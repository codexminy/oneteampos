package oneteampos.staff.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;

import oneteampos.main.MainFrame;
import oneteampos.staff.actions.AddStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class AddStaffBtn extends JButton{
	
	StaffInfoJPanel staffInfoPanel;
	
	public AddStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 등록");
		this.staffInfoPanel = staffInfoPanel;

		setBounds(280, 450, 200, 40);
		setFont(new Font("고딕", Font.PLAIN, 18));
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		
		addMouseListener(new AddStaffBtnListener(staffInfoPanel));

		staffInfoPanel.add(this);
	}
}
