package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableButton extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    JButton btn;
    
    public TableButton(JButton btn) {
    	this.btn = btn;
    	btn.setText("X");
    	btn.setBorderPainted(false);
    	btn.setContentAreaFilled(false);
    }
 
    @Override
    public Object getCellEditorValue() {
        return null;
    }
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return btn;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return btn;
    }
}
