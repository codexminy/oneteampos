package oneteampos.menu.container;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.MenuManage_confirmAction;
import oneteampos.menu.component.All_btn;
import oneteampos.menu.component.All_label;
import oneteampos.menu.component.All_opaquePanel;

public class MenuManage_refreshDialog extends JDialog {
	
	private JTextField nameField;
	private JTextField priceField;
	private JTextField typeField;
	
	public MenuManage_refreshDialog(MainFrame mainFrame) {
		super(mainFrame, "메뉴 수정", true);
		
		JPanel backgroundPanel = new All_opaquePanel(new BorderLayout());
		JPanel infoPanel = new All_opaquePanel(new GridLayout(3,2,0,15));
		JPanel btnPanel = new All_opaquePanel(new FlowLayout());
		
		JLabel title = new All_label("메뉴 정보 수정");
		JLabel name = new All_label("메뉴 이름 : ");
		JLabel price = new All_label("메뉴 가격 : ");
		JLabel type = new All_label("메뉴 종류 : ");
		
		nameField = new JTextField();
		priceField = new JTextField();
		typeField = new JTextField();
		
		JButton confirm = new All_btn("수정");

		title.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		backgroundPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		confirm.setPreferredSize(new Dimension(90, 30));
		
		infoPanel.add(name);
		infoPanel.add(nameField);
		infoPanel.add(price);
		infoPanel.add(priceField);
		infoPanel.add(type);
		infoPanel.add(typeField);
		
		btnPanel.add(confirm);
		
		backgroundPanel.add(title, "North");
		backgroundPanel.add(infoPanel, "Center");
		backgroundPanel.add(btnPanel, "South");
		
		confirm.addActionListener(new MenuManage_confirmAction(mainFrame, this));
		
		add(backgroundPanel);
		
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public JTextField getNameField() {
		return this.nameField;
	}
	
	public JTextField getPriceField() {
		return this.priceField;
	}
	
	public JTextField getTypeField() {
		return this.typeField;
	}
}
