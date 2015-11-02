/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ntou.cs.java.TimeMachine;
import java.awt.*;
import javax.swing.JComponent;

/**
 *
 * @author 桌電
 */
public abstract class MyShape extends JComponent {
    private Color color;
    private Point coordinates;
    public MyShape()
    {
        color = Color.BLACK;
        coordinates = new Point();
    }
    public MyShape(int x,int y,Color color)
    {
        this.coordinates = new Point(x,y);
        this.color = color;
    }
    public void setCoordinates(int x,int y)
    {
        this.coordinates.setLocation(x, y);
    }
    public void setColor(Color color)
    {
        this.color = color;
    }
    public Point getCoordinates()
    {
        return coordinates;
    }
    public Color getColor()
    {
        return color;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public Dimension getPreferredSize() {
    return new Dimension(500, 500);
    }
 
    public Dimension getMaximumSize() {
    return getPreferredSize();
    }
 
    public Dimension getMinimumSize() {
    return getPreferredSize();
    }
    
    public abstract void draw( Graphics g );
    
    public abstract boolean containPoint(Point pt);
}
