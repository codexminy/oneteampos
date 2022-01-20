package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.container.Member_inquiryDialog;

public class Member_searchAction extends MouseAdapter {

	Member_inquiryDialog mcd;
	
	public Member_searchAction(Member_inquiryDialog mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mcd.searchData(mcd.getItem(), mcd.getSearchField().getText(), mcd.getModel(), mcd.getTable());
	}
	
}
