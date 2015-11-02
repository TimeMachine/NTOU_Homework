package book4u;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class LineComponent extends JComponent {
    Insets inset = new Insets(10, -20, 10, 20);
    Color color;
    int fx, fy, tx, ty;
    Stroke stroke;

    public LineComponent(int fx, int fy, int tx, int ty, Color color) {
        this.color = color;
        this.fx = fx;
        this.fy = fy;
        this.tx = tx;
        this.ty = ty;
        stroke = new BasicStroke(1.0f);
    }

    public Dimension getPreferredSize() {
        return new Dimension(Math.max(fx, tx) + inset.left + inset.right,
                Math.max(fy, ty) + inset.top + inset.bottom);
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        createShape(g2d, fx, fy, tx, ty, color);
    }

    public void createShape(Graphics2D g2d, int fx, int fy, int tx, int ty,
            Color color) {
        g2d.setStroke(stroke);
        g2d.setPaint(color);
        g2d.drawLine(fx, fy, tx, ty);
    }
}