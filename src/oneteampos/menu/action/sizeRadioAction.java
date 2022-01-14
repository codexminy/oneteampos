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
			if(details.getSizeCnt()[i] == 0 && details.getSizeBtns().get(i).isSelected()) {
				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (nowSum + nowAmount) + "");
				details.getSizeCnt()[i]++;
			}
		}
		
		if(btn.isSelected()) {
			for(int i=0; i<details.getSizeBtns().size(); ++i) {
				if(btn.getName().equals(details.getSizeBtns().get(i).getName())) continue;
				if(details.getSizeCnt()[i] > 0) {
					int prevAmount = Integer.parseInt(details.getSizeBtns().get(i).getName());
					details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (nowSum - prevAmount) + "");
					int prevSum = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
					details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (prevSum + nowAmount) + "");
					details.getSizeCnt()[i] = 0;
				}
			}
		}

//		if(details.getSizeBtns().get(0).isSelected()) {
//			if(details.cnt[1] > 0) {
//				int a = Integer.parseInt(details.getSizeBtns().get(1).getName());
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - a) + "");
//				int sum2 = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum2 + amount) + "");
//				details.cnt[1] = 0;
//			} else if(details.cnt[2] > 0) {
//				int a = Integer.parseInt(details.getSizeBtns().get(2).getName());
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - a) + "");
//				int sum2 = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum2 + amount) + "");
//				details.cnt[2] = 0;
//			}
//		}
//		
//		if(details.getSizeBtns().get(1).isSelected()) {
//			if(details.cnt[0] > 0) {
//				int a = Integer.parseInt(details.getSizeBtns().get(0).getName());
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - a) + "");
//				int sum2 = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum2 + amount) + "");
//				details.cnt[0] = 0;
//			} else if(details.cnt[2] > 0) {
//				int a = Integer.parseInt(details.getSizeBtns().get(2).getName());
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - a) + "");
//				int sum2 = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum2 + amount) + "");
//				details.cnt[2] = 0;
//			}
//		}
//		
//		if(details.getSizeBtns().get(2).isSelected()) {
//			if(details.cnt[0] > 0) {
//				int a = Integer.parseInt(details.getSizeBtns().get(0).getName());
//				System.out.println(a);
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - a) + "");
//				int sum2 = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum2 + amount) + "");
//				details.cnt[0] = 0;
//			} else if(details.cnt[1] > 0) {
//				int a = Integer.parseInt(details.getSizeBtns().get(1).getName());
//				System.out.println(a);
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum - a) + "");
//				int sum2 = Integer.parseInt(details.getTitleList().get(details.getTitleList().size()-1).getText().substring(4));
//				details.getTitleList().get(details.getTitleList().size()-1).setText("￦   " + (sum2 + amount) + "");
//				details.cnt[1] = 0;
//			}
//		}
	}
}
