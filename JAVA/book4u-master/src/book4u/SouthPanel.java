package book4u;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class SouthPanel extends JFrame {

    private JPanel LayoutPanel; //宣告一個新的panel
    ImageComponent imageComp;
    public JTabbedPane tab;

    public JPanel addSouthPanel() {
        LayoutPanel = new JPanel();
        LayoutPanel.setLayout(new BorderLayout());
        JButton userAdd = new JButton("+");
        userAdd.setBounds(250, 0, 50, 20);
        //getContentPane().add( LayoutPanel );
        final String str[] = {"頁面樣式", "遮罩", "背景", "物件庫"};
        tab = new JTabbedPane();
        for (int i = 0; i < 4; i++) {
            tab.add(str[i], createPanel(str[i]));
        }
        tab.setBackground(Color.WHITE);
        LayoutPanel.add(userAdd);
        LayoutPanel.add(tab);
        //=======================================================
        userAdd.addActionListener(new ActionListener() {
            //使用者新增檔案按鈕動作
            @Override
            public void actionPerformed(ActionEvent arg0) {
                 final int temp = tab.getSelectedIndex(); //讀取目前所在的Jpanel位置
                 if(temp==0){
                      JOptionPane.showMessageDialog(null, "不可在頁面框架中新增圖片", "錯誤訊息 ",JOptionPane.ERROR_MESSAGE);  
                         return;
                 }
                ImageIODemo image = new ImageIODemo(); //使用者新增圖案且存到檔案夾中
                imageComp = image.getReturn();
                final String File_name = image.getFile();
                final File file = new File(image.getFile());
               
                System.out.println(temp);
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        switch (temp) {
                            case 0:
                                //new CopyFile(File_name, "pageMode/" + file.getName());
                                break;
                            case 1:
                                
                                new CopyFile(File_name, "mask/" + file.getName());
                                break;
                            case 2:
                                new CopyFile(File_name, "bground/" + file.getName());
                                break;
                            case 3:
                                new CopyFile(File_name, "pic/" + file.getName());
                                break;
                        }
                        tab.removeAll();//清除所有的tab方便重新建立新的tab((重新讀取檔案中的資料
                        //因為tab為final無法直接新增使用者上傳的圖案
                        //所以用清除所有的tab重新讀取檔案資料方便知道使用者上傳的檔案
                        for (int i = 0; i < 4; i++) {
                            tab.add(str[i], createPanel(str[i]));
                        }
                    }
                });
            }
        });
        //===============================使用者新增檔案按鈕動作<END>
        return LayoutPanel;
    }

    JScrollPane createPanel(String str) { //panel選項
        JScrollPane jp = new JScrollPane(); //有滑桿可拉的panel
        switch (str) {
            case "頁面樣式":
                jp.getViewport().add(objectDB("pageMode",1));//頁面框架型式
                break;
            case "遮罩":
                jp.getViewport().add(objectDB("mask",2)); //有點類似浮水印的做法
                break;
            case "背景":
                jp.getViewport().add(objectDB("bground",3));//物件庫的檔案存在bground資料夾底下
                break;
            case "物件庫":
                jp.getViewport().add(objectDB("pic",4)); //物件庫的檔案存在pic資料夾底下
                break;
        }
        return jp;
    }

    JPanel objectDB(String pathName, int type) {
        //存所有擋下的圖檔名稱
        File file = new File(pathName);
        String[] filelist = file.list();
        //*********************************存圖檔名稱

        JPanel picturePanelTab = new JPanel();
        picturePanelTab.setBackground(Color.WHITE);
        for (int i = 0; i < filelist.length; i++) {
            ImageIcon image = new ImageIcon(pathName + "/" + filelist[i]);
            try {
                int weight = image.getIconHeight() / 70;//設置圖片大小
                image.setImage(image.getImage().getScaledInstance(
                        image.getIconWidth() / weight,
                        image.getIconHeight() / weight,
                        Image.SCALE_DEFAULT));
            } catch (Exception ex) {
                image.setImage(image.getImage().getScaledInstance(
                        100,
                        100,
                        Image.SCALE_DEFAULT));
            }
             labelStore img=new labelStore(image,pathName + "/" + filelist[i],type);
           // JLabel imgLabel2 = new JLabel(image);
            //imgLabel2.setText(pathName + "/" + filelist[i]);
           
            picturePanelTab.add(img);//加入圖片
             
            DragSource dragSource = DragSource.getDefaultDragSource();
           dragSource.createDefaultDragGestureRecognizer(img,
                    DnDConstants.ACTION_COPY_OR_MOVE, new DragAndDropDragGestureListener());

        }
        picturePanelTab.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));  //物件向左對齊
        return picturePanelTab;
    }

    class DragAndDropDragGestureListener implements DragGestureListener {

        public void dragGestureRecognized(DragGestureEvent dge) {
            labelStore label = (labelStore) dge.getComponent();
            DragAndDropTransferable dragAndDropTransferable = new DragAndDropTransferable(label);
            dge.startDrag(DragSource.DefaultCopyDrop, dragAndDropTransferable, new DragAndDropDragSourceListener());
        }
    }

    class DragAndDropTransferable implements Transferable {

        private JLabel label;

        DragAndDropTransferable(JLabel label) {
            this.label = label;
        }
        DataFlavor flavors[] = {DataFlavor.stringFlavor};

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return flavors;
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return true;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return label;
        }
    }

    class DragAndDropDragSourceListener implements DragSourceListener {

        @Override
        public void dragEnter(DragSourceDragEvent dsde) {
            DragSourceContext context = dsde
                    .getDragSourceContext();
            int dropAction = dsde.getDropAction();
            if ((dropAction & DnDConstants.ACTION_COPY) != 0) {
                context.setCursor(DragSource.DefaultCopyDrop);
            } else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {
                context.setCursor(DragSource.DefaultMoveDrop);
            } else {
                context.setCursor(DragSource.DefaultCopyNoDrop);
            }
        }

        @Override
        public void dragOver(DragSourceDragEvent dsde) {
        }

        @Override
        public void dropActionChanged(DragSourceDragEvent dsde) {
        }

        @Override
        public void dragExit(DragSourceEvent dse) {
        }

        @Override
        public void dragDropEnd(DragSourceDropEvent dsde) {
        }
    }

    public class ClickThroughLabel extends JLabel implements MouseListener, MouseMotionListener {

        private ClickThroughLabel(ImageIcon image) {
            super(image);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
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
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
    public static BufferedImage imageToBufferedImage(Image image) {
//        InputStream imageIn = new FileInputStream(new File(imageFile));
//        JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
//        BufferedImage image = decoder.decodeAsBufferedImage();
        try {
            Frame frame = new Frame(); 
            frame.addNotify(); 
            MediaTracker mt = new MediaTracker(frame);// frame acts as an ImageObserver
            mt.addImage(image, 0);
            mt.waitForAll();
            int w = image.getWidth(frame);
            int h = image.getHeight(frame);
            BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics gc = buf.createGraphics();
            gc.drawImage(image,0,0,frame);
            return buf;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
