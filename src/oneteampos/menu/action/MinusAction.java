package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import oneteampos.menu.container.MenuDetailsPanel;

public class MinusAction implements ActionListener {

	private MenuDetailsPanel details;
	
	public MinusAction(MenuDetailsPanel details) {
		this.details = details;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		int sum = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
		
		for(int i=0; i<details.getAmountLabels().size(); ++i) {
			if(btn.getName().equals(details.getExtraLabels().get(i).getText())) {
				JLabel label = details.getAmountLabels().get(i);
				int cnt = Integer.parseInt(label.getText());
				int sum2 = Integer.parseInt(details.getExtraAmountLabels().get(i).getText().substring(2));
				
				if(Integer.parseInt(label.getText()) > 0) {
					details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - sum2) + "");
					label.setText((cnt - 1) + "");
				}

			}
		}
	}

}
