package book4u;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class OutlineComponent extends JComponent 
{
    String text;
    int fontSize;
    Color textColor;
    public OutlineComponent(String text,int fontSize, Color textColor){
        this.text=text;
        this.fontSize = fontSize;
        this.textColor = textColor;
    }
    public Dimension getPreferredSize() {
        return new Dimension(260, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension size = this.getPreferredSize();
        Font font = new Font("Helvetica", Font.BOLD|Font.ITALIC, fontSize);
        FontRenderContext frc = g2.getFontRenderContext(); 
        TextLayout tl = new TextLayout(text,font, frc);
        AffineTransform transform = new AffineTransform();
        Shape outline = tl.getOutline(null);
        Rectangle r = outline.getBounds();
        transform = g2.getTransform();
        transform.translate(size.getWidth() / 2 - (r.width / 2), size.getHeight() / 2 + (r.height / 2));
        g2.transform(transform);
        g2.setStroke(new BasicStroke(2.0f));
        g2.setPaint(textColor);
        g2.draw(outline); 
    }
}