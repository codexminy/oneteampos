package oneteampos.menu.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeSet;

import javax.swing.JDialog;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuDetail_dialog;
import oneteampos.menu.etc.ChangeString;

public class Dialog_windowAction extends WindowAdapter {

	private MainFrame mainFrame;
	
	public Dialog_windowAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		JDialog dialog = (JDialog)e.getSource();
		MenuDetail_dialog mdd = mainFrame.getMainPanel().getMenuPanel().getLeftPanel().getMenuDetail_dialog();
		TreeSet<Integer> list = mainFrame.getMainPanel().getMenuPanel().getList();
		
//		mdd.getTempBtns().get(0).setSelected(true);
//		mdd.getSizeBtns().get(0).setSelected(true);
//		
//		for(int i=0; i<mdd.getAmountLabels().size(); ++i) {
//			mdd.getAmountLabels().get(i).setText("0");
//		}
//
//		mdd.setCnt("1");
//		mdd.getTitleList().get(5).setText(ChangeStr.setCashMark(mdd.getOriginPrice()));
		
		list.removeAll(list);
		dialog.dispose();
	}
}
