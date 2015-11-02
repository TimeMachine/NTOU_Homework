package book4u;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageSaveToGifDemo extends JFrame {
    BufferedImage bi;
    JButton btn;

    public ImageSaveToGifDemo(String imageFile,final String saveFile) {
        try {
            bi = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
        }
        Calendar calender = new GregorianCalendar();
        int ms = calender.get(Calendar.MILLISECOND); 
                saveImage(bi, saveFile+"/"+"user"+ms+".gif");
    }	

    public void saveImage(BufferedImage img, String filename) {
        try {
            img = convertRGBAToGIF(img);
            ImageIO.write(img, "gif", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage convertRGBAToGIF(BufferedImage src) {
        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(),
                BufferedImage.TYPE_BYTE_INDEXED);
        Graphics g2 = dst.getGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, dst.getWidth(), dst.getHeight());
        IndexColorModel indexedModel = (IndexColorModel) dst.getColorModel();
        WritableRaster raster = dst.getRaster();
        int sample = raster.getSample(0, 0, 0);
        int size = indexedModel.getMapSize();
        byte[] r = new byte[size];
        byte[] g = new byte[size];
        byte[] b = new byte[size];
        indexedModel.getReds(r);
        indexedModel.getGreens(g);
        indexedModel.getBlues(b);
        IndexColorModel newModel = new IndexColorModel(8, size, r, g, b, sample);
        dst = new BufferedImage(newModel, raster, dst.isAlphaPremultiplied(),
                null);
        dst.createGraphics().drawImage(src, 0, 0, null);
        return dst;
    }
}