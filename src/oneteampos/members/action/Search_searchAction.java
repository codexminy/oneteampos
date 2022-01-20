package oneteampos.members.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import oneteampos.members.MemberDefaultJTableDAO;
import oneteampos.members.MemberJDialogGUI;
import oneteampos.members.MemberJPanel;

public class Search_searchAction implements ActionListener{
	
	private MemberJPanel mjp;
	private MemberDefaultJTableDAO dao;
	
	public Search_searchAction(MemberJPanel mjp, MemberDefaultJTableDAO dao) {
		this.mjp = mjp;
		this.dao = dao;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mjp.getSearch()) {	// 검색 버튼 클릭
			String fieldName = mjp.getCombo().getSelectedItem().toString();
//			System.out.println("필드명 : " + fieldName);
			
			if (fieldName.trim().equals("ALL")) {
				dao.memberSelectAll(mjp.getDt());
				if (mjp.getDt().getRowCount() > 0)
					mjp.getJt().setRowSelectionInterval(0, 0);
			} else {
				if (mjp.getJtf().getText().trim().equals("")) {
					MemberJDialogGUI.messageBox(null, "검색할 단어를 입력해주세요.");
					mjp.getJtf().requestFocus();
				} else {	// 검색어 입력
					dao.getMemberSearch(mjp.getDt(), fieldName, mjp.getJtf().getText());
					if (mjp.getDt().getRowCount() > 0)
						mjp.getJt().setRowSelectionInterval(0, 0);
				}
			}
		}
	}
	
}
