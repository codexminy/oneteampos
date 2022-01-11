package oneteampos.login.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import oneteampos.login.actions.LoginNumberkeyAction;
import oneteampos.main.MainFrame;


public class LoginJPanel extends JPanel{
	
	MainFrame mainframe;
	
	public LoginJPanel(MainFrame mframe) {
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
		JLabel logoLabel = new JLabel("OneTeamPos");
		logoLabel.setFont(new Font("고딕", Font.BOLD, 45));
		logoLabel.setBounds(480, 150, 1000, 100);
		this.add(logoLabel);
		
		// 안내 문구 라벨
		JLabel enterLabel = new JLabel("Please enter your PIN number");
		enterLabel.setFont(new Font("고딕", Font.PLAIN, 15));
		enterLabel.setBounds(520, 240, 1000, 20);
		this.add(enterLabel);
		
		// 입력 받은 라벨 만들기 
		int lx = 0;
		JLabel[] inputLabel = new JLabel[4];
		for(int i = 0 ; i < inputLabel.length; ++i) {
			inputLabel[i] = new JLabel("○");
			inputLabel[i].setBounds(515 + 60 * lx++, 280, 60, 60);
			inputLabel[i].setFont(new Font("돋움", Font.PLAIN, 35));
			this.add(inputLabel[i]);
		}
		
		// cnt 라벨
		JLabel cntLabel = new JLabel("0");
		
		// clear 버튼 
		JButton clearBtn = new JButton("clear");
		this.add(clearBtn);
		clearBtn.setBounds(480, 550, 100, 60);
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
		JButton backBtn = new JButton("<");
		this.add(backBtn);
		backBtn.setBounds(680, 550, 100, 60);
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
		
		JButton[] keyboard = new JButton['9' - '0' +1];
		for(int i = 0 ; i  <= 9 ; ++i) {
			keyboard[i] = new JButton(i +"");
			if(i == 0) {
				keyboard [i].setBounds(580 , 550 , 100, 60);
			} else {
				keyboard [i].setBounds(480 + 100 * x ++ , 550 - (y * 60) , 100, 60);
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
