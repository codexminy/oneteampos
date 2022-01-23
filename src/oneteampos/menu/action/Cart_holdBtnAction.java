package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.Cart_holdPanel;
import oneteampos.menu.container.MenuLeftPanel;
import oneteampos.menu.container.MenuRightPanel;

public class Cart_holdBtnAction extends MouseAdapter {

	private MainFrame mainFrame;
	private MenuLeftPanel leftPanel;
	private Cart_holdPanel holdPanel;
	
	public Cart_holdBtnAction(MainFrame mainFrame, MenuLeftPanel leftPanel, Cart_holdPanel holdPanel) {
		this.mainFrame = mainFrame;
		this.leftPanel = leftPanel;
		this.holdPanel = holdPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		ArrayList<MenuRightPanel> mrp = holdPanel.getMrpList();

		mrp.add(new MenuRightPanel(mainFrame, leftPanel));
		
		for(int i=0; i<mrp.size(); ++i) {
			if(i != mrp.size()-1) {
				mrp.get(i).setVisible(false);
			} else {
				mainFrame.getMainPanel().getMenuPanel().add(mrp.get(i));
				mrp.get(i).setVisible(true);
			}
		}

		JOptionPane.showMessageDialog(null, "보류가 완료되었습니다.");
	}
	
}
