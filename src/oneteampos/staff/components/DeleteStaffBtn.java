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
import oneteampos.staff.actions.DeleteStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class DeleteStaffBtn extends RoundedButton{
	

	StaffInfoJPanel staffInfoPanel;
	
	public DeleteStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 삭제");
		
		setBounds(780, 450, 200, 40);
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		
		addMouseListener(new DeleteStaffBtnListener(staffInfoPanel));
		
		staffInfoPanel.add(this);

	}

}
