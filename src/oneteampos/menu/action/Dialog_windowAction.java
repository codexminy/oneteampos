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

		list.removeAll(list);
		dialog.dispose();
	}
}
