package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.etc.ChangeString;

public class MenuDetail_cntMinusAction extends MouseAdapter {
	
	private MenuDetail_dialog mdd;
	
	public MenuDetail_cntMinusAction(MenuDetail_dialog mdd) {
		this.mdd = mdd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int sum =ChangeString.setErase(mdd.getTitleList().get(5).getText());
		int cnt2 = Integer.parseInt(mdd.getCnt().getText());
		mdd.getCnt().setText((cnt2 + 1) + "");
		mdd.getTitleList().get(5).setText(ChangeString.setCashMark(sum+mdd.getOriginPrice()));
	}
}
