package oneteampos.menu.container;

import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.component.All_ScrollPane;
import oneteampos.menu.component.All_Table;

public class Cart_holdDialog extends JDialog {

	private final static int D_WIDTH = 400;
	private final static int D_HEIGHT = 600;
	private static String[] cols = new String[] {"선택", "결제금액", "할인금액", "상태"};
	private Vector<Object> col;
	private Vector<Vector<Object>> row;
	private DefaultTableModel model;
	private JTable cart;
	private JScrollPane sc = new All_ScrollPane(cart);
	
	public Cart_holdDialog(MainFrame mainFrame) {
		super(mainFrame, "카트 보류 목록", true);
		this.model = new DefaultTableModel() {@Override public boolean isCellEditable(int row, int column) {return false;}};
		this.cart = new All_Table();
		this.col = new Vector<>();
		
		inputColumn();
		
		setSize(D_WIDTH, D_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void inputColumn() {
		for(int i=0; i<cols.length; ++i) {
			col.add(cols[i]);
		}
	}
	
}
