/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ntou.cs.java.TimeMachine;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Random;


/**
 *
 * @author 桌電
 */
public class MyLine extends MyShape {
    public static int count = 0;
    private int arriveX;
    private int arriveY;
    public MyLine()
    {
        count++;
        setShape();
    }
    public MyLine(int x,int y,Color color)
    {
        count++;
        setShape();
        this.setCoordinates(x, y);
        this.setColor(color);
    }
    
    private void setShape(){
        Random ran = new Random();
        arriveX = ran.nextInt(500);
        arriveY = ran.nextInt(500);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(this.getColor());
        Point point = this.getCoordinates();
        Random ran = new Random();
        Line2D line = new Line2D.Double(point.x, point.y, arriveX, arriveY);
        g2.draw( line );
    }

    @Override
    public boolean containPoint(Point pt) {
        return false;
    }

}
