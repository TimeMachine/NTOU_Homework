/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ntou.cs.java.TimeMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 *
 * @author 桌電
 */
public class MyRectangle extends MyShape {
    private boolean filled;
    public static int count = 0;
    private int height;
    private int width;
    Rectangle2D rectangle;
    
    public MyRectangle()
    {
        this.filled = false;
        count++;
        setShape();
    }
    
    public MyRectangle(int x,int y,Color color,boolean filled)
    {
        this.filled = filled;
        this.setCoordinates(x, y);
        this.setColor(color);
        count++;
        setShape();
    }
    
    private void setShape(){
        Random ran = new Random();
        height = ran.nextInt(150)+50;
        width = ran.nextInt(150)+50;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(this.getColor());
        Point point = this.getCoordinates();
        Random ran = new Random();
        rectangle = new Rectangle2D.Double(point.x, point.y, width, height);
        if(filled)
            g2.fill(rectangle);
        else
            g2.draw(rectangle);
    }

    @Override
    public boolean containPoint(Point pt) {
        if(filled&&rectangle.contains(pt)){
            return true;
        }
        return false;
    }
    
}