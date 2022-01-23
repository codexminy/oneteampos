package oneteampos.members;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenuItem;

public class MenuItemSet extends JMenuItem implements MouseListener{

	public MenuItemSet() { super(); decorate(); }  
    public MenuItemSet(Action action) { super(action); decorate(); } 
    public MenuItemSet(Icon icon) { super(icon); decorate(); } 
    public MenuItemSet(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    
    public MenuItemSet(String text) {
    	setText(text);
    	setContentAreaFilled(false);
    	setBorderPainted(false);
    	setFocusable(false);
    	setCursor(new Cursor(Cursor.HAND_CURSOR));
    	addMouseListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
   
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        
    	setFont(new Font("나눔스퀘어", Font.PLAIN, 14));	// 폰트
    	setMinimumSize(new Dimension(90,30));
    	setPreferredSize(new Dimension(90, 30));


//		int width = getWidth(); 
//		int height = getHeight();
//
//    	graphics.setFont(getFont());  
//    	graphics.fillRect(0, 0, width, height); 
//    	FontMetrics fontMetrics = graphics.getFontMetrics(); 
//		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
//    	int textX = (width - stringBounds.width) / 2; 
//		int textY = (height - stringBounds.height) / 2+ fontMetrics.getAscent();
//    	graphics.drawString(getText(), textX, textY);
//		graphics.dispose(); 
       
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		setBackground(new Color(44, 108, 212));
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// doClick();
		
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(new Color(135, 136, 138));
	}
	
}
