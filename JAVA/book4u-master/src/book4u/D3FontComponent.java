package book4u;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;

import javax.swing.JComponent;

public class D3FontComponent extends JComponent {
	 String text;
	 int fontSize;
	 Color textColor;

    public D3FontComponent(String text,int fontSize, Color textColor) {
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
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = getFont().deriveFont(Font.BOLD, fontSize);
        GlyphVector v = f.createGlyphVector(getFontMetrics(f)
                .getFontRenderContext(), text);
        g2.translate(5, 50);
        for (int i = 0; i < 8; i++) {
            Shape s = v.getOutline();
            g2.translate(1, 1);
            g2.setPaint(new Color(155 - i * 20, 30 + i * 20, 0));
            g2.fill(s);
        }
    }
}