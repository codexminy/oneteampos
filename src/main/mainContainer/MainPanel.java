package main.mainContainer;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainFrame;
import main.mainComponent.MainButton;

public class MainPanel {
	
	private final static String[] mainNames = new String[] {"메뉴", "매출", "발주", "사원"};
	private MainFrame mainFrame;
//	private MenuPanel menuPanel;
	private JPanel cardPanel;
	private JPanel gridPanel;
	
	public MainPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
//		this.menuPanel = new MenuPanel(mainFrame);
		this.cardPanel = new JPanel(new CardLayout());
		this.gridPanel = new JPanel(new GridLayout());
		
		createMainBtns();

		cardPanel.add(gridPanel);
//		cardPanel.add(mainNames[0], menuPanel.getPanel());
		cardPanel.setVisible(false);
	}
	
	private void createMainBtns() {
		for(int i=0; i<mainNames.length; ++i) {
			gridPanel.add(new MainButton(mainNames[i], mainFrame));
		}
	}
	
	public JPanel getCardPanel() {
		return this.cardPanel;
	}

	public void setVisibleTrue() {
		cardPanel.setVisible(true);
	}
	
	public void setVisibleFalse() {
		cardPanel.setVisible(false);
	}
}
