package book4u;
import java.awt.FileDialog;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class ImageIODemo extends JFrame {
    ImageComponent imageComp;
    String file_name;

    public ImageIODemo() {
        imageComp=new ImageComponent();
    	try {
             btnFileAction();
         } catch (IOException e) { 
             e.printStackTrace();
         }
    }
    public  ImageComponent getReturn(){
    	return  imageComp;
    }
    public  String getFile(){
    	return  file_name;
    }
    void btnFileAction() throws IOException{
        FileDialog fd= new FileDialog(this,"",FileDialog.LOAD);
        fd.setVisible(true);
        String file = fd.getFile();
        if(file==null)
            return;
        file  = fd.getDirectory()+file;
        file_name=file;
        BufferedImage bi = ImageIO.read(new File(file));
        imageComp.setImage(bi);
    }
}