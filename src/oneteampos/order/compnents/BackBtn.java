package oneteampos.order.compnents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.order.actions.BackBtnListener;
import oneteampos.sales.containers.RoundedButton;

public class BackBtn extends RoundedButton{
	
	MainFrame mainframe;
	JPanel orderPanel;
	
	public BackBtn(MainFrame mainframe , JPanel jpanel, String panelName ) {
		super("<");

		setBounds(1180, 10, 48, 40);
		setFocusable(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorderPainted(false);
		addMouseListener(new BackBtnListener(mainframe , jpanel, panelName));
	}

}
