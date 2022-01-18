package oneteampos.menu.component;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.main.MainFrame;
import oneteampos.menu.container.LinePanel;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.data.MemberData;

public class MemberCheckDialog extends JDialog {
	
	private final static int DIALOG_W = 720;
	private final static int DIALOG_H = 720;
	private final static int GAP = 20;
	private MainFrame mainFrame;
	private Vector<Object> col;
	private Vector<Object> col2;
	private Vector<MemberData> memberList;
	private Vector<Vector<Object>> row;
	private String item;
	private int discnt;
	public JTable table;
	public JLabel saveCash;
	public int point;
	
	public MemberCheckDialog(MainFrame mainFrame) {
		super(mainFrame, "회원 조회");
		this.mainFrame = mainFrame;
		this.col = new Vector<>();
		this.col2 = new Vector<>();
		this.memberList = insertMemberData();
		this.row = new Vector<>();
		
		insertRow();
		
		JPanel bg = new JPanel(null);
		
		JLabel memberCheckLabel = new JLabel("회원조회");
		memberCheckLabel.setBounds(GAP, GAP, DIALOG_W, DIALOG_H/30);
		
		JPanel line = new LinePanel(DIALOG_W - 60);
		line.setBounds(GAP, 40, DIALOG_W - 40, 10);
		
		JComboBox<Object> combo = new JComboBox<>(col2);
		combo.setBounds(GAP, 50, DIALOG_W - 580, GAP);
		
		item = (String) combo.getSelectedItem();

		combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				item = (String) combo.getSelectedItem();
			}
		});
		
		JPanel infoPanel = new JPanel(new GridLayout(3,2));
		JPanel chkPanel = new JPanel(new GridLayout());
		
		infoPanel.setBounds(20, 300, 200, 200);
		chkPanel.setBounds(20, 530, 200, 50);
		
		JLabel membership = new JLabel("멤버십");
		JLabel membershipCash = new JLabel();
		membershipCash.setVisible(false);
		
		JLabel save = new JLabel("적립금");
		saveCash = new JLabel();
		saveCash.setVisible(false);
		
		JLabel discount = new JLabel("할인금액");
		JLabel discountCash = new JLabel();
		discountCash.setVisible(false);
		
		infoPanel.add(membership);
		infoPanel.add(membershipCash);
		infoPanel.add(save);
		infoPanel.add(saveCash);
		infoPanel.add(discount);
		infoPanel.add(discountCash);

		JCheckBox dcBox = new JCheckBox("할인");
		JCheckBox svBox = new JCheckBox("적립");
		
		
		
		JTextField searchField = new JTextField();
		searchField.setBounds(combo.getWidth()+GAP, 50, DIALOG_W-combo.getWidth()-110-GAP, GAP + 1);

		DefaultTableModel model = new DefaultTableModel(row, col);
//		model.setDataVector(row, col);
		
		table = new JTable(model);
		
		
		
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				membershipCash.setVisible(true);
				membershipCash.setText(table.getValueAt(row, 2)+"");
				
				int total = Integer.parseInt(mainFrame.getMainPanel().getMenuPanel().getRightPanel().getTotalPrice().getText().substring(2));
				double save = (double) table.getValueAt(row, 7);
				
				int discnt = (int) table.getValueAt(row, 5);
				
				if(svBox.isSelected()) {
					saveCash.setVisible(true);
					saveCash.setText((int)(total*(save/100))+"");
				} else {
					saveCash.setVisible(false);
				}
				
				if(dcBox.isSelected()) {
					discountCash.setVisible(true);
					discountCash.setText(discnt+"");
				} else {
					discountCash.setVisible(false);
				}
				
				if(total >= discnt) {
					discountCash.setText(discnt+"");
				} else {
					discountCash.setText(total+"");
				}
			}
			
		});
		
		dcBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				if(row == -1 && dcBox.isSelected()) {
					JOptionPane.showMessageDialog(null, "회원을 선택해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
					dcBox.setSelected(false);
				} else if(dcBox.isSelected()) {
					int total = Integer.parseInt(mainFrame.getMainPanel().getMenuPanel().getRightPanel().getTotalPrice().getText().substring(2));
					discnt = (int) table.getValueAt(row, 5);
					discountCash.setVisible(true);
					
					if(total >= discnt) {
						discountCash.setText(discnt+"");
					} else {
						discountCash.setText(total+"");
					}
//					discountCash.setText(discnt+"");
				} else {
					discountCash.setVisible(false);
				}
			}
		});
		
		svBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				
				
				if(row == -1 && svBox.isSelected()) {
					JOptionPane.showMessageDialog(null, "회원을 선택해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
					svBox.setSelected(false);
				} else if (svBox.isSelected()) {
					discnt = (int) table.getValueAt(row, 5);
					int total = Integer.parseInt(mainFrame.getMainPanel().getMenuPanel().getRightPanel().getTotalPrice().getText().substring(2));
					double save = (double) table.getValueAt(row, 7);
					saveCash.setVisible(true);
					saveCash.setText((int)(total*(save/100))+"");
				} else {
					saveCash.setVisible(false);
				}
				
			}
		});
		
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(GAP, 80, DIALOG_W - 60, DIALOG_H-500);
		
		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(610, 50, 70, GAP);
		searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

		searchBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				searchData(item, searchField.getText(), model, table);
			}
			
		});

		JPanel numPadPanel = new JPanel(new GridLayout(4,3));

		String[] num = new String[] {"7", "8", "9", "4", "5", "6", "1", "2", "3", "clear", "0", "<"};
		
		for(int i=0; i<12; ++i) {
			JButton btn = new JButton(num[i]);
			numPadPanel.add(btn);
			btn.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseClicked(MouseEvent e) {
					String text = searchField.getText();
					
					if(btn.getText().equals("clear")) {
						searchField.setText("");
					} else if(text.length() != 0 && btn.getText().equals("<")) {
						searchField.setText(text.substring(0, text.length()-1));
					} else if(!btn.getText().equals("<")) {
						searchField.setText(text + btn.getText());
					}
				}
				
			});
		}
		
		numPadPanel.setBounds(430, 320, 250, 250);

		
		chkPanel.add(dcBox);
		chkPanel.add(svBox);

		
		
		
		JButton apply = new JButton("적용");
		
		apply.setBounds(600, 600, 80, 30);
		
		apply.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "적용하겠습니까?", "Message", JOptionPane.YES_NO_OPTION);
				MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
				if(choice == JOptionPane.YES_OPTION) {
					int totalPrice = Integer.parseInt(rp.getTotalPrice().getText().substring(2));
					
					
					
					if(totalPrice >= discnt) {
						point = 0;
					} else {
						point = discnt - totalPrice;
					}
					
					JOptionPane.showMessageDialog(null, "적용되었습니다.");
					
					int dis = Integer.parseInt(discountCash.getText());
					
					if(dcBox.isSelected() && svBox.isSelected()) {
						int row = table.getSelectedRow();
						point = discnt - Integer.parseInt(discountCash.getText()) + Integer.parseInt(saveCash.getText());
//						updatePointData(table.getColumnName(5), point, (String)table.getValueAt(row, 2));
						rp.getDiscountCash().setText(dis+"");
						rp.getTotalPrice().setText("￦ " + (totalPrice-dis));
					} else if(dcBox.isSelected()) {
						int row = table.getSelectedRow();
						point = discnt - Integer.parseInt(discountCash.getText());
//						updatePointData(table.getColumnName(5), point, (String)table.getValueAt(row, 2));
						rp.getDiscountCash().setText(dis+"");
						rp.getTotalPrice().setText("￦ " + (totalPrice-dis));
					} else if(svBox.isSelected()) {
						System.out.println("적립만 체크" + discnt);
						int row = table.getSelectedRow();
						point = discnt + Integer.parseInt(saveCash.getText());
//						updatePointData(table.getColumnName(5), point, (String)table.getValueAt(row, 2));
					}
					
					rp.getDiscountCash().setVisible(true);
//					rp.getDiscountCash().setText(dis+"");
//					rp.getTotalPrice().setText("￦ " + (totalPrice-dis));
					
					
					rp.cancelBtn.setVisible(true);
					rp.isDisCnt = true;
					dispose();
				}
			}
			
		});
//		
//		table.getColumn("GRADE_ID").setMinWidth(0);
//		table.getColumn("GRADE_ID").setMaxWidth(0);
//		table.getColumn("MEMBER_ID").setMinWidth(0);
//		table.getColumn("MEMBER_ID").setMaxWidth(0);
//		table.getColumn("SUM_AMOUNT").setMinWidth(0);
//		table.getColumn("SUM_AMOUNT").setMaxWidth(0);
//		table.getColumn("THRESHOLD").setMinWidth(0);
//		table.getColumn("THRESHOLD").setMaxWidth(0);
		
		
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

	private void insertRow() {
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
	
	private void searchData(String columnName, String condition, DefaultTableModel model, JTable table) {
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
	
	private void updatePointData(String column, int point, String phone_number) {
		String sql = String.format("UPDATE members SET %s=%d WHERE phone_number='%s'", column, point, phone_number.replaceAll("-", ""));
		
		try (
			Connection conn = DBConnector.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


























































