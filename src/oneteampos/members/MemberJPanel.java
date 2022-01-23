package oneteampos.members;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.members.action.MenuBar_clickAction;
import oneteampos.members.action.MenuBar_homeAction;
import oneteampos.members.action.Search_searchAction;
import oneteampos.menu.component.All_Table;
import oneteampos.menu.etc.CommonVariable;


public class MemberJPanel extends JPanel implements CommonVariable {
	
	private static final int GAP = 0;

	private MainFrame mainFrame;
	
	// 로그인 정보
	//JPanel loginInfo = new JPanel();
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
    JScrollPane jsp = new JScrollPane(jt);
    
    // 검색창
    JPanel searchfield = new JPanel();
    String[] comboName = { "  ALL  ", "  PHONE_NUMBER  ", "  NAME  " };
    JComboBox combo = new JComboBox(comboName);
    RoundJTextField jtf = new RoundJTextField(20);
    RoundedButton search = new RoundedButton("검색");
 
    MemberDefaultJTableDAO dao = new MemberDefaultJTableDAO();   
    
    // 화면 구성 및 이벤트 등록
    public MemberJPanel(MainFrame mainFrame) {
    	   	
    	setLayout(new BorderLayout());
    	
    	// 상단 메뉴 배치
    	btns.add(getLoginInfo);
    	btns.add(insert);
    	btns.add(update);
    	btns.add(delete);
    	btns.add(home);
    	add(btns, "North");	
    	btns.setBorder(BorderFactory.createEmptyBorder(20,700,20,0));
    	btns.setBackground(Color.WHITE);
		
		// 검색창 배치
    	searchfield.add(combo);
    	searchfield.add(jtf);
    	searchfield.add(search);
    	searchfield.setBackground(Color.WHITE);
    	searchfield.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
    	add(searchfield, "South");
    	
    	// 회원 정보창
    	add(jsp, "Center"); 
    	jsp.setBorder(BorderFactory.createEmptyBorder(0,100,20,100));
    	jsp.setBackground(Color.WHITE);
    	jt.setFont(new Font("나눔스퀘어", Font.PLAIN, 14));
    	jt.setGridColor(Color.LIGHT_GRAY);
    	jt.setForeground(Color.DARK_GRAY);
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
