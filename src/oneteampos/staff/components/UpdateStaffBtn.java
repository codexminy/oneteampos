package oneteampos.staff.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

import oneteampos.sales.containers.RoundedButton;
import oneteampos.staff.actions.UpdateStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class UpdateStaffBtn extends RoundedButton{
	
	StaffInfoJPanel staffInfoPanel;
	
	public UpdateStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 수정");
		setBounds(530, 450, 200, 40);
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		staffInfoPanel.add(this);
		
		
		addMouseListener(new UpdateStaffBtnListener(staffInfoPanel));
	}
	
	
}
