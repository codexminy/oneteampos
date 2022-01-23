package oneteampos.staff.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.Items;
import oneteampos.datamodel.StaffJoinJobs;
import oneteampos.datamodel.Stock;
import oneteampos.datamodel.Store_order;
import oneteampos.order.actions.TableAddItemListener;
import oneteampos.staff.actions.StoreStaffListener;
import oneteampos.staff.components.AddStaffBtn;
import oneteampos.staff.components.DeleteStaffBtn;
import oneteampos.staff.components.UpdateStaffBtn;



public class StaffInfoJPanel extends JPanel{
	
	private ArrayList<StaffJoinJobs> staffInfo = new ArrayList<>();
	StaffJPanel staffPanel;
	DefaultTableModel model;
	JTable staffInfoTable;
	AddStaffBtn addStaffBtn;
	UpdateStaffBtn upadteStffBtn;
	DeleteStaffBtn deleteStaffBtn;
	StaffJoinJobs staffJoinJobs = new StaffJoinJobs();
	
	public StaffInfoJPanel(StaffJPanel staffPanel) {
		this.staffPanel = staffPanel;
		setBounds(0, 100, 1280, 680);
		setBackground(Color.WHITE);
		setLayout(null);
		
		// 사원정보 테이블 추가
		addStaffInfoTable();
		
		// 사원 등록 버튼 추가 
		addStaffBtn = new AddStaffBtn(this);
		
		// 사원 수정 버튼 추가
		upadteStffBtn = new UpdateStaffBtn(this);
		
		// 사원 삭제 버튼 추가
		deleteStaffBtn = new DeleteStaffBtn(this);
		
		

	}
	
	public void addStaffInfoTable() {
		
		// 데이터 가져오기 
		setTableData();
	
		
		// 재고 테이블 만들기 
		String[] columnNames = { " ","이름", "직책", "입사일", "월급", "전화번호", "주소" };
		String[][] rowData = new String[staffInfo.size()][columnNames.length];
		int idx = 1;
		for(int i = 0; i < staffInfo.size(); ++i) {
			
			for(int j = 0; j < columnNames.length; ++j) {
				if(j == 0) {
					rowData[i][j] = idx++ + ""; 
				} else {
					rowData[i][j] = staffInfo.get(i).getRowData(j-1) ;
				}
			}
		}
		
		model = new DefaultTableModel(rowData, columnNames){
			public boolean isCellEditable(int i, int c) {
				return false;
			}
		};
		
		staffInfoTable = new JTable(model);
		
		// 테이블 클릭시
		staffInfoTable.addMouseListener(new StoreStaffListener(this));
		
		setSizeColumnWidth(staffInfoTable);
		
		
		// 컴포넌트를 스크롤 가능한 형태로 보여주기 위해 사용된다.
		JScrollPane sp = new JScrollPane(staffInfoTable);
		
		sp.setBounds(90, 50, 1100, 340);
		sp.setBackground(new Color(135, 136, 138));
		
		
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(sp);
	
	}
	
	
	public void setTableData() { // 데이터 넣는 메소드
		String sql = "SELECT stf_name, job_name , TO_CHAR(hire_date, 'YYYY-MM-DD') AS hire_date, salary, tel , address FROM staff inner join staff_jobs USING(job_id)\r\n"
				+ "WHERE job_id != 1 ORDER BY job_id, stf_id, hire_date ASC";
		try (Connection conn = DBConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				staffInfo.add(new StaffJoinJobs(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void setSizeColumnWidth(JTable table) {
		// 셀 간격 조정
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < table.getColumnCount(); ++i) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(celAlignCenter);
		}

		table.setRowHeight(30);
		table.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		table.getTableHeader().setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		table.getTableHeader().setBackground(new Color(135, 136, 138));
		table.getTableHeader().setForeground(Color.WHITE);

		


	}
	
	public AddStaffBtn getAddStaffBtn() {
		return addStaffBtn;
	}
	
	public UpdateStaffBtn getUpdateStaffBtn() {
		return upadteStffBtn;
	}
	
	public DeleteStaffBtn getDeleteStaffBtn() {
		return deleteStaffBtn;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	public JTable getStaffInfoTable() {
		return staffInfoTable;
	}
	
	public StaffJoinJobs getStaffJoinJobs() {
		return staffJoinJobs;
	}
	
	public  ArrayList<StaffJoinJobs> getStaffInfoList() {
		return staffInfo;
	}
	
	public StaffJPanel getStaffJpanel() {
		return staffPanel;
	}
	
}
