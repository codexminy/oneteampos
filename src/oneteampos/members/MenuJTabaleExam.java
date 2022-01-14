package oneteampos.members;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MenuJTabaleExam extends JFrame implements ActionListener {
	JMenu m = new JMenu("관리");
	JMenuItem insert = new JMenuItem("회원등록");
    JMenuItem update = new JMenuItem("정보수정");
    JMenuItem delete = new JMenuItem("회원삭제");
    JMenuItem quit = new JMenuItem("종료");
    JMenuBar mb = new JMenuBar();
 
    String[] name = { "멤버ID", "전화번호", "이름", "회원등급", "구매금액", "포인트" };
 
    DefaultTableModel dt = new DefaultTableModel(name, 0);
    JTable jt = new JTable(dt);
    JScrollPane jsp = new JScrollPane(jt);
 
    
    JPanel p = new JPanel();
    String[] comboName = { "  ALL  ", "  PHONE NUMBER  ", "  NAME  " };
 
    JComboBox combo = new JComboBox(comboName);
    JTextField jtf = new JTextField(20);
    JButton search = new JButton("검색");
 
    MemberDefaultJTableDAO dao = new MemberDefaultJTableDAO();
    
    // 화면 구성 및 이벤트 등록
    public MenuJTabaleExam() {
    	
    	super ("회원관리");
    	
    	// 메뉴 추가
    	m.add(insert);
    	m.add(update);
    	m.add(delete);
    	m.add(quit);
    	
    	// 메뉴바
    	mb.add(m);
    	
    	// 윈도우에 메뉴바 세팅
    	setJMenuBar(mb);
    	
    	p.setBackground(Color.yellow);
    	p.add(combo);
    	p.add(jtf);
    	p.add(search);
    	
    	add(jsp, "Center");
    	add(p, "South");
    	
    	setSize(1280, 720);
    	setLocationRelativeTo(null);
    	setVisible(true);
    	
    	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// 이벤트 등록
    	insert.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        quit.addActionListener(this);
        search.addActionListener(this);
    	
    	dao.memberSelectAll(dt);
    	
    	if (dt.getRowCount() > 0)
    		jt.setRowSelectionInterval(0, 0);    	
    }

    public static void main(String[] args) {
		new MenuJTabaleExam();
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {			// 메뉴 > 가입
			new MemberJDailogGUI(this, "회원등록");
			
		} else if (e.getSource() == update) {	// 메뉴 > 수정
			new MemberJDailogGUI(this, "정보수정");
			
		} else if (e.getSource() == delete) {	// 메뉴 > 삭제
			// 선택된 행과 열의 값 
			int row = jt.getSelectedRow();
			System.out.println("선택행 : " + row);
			
			Object obj = jt.getValueAt(row, 0);	// 행 열에 해당하는 Value
			System.out.println("값" + obj);
			
			if (dao.memberDelete(obj.toString()) > 0) {
				MemberJDailogGUI.messageBox(this, "레코드가 삭제되었습니다.");
				
				// 리스트 갱신
				dao.memberSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
				
			} else {
				MemberJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
			}
			
		} else if (e.getSource() == quit) {		// 메뉴 > 종료
			System.exit(0);
			
		} else if (e.getSource() == search) {	// 검색 버튼 클릭
			String fieldName = combo.getSelectedItem().toString();
			System.out.println("필드명 : " + fieldName);
			
			if (fieldName.trim().equals("ALL")) {
				dao.memberSelectAll(dt);
				if (dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);
			} else {
				if (jtf.getText().trim().equals("")) {
					MemberJDailogGUI.messageBox(this, "검색할 단어를 입력해주세요.");
					jtf.requestFocus();
				} else {	// 검색어 입력
					dao.getMemberSearch(dt, fieldName, jtf.getText());
					if (dt.getRowCount() > 0)
						jt.setRowSelectionInterval(0, 0);
				}
			}
		}	
	}
}
