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
import oneteampos.staff.actions.AddStaffBtnListener;
import oneteampos.staff.containers.StaffInfoJPanel;

public class AddStaffBtn extends JButton{
	
	StaffInfoJPanel staffInfoPanel;
	
	public AddStaffBtn(StaffInfoJPanel staffInfoPanel) {
		super("사원 등록");
		this.staffInfoPanel = staffInfoPanel;

		setBounds(280, 450, 200, 40);
//		setFont(new Font("고딕", Font.PLAIN, 18));
		setBackground(new Color(247, 245, 247));
		setRequestFocusEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		
		addMouseListener(new AddStaffBtnListener(staffInfoPanel));

		staffInfoPanel.add(this);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
		
		if(getModel().isSelected()) {
			g2d.setColor(new Color(44, 108, 212));
		} else if(getModel().isRollover()) {
			g2d.setColor(new Color(44, 108, 212));
		}
		
		FontMetrics fm = g2d.getFontMetrics();
		Rectangle sb = fm.getStringBounds(getText(), g2d).getBounds();
		
		int tx = (getWidth() - sb.width) / 2;
		int ty = (getHeight() - sb.height) / 2 + fm.getAscent();

		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
		g2d.setColor(Color.WHITE);
		g2d.drawString(getText(), tx, ty);
	}
}
