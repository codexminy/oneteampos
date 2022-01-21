package oneteampos.menu.component;

import javax.swing.JButton;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.Cart_deleteAction;

public class Cart_table extends All_Table {

	public Cart_table(MainFrame mainFrame, TableModel dm) {
		JButton btn = new Cart_tableBtn();
		setModel(dm);
		
		getColumn("메뉴이름").setPreferredWidth(130);
		getColumn("사이즈").setPreferredWidth(60);
		getColumn("금액").setPreferredWidth(80);
		getColumn("수량").setPreferredWidth(40);
		getColumn("선택").setPreferredWidth(50);
		
		tableAlign();
		setHeader();
		
		getColumnModel().getColumn(4).setCellRenderer((TableCellRenderer) btn);
		getColumnModel().getColumn(4).setCellEditor((TableCellEditor) btn);

		addMouseListener(new Cart_deleteAction(mainFrame));
//		setOpaque(false);
//		((DefaultTableCellRenderer)getDefaultRenderer(Object.class)).setOpaque(false);
//		setShowGrid(false);
//		setFocusable(false);
	}
	
}
