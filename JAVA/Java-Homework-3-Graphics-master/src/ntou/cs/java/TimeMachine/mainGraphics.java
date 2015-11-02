/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ntou.cs.java.TimeMachine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author 桌電
 */
public class mainGraphics extends JPanel implements MouseListener,MouseMotionListener {
    /**
     * @param args the command line arguments
     */
    private Point p1;
    private int currentSquareIndex = -1;
    private static ArrayList<MyShape> myList;
    
    public mainGraphics()
    {
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public static void main(String[] args) {
          JFrame frame = new JFrame("TimeMachine");
          JPanel graphics = new mainGraphics();
          JLabel label = new JLabel("123");
          myList = new ArrayList<MyShape>();
          graphics.setLayout(new OverlayLayout(graphics));
          Random ran = new Random();
          MyShape container ;
          String input = JOptionPane.showInputDialog(graphics,"Number of shapes");
        if(input!=null){
          for(int i=0,type=ran.nextInt(5);i< Integer.valueOf(input);i++,type = ran.nextInt(5))
          {
              float r = ran.nextFloat();
              float g = ran.nextFloat();
              float b = ran.nextFloat();
              Color randomColor = new Color(r, g, b);
              if(type==0)
                  container = new MyLine(ran.nextInt(300),ran.nextInt(300),randomColor);
              else if(type == 1)
                  container = new MyOval(ran.nextInt(300),ran.nextInt(300),randomColor,false);
              else if(type == 2)
                  container = new MyOval(ran.nextInt(300),ran.nextInt(300),randomColor,true);
              else if(type == 3)
                  container = new MyRectangle(ran.nextInt(300),ran.nextInt(300),randomColor,false);
              else
                  container = new MyRectangle(ran.nextInt(300),ran.nextInt(300),randomColor,true);
              myList.add(container);
              graphics.add(container);
          }
         
          graphics.setBackground(Color.WHITE);
          graphics.setVisible(true);
          label.setText("Lines:"+MyLine.count+",Ovals:"+MyOval.count+",Rectangles:"+MyRectangle.count);
          frame.add(graphics,BorderLayout.CENTER);
          frame.add(label,BorderLayout.SOUTH);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(500,500);
          frame.setResizable(false);
          frame.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        p1 = e.getPoint();
        currentSquareIndex = getRec(p1);
        //System.out.println( "mousePressed" );
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(currentSquareIndex>=0){
            int moveX = e.getPoint().x - p1.x;
            int moveY = e.getPoint().y - p1.y;
            Point orgin = myList.get(currentSquareIndex).getCoordinates();
            myList.get(currentSquareIndex).setCoordinates(orgin.x + moveX,orgin.y + moveY);
            myList.get(currentSquareIndex).repaint();
            p1 = e.getPoint(); 
        }
        //System.out.println( "mouseDragged" );  
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
    private int getRec(Point pt) {
        for (int i = 0; i < myList.size() ; i++) {
            if (myList.get(i).containPoint(pt)) {
                //System.out.println( i );
                return i;
            }
        }
        return -1;
    }
    
}
