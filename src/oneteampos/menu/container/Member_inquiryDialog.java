package oneteampos.menu.container;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.action.Member_dcBoxAction;
import oneteampos.menu.action.Member_applyAction;
import oneteampos.menu.action.Member_comboBoxAcion;
import oneteampos.menu.action.Member_selectAction;
import oneteampos.menu.action.Member_svBoxAction;
import oneteampos.menu.component.All_numberPad;
import oneteampos.menu.action.Member_searchAction;
import oneteampos.menu.data.MemberData;

public class Member_inquiryDialog extends JDialog {
	
	private final static int DIALOG_W = 720;
	private final static int DIALOG_H = 720;
	private final static int GAP = 20;
	private Vector<Object> col;
	private Vector<Object> col2;
	private Vector<MemberData> memberList;
	private Vector<Vector<Object>> row;
	private JTable table;
	private JLabel saveCash;
	private String item;
	private int discnt;
	private int point;
	private JLabel membershipCash;
	private JLabel discountCash;
	private JCheckBox dcBox;
	private JCheckBox svBox;
	private DefaultTableModel model;
	private JTextField searchField;

	public Member_inquiryDialog(MainFrame mainFrame) {
		super(mainFrame, "회원 조회", true);
		this.col = new Vector<>();
		this.col2 = new Vector<>();
		this.memberList = insertMemberData();
		this.row = insertRow();

		JPanel bg = new JPanel(null);
		
		JLabel memberCheckLabel = new JLabel("회원조회");
		memberCheckLabel.setBounds(GAP, GAP, DIALOG_W, DIALOG_H/30);
		
		JPanel line = new All_LinePanel(DIALOG_W - 60);
		line.setBounds(GAP, 40, DIALOG_W - 40, 10);
		
		JComboBox<Object> combo = new JComboBox<>(col2);
		combo.setBounds(GAP, 50, DIALOG_W - 580, GAP);
		
		item = (String) combo.getSelectedItem();

		combo.addActionListener(new Member_comboBoxAcion(this));
		
		JPanel infoPanel = new JPanel(new GridLayout(3,2));
		JPanel chkPanel = new JPanel(new GridLayout());
		
		infoPanel.setBounds(20, 300, 200, 200);
		chkPanel.setBounds(20, 530, 200, 50);
		
		JLabel membership = new JLabel("멤버십");
		membershipCash = new JLabel();
		membershipCash.setVisible(false);
		
		JLabel save = new JLabel("적립금");
		saveCash = new JLabel();
		saveCash.setVisible(false);
		
		JLabel discount = new JLabel("할인금액");
		discountCash = new JLabel();
		discountCash.setVisible(false);
		
		infoPanel.add(membership);
		infoPanel.add(membershipCash);
		infoPanel.add(save);
		infoPanel.add(saveCash);
		infoPanel.add(discount);
		infoPanel.add(discountCash);

		dcBox = new JCheckBox("할인");
		svBox = new JCheckBox("적립");

		searchField = new JTextField();
		searchField.setBounds(combo.getWidth()+GAP, 50, DIALOG_W-combo.getWidth()-110-GAP, GAP + 1);

		model = new DefaultTableModel();
		model.setDataVector(row, col);
		
		table = new JTable(model);
		
		table.addMouseListener(new Member_selectAction(mainFrame, this));
		dcBox.addActionListener(new Member_dcBoxAction(mainFrame, this));
		svBox.addActionListener(new Member_svBoxAction(mainFrame, this));
		
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(GAP, 80, DIALOG_W - 60, DIALOG_H-500);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(610, 50, 70, GAP);
		searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		searchBtn.addMouseListener(new Member_searchAction(this));

		JPanel numPadPanel = new All_numberPad(searchField);
		numPadPanel.setBounds(430, 320, 250, 250);

		chkPanel.add(dcBox);
		chkPanel.add(svBox);

		JButton apply = new JButton("적용");
		
		apply.setBounds(600, 600, 80, 30);
		
		apply.addMouseListener(new Member_applyAction(mainFrame, this));

		bg.add(memberCheckLabel);
		bg.add(line);
		bg.add(combo);
		bg.add(searchField);
		bg.add(searchBtn);
		bg.add(sc);
		bg.add(numPadPanel);
		bg.add(infoPanel);
		bg.add(chkPanel);
		bg.add(apply);
		
		add(bg);
		
		setSize(DIALOG_W, DIALOG_H);
		setLocationRelativeTo(null);
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
				innerRow.add(rs.getString(1));
				innerRow.add(rs.getString(2));
				innerRow.add(rs.getString(3).replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3"));
				innerRow.add(rs.getString(4));
				innerRow.add(rs.getString(5));
				innerRow.add(rs.getString(6));
				innerRow.add(rs.getString(7));
				innerRow.add(rs.getString(8));
				innerRow.add(rs.getString(9));
				row.add(innerRow);
			}
			model.setDataVector(row, col);
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
	
	public JLabel getMembershipCash() {
		return this.membershipCash;
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


























































