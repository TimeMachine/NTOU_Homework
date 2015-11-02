/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author mao
 */
public class labelStore extends  JLabel{
        public int type;
        public String pathName;
        //public BufferedImage orginImage;
        public ImageIcon imageIcon;
        public boolean modified;
        labelStore (ImageIcon image,String path,int t){
            type = t;
            pathName = path;
            //orginImage = orgin;
            imageIcon = image;
            modified = false;
            super.setIcon(image);
        }
        
       public BufferedImage getBufferedImage()
       {
           return imageToBufferedImage(imageIcon.getImage());
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
