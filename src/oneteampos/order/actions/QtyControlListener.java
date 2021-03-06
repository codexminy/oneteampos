package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import oneteampos.order.cotainer.AddItemJFrame;
import oneteampos.order.cotainer.OrderListJPanel;
import oneteampos.sales.containers.RoundedButton;

public class QtyControlListener implements MouseListener{

	AddItemJFrame addItemPanel;
	JLabel countLabel;
	
	public QtyControlListener(AddItemJFrame addItemPanel) {
		this.addItemPanel = addItemPanel;
		this.countLabel = addItemPanel.getCountLabel();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*품목 추가 판넬(addItemJPanel)에서의 수량 조절 리스너*/
		
		int countNum = Integer.parseInt(countLabel.getText());
		
		RoundedButton dirBtn = (RoundedButton)e.getSource();
		dirBtn.setSelected(false);
		if(e.getSource().toString().contains("◀")) {
			if(countNum != 0) {
				countNum -=   1;
				countLabel.setText(countNum + "" );
			}
		} else {
			countNum += 1 ;
			countLabel.setText(countNum + "" );
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
