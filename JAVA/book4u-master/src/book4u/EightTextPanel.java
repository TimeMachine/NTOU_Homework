/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;
import java.io.Serializable;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
/**
 *
 * @author 桌電
 */
public class EightTextPanel extends JTextPane implements Serializable{
    JLabel leftUp = new JLabel();
    JLabel Up = new JLabel();
    JLabel rightUp = new JLabel();
    JLabel right = new JLabel();
    JLabel rightButtom = new JLabel();
    JLabel Buttom = new JLabel();
    JLabel leftButtom = new JLabel();
    JLabel left = new JLabel();
     Point preTouchPoint;
    EightTextPanel(){
        setLayout(null);
        add(leftUp);
        add(Up);
        add(rightUp);
        add(right);
        add(rightButtom);
        add(Buttom);
        add(leftButtom);
        add(left);
        
        leftUp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        leftUp.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds((int) (EightTextPanel.this.getX()+moveX), (int) (EightTextPanel.this.getY()+moveY), (int) (EightTextPanel.this.getWidth()-moveX), (int) (EightTextPanel.this.getHeight()-moveY));
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        Up.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        Up.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds(EightTextPanel.this.getX(), (int) (EightTextPanel.this.getY()+moveY),EightTextPanel.this.getWidth(), (int) (EightTextPanel.this.getHeight()-moveY));
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        rightUp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        rightUp.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds(EightTextPanel.this.getX(), (int) (EightTextPanel.this.getY()+moveY), (int) (EightTextPanel.this.getWidth()+moveX), (int) (EightTextPanel.this.getHeight()-moveY));
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        right.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        right.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds(EightTextPanel.this.getX(), EightTextPanel.this.getY(), (int) (EightTextPanel.this.getWidth()+moveX), EightTextPanel.this.getHeight());
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        
        rightButtom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
                rightButtom.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds(EightTextPanel.this.getX(), (int) EightTextPanel.this.getY(), (int) (EightTextPanel.this.getWidth()+moveX), (int) (EightTextPanel.this.getHeight()+moveY));
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        Buttom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
                        Buttom.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds(EightTextPanel.this.getX(), (int) EightTextPanel.this.getY(), (int) EightTextPanel.this.getWidth(), (int) (EightTextPanel.this.getHeight()+moveY));
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        leftButtom.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
                                leftButtom.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds((int) (EightTextPanel.this.getX()+moveX), (int) EightTextPanel.this.getY(), (int) ((int) EightTextPanel.this.getWidth()-moveX), (int) (EightTextPanel.this.getHeight()+moveY));
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        left.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                preTouchPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
                                left.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double moveX = e.getX() - preTouchPoint.getX();
                double moveY = e.getY() - preTouchPoint.getY();
                EightTextPanel.this.setBounds((int) (EightTextPanel.this.getX()+moveX), (int) EightTextPanel.this.getY(), (int) ( EightTextPanel.this.getWidth()-moveX),EightTextPanel.this.getHeight());
                setLabel();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        leftButtom.setBorder(BorderFactory.createLineBorder(Color.black));
        left.setBorder(BorderFactory.createLineBorder(Color.black));
        rightUp.setBorder(BorderFactory.createLineBorder(Color.black));
        right.setBorder(BorderFactory.createLineBorder(Color.black));
        rightButtom.setBorder(BorderFactory.createLineBorder(Color.black));
        Buttom.setBorder(BorderFactory.createLineBorder(Color.black));
        leftUp.setBorder(BorderFactory.createLineBorder(Color.black));
        Up.setBorder(BorderFactory.createLineBorder(Color.black));
        
    }
    void display(boolean b){
        leftUp.setVisible(b);
        Up.setVisible(b);
        rightUp.setVisible(b);
        right.setVisible(b);
        rightButtom.setVisible(b);
        Buttom.setVisible(b);
        leftButtom.setVisible(b);       
        left.setVisible(b);        
    }
    void setLabel (){
        leftUp.setBounds(0, 0, 10, 10);
        Up.setBounds(this.getWidth()/2-5, 0, 10, 10);
        rightUp.setBounds(this.getWidth()-10, 0, 10, 10);
        right.setBounds(this.getWidth()-10, this.getHeight()/2-5, 10, 10);
        rightButtom.setBounds(this.getWidth()-10, this.getHeight()-10, 10, 10);
        Buttom.setBounds(this.getWidth()/2-5, this.getHeight()-10, 10, 10);
        leftButtom.setBounds(0, this.getHeight()-10, 10, 10);
        left.setBounds(0, this.getHeight()/2-5, 10, 10);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = new BufferedImage(getWidth(), getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                0));// 设置合成规则
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(img, 0, 0, null);        
    }
    public int containLabel(Point p){
        if(leftUp.contains(p)){
            return 1;
        }else if(Up.contains(p)){
            return 2;        
        }else if(rightUp.contains(p)){
            return 3;        
        }else if(right.contains(p)){
            return 4;        
        }else if(rightButtom.contains(p)){
            return 5;        
        }else if(Buttom.contains(p)){
            return 6;        
        }else if(leftButtom.contains(p)){
            return 7;        
        }else if(left.contains(p)){
            return 8;        
        }else{
            return 9;
        }
    }    
    public void reAdd(){
        remove(leftUp);
        remove(Up);
        remove(rightUp);
        remove(right);
        remove(rightButtom);
        remove(Buttom);
        remove(leftButtom);
        remove(left);
        
        add(leftUp, 0);
        add(Up, 0);
        add(rightUp, 0);
        add(right, 0);
        add(rightButtom, 0);
        add(Buttom, 0);
        add(leftButtom, 0);
        add(left, 0);
    }   
}
