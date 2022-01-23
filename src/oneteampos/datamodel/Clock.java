package oneteampos.datamodel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

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

					// String으로 객체를 생성하는것보다
					// 메모리를 더 효율적으로 사용하기 위해서 StirngBuffer 사용

					StringBuffer now = new StringBuffer();

					now.append(cal.get(Calendar.YEAR));
					now.append("년");
					now.append(cal.get(Calendar.MONDAY) + 1);
					now.append("월");
					now.append(cal.get(Calendar.DATE));
					now.append("일");
					now.append(cal.get(Calendar.HOUR_OF_DAY));
					now.append("시");
					now.append(cal.get(Calendar.MINUTE));
					now.append("분");
					now.append(cal.get(Calendar.SECOND));
					now.append("초");

					setText(now.toString());

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
