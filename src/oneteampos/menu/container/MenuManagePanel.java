package oneteampos.menu.container;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.action.JDialogWindowAction;
import oneteampos.menu.action.MenuCheckBoxAction;
import oneteampos.menu.action.MenuDeleteAction;
import oneteampos.menu.action.MenuInsertAction;
import oneteampos.menu.action.MenuUpdateAction;
import oneteampos.menu.allLayouts.Gbl;
import oneteampos.menu.component.ChkBox;
import oneteampos.menu.component.ChkBoxSetting;

public class MenuManagePanel extends JDialog {
	
	public DefaultTableModel model;
//	private TreeSet<Integer> list;
	private Vector<Object> col;
	private Vector<Vector<Object>> row;
	private Object[] col2;
	public ChkBox box;
	public JCheckBox chkBox;
	private JButton deleteBtn;
	public JTable menu;
	private JTable rsMenu;
	private JButton registrationBtn;
	public DefaultTableCellRenderer dtcr;
	private JScrollPane menuSc;
	
	public MenuManagePanel(MainFrame mainFrame, MenuLeftPanel menuLeftPanel) {
		super(mainFrame, "메뉴 관리", true);
		this.col = new Vector<>();
		this.row = new Vector<>();
		this.model = inputMenuData();
//		this.list = new TreeSet<>();
//		this.chkBox = new JCheckBox();
		this.box = new ChkBox();
		this.chkBox = box;
		this.menu = new JTable(model);
		this.rsMenu = new JTable(new Object[][] {{"","",""}}, col2);
		this.registrationBtn = new JButton("메뉴 등록");
		this.deleteBtn = new JButton("메뉴 삭제");
		this.dtcr = new ChkBoxSetting(chkBox);
		
		menuSc = new JScrollPane(menu);
		JScrollPane reMenuSc = new JScrollPane(rsMenu);
		
		JPanel searchPanel = new JPanel();
		JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel menuManagePanel = new JPanel(new GridBagLayout());
		JPanel menuListPanel = new JPanel(new GridLayout());
		JPanel registerMenuPanel = new JPanel(new GridLayout());
		
		JLabel menuListLabel = new JLabel("전체 메뉴", JLabel.CENTER);
		JLabel registerMenuLabel = new JLabel("메뉴 등록", JLabel.CENTER);
		
		JComboBox<Object> combo = new JComboBox<>(col2);
		
		JTextField searchField = new JTextField(40);
		JButton searchBtn = new JButton("검색");
		
		searchPanel.add(combo);
		searchPanel.add(searchField);
		searchPanel.add(searchBtn);
		
		searchBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				searchData(combo.getSelectedItem(), searchField.getText());
				box = new ChkBox();
				chkBox = box;
				dtcr = new ChkBoxSetting(chkBox);
				box.setting(menu, "선택", chkBox, dtcr);
			}
			
		});
		
		
		GridBagConstraints gbc = Gbl.getGbc();
		
		tableSetting(menu, false, false);
		tableSetting(rsMenu, false, false);
		
		menuListPanel.add(menuListLabel);
		registerMenuPanel.add(registerMenuLabel);
		
		btns.add(registrationBtn);
		btns.add(deleteBtn);
		
		menuManagePanel.add(menuListPanel, Gbl.setting(gbc, 0.1, 0.03, 0, 0));
		menuManagePanel.add(menuSc, Gbl.setting(gbc, 0.1, 0.8, 0, 1));
		menuManagePanel.add(registerMenuPanel, Gbl.setting(gbc, 0.1, 0.03, 0, 2));
		menuManagePanel.add(reMenuSc, Gbl.setting(gbc, 0.1, 0.1, 0, 3));
		menuManagePanel.add(btns, Gbl.setting(gbc, 0.1, 0.001, 0, 4));
		menuManagePanel.add(searchPanel, Gbl.setting(gbc, 0.1, 0.01, 0, 5));

		box.setting(menu, "선택", chkBox, dtcr);
		
		
//		menu.getColumn("선택").setCellRenderer(dtcr);
//		menu.getColumn("선택").setCellEditor(new DefaultCellEditor(chkBox));
//		chkBox.setHorizontalAlignment(JLabel.CENTER);
		chkBox.addActionListener(new MenuCheckBoxAction(mainFrame, menu, chkBox));
		deleteBtn.addActionListener(new MenuDeleteAction(mainFrame));
		registrationBtn.addActionListener(new MenuInsertAction(mainFrame));
		
		addWindowListener(new JDialogWindowAction(mainFrame));
		
		add(menuManagePanel);
		setSize(MainFrame.FRAME_WIDTH/2, (int)(MainFrame.FRAME_HEIGHT*0.8));
		setResizable(false);
		setLocationRelativeTo(null);
	}
	
	public void searchData(Object columnName, String condition) {
		String sql = String.format("SELECT * FROM menu WHERE %s LIKE '%%%s%%'", columnName, condition);
		Vector<Vector<Object>> row = new Vector<>();
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				Vector<Object> innerRow = new Vector<>();
				innerRow.add(false);
				innerRow.add(rs.getString(1));
				innerRow.add(rs.getString(2));
				innerRow.add(rs.getString(3));
				innerRow.add(rs.getString(4));
				row.add(innerRow);
			}
			model.setDataVector(row, col);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public DefaultTableModel inputMenuData() {
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 0;
			}
		};
		
		String sql = "SELECT menu_id, menu_name, price, menu_type FROM menu ORDER BY menu_id";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			ResultSetMetaData meta = rs.getMetaData();
			
			col.add("선택");
			col2 = new Object[meta.getColumnCount()-1];
			
			for(int i=1; i<=meta.getColumnCount(); ++i) {
				col.add(meta.getColumnName(i));
				if(i > 1) {
					col2[i-2] = meta.getColumnName(i);
				}
			}
			
			while(rs.next()) {
				Vector<Object> innerVt = new Vector<>();
				innerVt.add(false);
				innerVt.add(rs.getInt("menu_id"));
				innerVt.add(rs.getString("menu_name"));
				innerVt.add(rs.getInt("price"));
				innerVt.add(rs.getString("menu_type"));
				row.add(innerVt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.setDataVector(row, col);
		return model;
	}
	
	private void tableSetting(JTable table, boolean reordering, boolean resizing) {
		table.getTableHeader().setReorderingAllowed(reordering);
		table.getTableHeader().setResizingAllowed(resizing);
	}
	
//	private DefaultTableCellRenderer dcr = new DefaultTableCellRenderer() {
//		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//			JCheckBox chkBox = new JCheckBox();
//			chkBox.setSelected(((Boolean)value).booleanValue());  
//			chkBox.setHorizontalAlignment(JLabel.CENTER);
//			return chkBox;
//		}
//	};
	
	public JTable getMenuTable() {
		return this.menu;
	}
	
//	public TreeSet<Integer> getList() {
//		return this.list;
//	}
	
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
		return this;
	}
	
	public JTable getRsMenu() {
		return this.rsMenu;
	}
}
