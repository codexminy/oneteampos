package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.component.All_comboBox;
import oneteampos.menu.container.Member_inquiryDialog;

public class Member_searchAction extends MouseAdapter {

	private Member_inquiryDialog mcd;
	private All_comboBox combo;
	
	public Member_searchAction(Member_inquiryDialog mcd, All_comboBox combo) {
		this.mcd = mcd;
		this.combo = combo;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mcd.searchData(combo.getSelectedItem().toString(), mcd.getSearchField().getText(), mcd.getModel(), mcd.getTable());
	}
	
}
