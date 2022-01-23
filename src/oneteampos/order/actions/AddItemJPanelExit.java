package oneteampos.order.actions;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import oneteampos.order.cotainer.AddItemJFrame;

public class AddItemJPanelExit implements MouseListener{
	
	AddItemJFrame addItemPanel;
	
	public AddItemJPanelExit(AddItemJFrame addItemPanel) {
		this.addItemPanel = addItemPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*품목 추가 판넬에서 X버튼 눌렀을 때 리스너*/
		addItemPanel.setVisibleFalse();
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
