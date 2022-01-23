package oneteampos.login.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oneteampos.login.actions.LoginNumberkeyAction;
import oneteampos.main.MainFrame;
import oneteampos.sales.containers.RoundedButton;


public class LoginJPanel extends JPanel{
	
	MainFrame mainframe;
	static ImageIcon logoicon;
	static JLabel[] inputLabel = new JLabel[4];
	static int lx = 0;
	
	static {
		try { // 로고이미지
			BufferedImage bufferedimg;
			bufferedimg = ImageIO.read(new File("image\\logo.png"));
			Image logoimg = bufferedimg.getScaledInstance(400, 60, Image.SCALE_SMOOTH);
			logoicon = new ImageIcon(logoimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 입력받은 라벨
		for(int i = 0 ; i < inputLabel.length; ++i) {
			inputLabel[i] = new JLabel("○");
			inputLabel[i].setBounds(515 + 60 * lx++, 240, 60, 60);
			inputLabel[i].setFont(new Font("돋움", Font.PLAIN, 35));
			
		}
	}
	
	
	public LoginJPanel(MainFrame mframe) throws IOException {
		
		this.mainframe = mframe;
		
		this.setBounds(0, 0, 1280, 720);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		// 메인 프레임에 로그인 판넬 추가
		mainframe.add(this);


		// 로그인 실패시 라벨 
		JLabel loginFailLabel = new JLabel("Please try again and check your PIN number");
		loginFailLabel.setFont(new Font("고딕", Font.BOLD, 25));
		loginFailLabel.setBounds(380, 50, 1000, 50);
		loginFailLabel.setForeground(Color.RED);
		this.add(loginFailLabel);
		loginFailLabel.setVisible(false);
		


		// 로고 라벨 
		JLabel logoLabel = new JLabel(logoicon);
		logoLabel.setFont(new Font("고딕", Font.BOLD, 45));
		logoLabel.setBounds(400, 120, 480, 100);
		this.add(logoLabel);
		
		
		
		// 안내 문구 라벨
		JLabel enterLabel = new JLabel("Please enter your PIN number");
		enterLabel.setFont(new Font("고딕", Font.PLAIN, 15));
		enterLabel.setBounds(520, 220, 1000, 20);
		this.add(enterLabel);
		
		// 입력 받은 라벨 만들기 
		for(int i = 0 ; i < inputLabel.length; ++i) {
			this.add(inputLabel[i]);
		}
		
		// cnt 라벨
		JLabel cntLabel = new JLabel("0");
		
		// clear 버튼 
		RoundedButton clearBtn = new RoundedButton("clear");
		this.add(clearBtn);
		clearBtn.setBounds(460, 550, 100, 60);
		clearBtn.setSelected(false);
		clearBtn.setBackground(Color.WHITE);
		clearBtn.setFont(new Font("고딕", Font.PLAIN, 20));
		// clear Action
		clearBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cntLabel.setText("0");
				for(int i = 0; i < inputLabel.length; ++i) {
					inputLabel[i].setText("○");
				}
			}
		});
		
		// 백스페이스 버튼 
		RoundedButton backBtn = new RoundedButton("<");
		this.add(backBtn);
		backBtn.setBounds(700, 550, 100, 60);
		backBtn.setSelected(false);
		backBtn.setBackground(Color.WHITE);
		backBtn.setFont(new Font("고딕", Font.PLAIN, 30));
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx = Integer.parseInt(cntLabel.getText())-1 < 0 ? 0 : Integer.parseInt(cntLabel.getText())-1;
				inputLabel[idx].setText("○");
				cntLabel.setText(idx+"");
			}
		});
	
		
		// 번호 패드 만들기 
		// 번호 패드 
		int x = 0;
		int y = 1;
		
		RoundedButton[] keyboard = new RoundedButton['9' - '0' +1];
		for(int i = 0 ; i  <= 9 ; ++i) {
			keyboard[i] = new RoundedButton(i +"");
			if(i == 0) {
				keyboard [i].setBounds(580 , 550 , 100, 60);
				keyboard [i].setSelected(false);
			} else {
				keyboard [i].setBounds(460 + 120 * x ++ , 550 - (y * 80) , 100, 60);
				keyboard [i].setSelected(false);
				if(i % 3 == 0) {
					x = 0;
					y ++;
				}
			}
			this.add(keyboard[i]);
			keyboard[i].setFont(new Font("고딕", Font.BOLD, 20));
			keyboard[i].setBackground(Color.WHITE);
		}
		
		// 번호패드 리스너 
		for(int i = 0; i < keyboard.length; ++i) {
			keyboard[i].addMouseListener(new LoginNumberkeyAction(mainframe ,this, inputLabel, keyboard[i], cntLabel, loginFailLabel));

		}
	}
	

			
}
