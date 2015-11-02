/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import static javax.swing.JComponent.TOOL_TIP_TEXT_KEY;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author 桌電
 */
public class centerPanel extends javax.swing.JPanel implements Serializable {

    /**
     * Creates new form centerPanel1
     */
    JMenuItem item;
    JMenuItem item1;
    JPopupMenu menu = new JPopupMenu("Popup");
    JMenuItem deleteitem;
    JPopupMenu deleteMenu = new JPopupMenu("Delete");
    int rightClickX;
    int rightClickY;
    public int page;
    private static ArrayList<arrayListType> myList;
    public mainFrame mainFrame;
    ImageIcon imageIcon = null;
    int record = -1;
    int flag = 0;
    Point preTouchPoint;

    public centerPanel(JFrame frame) {
        if (frame != null) {
            mainFrame = (book4u.mainFrame) frame;
        }
        myList = new ArrayList();
        initComponents();
        item = new JMenuItem("Insert PicturePanel");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertInitialPictureFrame2(rightClickX, rightClickY, 100, 100);
            }
        });
        menu.add(item);

        item1 = new JMenuItem("Insert TextPanel");
        item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertTextArea2(new EightTextPanel(), rightClickX, rightClickY, 200, 100);
            }
        });
        menu.add(item1);

        deleteitem = new JMenuItem("Delete");
        deleteitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(myList.get(record).getContent());
                myList.remove(record);
                updateUI();
                record = -1;
            }
        });
        deleteMenu.add(deleteitem);
    }

    centerPanel() {
        this(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        rightPanel = new JPanel();

        setLayout(null);

        leftPanel.setBackground(new java.awt.Color(255, 255, 255));
        DropTarget dropTarget = new DropTarget(leftPanel,new DragAndDropDropTargetListener());
        leftPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        leftPanel.addMouseListener(new leftPopupTriggerListener());
        leftPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        leftPanel.setOpaque(false);
        leftPanel.addMouseListener(new leftMouseEvent());
        leftPanel.addMouseMotionListener(new leftMouseEvent());
        leftPanel.setPreferredSize(new java.awt.Dimension(2, 2));
        add(leftPanel);
        leftPanel.setBounds(0, 0, 320, 400);

        rightPanel.setBackground(new java.awt.Color(255, 255, 255));
        rightPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        DropTarget dropTarget2 = new DropTarget(rightPanel,new DragAndDropDropTargetListener());
        rightPanel.setOpaque(false);
        rightPanel.addMouseListener(new rightPopupTriggerListener());
        rightPanel.setPreferredSize(new java.awt.Dimension(2, 2));
        rightPanel.addMouseListener(new leftMouseEvent());
        rightPanel.addMouseMotionListener(new leftMouseEvent());
        add(rightPanel);
        rightPanel.setBounds(330, 0, 320, 400);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel leftPanel;
    public javax.swing.JPanel rightPanel;
    // End of variables declaration//GEN-END:variables

    class leftPopupTriggerListener extends MouseAdapter {

        public void mousePressed(MouseEvent ev) {

            if (ev.isPopupTrigger()) {
                System.out.println("right-click" + ev.getX() + " " + ev.getY());
                menu.show(ev.getComponent(), ev.getX(), ev.getY());

                rightClickX = ev.getX();
                rightClickY = ev.getY();
            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                System.out.println("right-click" + ev.getX() + " " + ev.getY() + " " + ev.getClass());
                menu.show(ev.getComponent(), ev.getX(), ev.getY());
                rightClickX = ev.getX();
                rightClickY = ev.getY();
            }
        }

        public void mouseClicked(MouseEvent ev) {
        }
    }

    class rightPopupTriggerListener extends MouseAdapter {

        public void mousePressed(MouseEvent ev) {

            if (ev.isPopupTrigger()) {
                System.out.println("right-click" + ev.getX() + " " + ev.getY());
                menu.show(ev.getComponent(), ev.getX(), ev.getY());

                rightClickX = ev.getX() + 325;
                rightClickY = ev.getY();
            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                System.out.println("right-click" + ev.getX() + " " + ev.getY() + " " + ev.getClass());
                menu.show(ev.getComponent(), ev.getX(), ev.getY());
                rightClickX = ev.getX() + 325;
                rightClickY = ev.getY();
            }
        }

        public void mouseClicked(MouseEvent ev) {
        }
    }

    class deletePopupTriggerListener extends MouseAdapter {

        public void mousePressed(MouseEvent ev) {

            if (ev.isPopupTrigger()) {
                System.out.println("right-click" + ev.getX() + " " + ev.getY());
                deleteMenu.show(ev.getComponent(), ev.getX(), ev.getY());

                rightClickX = ev.getX();
                rightClickY = ev.getY();
            }
        }

        public void mouseReleased(MouseEvent ev) {
            if (ev.isPopupTrigger()) {
                System.out.println("right-click" + ev.getX() + " " + ev.getY() + " " + ev.getClass());
                deleteMenu.show(ev.getComponent(), ev.getX(), ev.getY());
                rightClickX = ev.getX();
                rightClickY = ev.getY();
            }
        }

        public void mouseClicked(MouseEvent ev) {
        }
    }

    void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    class DragAndDropDropTargetListener implements DropTargetListener {

        @Override
        public void dragEnter(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragOver(DropTargetDragEvent dtde) {
        }

        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        @Override
        public void dragExit(DropTargetEvent dte) {
        }

        @Override
        public void drop(DropTargetDropEvent dtde) {

            Transferable tr = dtde.getTransferable();
            labelStore s = null;
            try {
                if (tr.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    s = (labelStore) tr.getTransferData(DataFlavor.stringFlavor);
                }
            } catch (IOException ex) {
            } catch (UnsupportedFlavorException ex) {
            }
            DropTarget c = (DropTarget) dtde.getSource();
            JPanel d = (JPanel) c.getComponent();
            // System.out.println(d.getComponentCount() + "components in d");
            //System.out.println(s.pathName + " label store path");
            if (s.type == 2) {//遮罩處理
                for (int i = 0; i < d.getComponentCount(); i++) {
                    if (d.getComponent(i).getClass() == labelStore.class) {
                        labelStore label = (labelStore) d.getComponent(i);
                        ImageIcon image = (ImageIcon) s.getIcon();
                        try {
                            s.pathName = maskImg(label.getBufferedImage(), s.pathName, d.getWidth() - 10, d.getHeight() - 10);
                        } catch (IOException ex) {
                            Logger.getLogger(centerPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        //anotherIcon.setImage(getScaledImage(anotherIcon.getImage(),d.getWidth(),d.getWidth()));
                        s.setIcon(new ImageIcon(s.pathName));
                        s.setBounds(5, 5, d.getWidth() - 10, d.getHeight() - 10);
                        d.add(s);
                        if (d instanceof EightPoint) {
                            ((EightPoint) d).setLabel();
                        }
                        break;
                    }
                }
            }
            //System.out.println(d.getComponentCount() + "components in d");
            for (int i = 0; i < d.getComponentCount(); i++) {
                //System.out.println("this is forloop" );
                if (d.getComponent(i).getClass() == labelStore.class) {
                    //System.out.println(d.getWidth()+" && "+d.getHeight() );
                    d.remove(i);
                }
            }
            d.setLayout(new OverlayLayout(d));

            if (s.type == 1) {
                if (d == centerPanel.this.leftPanel || d == centerPanel.this.rightPanel) {
                    if (!myList.isEmpty()) {
                        for (int i = myList.size() - 1; i >= 0; i--) {
                            if (myList.get(i).getX() < d.getWidth() && (d == centerPanel.this.leftPanel)) {
                                centerPanel.this.remove(myList.get(i).getContent());
                                myList.remove(i);

                            } else if (myList.get(i).getX() > d.getX() - 10 && (d == centerPanel.this.rightPanel)) {
                                centerPanel.this.remove(myList.get(i).getContent());
                                myList.remove(i);

                            }
                        }
                    }
                    d.updateUI();
                    int flag = (d == centerPanel.this.leftPanel) ? 0 : 1;
                    if (s.pathName.equals("pageMode/classPanel1.png")) {
                        d = new panelClass1(flag);
                    }
                    if (s.pathName.equals("pageMode/classPanel2.png")) {
                        d = new panelClass2(flag);
                    }
                    if (s.pathName.equals("pageMode/classPanel3.png")) {
                        d = new panelClass3(flag);
                    }
                    if (s.pathName.equals("pageMode/classPanel4.png")) {
                        d = new panelClass4(flag);
                    }
                    if (s.pathName.equals("pageMode/classPanel5.png")) {
                        d = new panelClass5(flag);
                    }
                    if (s.pathName.equals("pageMode/classPanel6.png")) {
                        d = new panelClass6(flag);
                    }
                    if (s.pathName.equals("pageMode/classPanel7.png")) {
                        d = new panelClass7(flag);
                    }
                } else {
                    return;
                }

            } else if (s.type != 2) {
                //d.setBackground(new Color(0,0,0,0));
                if (mainFrame == null) {
                    System.out.println("this is null in first line");
                }

                //if (d != centerPanel.this.leftPanel && d != centerPanel.this.rightPanel) {
                //d.removeAll();                    
                //System.out.println("d components"+d.getComponentCount());
                // ((EightPoint)d).reAdd();
                if (d != centerPanel.this.leftPanel && d != centerPanel.this.rightPanel) {
                    ImageIcon image = new ImageIcon(s.pathName);
                    image.setImage(image.getImage().getScaledInstance(d.getWidth() - 10, d.getHeight() - 10, Image.SCALE_DEFAULT));

                    //anotherIcon.setImage(getScaledImage(anotherIcon.getImage(),d.getWidth(),d.getWidth()));
                    s.setIcon(image);
                    d.setLayout(null);
                    s.setBounds(5, 5, d.getWidth() - 10, d.getHeight() - 10);
                    d.add(s);
                    ((EightPoint) d).display(true);
                } else {
                    ImageIcon image = new ImageIcon(s.pathName);
                    image.setImage(image.getImage().getScaledInstance(d.getWidth() , d.getHeight() , Image.SCALE_DEFAULT));

                    //anotherIcon.setImage(getScaledImage(anotherIcon.getImage(),d.getWidth(),d.getWidth()));
                    s.setIcon(image);
                    d.setLayout(null);
                    s.setBounds(0, 0, d.getWidth() , d.getHeight() );
                    d.add(s);
                }
                //}               
            }
            s.addMouseListener(new rightPopupTriggerListener());
            s.addMouseListener(new leftPopupTriggerListener());
            mainFrame.updataScreenShot();
            d.updateUI();
            mainFrame.pictureFilePathActionPerformed(s.pathName, d);
            
        }
    }

    public class panelClass1 extends javax.swing.JPanel {

        panelClass1(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel panel = insertInitialPictureFrame2(adjustment + 40, 70, 250, 250);
        }
    }

    public class panelClass2 extends javax.swing.JPanel {

        panelClass2(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel pane2 = insertInitialPictureFrame2(adjustment + 10, 20, 300, 300);
            insertTextArea2(new EightTextPanel(), adjustment + 10, 325, 300, 50);
        }
    }

    public class panelClass3 extends javax.swing.JPanel {

        panelClass3(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel pane = insertInitialPictureFrame2(adjustment + 10, 10, 300, 380);
        }
    }

    public class panelClass4 extends javax.swing.JPanel {

        panelClass4(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel pane = insertInitialPictureFrame2(adjustment + 0, 0, 200, 200);
            JPanel pane2 = insertInitialPictureFrame2(adjustment + 120, 200, 200, 200);
        }
    }

    public class panelClass5 extends javax.swing.JPanel {

        panelClass5(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel pane = insertInitialPictureFrame2(adjustment + 10, 90, 300, 300);
            insertTextArea2(new EightTextPanel(), adjustment + 10, 20, 300, 50);
        }
    }

    public class panelClass6 extends javax.swing.JPanel {

        panelClass6(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel pane1 = insertInitialPictureFrame2(adjustment + 5, 10, 200, 180);
            JPanel pane2 = insertInitialPictureFrame2(adjustment + 5, 190, 200, 200);
            JPanel pane3 = insertInitialPictureFrame2(adjustment + 205, 10, 110, 380);
        }
    }

    public class panelClass7 extends javax.swing.JPanel {

        panelClass7(int input) {
            int adjustment = (input == 1) ? 325 : 0;
            JPanel pane1 = insertInitialPictureFrame2(adjustment + 5, 20, 180, 180);
            JPanel pane2 = insertInitialPictureFrame2(adjustment + 5, 200, 180, 180);
            insertTextArea2(new EightTextPanel(), adjustment + 190, 100, 120, 250);
        }
    }

    public JPanel insertInitialPictureFrame2(int x, int y, int w, int h) {
        JPanel panel = new EightPoint();
        panel.setBounds(x, y, w, h);
        ((EightPoint) panel).setLabel();
        insertPictureFrame(panel);
        return panel;
    }

    public void insertTextArea2(JTextPane panel, int x, int y, int w, int h) {

        //panel.setEditable(true);

        panel.setOpaque(false);
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        panel.setLayout(null);
        panel.setBounds(x, y, w, h);
        panel.setDragEnabled(true);
        myList.add(new arrayListType(panel));
        panel.setBounds(50, 50, 100, 100);
        ((EightTextPanel) panel).setLabel();
        add(panel, 0);
        updateUI();
        panel.addMouseListener(new leftMouseEvent());
        panel.addMouseMotionListener(new leftMouseEvent());
        panel.addMouseListener(new deletePopupTriggerListener());
    }

    public String maskImg(BufferedImage im, BufferedImage im2) throws IOException {

        Graphics2D g = im.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // 圖片重疊:類似浮水印
        g.drawImage(im2, (im.getWidth() - im2.getWidth()) / 2, (im.getHeight() - im2.getHeight()) / 2, null);

        Calendar calender = new GregorianCalendar();
        int ms = calender.get(Calendar.MILLISECOND);
        im = ImageIO.read(new File("userPic/" + ms + ".gif"));

        return "userPic/" + ms + ".gif";
    }

    public String maskImg(BufferedImage im, String im2PathName, int iw, int ih) throws IOException {

        ImageIcon image = new ImageIcon(im2PathName);
        image.setImage(image.getImage().getScaledInstance(
                iw,
                ih,
                Image.SCALE_DEFAULT));

        //  BufferedImage im2 = toBufferedImage(image);
        ImageObserver observer = image.getImageObserver();
        BufferedImage im2 = toBufferedImage(image);

        Graphics2D g = im.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // 圖片重疊:類似浮水印
        g.drawImage(im2, (im.getWidth() - im2.getWidth()) / 2, (im.getHeight() - im2.getHeight()) / 2, null);

        Calendar calender = new GregorianCalendar();
        int ms = calender.get(Calendar.MILLISECOND);
        ImageIO.write(im, "png", new File("bufferImg/" + ms + ".png"));

        return "bufferImg/" + ms + ".png";
    }

    /**
     * 返回無背景色的印章図像所在路径
     *
     * @param image 印章図像文件Buffered
     * @return 印章図像文件Buffered
     */
    public static BufferedImage getNoBgColorImage(BufferedImage image) {
        ImageIcon imageIcon = new ImageIcon(image);
        BufferedImage bufferedImage = new BufferedImage(imageIcon
                .getIconWidth(), imageIcon.getIconHeight(),
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
        int alpha = 0;
        for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
            for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage
                    .getWidth(); j2++) {
                int rgb = bufferedImage.getRGB(j2, j1);

                int R = (rgb & 0xff0000) >> 16;
                int G = (rgb & 0xff00) >> 8;
                int B = (rgb & 0xff);
                if (((255 - R) > 30) && ((255 - G) > 30) && ((255 - B) > 30)) {
                    rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                }

                bufferedImage.setRGB(j2, j1, rgb);
            }
        }

        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
        // ImageIO.write(bufferedImage, "png", new File("d:/test/png.png"));
        return bufferedImage;
    }

    public static BufferedImage toBufferedImage(ImageIcon icon) {

        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        ImageObserver observer = icon.getImageObserver();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics gc = bufferedImage.createGraphics();
        gc.drawImage(icon.getImage(), 0, 0, observer);
        bufferedImage = getNoBgColorImage(bufferedImage);
        return bufferedImage;

    }

    public void insertPictureFrame(JPanel panel) {

        panel.setOpaque(false);
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));

        DropTarget dropTarget = new DropTarget(panel, new DragAndDropDropTargetListener());

        myList.add(new arrayListType(panel));
        add(panel, 0);
        updateUI();
        System.out.println("insert in mainFrame " + mainFrame);
        //myList.get(count).setBorder(new LineBorder(Color.BLUE, 1)); 
        if (record != -1) {
            if (myList.get(record).getContent() instanceof EightPoint) {
                ((EightPoint) myList.get(record).getContent()).display(false);
            } else {
                ((EightTextPanel) myList.get(record).getContent()).display(false);
            }
        }

        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        panel.addMouseListener(new leftMouseEvent());
        panel.addMouseMotionListener(new leftMouseEvent());
        panel.addMouseListener(new deletePopupTriggerListener());
        //  myList.get(count)

    }

    public JPanel insertInitialPictureFrame() {
        JPanel panel = new EightPoint();
        panel.setBounds(50, 50, 100, 100);
        ((EightPoint) panel).setLabel();
        insertPictureFrame(panel);
        return panel;
    }

    public JTextPane insertInitialTextArea() {
        /* JTextPane panel = new JTextPane(){
         public void paintComponent(Graphics g) {
         BufferedImage  img = new BufferedImage(getWidth(), getHeight(),
         BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2d = img.createGraphics();
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
         RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
         0));// 设置合成规则
         g2d.fillRect(0, 0, getWidth(), getHeight());
         g.drawImage(img, 0, 0, null);
         }
         };*/
        JTextPane panel = new EightTextPanel();
        panel.setBounds(50, 50, 100, 100);
        ((EightTextPanel) panel).setLabel();
        insertTextArea(panel);
        return panel;
    }

    public void insertTextArea(JTextPane panel) {

        //panel.setEditable(true);
        panel.setOpaque(false);
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        panel.setLayout(null);
        panel.setBounds(50, 50, 100, 100);
        panel.setDragEnabled(true);
        myList.add(new arrayListType(panel));
        add(panel, 0);
        updateUI();

        if (record != -1) {
            if (myList.get(record).getContent() instanceof EightPoint) {
                ((EightPoint) myList.get(record).getContent()).display(false);
            } else {
                ((EightTextPanel) myList.get(record).getContent()).display(false);
            }
        }
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        panel.addMouseListener(new leftMouseEvent());
        panel.addMouseMotionListener(new leftMouseEvent());
        panel.addMouseListener(new deletePopupTriggerListener());
        //  myList.get(count)       
    }

    public void addListen() {
        for (int i = 0; i < myList.size(); i++) {
            new DropTarget(myList.get(i).getArrayListJPanel(), new DragAndDropDropTargetListener());
        }
        new DropTarget(leftPanel, new DragAndDropDropTargetListener());
        new DropTarget(rightPanel, new DragAndDropDropTargetListener());

    }

    class leftMouseEvent implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            preTouchPoint = e.getPoint();
            int i;
            if (record != -1) {
                if (myList.get(record).getContent() instanceof EightPoint) {
                    ((EightPoint) myList.get(record).getContent()).display(false);
                } else {
                    ((EightTextPanel) myList.get(record).getContent()).display(false);
                }
            }
            for (i = 0; i < myList.size(); i++) {
                //((EightPoint)myList.get(i).getContent()).display(false);
                //System.out.println(myList.get(i).getX()+"-"+myList.get(i).getY()+"-"+myList.get(i).getWidth()+"-"+myList.get(i).getY()+"-");
                //System.out.println(preTouchPoint.getX()+"-"+preTouchPoint.getY());
                if (myList.get(i).getContent().equals(e.getSource())) {
                    record = i;
                    if (myList.get(i).getContent() instanceof EightPoint) {
                        ((EightPoint) myList.get(i).getContent()).display(true);
                    } else {
                        ((EightTextPanel) myList.get(i).getContent()).display(true);
                    }
                    break;
                }
            }
            //System.out.println(record+" record");
            if (i == myList.size()) {
                flag = 0;
            } else {
                if (myList.get(i).getContent() instanceof EightPoint) {
                    flag = ((EightPoint) myList.get(i).getContent()).containLabel(e.getPoint());
                } else {
                    flag = ((EightTextPanel) myList.get(i).getContent()).containLabel(e.getPoint());
                }

                if (myList.get(i).getContent() instanceof EightPoint) {
                    for (Component j : ((EightPoint) myList.get(i).getContent()).getComponents()) {
                        if (j.getClass() == labelStore.class) {
                            mainFrame.pictureFilePathActionPerformed(((labelStore) j).pathName, (JPanel) myList.get(i).getContent());
                        }
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            flag = 0;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            double moveX = e.getX() - preTouchPoint.getX();
            double moveY = e.getY() - preTouchPoint.getY();


            if (flag == 9) {
                if (myList.get(record).getContent() instanceof EightPoint) {
                    ((EightPoint) myList.get(record).getContent()).setLocation((int) (((EightPoint) myList.get(record).getContent()).getX() + moveX), (int) (((EightPoint) myList.get(record).getContent()).getY() + moveY));
                } else {
                    ((EightTextPanel) myList.get(record).getContent()).setLocation((int) (((EightTextPanel) myList.get(record).getContent()).getX() + moveX), (int) (((EightTextPanel) myList.get(record).getContent()).getY() + moveY));
                }
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}
