/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

/**
 * @author mao
 */
public class UpPanel extends JFrame {

    private JScrollPane UpPanel;
    private JPanel LayoutPanel, imagePanel; //宣告一個新的panel
    mainFrame mainFrmae;
    private ArrayList<labelStore> myList = new ArrayList<labelStore>();
    
    public JScrollPane UpPanel(final mainFrame main) {
        mainFrmae = main;
        LayoutPanel = new JPanel();
        LayoutPanel.setLayout(new BorderLayout());

        UpPanel = new JScrollPane();

        imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());

        LayoutPanel.setBackground(new Color(176, 224, 230));

        for ( int i = 1; i <= mainFrmae.propertyList.getSize(); i++) {
            BufferedImage pageImg = mainFrmae.propertyList.getScreenShot(i);
            ImageIcon image = new ImageIcon(pageImg);
            image.setImage((image.getImage().getScaledInstance(125, 78, Image.SCALE_DEFAULT)));

            final labelStore img;
            img = new labelStore(image, null, i);//i是儲存的頁數
            myList.add(img);
            imagePanel.add(img);//加入圖片
            img.setBorder(new LineBorder(Color.BLACK, 2));
            if(i==main.getCenterPanel().page)
                img.setBorder(new LineBorder(Color.BLUE, 2));
            final int page=i;
            img.addMouseListener(new MouseListener() {//點擊則中間centerPanel換頁數
                @Override
                public void mouseClicked(MouseEvent arg0) {
                    
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    for(int i = 0; i < myList.size(); i++){
                        myList.get(i).setBorder(new LineBorder(Color.BLACK, 2));
                    }
                   img.setBorder(new LineBorder(Color.BLUE, 2));//當點擊到圖片時圖片邊框變藍變粗
                    mainFrmae.setCenterPanel(mainFrmae.propertyList.getElement(img.type));
                    /* labelStore label = (labelStore) e.getSource();
                    centerPanel panel = mainFrmae.getCenterPanel();
                    System.out.println(panel.page);
                    mainFrmae.propertyList.set(panel);
                    mainFrmae.remove(panel);
                    panel = mainFrmae.propertyList.getElement(label.type);
                    System.out.println(label.type+"---"+panel.page);
                    mainFrmae.add(panel);
                    mainFrmae.getCenterPanel().updateUI();
                    System.out.println(mainFrmae.getCenterPanel().page);*/
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
            });
        }
        imagePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 3));  //物件向左對齊

        //UpPanel.updateUI();
        // LayoutPanel.add(UpPanel);
        //imagePanel.setBackground(Color.red);
        UpPanel.getViewport().add(imagePanel);

        return UpPanel;
    }
}
