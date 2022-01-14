package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import oneteampos.menu.container.MenuDetailsPanel;
import oneteampos.menu.data.MenuData;

public class MenuDetailsChkBoxAction implements ActionListener {

	private MenuDetailsPanel details;
	private ArrayList<MenuData> lists;
	
	public MenuDetailsChkBoxAction(MenuDetailsPanel details, ArrayList<MenuData> lists) {
		this.details = details;
		this.lists = lists;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox chkBox = (JCheckBox)e.getSource();
		
		
		JLabel label = details.getTitleList().get(details.getTitleList().size()-1);
		
		int origin = Integer.parseInt(label.getText());
		int sum = Integer.parseInt(label.getText());
		
		if(chkBox.isSelected()) {
			for(int i=0; i<lists.size(); ++i) {
				if(chkBox.getText().equals(lists.get(i).getMenuName())) {
					label.setText((sum + lists.get(i).getPrice())+"");
				}
			}
		} else {
			for(int i=0; i<lists.size(); ++i) {
				if(chkBox.getText().equals(lists.get(i).getMenuName())) {
					label.setText((sum-lists.get(i).getPrice())+"");
				}
//				if(Integer.)
			}
		}
	}

}
