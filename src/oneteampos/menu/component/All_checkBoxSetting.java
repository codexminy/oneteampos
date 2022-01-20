package oneteampos.menu.component;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class All_checkBoxSetting extends DefaultTableCellRenderer {
	
	private JCheckBox box;
	
	public All_checkBoxSetting(JCheckBox box) {
		this.box = box;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		box = new JCheckBox();
		box.setSelected(((Boolean)value).booleanValue());  
		box.setHorizontalAlignment(JLabel.CENTER);
		box.setOpaque(false);
		return box;
	}
}
