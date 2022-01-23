package oneteampos.menu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeString;

public class Cart_updateAction implements ActionListener {

	private MainFrame mainFrame;
	private MenuDetail_dialog mdp;
	
	public Cart_updateAction(MainFrame mainFrame, MenuDetail_dialog mdp) {
		this.mainFrame = mainFrame;
		this.mdp = mdp;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showOptionDialog(null, "메뉴를 수정하겠습니까?", "Message", 0, JOptionPane.QUESTION_MESSAGE, null, null, null);
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		
		if(result == JOptionPane.YES_OPTION) {
			ArrayList<MenuData> menuList = mdp.getMenuData();
			ArrayList<JLabel> titleList = mdp.getTitleList();
			ArrayList<JLabel> amountLabels = mdp.getAmountLabels();
			Vector<Object> list = new Vector<>();
			Vector<Object> innerId = new Vector<>();
			Vector<Object> innerCnt = new Vector<>();
			JLabel totalPrice = rp.getTotalPrice();
			String cnt = mdp.getCnt().getText();
			
			int row = rp.getCart().getSelectedRow();

			addCartData(innerId, innerCnt, menuList, titleList.get(1).getText(), cnt);

			for(JRadioButton rb : mdp.getTempBtns()) {
				if(rb.isSelected()) {
					list.add(rb.getText() + titleList.get(1).getText());
					addCartData(innerId, innerCnt, menuList, rb.getText(), cnt);
				}
			}

			for(JRadioButton rb : mdp.getSizeBtns()) {
				if(rb.isSelected()) {
					list.add(rb.getText());
					addCartData(innerId, innerCnt, menuList, rb.getText(), cnt);
				}
			}
			
			for(int i=0; i<amountLabels.size(); ++i) {
				if((!amountLabels.get(i).getText().equals("0"))) {
					addCartData(innerId, innerCnt, menuList, mdp.getExtraLabels().get(i).getText(), amountLabels.get(i).getText());
				}
			}
			
			rp.getMenuIdList().set(row, innerId);
			rp.getMenuCntList().set(row, innerCnt);

			list.add(titleList.get(5).getText());

			int sum = ChangeString.setErase(titleList.get(5).getText());
			int total = ChangeString.setErase(totalPrice.getText());

			totalPrice.setText(ChangeString.setCashMark(total - rp.getPrevSum().get(row) + sum));

			rp.getPrevSum().set(row, sum);

			list.add(cnt);
			
			rp.getModel().removeRow(row);
			rp.getModel().insertRow(row, list);
			
			JOptionPane.showMessageDialog(null, "메뉴가 수정되었습니다.");
			mdp.dispose();
		}
	}
	
	private void addCartData(Vector<Object> innerId, Vector<Object> innerCnt, ArrayList<MenuData> menuList, String condition, String text) {
		for(int j=0; j<menuList.size(); ++j) {
			if(condition.equals(menuList.get(j).getMenuName())) {
				innerId.add(menuList.get(j).getMenuId());
				innerCnt.add(text);
			}
		}
	}
	
}
