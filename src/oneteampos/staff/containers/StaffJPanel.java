package oneteampos.staff.containers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import oneteampos.main.MainFrame;
import oneteampos.order.compnents.BackBtn;

public class StaffJPanel extends JPanel{
	 MainFrame mainframe;
	 StaffInfoJPanel staffInfoPanel;
	  
	  public StaffJPanel(MainFrame mainframe) {
		  this.mainframe = mainframe;
	      this.staffInfoPanel = new StaffInfoJPanel(this);
	      
	      add(staffInfoPanel);
	      
	      // 판넬 설정 
	      this.setBounds(0, 0, 1280, 720);
	      this.setBackground(Color.WHITE);
	      this.setLayout(null);
	      
	      // 메인프레임에 판넬 추가
	      mainframe.add(this);
	      
	      // 사장님 라벨
	      addLoginInfoLabel();
	      
	      // 사원 관리 라벨
	      addStaffLable();
	      
	      // 버튼 추가
	      add(new BackBtn(mainframe , this, "staffPanel"));
	      

	}
	  
	  @Override
	   public void paint(Graphics g) { // 선 긋기
	      super.paint(g);
	      
	      g.drawLine(20, 60, 1240, 60);
	      g.drawLine(20, 100, 1240, 100);
	   }
	   
	   public void setVisibleTrue() {
	      setVisible(true);
	   }
	   
	   public void setVisibleFalse() {
	      setVisible(false);
	   }
	   
	   public void addLoginInfoLabel() { 
	     JLabel boss_name = new JLabel("김사장");
	     boss_name.setBounds(30, 10, 100, 40);
	     boss_name.setBackground(Color.WHITE);
	     boss_name.setFont(new Font("고딕", Font.BOLD, 20));
	     boss_name.setVisible(true);
	     add(boss_name);
	   }
	   
	   public void addStaffLable() {
		   JLabel staffLabel = new JLabel("사원관리");
		   staffLabel.setFont(new Font("고딕", Font.BOLD, 17));
		   staffLabel.setBounds(30, 65, 300, 30);
		   add(staffLabel);
	   }
}
