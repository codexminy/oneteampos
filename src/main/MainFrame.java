package main;

import javax.swing.JFrame;

import main.mainContainer.MainPanel;

public class MainFrame extends JFrame {
	
	public final static int FRAME_WIDTH = 1280;
	public final static int FRAME_HEIGHT = 720;
	
	private MainPanel mainPanel;
	
	public MainFrame() {
		this.mainPanel = new MainPanel(this);
		add(mainPanel.getCardPanel());
		settings();
	}

	private void settings() {
		setTitle("팀 프로젝트 테스트");
		setVisible(true);
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public MainPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
