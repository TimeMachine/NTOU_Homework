package book4u;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;

import javax.swing.JComponent;

public class ShadowText extends JComponent {
	String text;
	int fontSize;
	Color textColor;

    public ShadowText(String text,int fontSize, Color textColor) {
    	this.text=text;
        this.fontSize = fontSize;
        this.textColor = textColor;
    }

    public Dimension getPreferredSize() {
        return new Dimension(240, 70);
    }

    public void paint(Graphics g) {
        int x = 10;
        int y = 45;
        Font font = new Font("", Font.BOLD | Font.ITALIC, fontSize);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        TextLayout textLayout = new TextLayout(text, font,
                g2.getFontRenderContext());
        g2.setPaint(new Color(122, 122, 122));
        textLayout.draw(g2, x + 2, y + 2);
        g2.setPaint(textColor);
        textLayout.draw(g2, x, y);
    }
}