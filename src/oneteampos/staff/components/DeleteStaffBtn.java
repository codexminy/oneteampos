package oneteampos.staff.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

import oneteampos.staff.actions.DeleteStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class DeleteStaffBtn extends JButton{
	

	StaffInfoJPanel staffInfoPanel;
	
	public DeleteStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 삭제");
		
		
		setBounds(780, 450, 200, 40);
		setFont(new Font("고딕", Font.PLAIN, 18));
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		
		addMouseListener(new DeleteStaffBtnListener(staffInfoPanel));
		
		staffInfoPanel.add(this);
		
		
	}
}
