package oneteampos.members;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oneteampos.main.MainFrame;

public class MemberJDialogGUI extends JDialog implements ActionListener {

	// 회원정보관리창
	private static MainFrame mainFrame;
	
	JPanel pw = new JPanel(new GridLayout(6,1,0,40));
    JPanel pc = new JPanel(new GridLayout(6,1,0,40));
    JPanel ps = new JPanel();
    JPanel title = new JPanel();
 
    JLabel label_member_id = new JLabel("멤 버 I D");
    JLabel label_phone_number = new JLabel("전화번호");
    JLabel label_name = new JLabel("이      름");
    JLabel label_grade_id = new JLabel("등      급");
    JLabel label_sum_amount = new JLabel("누적금액");
    JLabel label_point = new JLabel("포  인  트");
    
    JLabel tt = new JLabel("회 원 정 보 관 리");
 
    RoundJTextField member_id = new RoundJTextField(15);
    RoundJTextField phone_number = new RoundJTextField(15);
    RoundJTextField name = new RoundJTextField(15);
    RoundJTextField grade_id = new RoundJTextField(15);
    RoundJTextField sum_amount = new RoundJTextField(15);
    RoundJTextField point = new RoundJTextField(15);
   
    RoundedButton confirm;
    RoundedButton reset = new RoundedButton("취소");
 
    MemberJPanel me;
 
	JPanel phone_numberCkP =new JPanel(new BorderLayout(10, 0));
	RoundedButton phone_numberCkBtn = new RoundedButton("중복확인");

	MemberDefaultJTableDAO dao = new MemberDefaultJTableDAO();
	
	public MemberJDialogGUI(MemberJPanel me, String index) {
		this.me = me;
		int num = 0;
				
		if(index.equals("회원등록")) {
			confirm = new RoundedButton(index);
			member_id.setEditable(false);
		} else {
			confirm = new RoundedButton("정보수정");
			
			int row = me.jt.getSelectedRow();	// 선택된 행
			member_id.setText(me.jt.getValueAt(row, 0).toString());
			phone_number.setText(me.jt.getValueAt(row, 1).toString());
			name.setText(me.jt.getValueAt(row, 2).toString());
			// 등급 다시 ID로 받기
			if(me.jt.getValueAt(row ,3).equals("silver")){
				   num = 1;
				} else if(me.jt.getValueAt(row ,3).equals("gold")){
					num = 2;
				} else if(me.jt.getValueAt(row ,3).equals("vip")) {
					num = 3;
				} else if(me.jt.getValueAt(row ,3).equals("vvip")) {
					num = 4;
				}
			grade_id.setText(num+"");
			
			//grade_id.setText(me.jt.getValueAt(row, 3).toString());
			sum_amount.setText(me.jt.getValueAt(row, 4).toString());
			point.setText(me.jt.getValueAt(row, 5).toString());
			
			// member_id 텍스트박스 비활성
			member_id.setEditable(false);
			// phone_numberCkBtn 버튼 활성화
			phone_numberCkBtn.setEnabled(true);
		}
		
		title.add(tt);
		tt.setFont(new Font("나눔스퀘어 ExtraBold", Font.BOLD, 18));
				
		pw.add(label_member_id);
		label_member_id.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pw.add(label_phone_number);
		label_phone_number.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pw.add(label_name);
		label_name.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pw.add(label_grade_id);
		label_grade_id.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pw.add(label_sum_amount);
		label_sum_amount.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		pw.add(label_point);
		label_point.setFont(new Font("나눔스퀘어", Font.BOLD, 14));
		
		tt.setBorder(BorderFactory.createEmptyBorder(30,20,0,0));
		pw.setBorder(BorderFactory.createEmptyBorder(30,20,10,20));
		pc.setBorder(BorderFactory.createEmptyBorder(30,0,10,30));
		ps.setBorder(BorderFactory.createEmptyBorder(10,10,30,10));
		
		phone_numberCkP.add(phone_number, "Center");
		phone_numberCkP.add(phone_numberCkBtn, "East");
		
		pc.add(member_id);
		pc.add(phone_numberCkP);
		pc.add(name);
		pc.add(grade_id);
		pc.add(sum_amount);
		pc.add(point);
		
		ps.add(confirm);
		ps.add(reset);
		
		add(tt,"North");
		add(pw,"West");
        add(pc,"Center");
        add(ps,"South");
       
        setSize(400,550);
        setResizable(false);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        // 이벤트 등록
        confirm.addActionListener(this);
        reset.addActionListener(this);
        phone_numberCkBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
		
		if (btnLabel.equals("회원등록")) {
			if (dao.memberInsert(this) > 0) { // 가입 성공
				messageBox(this, name.getText() + "님의 등록이 완료되었습니다.");
				dispose();
				
				dao.memberSelectAll(me.dt);		// 모든 레코드 가져와서 올리기
				
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);	// 첫번째 행 선택
			} else {
				messageBox(this, "등록에 실패하였습니다.");
			}
			
		} else if (btnLabel.equals("정보수정")){
			if (dao.memberUpdate(this) > 0) {
				messageBox(this,"수정이 완료되었습니다.");
				dispose();
				
				dao.memberSelectAll(me.dt);
				
				if (me.dt.getRowCount() > 0)
					me.jt.setRowSelectionInterval(0, 0);
			} else {
				messageBox(this, "수정에 실패하였습니다.");
			}
			
		} else if (btnLabel.equals("취소")) {
			dispose();
			
		} else if (btnLabel.equals("중복확인")) {
			
			if (phone_number.getText().trim().equals("")) {
				messageBox(this, "전화번호를 입력해주세요.");
				phone_number.requestFocus();
			} else {
				if (dao.getPhoneNumChk(phone_number.getText())) {	// 중복X (사용가능)
					messageBox(this, phone_number.getText() + "은(는) 사용 가능합니다.");
				} else {	// 중복 (사용불가)
					messageBox(this, phone_number.getText() + "은(는) 중복입니다. 다른 번호를 입력해주세요.");
					
					phone_number.setText("");
					phone_number.requestFocus();					
				}
			}
		}		
	}
	
	// 메세지 박스
	public static void messageBox(Object obj , String message) {
		JOptionPane.showMessageDialog((Component)obj , message);
	}			
}
