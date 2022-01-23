package oneteampos.members;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.datamodel.Clock;
import oneteampos.main.MainFrame;
import oneteampos.members.action.MenuBar_clickAction;
import oneteampos.members.action.MenuBar_homeAction;
import oneteampos.members.action.Search_searchAction;
import oneteampos.menu.component.All_ScrollPane;
import oneteampos.menu.component.All_Table;
import oneteampos.menu.etc.CommonVariable;


public class MemberJPanel extends JPanel {

	private MainFrame mainFrame;
	
	// 로그인 정보
	JPanel loginInfo = new JPanel();
	JLabel getLoginInfo = new JLabel(); 
	
	// 상단 메뉴
	JPanel btns = new JPanel();
	RoundedButton insert = new RoundedButton(" 회 원 등 록 ");
	RoundedButton update = new RoundedButton(" 정 보 수 정 ");
	RoundedButton delete = new RoundedButton(" 회 원 삭 제 ");
	RoundedButton home = new RoundedButton("<");
    
    // 회원 정보
	String[] name = { "멤버ID", "전화번호", "이름", "회원등급", "구매금액", "포인트" };   
	DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new All_Table(dt);
    JScrollPane jsp = new All_ScrollPane(jt);
    
    // 검색창
    JPanel searchfield = new JPanel();
    String[] comboName = { "  PHONE_NUMBER  ", "  NAME  " };
    JComboBox combo = new JComboBox(comboName);
    RoundJTextField jtf = new RoundJTextField(20);
    RoundedButton search = new RoundedButton("검색");
 
    MemberDefaultJTableDAO dao = new MemberDefaultJTableDAO();   
    
    
    // 화면 구성 및 이벤트 등록
    public MemberJPanel(MainFrame mainFrame) {
    	   	
    	setLayout(null);
    	setBackground(Color.WHITE);
    	    	
    	// 시계 배치
    	Clock clock = new Clock();
    	add(clock);
    	clock.setBounds(250, 30, 400, 30);
    	
    	// 로그인 정보 배치
    	loginInfo.add(getLoginInfo);
    	add(loginInfo);
    	loginInfo.setBounds(100, 30, 90, 30);
    	loginInfo.setBackground(Color.WHITE);
    	getLoginInfo.setFont(new Font("나눔스퀘어", Font.BOLD, 16));
    	
    	// 상단 메뉴 배치
    	btns.add(insert);
    	btns.add(update);
    	btns.add(delete);
    	btns.add(home);
    	add(btns);
    	btns.setBounds(800, 30, 400, 40);
    	btns.setBackground(Color.WHITE);
		   	
    	// 회원 정보창
    	add(jsp); 
    	jsp.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    	jsp.setBounds(100, 100, 1080, 450);
    	dao.memberSelectAll(dt);    	
    	if (dt.getRowCount() > 0) {
    		jt.setRowSelectionInterval(0, 0);  	
    	}
    	
    	// 검색창 배치
    	searchfield.add(combo);
    	searchfield.add(jtf);
    	searchfield.add(search);
    	searchfield.setBackground(Color.WHITE);
    	add(searchfield);
    	searchfield.setBounds(0, 600, 1280, 80);
    	
    	insert.addActionListener(new MenuBar_clickAction(this, dao));
    	update.addActionListener(new MenuBar_clickAction(this, dao));
    	delete.addActionListener(new MenuBar_clickAction(this, dao));
    	search.addActionListener(new Search_searchAction(this, dao));
    	home.addActionListener(new MenuBar_homeAction(mainFrame));
    }

    
	public JLabel getLoginInfo() {
		return this.getLoginInfo;	
    }
    
	public RoundedButton getInsert() {
    	return this.insert;
    }
    
    public RoundedButton getUpdate() {
    	return this.update;
    }
    
    public RoundedButton getDelete() {
    	return this.delete;
    }
    
    public JTable getJt() {
    	return this.jt;
    }
    
    public DefaultTableModel getDt() {
    	return this.dt;
    }
    
    public JTextField getJtf() {
    	return this.jtf;
    }
    
    public JComboBox getCombo() {
    	return this.combo;
    }
    
    public JButton getSearch() {
    	return this.search;
    }
}
