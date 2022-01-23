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
import javax.swing.JFrame;

import oneteampos.main.MainFrame;
import oneteampos.sales.containers.RoundedButton;
import oneteampos.staff.actions.AddStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class AddStaffBtn extends RoundedButton{
	
	StaffInfoJPanel staffInfoPanel;
	
	public AddStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 등록");
		this.staffInfoPanel = staffInfoPanel;

		setBounds(280, 450, 200, 40);
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		
		addMouseListener(new AddStaffBtnListener(staffInfoPanel));

		staffInfoPanel.add(this);
	}
	
	
}
