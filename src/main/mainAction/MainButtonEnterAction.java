package main.mainAction;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.MainFrame;

public class MainButtonEnterAction implements ActionListener {
	
	private MainFrame mainFrame;
	
	public MainButtonEnterAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		JButton btn = (JButton)e.getSource();
//		CardLayout card = (CardLayout) mainFrame.getMainPanel().getCardPanel().getLayout();
//		card.show(mainFrame.getMainPanel().getCardPanel(), btn.getText());
	}
	
}
