package oneteampos.members;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.members.action.MenuBar_clickAction;
import oneteampos.members.action.MenuBar_homeAction;
import oneteampos.members.action.Search_searchAction;


public class MemberJPanel extends JPanel {
	
	private MainFrame mainFrame; 
	
	// 메뉴바
	JPanel p1 = new JPanel();
	MenuBarSet b = new MenuBarSet();	
	JMenu m = new JMenu("관리");
	
	JMenuItem insert = new JMenuItem("회원등록");
    JMenuItem update = new JMenuItem("정보수정");
    JMenuItem delete = new JMenuItem("회원삭제");
	JMenuItem home = new JMenuItem("처음으로");
    
    // 회원 정보
	String[] name = { "멤버ID", "전화번호", "이름", "회원등급", "구매금액", "포인트" };		 
    
	DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new JTable(dt);
    JScrollPane jsp = new JScrollPane(jt);
    
    // 검색창
    JPanel p2 = new JPanel();
    String[] comboName = { "  ALL  ", "  PHONE_NUMBER  ", "  NAME  " };	 
    JComboBox combo = new JComboBox(comboName);
    RoundJTextField jtf = new RoundJTextField(20);
    RoundedButton search = new RoundedButton("검색");
 
    MemberDefaultJTableDAO dao = new MemberDefaultJTableDAO();   
    
    // 화면 구성 및 이벤트 등록
    public MemberJPanel(MainFrame mainFrame) {
    	
    	setLayout(new BorderLayout());
    	
    	// 메뉴바 > 메뉴 추가
		b.add(m);
		m.setFont(new Font("나눔스퀘어", Font.BOLD, 14));

		m.add(insert);
		insert.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		insert.setBackground(Color.WHITE);
		insert.setMinimumSize(new Dimension(100,30));
		insert.setPreferredSize(new Dimension(100,30));
		
		m.add(update);
		update.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		update.setBackground(Color.WHITE);
		update.setMinimumSize(new Dimension(100,30));
		update.setPreferredSize(new Dimension(100,30));
		
		m.add(delete);
		delete.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		delete.setBackground(Color.WHITE);
		delete.setMinimumSize(new Dimension(100,30));
		delete.setPreferredSize(new Dimension(100,30));
		
		m.add(home);
		home.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
		home.setBackground(Color.WHITE);
		home.setMinimumSize(new Dimension(100,30));
		home.setPreferredSize(new Dimension(100,30));
				
		// 메뉴바 배치
		p1.setLayout(new BorderLayout());
		p1.add(b, BorderLayout.NORTH);
		add(p1, BorderLayout.NORTH); 
		
		// 검색창 배치
    	p2.add(combo);
    	p2.add(jtf);
    	p2.add(search);
    	p2.setBackground(Color.WHITE);
    	add(p2, "South");
    	
    	
    	add(jsp, "Center");
    	
    	dao.memberSelectAll(dt);
    	
    	if (dt.getRowCount() > 0) {
    		jt.setRowSelectionInterval(0, 0);
    	}
    	
    	insert.addActionListener(new MenuBar_clickAction(this, dao));
    	update.addActionListener(new MenuBar_clickAction(this, dao));
    	delete.addActionListener(new MenuBar_clickAction(this, dao));
    	search.addActionListener(new Search_searchAction(this, dao));
    	home.addActionListener(new MenuBar_homeAction(mainFrame));
    }
    
    public JMenuItem getInsert() {
    	return this.insert;
    }
    
    public JMenuItem getUpdate() {
    	return this.update;
    }
    
    public JMenuItem getDelete() {
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
