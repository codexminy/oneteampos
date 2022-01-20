package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuRightPanel;
import oneteampos.menu.container.Payment_dialog;

public class Payment_turnOnAcion extends MouseAdapter {

	MainFrame mainFrame;
	MenuRightPanel mrp;
	
	public Payment_turnOnAcion(MainFrame mainFrame, MenuRightPanel mrp) {
		this.mainFrame = mainFrame;
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mrp.getCart().getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			new Payment_dialog(mainFrame, mrp);
		}
	}
	
}
