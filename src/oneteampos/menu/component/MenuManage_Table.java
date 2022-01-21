package oneteampos.menu.component;

import java.awt.Font;

import javax.swing.table.TableModel;

public class MenuManage_Table extends All_Table {

	public MenuManage_Table(TableModel dm) {
		setModel(dm);
		tableAlign();
		setHeader();
	}
	
	public MenuManage_Table(Object[][] row, Object[] col) {
		super(row, col);
		tableAlign();
		setHeader();
	}
	
}
