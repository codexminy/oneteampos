package oneteampos.menu.component;

import javax.swing.table.TableModel;

public class Member_Table extends All_Table {

	public Member_Table(TableModel dm) {
		setModel(dm);
		tableAlign();
		setHeader();
	}
	
}
