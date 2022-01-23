package oneteampos.menu.container;

import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import oneteampos.menu.action.Dialog_windowAction;
import oneteampos.menu.action.MenuManage_checkBoxAction;
import oneteampos.menu.action.MenuManage_deleteAction;
import oneteampos.menu.action.MenuManage_insertAction;
import oneteampos.menu.component.All_ScrollPane;
import oneteampos.menu.component.All_Table;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_checkBox;
import oneteampos.menu.component.All_checkBoxSetting;
import oneteampos.menu.component.All_comboBox;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.All_opaquePanel;
import oneteampos.menu.etc.Gbl;

public class MenuManage_dialog extends JDialog {
	
	public DefaultTableModel model;
	private Vector<Object> col;
	private Vector<Vector<Object>> row;
	private Object[] col2;
	public All_checkBox box;
	public JCheckBox chkBox;
	private JButton deleteBtn;
	public All_Table menu;
	private All_Table rsMenu;
	private JButton registrationBtn;
	public DefaultTableCellRenderer dtcr;
	private JScrollPane menuSc;
	
	public MenuManage_dialog(MainFrame mainFrame, MenuLeftPanel menuLeftPanel) {
		super(mainFrame, "메뉴 관리", true);
		this.col = new Vector<>();
		this.row = new Vector<>();
		this.model = inputMenuData();
		this.box = new All_checkBox();
		this.chkBox = box;
		this.menu = new All_Table(model);
		this.rsMenu = new All_Table(new Object[][] {{"","",""}}, col2);
		this.registrationBtn = new All_btn("메뉴 등록");
		this.deleteBtn = new All_btn("메뉴 삭제");
		this.dtcr = new All_checkBoxSetting(chkBox);
		this.menuSc = new All_ScrollPane(menu);

		JScrollPane rsMenuSc = new All_ScrollPane(rsMenu, "N");
		rsMenuSc.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		JPanel searchPanel = new All_opaquePanel();
		JPanel btns = new All_opaquePanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel menuManagePanel = new All_opaquePanel(new GridBagLayout());
		JPanel menuListPanel = new All_opaquePanel(new GridLayout());
		JPanel registerMenuPanel = new All_opaquePanel(new GridLayout());
		
		All_label menuListLabel = new All_label("전체 메뉴", JLabel.CENTER);
		All_label registerMenuLabel = new All_label("메뉴 등록", JLabel.CENTER);
		
		All_comboBox combo = new All_comboBox(col2);
		
		JTextField searchField = new JTextField(40);
		JButton searchBtn = new All_btn("검색");
		
		searchPanel.add(combo);
		searchPanel.add(searchField);
		searchPanel.add(searchBtn);
		
		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchData(combo.getSelectedItem(), searchField.getText());
				box.setting(menu, "선택", chkBox, dtcr);
			}
		});

		GridBagConstraints gbc = Gbl.getGbc();

		menuListPanel.add(menuListLabel);
		registerMenuPanel.add(registerMenuLabel);
		
		btns.add(registrationBtn);
		btns.add(deleteBtn);
		
		menuManagePanel.add(searchPanel, Gbl.setting(gbc, 0.1, 0.01, 0, 0));
		menuManagePanel.add(menuListPanel, Gbl.setting(gbc, 0.1, 0.03, 0, 1));
		menuManagePanel.add(menuSc, Gbl.setting(gbc, 0.1, 0.8, 0, 2));
		menuManagePanel.add(registerMenuPanel, Gbl.setting(gbc, 0.1, 0.03, 0, 3));
		menuManagePanel.add(rsMenuSc, Gbl.setting(gbc, 0.1, 0.1, 0, 4));
		menuManagePanel.add(btns, Gbl.setting(gbc, 0.1, 0.001, 0, 5));

		box.setting(menu, "선택", chkBox, dtcr);

		chkBox.addActionListener(new MenuManage_checkBoxAction(mainFrame, menu, chkBox));
		deleteBtn.addActionListener(new MenuManage_deleteAction(mainFrame));
		registrationBtn.addActionListener(new MenuManage_insertAction(mainFrame));
		
		addWindowListener(new Dialog_windowAction(mainFrame));

		add(menuManagePanel);
		setSize(MainFrame.FRAME_WIDTH/2, (int)(MainFrame.FRAME_HEIGHT*0.8));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
			menu.tableAlign();
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
		
		row.clear();
		
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

	public JTable getMenuTable() {
		return this.menu;
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
		return this;
	}
	
	public JTable getRsMenu() {
		return this.rsMenu;
	}
}
