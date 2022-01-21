package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.etc.ChangeString;

public class MenuDetail_sizeRadioAction implements ActionListener {

	private MenuDetail_dialog mdd;
	
	public MenuDetail_sizeRadioAction(MenuDetail_dialog mdd) {
		this.mdd = mdd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton btn = (JRadioButton)e.getSource();
		ArrayList<JLabel> titleList = mdd.getTitleList();
		ArrayList<JRadioButton> sizeBtns = mdd.getSizeBtns();
		int nowSum = Integer.parseInt(ChangeString.setErase(titleList.get(titleList.size()-1).getText()));
		int nowAmount = Integer.parseInt(btn.getName());

		for(int i=0; i<sizeBtns.size(); ++i) {
			if(!mdd.getisSelect()[i] && sizeBtns.get(i).isSelected()) {
				titleList.get(titleList.size()-1).setText(ChangeString.setCashMark(nowSum+nowAmount));
				mdd.getisSelect()[i] = true;
			}
		}
		
		if(btn.isSelected()) {
			for(int i=0; i<sizeBtns.size(); ++i) {
				if(btn.getName().equals(sizeBtns.get(i).getName())) continue;
				if(mdd.getisSelect()[i]) {
					int prevAmount = Integer.parseInt(sizeBtns.get(i).getName());
					titleList.get(titleList.size()-1).setText(ChangeString.setCashMark(nowSum-prevAmount));
					int prevSum = Integer.parseInt(ChangeString.setErase(titleList.get(titleList.size()-1).getText()));
					titleList.get(titleList.size()-1).setText(ChangeString.setCashMark(prevSum+nowAmount));
					mdd.getisSelect()[i] = false;
				}
			}
		}
	}
}
