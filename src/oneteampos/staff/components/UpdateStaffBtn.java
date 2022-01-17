package oneteampos.staff.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import oneteampos.staff.actions.AddStaffBtnListener;
import oneteampos.staff.actions.UpdateStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class UpdateStaffBtn extends JButton {
	
	StaffInfoJPanel staffInfoPanel;
	
	public UpdateStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 수정");
		setBounds(530, 450, 200, 40);
		setFont(new Font("고딕", Font.PLAIN, 18));
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		staffInfoPanel.add(this);
		
		
		addMouseListener(new UpdateStaffBtnListener(staffInfoPanel));
		
	
		
		
	}
}
