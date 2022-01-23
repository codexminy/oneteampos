package oneteampos.menu.component;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class All_arrowUI extends BasicComboBoxUI {

    public static ComboBoxUI createUI(JComponent c) {
        return new All_arrowUI();
        
    }

    @Override protected JButton createArrowButton() {
        return new BasicArrowButton(BasicArrowButton.SOUTH, Color.WHITE, Color.WHITE, new Color(135, 136, 138), Color.WHITE);
    }
}
