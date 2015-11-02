/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author CHANG
 */
public class centerPanelList implements Serializable {

    private ArrayList<centerPanel> myList = new ArrayList<centerPanel>();
    BufferedImage bi;
    mainFrame mainFrame;
    centerPanelList(mainFrame main) {
        mainFrame = main;
    }

    centerPanelList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void set(centerPanel panel) {
        myList.set(panel.page - 1, panel);
    }

    centerPanel getElement(int page) {
        if (page <= myList.size()&&page>0) {
            return myList.get(page - 1);
        }
        return null;
    }

    int getSize() {
        return myList.size();
    }

    void add(centerPanel panel) {
        myList.add(panel);
    }

    void remove(int page) {          // problem
        myList.remove(page - 1);
        for(int i = page-1;i < myList.size();i++)
        {
            myList.get(i).page --;
        }
    }

    void init(int page) {
		if(page == 0){
			System.out.println("no page can init");
		}		
        for (int i = 0; i < page; i++) {
            centerPanel p = new centerPanel(mainFrame);
            p.setLocation(190, 160);
            p.setSize(650, 400);
            p.page = i+1;
            p.setBackground(new java.awt.Color(255, 255, 255));
            myList.add(p);
        }
    }

    public BufferedImage getScreenShot(int i) {
	centerPanel panel = myList.get(i-1);
        panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        panel.leftPanel.setBorder(null);
        panel.rightPanel.setBorder(null);
        BufferedImage bi = new BufferedImage(
                panel.getWidth()+1, panel.getHeight() + 1, BufferedImage.TYPE_INT_ARGB);

        panel.paint(bi.getGraphics());
        panel.setBorder(null);
        panel.leftPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        panel.rightPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        return bi;
    }

    void outputPDF() {
        System.out.println(getSize());
        Rectangle rect;
        rect = new Rectangle(648 + 72, 420 + 72);
        Document document = new Document(rect);
        try {
            JFileChooser fileChooser = new JFileChooser(".");
            FileFilter filter1 = new ExtensionFileFilter("PDF檔案", ".pdf");
            fileChooser.setFileFilter(filter1);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile() + ".pdf");
                PdfWriter.getInstance(document, new FileOutputStream(file.toString()));
                document.open();
                for (int i = 0; i < getSize(); i++) {
                    centerPanel temp = new centerPanel();                    
                    bi = getScreenShot(i+1);
                    saveImage();
                    Image img = PngImage.getImage("MyFile.png");
                    document.add(img);
                    if (i != getSize()) {
                        document.newPage();
                    }
                }
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("down");
    }

    public void saveImage() {
        File f = new File("MyFile.png");
        try {
            ImageIO.write(bi, "PNG", f);
            System.out.println("down");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void store() {
        try {
            JFileChooser fileChooser = new JFileChooser(".");
            FileFilter filter1 = new ExtensionFileFilter("Book4U(.b4u)", ".b4u");
            fileChooser.setFileFilter(filter1);
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile() + ".b4u");
                // System.out.println(file.toString());
                SLMethod fo = new SLMethod(file.toString());
                for (int i = 0; i < getSize(); i++) {
                    centerPanel s = myList.get(i);
                    s.mainFrame = null;
                    set(s);
                }
                fo.save(myList);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void open() {
        try {
            JFileChooser fileChooser = new JFileChooser(".");
            FileFilter filter1 = new ExtensionFileFilter("Book4U(.b4u)", "b4u");
            fileChooser.setFileFilter(filter1);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                SLMethod fo = new SLMethod(file.toString());
                //myList =  (ArrayList<centerPanel>)fo.load();
                myList = new ArrayList<centerPanel>((ArrayList<centerPanel>) fo.load());
                for(int i = 0; i < myList.size(); i++){
                    centerPanel temp = myList.get(i);
                    temp.mainFrame = mainFrame;      
                    temp.addListen();
                    set(temp);                    
                }
                System.out.println(myList.size());              
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }    
}
