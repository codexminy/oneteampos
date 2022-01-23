package oneteampos.members;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class RoundedButton extends JButton {
	public RoundedButton() { super(); decorate(); } 
    //public RoundedButton(String text) { super(text); decorate(); } 
    public RoundedButton(Action action) { super(action); decorate(); } 
    public RoundedButton(Icon icon) { super(icon); decorate(); } 
    public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    
    
    public RoundedButton(String text) {
    	setText(text);
    	setContentAreaFilled(false);
    	setBorderPainted(false);
    	setFocusable(false);
    	setCursor(new Cursor(Cursor.HAND_CURSOR));    	
    }
    
    @Override 
    protected void paintComponent(Graphics g) {
    	Graphics2D graphics = (Graphics2D) g; 
    	graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
    	
    	Color o=Color.WHITE; // 폰트컬러
    	setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 14));	// 폰트
    	
    	// 버튼 컬러 기본 회색, 마우스 올리면 파란색    	
    	graphics.setColor(new Color(135, 136, 138));		
		if(getModel().isRollover()) {
			graphics.setColor(new Color(44, 108, 212));
		}
		
		int width = getWidth(); 
		int height = getHeight(); 
		
		graphics.fillRoundRect(0, 0, width, height, 10, 10); 
		FontMetrics fontMetrics = graphics.getFontMetrics(); 
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
		int textX = (width - stringBounds.width) / 2; 
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
		graphics.setColor(o); 
		graphics.setFont(getFont()); 
		graphics.drawString(getText(), textX, textY); 
		graphics.dispose(); 
		super.paintComponent(g); 
   }
    
    
}
