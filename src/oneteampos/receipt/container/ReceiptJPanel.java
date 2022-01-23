package oneteampos.receipt.container;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import oneteampos.database.DBConnector;
import oneteampos.datamodel.ReceiptInfo;
import oneteampos.main.MainFrame;
import oneteampos.order.compnents.BackBtn;
import oneteampos.receipt.components.ReturnBtn;

public class ReceiptJPanel extends JPanel{
	
	MainFrame mainframe;
	JLabel receiptLable;
	ReceiptTableJPanel receiptTablePanel;
	
	public ReceiptJPanel(MainFrame mainframe) {
		// 판넬 설정 
		this.setBounds(0, 0, 1280, 720);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		this.mainframe = mainframe;
		
		
		// 로그인 정보 : 사번 + 사원이름
		addLoginInfoLabel();
		
		//영수증 라벨 추가
		addReceiptLabel();
		
		//테이블 추가 
		receiptTablePanel = new ReceiptTableJPanel(this);
		add(receiptTablePanel);
		
		// 버튼 추가
		add(new BackBtn(mainframe , this , "receiptPanel"));
		
	}
	
	@Override
	public void paint(Graphics g) { // 선 긋기
		super.paint(g);
		
		g.drawLine(20, 60, 1240, 60);
		g.drawLine(20, 100, 1240, 100);
	}
	
	public void addLoginInfoLabel() { // 로그인 정보 라벨 (stf_id, stf_name)
		mainframe.getLoginStaffIDJLabel().setVisible(true);
		mainframe.getStaffNameJLabel().setVisible(true);
		
		add(mainframe.getLoginStaffIDJLabel());
		add(mainframe.getStaffNameJLabel());
	}
	
	public void addReceiptLabel() { // 영수증 라벨 속성 지정 , 판넬에 추가
		receiptLable = new JLabel("영수증");
		receiptLable.setFont(new Font("나눔스퀘어", Font.BOLD, 17));
		receiptLable.setBounds(30, 65, 300, 30);
		receiptLable.setBackground(Color.WHITE);
		
		add(receiptLable);
	}
	
	public ReceiptTableJPanel getReceiptTablePanel() {
		return receiptTablePanel;
	}
	
	public JLabel getReceiptLabel() {
		return receiptLable;
	}
	
	public MainFrame getMainFrame() {
		return mainframe;
	}
}
