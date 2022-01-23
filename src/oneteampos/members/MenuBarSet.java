package oneteampos.members;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JMenuBar;

public class MenuBarSet extends JMenuBar {
    Color bgColor=Color.WHITE;
   

    public void setColor(Color color) {
        bgColor=color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        
        Color o = Color.WHITE; // 폰트컬러
    	setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 20));	// 폰트
    	graphics.setColor(o);
        graphics.setColor(bgColor);
        
        graphics.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        setPreferredSize(new Dimension(1290,50));
       

    }
}