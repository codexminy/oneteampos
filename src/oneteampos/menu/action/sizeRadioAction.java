package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import oneteampos.menu.container.MenuDetailsPanel;

public class sizeRadioAction implements ActionListener {

	private MenuDetailsPanel details;
	
	public sizeRadioAction(MenuDetailsPanel details) {
		this.details = details;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JRadioButton btn = (JRadioButton)e.getSource();
		int nowSum = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
		int nowAmount = Integer.parseInt(btn.getName());

		for(int i=0; i<details.getSizeBtns().size(); ++i) {
			if(!details.getisSelect()[i] && details.getSizeBtns().get(i).isSelected()) {
				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (nowSum + nowAmount) + "");
				details.getisSelect()[i] = true;
			}
		}
		
		if(btn.isSelected()) {
			for(int i=0; i<details.getSizeBtns().size(); ++i) {
				if(btn.getName().equals(details.getSizeBtns().get(i).getName())) continue;
				if(details.getisSelect()[i]) {
					int prevAmount = Integer.parseInt(details.getSizeBtns().get(i).getName());
					details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (nowSum - prevAmount) + "");
					int prevSum = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
					details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (prevSum + nowAmount) + "");
					details.getisSelect()[i] = false;
				}
			}
		}
	}
}
