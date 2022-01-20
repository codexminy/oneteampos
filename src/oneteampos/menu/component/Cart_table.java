package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.Cart_deleteAction;

public class Cart_table extends JTable {

	public Cart_table(MainFrame mainFrame, TableModel dm) {
		super(dm);
		JButton btn = new JButton("X");
		btn.setForeground(Color.WHITE);
		
		setForeground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		getColumnModel().getColumn(4).setCellRenderer(new Cart_tableBtn(btn));
		getColumnModel().getColumn(4).setCellEditor(new Cart_tableBtn(btn));
		
		getColumn("메뉴이름").setPreferredWidth(130);
		getColumn("사이즈").setPreferredWidth(60);
		getColumn("금액").setPreferredWidth(90);
		getColumn("수량").setPreferredWidth(30);
		getColumn("선택").setPreferredWidth(50);
		
		setOpaque(false);
		((DefaultTableCellRenderer)getDefaultRenderer(Object.class)).setOpaque(false);
		setShowGrid(false);
		setFocusable(false);
		addMouseListener(new Cart_deleteAction(mainFrame));
	}
	
}
