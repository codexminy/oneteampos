package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class All_Table extends JTable {

	public All_Table() {
		settings();
		tableAlign();
		setHeader();
	}
	
	public All_Table(Object[][] row, Object[] col) {
		super(row, col);
		settings();
		tableAlign();
		setHeader();
	}
	
	public All_Table(TableModel dm) {
		setModel(dm);
		settings();
		tableAlign();
		setHeader();
	}
	
	public void tableAlign() {
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();		
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcmSchedule = this.getColumnModel();

		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
	}
	
	protected void setHeader() {
		getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(135, 136, 138)));
		getTableHeader().setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 14));
		getTableHeader().setForeground(Color.WHITE);
		getTableHeader().setBackground(new Color(135, 136, 138));
		getTableHeader().setPreferredSize(new Dimension(0, 30));
	}
	
	protected void settings() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		setRowHeight(25);
		setGridColor(new Color(135, 136, 138));
		getTableHeader().setReorderingAllowed(false);
		getTableHeader().setResizingAllowed(false);
	}
}
