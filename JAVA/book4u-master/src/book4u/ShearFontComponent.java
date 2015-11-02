package book4u;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class ShearFontComponent extends JComponent {
	 String text;
	 int fontSize;
	 Color textColor;
    public ShearFontComponent(String text,int fontSize, Color textColor) {
    	this.text=text;
        this.fontSize = fontSize;
        this.textColor = textColor;
    }

    public Dimension getPreferredSize() {
        return new Dimension(280, 80);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        AffineTransform aff = g2.getTransform();
        aff.shear(-1d, 0);
        g2.setTransform(aff);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(getFont().deriveFont(Font.BOLD, fontSize));
        g2.setColor(textColor);
        g2.drawString(text, 50, 50);
    }
}