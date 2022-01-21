package oneteampos.menu.component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class All_checkBox extends JCheckBox {

	public All_checkBox() {
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	public All_checkBox(String text) {
		setText(text);
		setOpaque(false);
	}
	
	public void setting(JTable table, String column, JCheckBox box, DefaultTableCellRenderer dtcr) {
		table.getColumn(column).setCellRenderer(dtcr);
		table.getColumn(column).setCellEditor(new DefaultCellEditor(box));
		box.setHorizontalAlignment(JLabel.CENTER);
	}
	
}
