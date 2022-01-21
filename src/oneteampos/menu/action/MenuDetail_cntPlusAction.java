package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.etc.ChangeString;

public class MenuDetail_cntPlusAction extends MouseAdapter {
	
	private MenuDetail_dialog mdd;
	
	public MenuDetail_cntPlusAction(MenuDetail_dialog mdd) {
		this.mdd = mdd;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int cnt2 = Integer.parseInt(mdd.getCnt().getText());
		int sum = Integer.parseInt(ChangeString.setErase(mdd.getTitleList().get(5).getText()));
		if(cnt2 > 1) {
			mdd.getTitleList().get(5).setText(ChangeString.setCashMark(sum-mdd.getOriginPrice()));
			mdd.getCnt().setText((cnt2 - 1) + "");
		}
	}
	
}
