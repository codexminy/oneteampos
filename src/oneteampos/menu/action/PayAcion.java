package oneteampos.menu.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import oneteampos.main.MainFrame;
import oneteampos.menu.component.PaymentDialog;
import oneteampos.menu.container.MenuRightPanel;

public class PayAcion extends MouseAdapter {

	MainFrame mainFrame;
	MenuRightPanel mrp;
	
	public PayAcion(MainFrame mainFrame, MenuRightPanel mrp) {
		this.mainFrame = mainFrame;
		this.mrp = mrp;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mrp.getCart().getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
		} else {
			new PaymentDialog(mainFrame, mrp);
		}
	}
	
}
