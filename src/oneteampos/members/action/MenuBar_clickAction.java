package oneteampos.members.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import oneteampos.members.MemberDefaultJTableDAO;
import oneteampos.members.MemberJDialogGUI;
import oneteampos.members.MemberJPanel;

public class MenuBar_clickAction implements ActionListener {
	
	private MemberJPanel mjp;
	private MemberDefaultJTableDAO dao;
	
	public MenuBar_clickAction(MemberJPanel mjp, MemberDefaultJTableDAO dao) {
		this.mjp = mjp;
		this.dao = dao;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menu = (JMenuItem)e.getSource();
		
		if (menu == mjp.getInsert()) {			// 메뉴 > 등록
			new MemberJDialogGUI(mjp, "회원등록");
			
		} else if (menu == mjp.getUpdate()) {	// 메뉴 > 수정
			new MemberJDialogGUI(mjp, "정보수정");
			
		} else if (menu == mjp.getDelete()) {	// 메뉴 > 삭제
			// 선택된 행과 열의 값 
			int row = mjp.getJt().getSelectedRow();
//			System.out.println("선택행 : " + row);
			
			Object obj = mjp.getJt().getValueAt(row, 0);	// 행 열에 해당하는 Value
//			System.out.println("값" + obj);
			
			if (dao.memberDelete(obj.toString()) > 0) {
				MemberJDialogGUI.messageBox(null, "레코드가 삭제되었습니다.");
				
				// 리스트 갱신
				dao.memberSelectAll(mjp.getDt());
				if (mjp.getDt().getRowCount() > 0)
					mjp.getJt().setRowSelectionInterval(0, 0);
				
			} else {
				MemberJDialogGUI.messageBox(null, "레코드가 삭제되지 않았습니다.");
			}
		}	
	}

}
