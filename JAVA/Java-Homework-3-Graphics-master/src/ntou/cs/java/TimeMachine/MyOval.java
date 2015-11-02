/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ntou.cs.java.TimeMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

/**
 *
 * @author 桌電
 */
public class MyOval extends MyShape {
    private boolean filled;
    public static int count = 0;
    private int height;
    private int width;
    private int archeight;
    private int arcwidth;
    private RoundRectangle2D round;
    private Point p1;
    private boolean press;
    public MyOval()
    {
        count++;
        this.filled = false;
        setShape();
    }
    public MyOval(int x,int y,Color color,boolean filled)
    {
        count++;
        this.filled = filled;
        this.setCoordinates(x, y);
        this.setColor(color);
        setShape();
    }

    private void setShape(){
        Random ran = new Random();
        height = ran.nextInt(150)+50;
        width = ran.nextInt(150)+50;
        archeight = ran.nextInt(150)+50;
        arcwidth = ran.nextInt(150)+50;
    }
    
    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(this.getColor());
        Point point = this.getCoordinates();
        Random ran = new Random();
        round = new RoundRectangle2D.Double(point.x, point.y, width, height, width, height);
        if(filled)
            g2.fill(round);
        else
            g2.draw(round);
    }
    @Override
    public boolean containPoint(Point pt) {
        if(filled&&round.contains(pt)){
            return true;
        }
        return false;
    }
}
