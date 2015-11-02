/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;

/**
 *
 * @author 桌電
 */
public class arrayListType  extends JComponent{
    private JPanel panelType = null;
    private JTextPane textPanelType = null;
    public arrayListType(JTextPane panel)
    {
        textPanelType = panel;
    }
    
    public arrayListType(JPanel panel)
    {
        panelType = panel;
    }
    
    public boolean containJPanel()
    {
        return panelType!=null ? true : false;
    }
    
    public boolean containJTextPane()
    {
        return textPanelType!=null ? true : false;
    }
    
    public JTextPane getArrayListJTextPane()
    {
        return textPanelType;
    }
    
    public JPanel getArrayListJPanel()
    {
        return panelType;
    }
    
    public Component getContent()
    {
        if(containJPanel())
            return panelType;
         return textPanelType;
    }
    
    @Override
    public boolean contains(Point p) 
    {
        if(textPanelType!=null)
            return textPanelType.contains(p);
        return panelType.contains(p);
        
    }
    

    @Override
    public Point getLocation()
    {
        if(textPanelType!=null)
            return textPanelType.getLocation();
        return panelType.getLocation();
    }
    
     @Override
     public int getX()
     {
        if(textPanelType!=null)
            return textPanelType.getX();
        return panelType.getX();
     }
    
     @Override
     public int getY()
     {
        if(textPanelType!=null)
            return textPanelType.getY();
        return panelType.getY();
     }
     
     @Override
     public int getWidth()
     {
        if(textPanelType!=null)
            return textPanelType.getWidth();
        return panelType.getWidth();
     }
     
     @Override
     public int getHeight()
     {
        if(textPanelType!=null)
            return textPanelType.getHeight();
        return panelType.getHeight();
     }
     
     @Override
     public void setLocation(int x, int y)
     {
        if(textPanelType!=null)
            textPanelType.setLocation(x,y);
        else 
            panelType.setLocation(x,y);
     }

     @Override
     public void repaint()
     {
        if(textPanelType!=null)
            textPanelType.repaint();
        else 
            panelType.repaint();
     }
     
     @Override
     public void setBounds(int x,
                      int y,
                      int width,
                      int height)
     {
     
         if(textPanelType!=null)
            textPanelType.setBounds(x,y,width,height);
        else 
            panelType.setBounds(x,y,width,height);
     }
     
     @Override
     public void setLayout(LayoutManager mgr)
     {
         if(textPanelType!=null)
            textPanelType.setLayout(mgr);
        else 
            panelType.setLayout(mgr);
     }
     
     @Override
     public Dimension getMinimumSize()
     {
         if(textPanelType!=null)
            return textPanelType.getMinimumSize();
        else 
            return panelType.getMinimumSize();
     }
     
     @Override
     public void addMouseListener(MouseListener l)
     {
         if(textPanelType!=null)
            textPanelType.addMouseListener(l);
        else 
            panelType.addMouseListener(l);
     }
     
     @Override
     public void addMouseMotionListener(MouseMotionListener l)
     {
        if(textPanelType!=null)
            textPanelType.addMouseMotionListener(l);
        else 
            panelType.addMouseMotionListener(l);
     }
     
      @Override
      public void add(Component comp,Object constraints)
      {
          if(textPanelType!=null)
            textPanelType.add(comp,constraints);
          else 
            panelType.add(comp,constraints);
      }
      
      @Override
      public void setBorder(Border border)
      {
          if(textPanelType!=null)
            textPanelType.setBorder(border);
          else 
            panelType.setBorder(border);
      }
}
