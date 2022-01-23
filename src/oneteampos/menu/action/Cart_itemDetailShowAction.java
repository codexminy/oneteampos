package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.data.MenuData;
import oneteampos.menu.etc.ChangeString;

public class Cart_itemDetailShowAction extends MouseAdapter {

	private MainFrame mainFrame;
	
	public Cart_itemDetailShowAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTable cart = (JTable)e.getSource();
		HashMap<String, String> cartData = mainFrame.getMainPanel().getMenuPanel().getLeftPanel().getCartData();
		Vector<Vector<Object>> menuCntList = mainFrame.getMainPanel().getMenuPanel().getRightPanel().getMenuCntList();
		ArrayList<MenuData> menuList = mainFrame.getMainPanel().getMenuPanel().getLeftPanel().getCafeMenuData().getMenuData();
		MenuRightPanel rp = mainFrame.getMainPanel().getMenuPanel().getRightPanel();
		
		int row = cart.getSelectedRow();
		
		String temp = cart.getValueAt(row, 0).toString().substring(0,3);
		String name = cart.getValueAt(row, 0).toString().substring(3);
		String size = cart.getValueAt(row, 1).toString();
		
		int price = ChangeString.setErase(cart.getValueAt(row, 2).toString());
		int originPrice = 0;
		
		if(!rp.getIsDiscnt()) {
			if(cart.getSelectedColumn() != cart.getColumnCount()-1 && cart.getRowCount() > 0) {
				
				for(String key : cartData.keySet()) {
					if(key.equals(name)) {
						originPrice = ChangeString.setErase(cartData.get(key));
						break;
					}
				}

				MenuDetail_dialog mdd = new MenuDetail_dialog(mainFrame, name, price, menuList);
				
				mdd.setTitle("메뉴 정보 수정");
				mdd.setOriginPrice(originPrice);
				mdd.setCnt(menuCntList.get(row).get(0)+"");
				mdd.setIsClick(true);
				mdd.getBtnPanel().removeAll();
				mdd.getBtnPanel().add(mdd.getUpdateBtn());
				
				btnSelect(mdd.getTempBtns(), temp);
				btnSelect(mdd.getSizeBtns(), size);

				for(int i=3; i<menuCntList.get(row).size(); ++i) {
					mdd.getAmountLabels().get(i-3).setText(menuCntList.get(row).get(i)+"");
				}
				
				for(int i=0; i<mdd.getSizeBtns().size(); ++i) {
					if(!mdd.getisSelect()[i] && mdd.getSizeBtns().get(i).isSelected()) {
						mdd.getisSelect()[i] = true;
					}
				}

				mdd.setVisible(true);
			}
		}
	}
	
	private void btnSelect(ArrayList<JRadioButton> btns, String condition) {
		for(JRadioButton rb : btns) {
			if(rb.getText().equals(condition)) {
				rb.setSelected(true);
			}
		}
	}
	
}
