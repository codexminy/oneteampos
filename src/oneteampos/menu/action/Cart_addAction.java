package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeStr;

public class Cart_addAction implements ActionListener {
	
	private MainFrame mainFrame;
	private MenuDetail_dialog mdp;
	
	public Cart_addAction(MainFrame mainFrame, MenuDetail_dialog mdp) {
		this.mainFrame = mainFrame;
		this.mdp = mdp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showOptionDialog(null, MenuDetail_dialog.MESSAGE, MenuDetail_dialog.TITLE, 0, JOptionPane.QUESTION_MESSAGE, null, MenuDetail_dialog.OPTIONS, MenuDetail_dialog.OPTIONS[0]);
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		
		if(result == JOptionPane.YES_OPTION) {
			
			if(rp.getIsDiscnt()) {
				JOptionPane.showMessageDialog(null, "할인&적립을 취소해주세요!", "Message", JOptionPane.WARNING_MESSAGE);
				mdp.dispose();
			} else {
				ArrayList<MenuData> menuList = mdp.getMenuData();
				Vector<Object> list = new Vector<>();
				
				String word = "";
				
				Vector<Object> innerId = new Vector<>();
				Vector<Object> innerCnt = new Vector<>();
				
				for(int i=0; i<menuList.size(); ++i) {
					if(mdp.getTitleList().get(1).getText().equals(menuList.get(i).getMenuName())) {
						innerId.add(menuList.get(i).getMenuId());
						innerCnt.add(mdp.cnt.getText());
					}
				}
				
				for(JRadioButton rb : mdp.getTempBtns()) {
					if(rb.isSelected()) {
//					word += rb.getText();
						list.add(rb.getText() + mdp.getTitleList().get(1).getText());
						
						for(int i=0; i<menuList.size(); ++i) {
							if(rb.getText().equals(menuList.get(i).getMenuName())) {
								innerId.add(menuList.get(i).getMenuId());
								innerCnt.add(mdp.cnt.getText());
							}
						}
					}
				}
				
				word += mdp.getTitleList().get(1).getText();
				
				for(JRadioButton rb : mdp.getSizeBtns()) {
					if(rb.isSelected()) {
//					word += String.format(" / %s", rb.getText());
						list.add(rb.getText());
						
						for(int i=0; i<menuList.size(); ++i) {
							if(rb.getText().equals(menuList.get(i).getMenuName())) {
								innerId.add(menuList.get(i).getMenuId());
								innerCnt.add(mdp.cnt.getText());
							}
						}
					}
				}
				
				for(int i=0; i<mdp.getAmountLabels().size(); ++i) {
					if((!mdp.getAmountLabels().get(i).getText().equals("0"))) {
						for(int j=0; j<menuList.size(); ++j) {
							if(mdp.getExtraLabels().get(i).getText().equals(menuList.get(j).getMenuName())) {
								innerId.add(menuList.get(j).getMenuId());
								innerCnt.add(mdp.getAmountLabels().get(i).getText());
							}
						}
					}
				}
				
				
				
				rp.getMenuIdList().add(innerId);
				rp.getMenuCntList().add(innerCnt);
				
//			for(int i=0; i<mdp.getExtraLabels().size(); ++i) {
//				if(!mdp.getAmountLabels().get(i).getText().equals("0")) {
//					word += " / 추가 있음";
//					list.add("추가 있음");
//					break;
//				} else {
//					list.add("추가 없음");
//				}
//			}
				
				word += " " + mdp.getTitleList().get(5).getText();
				list.add(mdp.getTitleList().get(5).getText());
				int sum = Integer.parseInt(ChangeStr.setErase(mdp.getTitleList().get(5).getText()));
				int total = Integer.parseInt(ChangeStr.setErase(rp.getTotalPrice().getText()));
//				int sum = Integer.parseInt(mdp.getTitleList().get(5).getText().replaceAll("\\D", ""));
//				int total = Integer.parseInt(rp.getTotalPrice().getText().replaceAll("\\D", ""));
				
				rp.getTotalPrice().setVisible(true);
				rp.getTotalPrice().setText(ChangeStr.setCashMark(sum+total));
				

//			JPanel p = new JPanel();
				list.add(mdp.cnt.getText());
				
//			p.add(new JLabel(word));
//			p.add(new ItemChkBox());
				
//				rp.getCartPanel().add(new JLabel(word));
				
				rp.getModel().addRow(list);
				
				
				
				
				JOptionPane.showMessageDialog(null, "메뉴가 추가되었습니다.");
				

				
				mdp.getTempBtns().get(0).setSelected(true);
				mdp.getSizeBtns().get(0).setSelected(true);
				for(int i=0; i<mdp.getAmountLabels().size(); ++i) {
					mdp.getAmountLabels().get(i).setText("0");
				}
				mdp.cnt.setText("1");
//				mdp.getTitleList().get(5).setText("￦   " + mdp.getOriginPrice());
				mdp.getTitleList().get(5).setText(ChangeStr.setCashMark(mdp.getOriginPrice()));
				mdp.dispose();
				
				
			}
			
		}
	}
}


























