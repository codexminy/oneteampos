package oneteampos.main.mainAction;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import oneteampos.main.MainFrame;
import oneteampos.order.cotainer.OrderJPanel;
import oneteampos.staff.containers.LoginStaffJPanel;
import oneteampos.staff.containers.StaffJPanel;



public class MainButtonEnterAction implements ActionListener {
	
	private MainFrame mainFrame;
	
	public MainButtonEnterAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		CardLayout card = (CardLayout) mainFrame.getMainPanel().getCardPanel().getLayout();
		card.show(mainFrame.getMainPanel().getCardPanel(), btn.getText());
		
//		if(btn.getText().equals("회원")) {
//			mainFrame.getMainPanel().getMember().setVisible(true);
//		}	
		if(e.toString().contains("발주")) {
			mainFrame.getMainPanel().setVisibleFalse();
			new OrderJPanel(mainFrame);
		} else if((btn.getText().equals("사원"))){
			mainFrame.getMainPanel().setVisibleFalse();
			new LoginStaffJPanel(mainFrame);
		}

		
	}
	
}
