package oneteampos.menu.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TreeSet;

import javax.swing.JDialog;

import oneteampos.main.MainFrame;
import oneteampos.menu.container.MenuDetailsPanel;

public class JDialogWindowAction extends WindowAdapter {

	private MainFrame mainFrame;
	
	public JDialogWindowAction(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		JDialog dialog = (JDialog)e.getSource();
		TreeSet<Integer> list = mainFrame.getMainPanel().getMenuPanel().getList();
		list.removeAll(list);
		dialog.dispose();
	}
}
