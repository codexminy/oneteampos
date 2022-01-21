package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.etc.ChangeString;

public class MenuDetail_minusAction implements ActionListener {

	private MenuDetail_dialog mdd;
	
	public MenuDetail_minusAction(MenuDetail_dialog mdd) {
		this.mdd = mdd;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		ArrayList<JLabel> titleList = mdd.getTitleList();
		ArrayList<JLabel> amountLabels = mdd.getAmountLabels();
		int sum = Integer.parseInt(ChangeString.setErase(titleList.get(titleList.size()-1).getText()));
		
		for(int i=0; i<amountLabels.size(); ++i) {
			if(btn.getName().equals(mdd.getExtraLabels().get(i).getText())) {
				JLabel label = amountLabels.get(i);
				int cnt = Integer.parseInt(label.getText());
				int sum2 = Integer.parseInt(ChangeString.setErase(mdd.getExtraAmountLabels().get(i).getText()));
				
				if(Integer.parseInt(label.getText()) > 0) {
					titleList.get(titleList.size()-1).setText(ChangeString.setCashMark(sum-sum2));
					label.setText((cnt - 1) + "");
				}
			}
		}
	}

}
