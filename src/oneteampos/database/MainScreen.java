package oneteampos.database;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.TreeSet;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MainScreen {
	
	final static int FRAME_WIDTH = 1280;
	final static int FRAME_HEIGHT = 720;
	
	public MainScreen() {
		JFrame frame = new JFrame("ī�� ������ �׽�Ʈ");
		JDialog dialog = new JDialog();
		dialog.setTitle("�޴� ���� �׽�Ʈ");
		JCheckBox box = new JCheckBox();
		
		JButton b = new JButton("�޴� ����");
		
		JPanel dPn1 = new JPanel();
		
		String[] ti = new String[] {"����", "�޴�id", "�޴���", "����", "����", "������"};
		Object[][] li = new Object[][] {
			{false, "1000", "�Ƹ޸�ī��", "4500", "coffee", "s"},
			{false, "1001", "����Ŀ��", "5000", "tea", "s"},
			{false, "1002", "�Ƹ޸�ī��", "4500", "coffee", "s"},
			{false, "1003", "�Ƹ޸�ī��", "4500", "coffee", "s"},
			{false, "1004", "�Ƹ޸�ī��", "4500", "coffee", "s"},
			{false, "1005", "�Ƹ޸�ī��", "4500", "coffee", "s"},
			{false, "1006", "�Ƹ޸�ī��", "4500", "coffee", "s"}
		};
		
		DefaultTableModel model = new DefaultTableModel(li, ti);
		
		JTable ta1 = new JTable(model);
		
		ta1.getColumn("����").setCellRenderer(dcr);
		box.setHorizontalAlignment(JLabel.CENTER);
		ta1.getColumn("����").setCellEditor(new DefaultCellEditor(box));

		JScrollPane sc = new JScrollPane(ta1);

		dPn1.add(b);
		dPn1.add(sc);
		dialog.add(dPn1);
		
		TreeSet<Integer> list = new TreeSet<>();
		
		box.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tm = (DefaultTableModel)ta1.getModel();
				int n = ta1.getSelectedRow();
				if(box.isSelected()) {
					list.add(n);
					System.out.println(tm.getValueAt(n, 1));
					System.out.println(list);
				} else {
					list.remove(n);
				}
				System.out.println(list);
			}
		});

		
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int cnt = 0;
				DefaultTableModel tm = (DefaultTableModel)ta1.getModel();
				System.out.println(list);
				for(int a : list) {
					System.out.println(a);
					tm.removeRow(a-cnt);
					cnt++;
				}
				list.removeAll(list);
				System.out.println(list);
			}
		});
		
		CardLayout lay = new CardLayout();
		GridLayout grid = new GridLayout();
		
		JPanel pn1 = new JPanel(lay);
		JPanel pn2 = new JPanel(grid);
		JPanel pn3 = new JPanel(null);
		JPanel pn4 = new JPanel();
		JPanel pn5 = new JPanel(null);
		JPanel pn6 = new JPanel(lay);
		JPanel pn7 = new JPanel(new GridLayout(3,4,20,20));
		JPanel pn8 = new JPanel(new GridLayout(3,4,20,20));
		JPanel pn9 = new JPanel(new GridLayout(3,4,20,20));
		JPanel pn10 = new JPanel(new GridLayout(3,4,20,20));
		JPanel pn11 = new JPanel(new GridLayout(3,4,20,20));
		
		pn4.setBounds(980, 0, 300, FRAME_HEIGHT);
		pn4.setBackground(Color.LIGHT_GRAY);
		
		pn5.setBounds(0, 0, 980, FRAME_HEIGHT);
		
		pn6.setBounds(30, 120, 920, 550);
		
		JButton btn1 = new JButton("�޴�");
		JButton btn2 = new JButton("���/����");
		JButton btn3 = new JButton("����");
		JButton btn4 = new JButton("�������");
		JButton btn5 = new JButton("�޴� ����");

		
		JButton[] btns1 = new JButton[12];
		
		for(int i=0; i<12; ++i) {
			btns1[i] = new JButton("��Ʈ�޴�");
			pn7.add(btns1[i]);
		}
		
		JButton[] btns2 = new JButton[9];
		
		for(int i=0; i<9; ++i) {
			btns2[i] = new JButton("�Ÿ޴�");
			pn8.add(btns2[i]);
		}
		
		JButton[] btns3 = new JButton[7];
		
		for(int i=0; i<7; ++i) {
			btns3[i] = new JButton("Ŀ��");
			pn9.add(btns3[i]);
		}
		
		JButton[] btns4 = new JButton[5];
		
		for(int i=0; i<5; ++i) {
			btns4[i] = new JButton("������&������");
			pn10.add(btns4[i]);
		}
		
		JToggleButton t1 = new JToggleButton("��Ʈ �޴�");
		JToggleButton t2 = new JToggleButton("�Ÿ޴�");
		JToggleButton t3 = new JToggleButton("Ŀ��");
		JToggleButton t4 = new JToggleButton("������&������");

		JLabel la = new JLabel("0001 ȫ�浿");

		la.setBounds(30, 20, 100, 30);
		btn5.setBounds(850, 20, 100, 30);
		
		t1.setBounds(30, 70, 100, 30);
		t2.setBounds(140, 70, 100, 30);
		t3.setBounds(250, 70, 100, 30);
		t4.setBounds(360, 70, 100, 30);
		pn6.add("set", pn7);
		pn6.add("new", pn8);
		pn6.add("coffee", pn9);
		pn6.add("smoothe", pn10);
		
		pn5.add(la);
		pn5.add(btn5);
		pn5.add(t1);
		pn5.add(t2);
		pn5.add(t3);
		pn5.add(t4);
		
		pn5.add(pn6);
		
		pn3.add(pn4);
		pn3.add(pn5);
		
		pn2.add(btn1);
		pn2.add(btn2);
		pn2.add(btn3);
		pn2.add(btn4);
		
		pn1.add(pn2);
		pn1.add("menu", pn3);
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lay.show(pn1, "menu");
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		
		
		t1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lay.show(pn6, "set");
			}
		});
		
		t2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lay.show(pn6, "new");
			}
		});
		
		t3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lay.show(pn6, "coffee");
			}
		});
		
		t4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lay.show(pn6, "smoothe");
			}
		});

		dialog.setVisible(false);
		dialog.setSize(500, 500);
		dialog.setLocationRelativeTo(null);
		
		frame.add(pn1);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
	 {
	  public Component getTableCellRendererComponent  // ��������
	   (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	  {
	   JCheckBox box= new JCheckBox();
	   box.setSelected(((Boolean)value).booleanValue());  
	   box.setHorizontalAlignment(JLabel.CENTER);
	   return box;
	  }
	 };
	
	public static void main(String[] args) {
		new MainScreen();
	}
}
