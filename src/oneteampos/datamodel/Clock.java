package oneteampos.datamodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Clock extends JLabel{
	
	
	private boolean aa = true;
	private Thread t1;

	private JLabel lblCenter;

	Font font;
	Color color = (Color.WHITE);

	public void ThreadTime() {
		t1 = new Thread() {
			public void run() {

				while (aa) {
					Calendar cal = Calendar.getInstance();

					SimpleDateFormat fm = new SimpleDateFormat("yyyy년  MM월  dd일   HH:mm:ss");
					setText(fm.format(new Date()));

					try {

						Thread.sleep(1000);

					} catch (InterruptedException ie) {

						ie.printStackTrace();

						aa = false;

					}
				}

			}
		};
		t1.start();
	}

	public Clock() {

		font = new Font("나눔스퀘어", Font.PLAIN, 17);

		setFont(font);
		setOpaque(true);
		setBackground(color);
		// 실행과 동시에 메소드로 지정한 스레드 시작
		ThreadTime();
		setBounds(200, 13, 300, 40);
		setVisible(true);

	}

}
