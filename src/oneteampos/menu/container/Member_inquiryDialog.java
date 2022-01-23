package oneteampos.menu.container;

import java.awt.GridLayout;
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
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.action.Member_applyAction;
import oneteampos.menu.action.Member_comboBoxAcion;
import oneteampos.menu.action.Member_dcBoxAction;
import oneteampos.menu.action.Member_searchAction;
import oneteampos.menu.action.Member_selectAction;
import oneteampos.menu.action.Member_svBoxAction;
import oneteampos.menu.component.All_ScrollPane;
import oneteampos.menu.component.All_Table;
import oneteampos.menu.component.All_boxPanel;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_checkBox;
import oneteampos.menu.component.All_comboBox;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.All_numberPad;
import oneteampos.menu.component.All_opaquePanel;
import oneteampos.menu.data.MemberData;
import oneteampos.menu.etc.CommonVariable;

public class Member_inquiryDialog extends JDialog implements CommonVariable {
	
	private Vector<Object> col;
	private Vector<Object> col2;
	private Vector<MemberData> memberList;
	private Vector<Vector<Object>> row;
	private All_Table table;
	private JLabel saveCash;
	private JLabel membershipNum;
	private JLabel discountCash;
	private JCheckBox dcBox;
	private JCheckBox svBox;
	private JTextField searchField;
	private DefaultTableModel model;
	private String item;
	private int discnt;
	private int point;

	public Member_inquiryDialog(MainFrame mainFrame) {
		super(mainFrame, "회원 조회", true);
		this.col = new Vector<>();
		this.col2 = new Vector<>();
		this.memberList = insertMemberData();
		this.row = insertRow();
		this.searchField = new JTextField();
		this.model = new DefaultTableModel(row, col) { public boolean isCellEditable(int row, int column) {return false;} };
		this.table = new All_Table(model);
		this.membershipNum = new All_label("");
		this.saveCash = new All_label("0");
		this.discountCash = new All_label("0");
		this.dcBox = new All_checkBox("할인");
		this.svBox = new All_checkBox("적립");

		All_boxPanel bgBoxPanel = new All_boxPanel("Y", CENTER_ALIGNMENT);
		All_boxPanel searchBoxPanel = new All_boxPanel(CENTER_ALIGNMENT);
		All_boxPanel infoCheckBoxPanel = new All_boxPanel("Y", CENTER_ALIGNMENT);
		All_boxPanel numInfoBoxPanel = new All_boxPanel(CENTER_ALIGNMENT);
		All_boxPanel applyBoxPanel = new All_boxPanel(CENTER_ALIGNMENT);
		
		JPanel infoPanel = new All_opaquePanel(new GridLayout(3,2));
		JPanel checkPanel = new All_opaquePanel(new GridLayout());
		JPanel numPadPanel = new All_numberPad(searchField);
		
		JButton searchBtn = new All_btn("검색");
		JButton apply = new All_btn("적용");
		
		All_comboBox combo = new All_comboBox(col2);

		JScrollPane sc = new All_ScrollPane(table);
		
		JLabel membership = new All_label("멤버십");
		JLabel save = new All_label("적립금");
		JLabel discount = new All_label("할인금액");
		
		saveCash.setVisible(false);
		discountCash.setVisible(false);
		
		combo.addActionListener(new Member_comboBoxAcion(this));
		searchBtn.addMouseListener(new Member_searchAction(this));
		apply.addMouseListener(new Member_applyAction(mainFrame, this));
		this.table.addMouseListener(new Member_selectAction(mainFrame, this));
		this.dcBox.addActionListener(new Member_dcBoxAction(mainFrame, this));
		this.svBox.addActionListener(new Member_svBoxAction(mainFrame, this));

		bgBoxPanel.setEmptyBorder(GAP, GAP, GAP, GAP);
		searchBoxPanel.setEmptyBorder(0, 0, GAP/2, 0);
		numInfoBoxPanel.setEmptyBorder(GAP/2, 0, GAP*2, 0);
		numInfoBoxPanel.setPnSize(0, 300);
		infoCheckBoxPanel.setPnSize(DIALOG_W/4, 0);
		numPadPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
		
		searchBoxPanel.add(combo);
		searchBoxPanel.add(searchField);
		searchBoxPanel.add(searchBtn);

		infoPanel.add(membership);
		infoPanel.add(membershipNum);
		infoPanel.add(save);
		infoPanel.add(saveCash);
		infoPanel.add(discount);
		infoPanel.add(discountCash);

		checkPanel.add(dcBox);
		checkPanel.add(svBox);
		
		infoCheckBoxPanel.add(infoPanel);
		infoCheckBoxPanel.add(checkPanel);
		
		numInfoBoxPanel.add(infoCheckBoxPanel);
		numInfoBoxPanel.add(numPadPanel);

		applyBoxPanel.add(apply);
		
		bgBoxPanel.add(searchBoxPanel);
		bgBoxPanel.add(sc);
		bgBoxPanel.add(numInfoBoxPanel);
		bgBoxPanel.add(applyBoxPanel);
		
		add(bgBoxPanel);
		
		setSize(DIALOG_W, DIALOG_H);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private Vector<Vector<Object>> insertRow() {
		Vector<Vector<Object>> row = new Vector<>();
		
		for(int i=0; i<memberList.size(); ++i) {
			Vector<Object> innerRow = new Vector<Object>();
			innerRow.add(memberList.get(i).getGradeId());
			innerRow.add(memberList.get(i).getMemberId());
			innerRow.add(memberList.get(i).getPhoneNumber());
			innerRow.add(memberList.get(i).getName());
			innerRow.add(memberList.get(i).getSumAmount());
			innerRow.add(memberList.get(i).getPoint());
			innerRow.add(memberList.get(i).getGrade());
			innerRow.add(memberList.get(i).getSave());
			innerRow.add(memberList.get(i).getThresHold());
			row.add(innerRow);
		}
		
		return row;
	}

	private Vector<MemberData> insertMemberData() {
		Vector<MemberData> list = new Vector<>();
		String sql = "SELECT * FROM members INNER JOIN member_grade USING (grade_id)";
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			ResultSetMetaData meta = rs.getMetaData();

			col2.add(meta.getColumnName(3));
			col2.add(meta.getColumnName(4));
			col2.add(meta.getColumnName(6));
			col2.add(meta.getColumnName(7));
			col2.add(meta.getColumnName(8));
			
			for(int i=0; i<meta.getColumnCount(); ++i) {
				col.add(meta.getColumnName(i+1));
			}
			
			while(rs.next()) {
				list.add(new MemberData(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void searchData(String columnName, String condition, DefaultTableModel model, JTable table) {
		String sql = String.format("SELECT * FROM members INNER JOIN member_grade USING (grade_id) WHERE %s LIKE '%%%s%%'", columnName, condition);
		Vector<Vector<Object>> row = new Vector<>();

		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
		) {
			while(rs.next()) {
				Vector<Object> innerRow = new Vector<>();
				innerRow.add(rs.getInt(1));
				innerRow.add(rs.getInt(2));
				innerRow.add(rs.getString(3).replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3"));
				innerRow.add(rs.getString(4));
				innerRow.add(rs.getInt(5));
				innerRow.add(rs.getInt(6));
				innerRow.add(rs.getString(7));
				innerRow.add(rs.getDouble(8));
				innerRow.add(rs.getInt(9));
				row.add(innerRow);
			}
			model.setDataVector(row, col);
			this.table.tableAlign();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPoint() {
		return this.point;
	}
	
	public JTable getTable() {
		return this.table;
	}

	public JLabel getSaveCash() {
		return this.saveCash;
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}
	
	public JTextField getSearchField() {
		return this.searchField;
	}
	
	public String getItem() {
		return this.item;
	}

	public int getDiscnt() {
		return this.discnt;
	}

	public JCheckBox getSvBox() {
		return this.svBox;
	}
	
	public JCheckBox getDcBox() {
		return this.dcBox;
	}
	
	public JLabel getDiscountCash() {
		return this.discountCash;
	}
	
	public JLabel getMembershipNum() {
		return this.membershipNum;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public void setDiscnt(int discnt) {
		this.discnt = discnt;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
}
