package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.etc.ChangeStr;

public class MenuDetail_minusAction implements ActionListener {

	private MenuDetail_dialog details;
	
	public MenuDetail_minusAction(MenuDetail_dialog details) {
		this.details = details;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
//		int sum = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
		int sum = Integer.parseInt(ChangeStr.setErase(details.getTitleList().get(details.getTitleList().size()-1).getText()));
		
		for(int i=0; i<details.getAmountLabels().size(); ++i) {
			if(btn.getName().equals(details.getExtraLabels().get(i).getText())) {
				JLabel label = details.getAmountLabels().get(i);
				int cnt = Integer.parseInt(label.getText());
//				int sum2 = Integer.parseInt(details.getExtraAmountLabels().get(i).getText().substring(2));
				int sum2 = Integer.parseInt(ChangeStr.setErase(details.getExtraAmountLabels().get(i).getText()));
				
				if(Integer.parseInt(label.getText()) > 0) {
//					details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - sum2) + "");
					details.getTitleList().get(details.getTitleList().size()-1).setText(ChangeStr.setCashMark(sum-sum2));
					label.setText((cnt - 1) + "");
				}

			}
		}
	}

}
