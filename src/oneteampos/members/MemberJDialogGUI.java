package oneteampos.members;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberJDialogGUI extends JDialog implements ActionListener {

	JPanel pw=new JPanel(new GridLayout(6,1));
    JPanel pc=new JPanel(new GridLayout(6,1));
    JPanel ps=new JPanel();
 
    JLabel label_member_id = new JLabel("멤버ID");
    JLabel label_phone_number = new JLabel("전화번호");
    JLabel label_name=new JLabel("이름");
    JLabel label_grade_id=new JLabel("등급");
    JLabel label_sum_amount=new JLabel("누적금액");
    JLabel label_point=new JLabel("포인트");
 
    JTextField member_id = new JTextField();
    JTextField phone_number = new JTextField();
    JTextField name = new JTextField();
    JTextField grade_id = new JTextField();
    JTextField sum_amount = new JTextField();
    JTextField point = new JTextField();
   
    JButton confirm;
    JButton reset=new JButton("취소");
 
    MemberJPanel me;
 
	JPanel phone_numberCkP =new JPanel(new BorderLayout());
	JButton phone_numberCkBtn = new JButton("중복확인");

	MemberDefaultJTableDAO dao = new MemberDefaultJTableDAO();
	
	public MemberJDialogGUI(MemberJPanel me, String index) {
		this.me = me;
		
		if(index.equals("회원등록")) {
			confirm = new JButton(index);
			member_id.setEditable(false);
		} else {
			confirm = new JButton("정보수정");
			
			int row = me.jt.getSelectedRow();	// 선택된 행
			member_id.setText(me.jt.getValueAt(row, 0).toString());
			phone_number.setText(me.jt.getValueAt(row, 1).toString());
			name.setText(me.jt.getValueAt(row, 2).toString());
			grade_id.setText(me.jt.getValueAt(row, 3).toString());
			sum_amount.setText(me.jt.getValueAt(row, 4).toString());
			point.setText(me.jt.getValueAt(row, 5).toString());
			
			// member_id 텍스트박스 비활성
			member_id.setEditable(false);
			// phone_numberCkBtn 버튼 활성화
			phone_numberCkBtn.setEnabled(true);
		}
		
		pw.add(label_member_id);
		pw.add(label_phone_number);
		pw.add(label_name);
		pw.add(label_grade_id);
		pw.add(label_sum_amount);
		pw.add(label_point);
		
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
		
		add(pw,"West");
        add(pc,"Center");
        add(ps,"South");
       
        setSize(300,500);
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
	public static void messageBox(MemberJDialogGUI obj, String message) {
		JOptionPane.showMessageDialog(null, message);
	}			
}

