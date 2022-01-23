package oneteampos.main;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import oneteampos.login.components.LoginJPanel;
import oneteampos.login.components.LoginStaffIDJLabel;
import oneteampos.login.components.LoginStaffNameJLabel;
import oneteampos.main.mainContainer.MainPanel;

public class MainFrame extends JFrame {
	
	public final static int FRAME_WIDTH = 1280;
	public final static int FRAME_HEIGHT = 720;
	
	private MainPanel mainPanel;
	private LoginStaffIDJLabel loginStfIdLabel;
	private LoginStaffNameJLabel loginStfNameLabel;
	
	public MainFrame() {
		this.mainPanel = new MainPanel(this);
		this.loginStfIdLabel = new LoginStaffIDJLabel();
		this.loginStfNameLabel = new LoginStaffNameJLabel();
		
		settings();
		setFaviCon();
	}

	private void settings() {
		setTitle("OneTeamPos");
		setVisible(true);
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public MainPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public LoginStaffIDJLabel getLoginStaffIDJLabel() {
		return this.loginStfIdLabel;
	}
	
	public LoginStaffNameJLabel getStaffNameJLabel() {
		return this.loginStfNameLabel;
	}
	
	public static void main(String[] args) {
		MainFrame mframe = new MainFrame();
		try {
			new LoginJPanel(mframe);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setFaviCon() {
		Image image = null; 
		try { 
			image = ImageIO.read(new File("image\\icon4.png")); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		this.setIconImage(image);

	}
}
