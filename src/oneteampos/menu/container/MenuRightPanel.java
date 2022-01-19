package oneteampos.menu.container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import oneteampos.main.MainFrame;
import oneteampos.menu.action.DeleteCartAction;
import oneteampos.menu.allLayouts.Gbl;
import oneteampos.menu.component.MemberCheckDialog;
import oneteampos.menu.component.PaymentDialog;
import oneteampos.menu.component.TableButton;

public class MenuRightPanel extends JPanel {
	
	private JPanel totalPanel;
	private JPanel totalPricePanel;
	private JLabel totalPrice;
	private JPanel payPanel;
	private JPanel cartPanel;
	public DefaultTableModel model;
	private JTable cart;
	private JLabel discountCash;
	private JPanel disPanel;
	public MemberCheckDialog d;
	public Vector<Vector<Object>> menuIdList;
	public Vector<Vector<Object>> menuCntList;
	public boolean isDisCnt;
	public JButton cancelBtn;
	
	public MenuRightPanel(MainFrame mainFrame) {
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 13, 20));
		
		this.totalPanel = new JPanel(new GridBagLayout());
		this.totalPricePanel = new JPanel(new GridLayout());
		this.totalPrice = new JLabel("￦ 0");
		this.payPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
		this.cartPanel = new JPanel();
		this.model = new DefaultTableModel() {@Override public boolean isCellEditable(int row, int column) {return false;}};
		this.cart = new JTable(model);
		this.disPanel = new JPanel(new GridLayout());
		this.discountCash = new JLabel();
		this.menuIdList = new Vector<>();
		this.menuCntList = new Vector<>();
		
		cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
		
		
		JLabel title = new JLabel("선택한 메뉴");
		JLabel discount = new JLabel("할인 금액");
		JPanel LinePanel = new LinePanel(350);
		JLabel t = new JLabel("결제 금액");
		
		JButton payBtn = new JButton("결제");
		cancelBtn = new JButton("할인&적립취소");
		cancelBtn.setVisible(false);
		
		cancelBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int ch = JOptionPane.showOptionDialog(null, "할인&적립을 취소하겠습니까?", "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				if(ch == JOptionPane.YES_OPTION) {
					d.point = 0;
					totalPrice.setText("￦ " + (Integer.parseInt(totalPrice.getText().substring(2)) + Integer.parseInt(discountCash.getText())));
					d.saveCash.setText("");
					discountCash.setText("￦ ");
					discountCash.setVisible(false);
					cancelBtn.setVisible(false);
					JOptionPane.showMessageDialog(null, "할인&적립이 취소되었습니다.");
					isDisCnt = false;
				}
			}
			
		});
		
		
		
		
		LinePanel.setOpaque(false);
		
		payBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cart.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
				} else {
					PaymentDialog doo = new PaymentDialog(mainFrame);
				}
			}
			
		});
		
		JButton memberCheck = new JButton("회원조회");
		
		memberCheck.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(cart.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "선택한 메뉴가 없습니다.", "Message", JOptionPane.WARNING_MESSAGE);
				} else {
					d = new MemberCheckDialog(mainFrame);
					d.setVisible(true);
				}
				
			}
			
		});
		discountCash.setVisible(false);
		
		disPanel.setOpaque(false);
		
		disPanel.add(discount);
		disPanel.add(discountCash);
		
		payPanel.add(cancelBtn);
		payPanel.add(memberCheck);
		payPanel.add(payBtn);
		totalPanel.setOpaque(false);
		payPanel.setOpaque(false);
		totalPricePanel.setOpaque(false);
		
		model.setDataVector(new Object[][] {{"","","","",""}}, new Object[] {"메뉴이름","사이즈","수량","금액","선택"});
		model.removeRow(0);

		JButton btn = new JButton();
		cart.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cart.addMouseListener(new DeleteCartAction(mainFrame));
		
		cart.getColumnModel().getColumn(4).setCellRenderer(new TableButton(btn));
		cart.getColumnModel().getColumn(4).setCellEditor(new TableButton(btn));
		
		cart.setOpaque(false);
		((DefaultTableCellRenderer)cart.getDefaultRenderer(Object.class)).setOpaque(false);
		cart.setShowGrid(false);
		cart.setPreferredSize(new Dimension(0, 0));
		cart.setFocusable(false);
		
		totalPrice.setHorizontalAlignment(JLabel.RIGHT);
		discountCash.setHorizontalAlignment(JLabel.RIGHT);
		
		totalPricePanel.add(t);
		totalPricePanel.add(totalPrice);
		
		cartPanel.setOpaque(false);
		
		totalPrice.setVisible(false);
		
		GridBagConstraints gbc = Gbl.getGbc();
		
		totalPanel.add(disPanel, Gbl.setting(gbc, 0.1, 0.1, 0, 0));
		totalPanel.add(LinePanel, Gbl.setting(gbc, 0.1, 0.1, 0, 1));
		totalPanel.add(totalPricePanel, Gbl.setting(gbc, 0.1, 0.1, 0, 2));
		totalPanel.add(payPanel, Gbl.setting(gbc, 0.1, 0.1, 0, 3));

		add(title, "North");
		add(cart, "Center");
		add(totalPanel, "South");
	}
	
	public JLabel getTotalPrice() {
		return this.totalPrice;
	}
	
	public JPanel getCartPanel() {
		return this.cartPanel;
	}
	
	public DefaultTableModel getModel() {
		return this.model;
	}
	
	public JLabel getDiscountCash() {
		return this.discountCash;
	}
}




























