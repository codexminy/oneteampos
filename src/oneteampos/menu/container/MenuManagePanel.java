package oneteampos.menu.container;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuCheckBoxAction;
import oneteampos.menu.action.MenuDeleteAction;
import oneteampos.menu.action.MenuInsertAction;
import oneteampos.menu.action.MenuWindowAction;
import oneteampos.menu.allLayouts.Gbl;

public class MenuManagePanel {
	
	private JDialog menuManageDialog;
	private DefaultTableModel model;
	private TreeSet<Integer> list;
	private Vector<Object> col;
	private Vector<Vector<Object>> row;
	private Object[] col2;
	private JCheckBox chkBox;
	private JButton deleteBtn;
	private JTable menu;
	private JTable rsMenu;
	private JButton registrationBtn;

	public MenuManagePanel(MainFrame mainFrame, MenuLeftPanel menuLeftPanel) {
		this.menuManageDialog = new JDialog(mainFrame, "메뉴 관리");
		this.col = new Vector<>();
		this.row = new Vector<>();
		this.model = inputMenuData();
		this.list = new TreeSet<>();
		this.chkBox = new JCheckBox();
		this.menu = new JTable(model);
		this.rsMenu = new JTable(new Object[][] {{"","","",""}}, col2);
		this.registrationBtn = new JButton("메뉴 등록");
		this.deleteBtn = new JButton("메뉴 삭제");
		
		JScrollPane menuSc = new JScrollPane(menu);
		JScrollPane reMenuSc = new JScrollPane(rsMenu);
		
		JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel menuManagePanel = new JPanel(new GridBagLayout());
		JPanel menuListPanel = new JPanel(new GridLayout());
		JPanel registerMenuPanel = new JPanel(new GridLayout());
		
		JLabel menuListLabel = new JLabel("전체 메뉴", JLabel.CENTER);
		JLabel registerMenuLabel = new JLabel("메뉴 등록", JLabel.CENTER);
		
		GridBagConstraints gbc = Gbl.getGbc();
		
		menuManageDialog.addWindowListener(new MenuWindowAction(mainFrame));
		
		menuListPanel.add(menuListLabel);
		registerMenuPanel.add(registerMenuLabel);
		
		btns.add(registrationBtn);
		btns.add(deleteBtn);
		
		menuManagePanel.add(menuListPanel, Gbl.getSetting(gbc, 0.1, 0.03, 0, 0));
		menuManagePanel.add(menuSc, Gbl.getSetting(gbc, 0.1, 0.8, 0, 1));
		menuManagePanel.add(registerMenuPanel, Gbl.getSetting(gbc, 0.1, 0.03, 0, 2));
		menuManagePanel.add(reMenuSc, Gbl.getSetting(gbc, 0.1, 0.1, 0, 3));
		menuManagePanel.add(btns, Gbl.getSetting(gbc, 0.1, 0.001, 0, 4));
		
		menu.getColumn("선택").setCellRenderer(dcr);
		menu.getColumn("선택").setCellEditor(new DefaultCellEditor(chkBox));
		chkBox.setHorizontalAlignment(JLabel.CENTER);
		chkBox.addActionListener(new MenuCheckBoxAction(this));
		deleteBtn.addActionListener(new MenuDeleteAction(menuLeftPanel));
		registrationBtn.addActionListener(new MenuInsertAction(menuLeftPanel));
		menuManageDialog.add(menuManagePanel);
		menuManageDialog.setSize(MainFrame.FRAME_WIDTH/2, (int)(MainFrame.FRAME_HEIGHT*0.8));
		menuManageDialog.setResizable(false);
		menuManageDialog.setLocationRelativeTo(null);
	}

	public DefaultTableModel inputMenuData() {
		model = new DefaultTableModel();
		String sql = "SELECT menu_id AS \"메뉴ID\", menu_name AS \"메뉴이름\", price AS \"가격\", menu_type AS \"종류\" FROM tempmenu ORDER BY menu_id";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			ResultSetMetaData meta = rs.getMetaData();
			
			col.add("선택");
			col2 = new Object[meta.getColumnCount()];
			
			for(int i=1; i<=meta.getColumnCount(); ++i) {
				col.add(meta.getColumnName(i));
				col2[i-1] = meta.getColumnName(i);
			}
			
			while(rs.next()) {
				Vector<Object> innerVt = new Vector<>();
				innerVt.add(false);
				innerVt.add(rs.getInt("메뉴ID"));
				innerVt.add(rs.getString("메뉴이름"));
				innerVt.add(rs.getInt("가격"));
				innerVt.add(rs.getString("종류"));
				row.add(innerVt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.setDataVector(row, col);
		return model;
	}
	
	private DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JCheckBox chkBox = new JCheckBox();
			chkBox.setSelected(((Boolean)value).booleanValue());  
			chkBox.setHorizontalAlignment(JLabel.CENTER);
			return chkBox;
		}
	};
	
	public JTable getMenuTable() {
		return this.menu;
	}
	
	public TreeSet<Integer> getList() {
		return this.list;
	}
	
	public JCheckBox getChkBox() {
		return this.chkBox;
	}
	
	public Vector<Vector<Object>> getRow() {
		return this.row;
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}
	
	public JDialog getmenuManageDialog() {
		return this.menuManageDialog;
	}
	
	public JTable getRsMenu() {
		return this.rsMenu;
	}
}
