package book4u;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MediaTracker;
import java.awt.MenuItem;
import java.awt.PopupMenu;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class leftPanel extends JFrame {

    private JScrollPane scrollPane;
    private JPanel LayoutPanel; //宣告一個新的panel
    ImageComponent imageComp;
    private JPanel buttonPanel, imagePanel;
    JFileChooser jfc;

    public JPanel addLeftPanel() {
        PopupMenu popupMenu1 = new PopupMenu();
        MenuItem menuItem1 = new MenuItem();
        MenuItem menuItem2 = new MenuItem();
        MenuItem menuItem3 = new MenuItem();
        menuItem1.setLabel("功能表1");
        popupMenu1.add(menuItem1);

        LayoutPanel = new JPanel();
        LayoutPanel.setLayout(new BorderLayout());
        JButton userAdd, fileAdd;
        userAdd = new JButton("+");
        fileAdd = new JButton("file");

        userAdd.setMargin(new Insets(0, 10, 0, 10));
        fileAdd.setMargin(new Insets(0, 10, 0, 10));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttonPanel.add(userAdd);
        buttonPanel.add(fileAdd);
        imagePanel = new JPanel(new ModifiedFlowLayout(FlowLayout.LEFT, 5, 5));

        LayoutPanel.add(buttonPanel, BorderLayout.NORTH);
        objectDB("userPic");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();	 // 取得螢幕尺寸

        System.out.print(screenSize.getWidth());
        jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);
        //=======================================================
        userAdd.addActionListener(new ActionListener() {
            //使用者新增檔案按鈕動作
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ImageIODemo image = new ImageIODemo(); //使用者新增圖案且存到檔案夾中
                imageComp = image.getReturn();
                final String File_name = image.getFile();
                final File file = new File(image.getFile());
                new CopyFile(File_name, "userPic/" + file.getName());
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        ImageIcon image = new ImageIcon(File_name);//重設圖片大小
                        int weight = image.getIconWidth() / 120;//設置圖片大小
                        image.setImage(image.getImage().getScaledInstance(
                                image.getIconWidth() / weight,
                                image.getIconHeight() / weight,
                                Image.SCALE_DEFAULT));
                        labelStore img = new labelStore(image, File_name, 5);

                        imagePanel.add(img);  //加入圖片

                        DragSource dragSource = DragSource.getDefaultDragSource();
                        dragSource.createDefaultDragGestureRecognizer(img,
                                DnDConstants.ACTION_COPY_OR_MOVE, new DragAndDropDragGestureListener());

                        imagePanel.updateUI();
                        JScrollBar jscrollBar = scrollPane.getVerticalScrollBar();//新增圖片後將拉條拉到最上方
                        if (jscrollBar != null) {
                            jscrollBar.setValue(0);

                        }
                    }
                });
            }
        });
        fileAdd.addActionListener(new ActionListener() {
            //使用者新增檔案按鈕動作
            @Override
            public void actionPerformed(ActionEvent arg0) {
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnVal = jfc.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File[] file = jfc.getSelectedFiles();
                    for (File f : file) {																		// open
                        if (f.isDirectory()) {
                            System.out.println(f);
                            String[] filelist = f.list();
                            for (int i = 0; i < filelist.length; i++) {
                                ImageIcon image = new ImageIcon(f + "/" + filelist[i]);
                                String allowedFileTypes = ".png .gif .jpg .gif .swf .PNG .GIF .JPG .GIF .SWF";
                                if (filelist[i].indexOf(".png") != -1
                                        || filelist[i].indexOf(".gif") != -1
                                        || filelist[i].indexOf(".jpg") != -1
                                        || filelist[i].indexOf(".swf") != -1
                                        || filelist[i].indexOf(".PNG") != -1
                                        || filelist[i].indexOf(".GIF") != -1
                                        || filelist[i].indexOf(".JPG") != -1
                                        || filelist[i].indexOf(".SWF") != -1) {
                                    new CopyFile(f + "/" + filelist[i], "userPic/" + filelist[i]);
                                }
                   
                                try {
                                    int weight = image.getIconWidth() / 120;//設置圖片大小
                                    image.setImage(image.getImage().getScaledInstance(
                                            image.getIconWidth() / weight,
                                            image.getIconHeight() / weight,
                                            Image.SCALE_DEFAULT));
                                } catch (Exception ex) {
                                    image.setImage(image.getImage().getScaledInstance(
                                            100,
                                            150,
                                            Image.SCALE_DEFAULT));
                                }
                                labelStore img = new labelStore(image, f + "/" + filelist[i], 5);

                                imagePanel.add(img);  //加入圖片
     
                                DragSource dragSource = DragSource.getDefaultDragSource();
                                dragSource.createDefaultDragGestureRecognizer(img,
                                        DnDConstants.ACTION_COPY_OR_MOVE, new DragAndDropDragGestureListener());
                            }
                        }
                    }
                }
            }
        });
        //===============================使用者新增檔案按鈕動作<END>
        LayoutPanel.add(popupMenu1);
        return LayoutPanel;
    }

    void objectDB(String pathName) {

        //存所有擋下的圖檔名稱
        File file = new File(pathName);
        String[] filelist = file.list();
        //*********************************存圖檔名稱
        //JPanel picturePanelTab = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        for (int i = 0; i < filelist.length; i++) {

            ImageIcon image = new ImageIcon(pathName + "/" + filelist[i]);
            try {
                int weight = image.getIconWidth() / 120;//重設圖片大小
                image.setImage(image.getImage().getScaledInstance(
                        image.getIconWidth() / weight,
                        image.getIconHeight() / weight,
                        Image.SCALE_DEFAULT));
            } catch (Exception ex) {
                image.setImage(image.getImage().getScaledInstance(
                        100,
                        150,
                        Image.SCALE_DEFAULT));
            }

            labelStore img = new labelStore(image, pathName + "/" + filelist[i], 5);

            imagePanel.add(img);  //加入圖片
            DragSource dragSource = DragSource.getDefaultDragSource();
            dragSource.createDefaultDragGestureRecognizer(img,
                    DnDConstants.ACTION_COPY_OR_MOVE, new DragAndDropDragGestureListener());

        }
        scrollPane = new JScrollPane(imagePanel);
        LayoutPanel.add(scrollPane);
    }

    class ModifiedFlowLayout extends FlowLayout {

        public ModifiedFlowLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }

        public Dimension minimumLayoutSize(Container target) {
            return computeSize(target, false);
        }

        public Dimension preferredLayoutSize(Container target) {
            return computeSize(target, true);
        }

        private Dimension computeSize(Container target, boolean minimum) {
            synchronized (target.getTreeLock()) {
                int hgap = getHgap();
                int vgap = getVgap();
                int w = target.getWidth();

                if (w == 0) {
                    w = Integer.MAX_VALUE;
                }

                Insets insets = target.getInsets();
                if (insets == null) {
                    insets = new Insets(0, 0, 0, 0);
                }
                int reqdWidth = 0;

                int maxwidth = 180; // w - (insets.left + insets.right + hgap * 2);
                int n = target.getComponentCount();
                int x = 0;
                int y = insets.top;
                int rowHeight = 0;

                for (int i = 0; i < n; i++) {
                    Component c = target.getComponent(i);
                    if (c.isVisible()) {
                        Dimension d =
                                minimum ? c.getMinimumSize() : c.getPreferredSize();
                        if ((x == 0) || ((x + d.width) <= maxwidth)) {
                            if (x > 0) {
                                x += hgap;
                            }
                            x += d.width;
                            rowHeight = Math.max(rowHeight, d.height);
                        } else {
                            x = d.width;
                            y += vgap + rowHeight;
                            rowHeight = d.height;
                        }
                        reqdWidth = Math.max(reqdWidth, x);
                    }
                }
                y += rowHeight;
                return new Dimension(120, y);
            }
        }
    }

    class DragAndDropDragGestureListener implements DragGestureListener {

        @Override
        public void dragGestureRecognized(DragGestureEvent dge) {
            labelStore label = (labelStore) dge.getComponent();
            DragAndDropTransferable dragAndDropTransferable = new DragAndDropTransferable(label);
            dge.startDrag(DragSource.DefaultCopyDrop, dragAndDropTransferable, new DragAndDropDragSourceListener());
        }
    }

    class DragAndDropTransferable implements Transferable {

        private labelStore label;

        DragAndDropTransferable(labelStore label) {
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
}
