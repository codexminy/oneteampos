package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.component.MemberCheckDialog;

public class searchBtnAction extends MouseAdapter {

	MemberCheckDialog mcd;
	
	public searchBtnAction(MemberCheckDialog mcd) {
		this.mcd = mcd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mcd.searchData(mcd.getItem(), mcd.getSearchField().getText(), mcd.getModel(), mcd.getTable());
	}
	
}
