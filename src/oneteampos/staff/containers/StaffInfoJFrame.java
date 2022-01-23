package oneteampos.staff.containers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.StaffJoinJobs;
import oneteampos.sales.containers.RoundedButton;
import oneteampos.staff.actions.InsertStaffBtn;
import oneteampos.staff.actions.StaffInfoUpdateBtn;
import oneteampos.staff.actions.StaffKeyListener;

public class StaffInfoJFrame extends JFrame{
	
	Container ctPane;
	StaffInfoJPanel staffInfoPanel;
	private static ArrayList<StaffJoinJobs> staffList = new ArrayList<>();
	private JTextField nameField;
	private JTextField jobNameField;
	private JTextField hireField;
	private JTextField salaryField;
	private JTextField telField;
	private JTextField adrField;
	private static StaffJoinJobs staffjoinjobs = new StaffJoinJobs();
	
	public StaffInfoJFrame(StaffInfoJPanel staffInfoPanel) {
		this.staffInfoPanel = staffInfoPanel;
		setTitle("사원 등록");

		setAttribute();

		// 등록 버튼
		getInsertBtn();
	}
	
	public StaffInfoJFrame(StaffInfoJPanel staffInfoPanel, StaffJoinJobs staffJoinJobs2) {
		this.staffInfoPanel = staffInfoPanel;
		setTitle("사원 수정");
		setVisible(true);
		setResizable(false);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		
		ctPane = getContentPane(); 
		ctPane.setBackground(Color.WHITE);
		ctPane.setLayout(null);
		
		setAttribute();
		
		// 업데이트할 스테프 아이디 가져오기
		Integer stf_id = 0; 
		String sql = "SELECT stf_id FROM staff WHERE stf_name = '"+staffJoinJobs2.getStaffName()+"' AND address = '"+staffJoinJobs2.getAddress()+"'";

		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				stf_id = rs.getInt("stf_id");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("스태프 아이디"+ stf_id);
		
		this.nameField.setText(staffJoinJobs2.getStaffName());
		this.jobNameField.setText(staffJoinJobs2.getJobName());
		this.hireField.setText(staffJoinJobs2.getHireDate());
		this.salaryField.setText(staffJoinJobs2.getSalary() + "");
		this.telField.setText(staffJoinJobs2.getTel());
		this.adrField.setText(staffJoinJobs2.getAddress());
		// 수정할 정보 넣기 
		staffjoinjobs.setStf_name(nameField.getText());
		staffjoinjobs.setJob_name(jobNameField.getText());
		staffjoinjobs.setHire_date(hireField.getText());
		staffjoinjobs.setSalary(salaryField.getText());
		staffjoinjobs.setTel(telField.getText());
		staffjoinjobs.setAddress(adrField.getText());
		staffList.add(staffjoinjobs);
		// 수정 버튼
		addUpdateBtn().addMouseListener(new StaffInfoUpdateBtn(this, stf_id));
	}
	
	public void setAttribute( ) {
		
		setVisible(true);
		setResizable(false);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setLayout(null);
		
		ctPane = getContentPane(); 
		ctPane.setBackground(Color.WHITE);
		ctPane.setLayout(null);


		// 사원 이름 
		getStaffName();
		// 사원 직책
		getStaffJobName();
		// 사원 입사일
		getHireDate();
		// 사원 월급
		getSalary();
		// 사원 번호
		getTel();
		// 사원 주소
		getAddress();
		
	}

	public void getStaffName( ) {
		JLabel nameLabel = new SetStaffLabel("이름    : ");
		nameLabel.setBounds(50, 50, 100, 40);
		add(nameLabel);
		
		String staffName = "";
		nameField = new SetStaffField();
		nameField.setBounds(145, 50, 270, 40);
		nameField.addKeyListener(new StaffKeyListener(this, staffName , 0));	
	
		add(nameField);
	}
	
	public void getStaffJobName( ) {
		JLabel jobNameLabel = new SetStaffLabel("직책    : ");
		jobNameLabel.setBounds(50, 100, 100, 40);
		add(jobNameLabel);
		
		String staffJobName = "";
		jobNameField = new SetStaffField();
		jobNameField.setBounds(145, 100, 270, 40);
		jobNameField.addKeyListener(new StaffKeyListener(this, staffJobName, 1));
		add(jobNameField);
	}
	
	public void getHireDate( ) {
		JLabel hireLabel = new SetStaffLabel("입사일 : ");
		hireLabel.setBounds(50, 150, 100, 40);
		add(hireLabel);
		
		String hireDate = "";
		hireField = new SetStaffField();
		hireField.setBounds(145, 150, 270, 40);
		hireField.addKeyListener(new StaffKeyListener(this, hireDate, 2));
		add(hireField);
	}
	
	public void getSalary( ) {
		JLabel salaryLabel = new SetStaffLabel("월급    : ");
		salaryLabel.setBounds(50, 200, 100, 40);
		add(salaryLabel);
		
		String salary = "";
		salaryField = new SetStaffField();
		salaryField.setBounds(145, 200, 270, 40);
		salaryField.addKeyListener(new StaffKeyListener(this, salary, 3));
		add(salaryField);
	}
	
	public void getTel( ) {
		JLabel telLabel = new SetStaffLabel("전화    : ");
		telLabel.setBounds(50, 250, 100, 40);
		add(telLabel);
		
		String tel = "";
		telField = new SetStaffField();
		telField.setBounds(145, 250, 270, 40);
		telField.addKeyListener(new StaffKeyListener(this, tel, 4));
		add(telField);
	}
	
	public void getAddress( ) {
		JLabel adrLabel = new SetStaffLabel("주소    : ");
		adrLabel.setBounds(50, 300, 100, 40);
		add(adrLabel);
		
		String address = "";
		adrField = new SetStaffField();
		adrField.setBounds(145, 300, 270, 40);
		adrField.addKeyListener(new StaffKeyListener(this, address, 5));
		add(adrField);
	}
	
	public void getInsertBtn() {
		RoundedButton insertBtn = new RoundedButton();
		insertBtn.setText("등록");
		insertBtn.setBounds(340, 360, 100, 35);
		insertBtn.setFont(new Font("돋움", Font.BOLD, 20));
		insertBtn.setBackground(new Color(247, 245, 247));
		insertBtn.setRequestFocusEnabled(false);
		insertBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		insertBtn.setFocusPainted(false);
		
		insertBtn.addMouseListener(new InsertStaffBtn(this));
		
		add(insertBtn);
	}
	
	public RoundedButton addUpdateBtn() {
		RoundedButton updateBtn = new RoundedButton("수정");
		updateBtn.setBounds(340, 360, 100, 35);
		updateBtn.setFont(new Font("돋움", Font.BOLD, 20));
		updateBtn.setBackground(new Color(247, 245, 247));
		updateBtn.setRequestFocusEnabled(false);
		updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateBtn.setFocusPainted(false);
	
		add(updateBtn);
		
		return updateBtn;
	}
	
	public ArrayList<StaffJoinJobs> getStaffList() {
		staffList.add(staffjoinjobs);
		return staffList;
	}
	
	
	public StaffJoinJobs getStaffjoinjobs() {
		return staffjoinjobs;
	}
	
	public StaffInfoJPanel getStaffInfoJPanel() {
		return staffInfoPanel;
	}
	
class SetStaffField extends JTextField {
	
	public SetStaffField() {
		setBackground(Color.WHITE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		setBorder(null);
	    setHorizontalAlignment(JTextField.CENTER);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
		
	}
}

class SetStaffLabel extends JLabel {
	
	public SetStaffLabel(String labelName) {
		super(labelName);
		setFont(new Font("나눔스퀘어", Font.BOLD, 17));
	}
}
	
}
