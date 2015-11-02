package book4u;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ByteLookupTable;
import java.awt.image.ConvolveOp;
import java.awt.image.IndexColorModel;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingUtilities;

public class ImageVFlipDemo extends JComponent {

    BufferedImage bi, orig;
    BufferedImage bi2, biEdit;// �w�ƥ�
    RotateComponent temp;
    DisplayPanel displayPanel;
    int flag, i, j, k, s;
    float light, cont;
    boolean isEntered = false;
    String path;
    public int framewidth = 0;
    public int frameheight = 0;
    boolean edited = false, bEdited = false;
       
    public void setImageResource(BufferedImage s, String path, BufferedImage tempBi) {
        this.path = path;
        this.bi = s;
        this.bi2 = tempBi;
        this.biEdit = s;
        this.orig = s;
        this.framewidth = s.getWidth();
        this.frameheight = s.getHeight();
        //this.add(new JLabel(new ImageIcon(bi)));
        //this.pack();
        //this.setVisible(true);;
    }

    BufferedImage getImage() {
        if(bEdited)
            return bi;
        else 
            return biEdit;
    }
    /*public void TRescaleOp(int flag, float light, float cont){ // flag = 1, light. flag = 0, cont.
     removeAll();
     this.flag = flag;
     this.light = light;
     this.cont = cont;
     //bi2 = bi;
     isEntered = true;
     displayPanel = new DisplayPanel(bi);
     displayPanel.changeOffSet(flag,light);
     displayPanel.changeScaleFactor(flag, cont);
     displayPanel.rescale();
     displayPanel.repaint();
     System.out.println(cont+"+"+light);
     bi = displayPanel.getImage();
     ImageVFlip(  i,  j,  k,  s);
     this.add(new JLabel(new ImageIcon(displayPanel.getImage())));
     this.setVisible(true);
     //return this;
     }*/

    public void ImageVFlip(int i, int j, int k, int s, int flag, float light, float cont, int re, boolean f) throws IOException {		// i ����W�U���k����  j����
        
        edited = true;
        this.removeAll();
        this.i = i;
        this.j = j;
        this.k = k;
        this.s = s;
        //if(bEdited == false)
            biEdit = orig;
        //else
            //biEdit = bi;
                
        if (s == 1) {
            displayPanel = new DisplayPanel(biEdit);
            displayPanel.changeOffSet(flag, light);
            displayPanel.changeScaleFactor(flag, cont);
            displayPanel.rescale();
            displayPanel.repaint();
            biEdit = displayPanel.getImage();
            isEntered = true;
        }
        if (!isEntered) {
            biEdit = orig;
        }
        switch (flag) {
            case 1:
                displayPanel = new DisplayPanel(biEdit, false);
                displayPanel.sharpen();
                displayPanel.repaint();
                biEdit = displayPanel.getImage();
            case 2:
                displayPanel = new DisplayPanel(biEdit, false);
                displayPanel.blur();
                displayPanel.repaint();
                biEdit = displayPanel.getImage();
            case 3:
                displayPanel = new DisplayPanel(biEdit, false);
                displayPanel.sharpen();
                displayPanel.blur();
                displayPanel.repaint();
                biEdit = displayPanel.getImage();
            case 0:
                displayPanel = new DisplayPanel(biEdit, false);
                displayPanel.reset();
                displayPanel.repaint();
                biEdit = displayPanel.getImage();
        }
        if (re == 1) {
            displayPanel = new DisplayPanel(biEdit, true);
            displayPanel.reverseLUT();
            displayPanel.applyFilter();
            displayPanel.repaint();
            biEdit = displayPanel.getImage();
        } else if (re == -1) {
            displayPanel = new DisplayPanel(biEdit, true);
            displayPanel.reset();
            displayPanel.repaint();
            biEdit = displayPanel.getImage();
        }
        //super("javahome.idv.tw");

        //this.setLayout(new GridLayout());
        //System.out.println(i+" & "+this.i);
        switch (i) {
            case 0:
                break;
            case 1:
                biEdit = flip(biEdit);
                break;
            case 2:
                biEdit = flip2(biEdit);
                break;
            case 3:
                biEdit = flip2(flip(biEdit));
                break;
        }
        if (j == 1) {
            //biEdit = orig;
        } else {
            temp = new RotateComponent(biEdit, j);
            biEdit = temp.rotateImage(biEdit, j);
        }
        if (k == 0) {
            //biEdit = orig;
        } else if (k == 1) {
            biEdit = IndexColorImage(biEdit);
        } else if (k == 2) {
            biEdit = getGrayImage(biEdit);
        }
        isEntered = false;
        //biEdit = compressImage(biEdit);
        //JPanel panel = new JPanel();
        //panel.setLayout(new OverlayLayout(panel));
        //panel.add(new JLabel(new ImageIcon(bi)));
        //add(panel);
        //this.setVisible(true);
        //TRescaleOp(flag, light, cont);
        //return this;

    }

    private static BufferedImage getConvertedImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage convertedImage = null;
        Graphics2D g2D = null;
        //采用带1 字节alpha的TYPE_4BYTE_ABGR，可以修改像素的布尔透明  
        convertedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        g2D = (Graphics2D) convertedImage.getGraphics();
        g2D.drawImage(image, 0, 0, null);
        //像素替换，直接把背景颜色的像素替换成0  
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = convertedImage.getRGB(i, j);
                if (isBackPixel(rgb)) {
                    convertedImage.setRGB(i, j, 0);
                }
            }
        }
        g2D.drawImage(convertedImage, 0, 0, null);
        return convertedImage;
    }

    public static BufferedImage compressImage(BufferedImage sourceImage) throws IOException {
        if (sourceImage == null) {
            throw new NullPointerException("空图片");
        }
        BufferedImage cutedImage = null;
        BufferedImage tempImage = null;
        BufferedImage compressedImage = null;
        Graphics2D g2D = null;
        //图片自动裁剪  
        cutedImage = cutImageAuto(sourceImage);
        int width = cutedImage.getWidth();
        int height = cutedImage.getHeight();
        //图片格式为555格式  
        tempImage = new BufferedImage(width, height, BufferedImage.TYPE_USHORT_555_RGB);
        g2D = (Graphics2D) tempImage.getGraphics();
        g2D.drawImage(sourceImage, 0, 0, null);
        compressedImage = getConvertedImage(tempImage);
        //经过像素转化后的图片  
        compressedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        g2D = (Graphics2D) compressedImage.getGraphics();
        g2D.drawImage(tempImage, 0, 0, null);
        int pixel[] = new int[width * height];
        int sourcePixel[] = new int[width * height];
        int currentPixel[] = new int[width * height];
        sourcePixel = cutedImage.getRGB(0, 0, width, height, sourcePixel, 0, width);
        currentPixel = tempImage.getRGB(0, 0, width, height, currentPixel, 0, width);
        for (int i = 0; i < currentPixel.length; i++) {
            if (i == 0 || i == currentPixel.length - 1) {
                pixel[i] = 0;
                //内部像素  
            } else if (i > width && i < currentPixel.length - width) {
                int bef = currentPixel[i - 1];
                int now = currentPixel[i];
                int aft = currentPixel[i + 1];
                int up = currentPixel[i - width];
                int down = currentPixel[i + width];
                //背景像素直接置为0  
                if (isBackPixel(now)) {
                    pixel[i] = 0;
                    //边框像素和原图一样  
                } else if ((!isBackPixel(now) && isBackPixel(bef))
                        || (!isBackPixel(now) && isBackPixel(aft))
                        || (!isBackPixel(now) && isBackPixel(up))
                        || (!isBackPixel(now) && isBackPixel(down))) {
                    pixel[i] = sourcePixel[i];
                    //其他像素和555压缩后的像素一样  
                } else {
                    pixel[i] = now;
                }
                //边界像素  
            } else {
                int bef = currentPixel[i - 1];
                int now = currentPixel[i];
                int aft = currentPixel[i + 1];
                if (isBackPixel(now)) {
                    pixel[i] = 0;
                } else if ((!isBackPixel(now) && isBackPixel(bef))
                        || (!isBackPixel(now) && isBackPixel(aft))) {
                    pixel[i] = sourcePixel[i];
                } else {
                    pixel[i] = now;
                }
            }
        }
        compressedImage.setRGB(0, 0, width, height, pixel, 0, width);
        g2D.drawImage(compressedImage, 0, 0, null);

        return compressedImage;
    }

    private static boolean isBackPixel(int pixel) {
        int back[] = {-16777216};
        for (int i = 0; i < back.length; i++) {
            if (back[i] == pixel) {
                return true;
            }
        }
        return false;
    }

    public static BufferedImage cutImageAuto(BufferedImage image) {
        Rectangle area = getCutAreaAuto(image);
        return image.getSubimage(area.x, area.y, area.width, area.height);
    }

    /**
     * 获得裁剪图片保留区域
     *
     * @param image 要裁剪的图片
     * @return 保留区域
     */
    private static Rectangle getCutAreaAuto(BufferedImage image) {
        if (image == null) {
            throw new NullPointerException("图片为空");
        }
        int width = image.getWidth();
        int height = image.getHeight();
        int startX = width;
        int startY = height;
        int endX = 0;
        int endY = 0;
        int[] pixel = new int[width * height];

        pixel = image.getRGB(0, 0, width, height, pixel, 0, width);
        for (int i = 0; i < pixel.length; i++) {
            if (isCutBackPixel(pixel[i])) {
                continue;
            } else {
                int w = i % width;
                int h = i / width;
                startX = (w < startX) ? w : startX;
                startY = (h < startY) ? h : startY;
                endX = (w > endX) ? w : endX;
                endY = (h > endY) ? h : endY;
            }
        }
        if (startX > endX || startY > endY) {
            startX = startY = 0;
            endX = width;
            endY = height;
        }
        return new Rectangle(startX, startY, endX - startX, endY - startY);
    }

    /**
     * 当前像素是否为背景像素
     *
     * @param pixel
     * @return
     */
    private static boolean isCutBackPixel(int pixel) {
        int back[] = {0, 8224125, 16777215, 8947848, 460551, 4141853, 8289918};
        for (int i = 0; i < back.length; i++) {
            if (back[i] == pixel) {
                return true;
            }
        }
        return false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(bi, 0, 0, this);
    }
    /*public JPanel getPanel(){
     return this;
     }*/

    public static BufferedImage flip(BufferedImage img) {				// �W�U����
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getColorModel()
                .getTransparency());
        Graphics2D g2 = dimg.createGraphics();
        g2.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g2.dispose();
        return dimg;
    }

    public static BufferedImage flip2(BufferedImage img) {				// ���k����
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, img.getType());
        Graphics2D g2 = dimg.createGraphics();
        g2.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g2.dispose();
        return dimg;
    }

    public BufferedImage IndexColorImage(BufferedImage image) {			// �¥�
        
    final BufferedImage blackAndWhiteImage = new BufferedImage(
            image.getWidth(null), 
            image.getHeight(null), 
            BufferedImage.TYPE_BYTE_BINARY);
    final Graphics2D g = (Graphics2D) blackAndWhiteImage.getGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return blackAndWhiteImage;

    }

    BufferedImage getGrayImage(BufferedImage bi) {						// �Ƕ�
        BufferedImage img = new BufferedImage(bi.getWidth(), bi.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2 = (Graphics2D) img.getGraphics();
        g2.drawImage(bi, 0, 0, null);
        g2.dispose();
        return img;
    }

    public int getRadius() {
        return Math.min(this.framewidth, this.frameheight);
    }

    public void CirclePane() {
        if(edited == false)
            bi = orig;
        else 
            bi = biEdit;
            
        BufferedImage output = new BufferedImage(this.framewidth, this.frameheight,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing 
        // g2.setClip(new RoundRectangle2D ...) 

        // so instead fake soft-clipping by first drawing the desired clip shape 
        // in fully opaque white with antialiasing enabled... 
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        int radius = getRadius();
        g2.fill(new Ellipse2D.Float(0, 0, radius, radius));

        // ... then compositing the image on top, 
        // using the white shape from above as alpha source 
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(bi, 0, 0, null);

        g2.dispose();

        bi = output;
        bEdited = true;
    }

    public void OvalPane(int round) {
        if(edited == false)
            bi = orig;
        else 
            bi = biEdit;
        BufferedImage output = new BufferedImage(this.framewidth, this.frameheight,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing 
        // g2.setClip(new RoundRectangle2D ...) 

        // so instead fake soft-clipping by first drawing the desired clip shape 
        // in fully opaque white with antialiasing enabled... 
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new Ellipse2D.Float(0, 0, framewidth, frameheight - round * 2));

        // ... then compositing the image on top, 
        // using the white shape from above as alpha source 
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(bi, 0, 0, null);

        g2.dispose();

        bi = output;
        bEdited = true;
    }
    public void polygon(int edge) {
        if(edited == false)
            bi = orig;
        else 
            bi = biEdit;
        int[] X=new int[20],Y=new int[20]; //宣告X,Y整數陣列，存放點的位置。
        BufferedImage output = new BufferedImage(this.framewidth, this.frameheight,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics(); 

        // This is what we want, but it only does hard-clipping, i.e. aliasing 
        // g2.setClip(new RoundRectangle2D ...) 

        // so instead fake soft-clipping by first drawing the desired clip shape 
        // in fully opaque white with antialiasing enabled... 
        g2.setComposite(AlphaComposite.Src); 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.setColor(Color.WHITE);
        int s=0; //宣告整數變數。s為for迴圈之計次變數；i為for迴圈之計次變數；k為i的for迴圈裡的for迴圈之計次變數。
		double a; //宣告a為倍精準浮點數變數，存放多邊形之公式變數值。
		final double PIDouble=Math.PI*2; //宣告PIDouble為倍精準浮點數常數，值為兩倍的圓周率。

		//------多邊形頂點公式運算------
			for(a=0;a<PIDouble;a+=PIDouble/edge)
			{
	            X[s] = (int) (Math.round((this.framewidth/2-1) * Math.cos(a)*Math.pow(10,0))/Math.pow(10,0)+(this.frameheight/2)); //存入X點。
	            Y[s] = (int) (Math.round((this.framewidth/2-1) * Math.sin(a)*Math.pow(10,0))/Math.pow(10,0)+(this.frameheight/2)); //存入Y點。
	            s++; //計次s=s+1。
			}	
		//-------------END-------------
		g2.fill(new Polygon(X, Y, edge));
        // ... then compositing the image on top, 
        // using the white shape from above as alpha source 
        g2.setComposite(AlphaComposite.SrcAtop); 
        g2.drawImage(bi, 0, 0, null); 

        g2.dispose(); 

        bi = output; 
        bEdited = true;
    } 
    public void RoundRecPane(int round) {  
        if(edited == false)
            bi = orig;
        else 
            bi = biEdit;
        BufferedImage output = new BufferedImage(this.framewidth, this.frameheight,
                BufferedImage.TYPE_INT_ARGB); 

        Graphics2D g2 = output.createGraphics(); 

        // This is what we want, but it only does hard-clipping, i.e. aliasing 
        // g2.setClip(new RoundRectangle2D ...) 

        // so instead fake soft-clipping by first drawing the desired clip shape 
        // in fully opaque white with antialiasing enabled... 
        g2.setComposite(AlphaComposite.Src); 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON); 
        g2.setColor(Color.WHITE); 
        g2.fill(new RoundRectangle2D.Float(0, 0, this.framewidth, this.frameheight, round, 
        		round)); 

        // ... then compositing the image on top, 
        // using the white shape from above as alpha source 
        g2.setComposite(AlphaComposite.SrcAtop); 
        g2.drawImage(bi, 0, 0, null); 

        g2.dispose(); 

        bi = output; 
        bEdited = true;
    }
}

class RotateComponent extends JComponent {

    BufferedImage bi;
    int theta;

    public BufferedImage getImage() {
        return bi;
    }

    public RotateComponent(BufferedImage bi, int theta) {
        this.bi = bi;
        this.theta = theta;
    }

    public Dimension getPreferredSize() {
        int max = Math.max(bi.getWidth(), bi.getHeight());
        return new Dimension(max, max);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        bi = rotateImage(bi, theta);
        g2.drawImage(bi, 0, 0, null);
    }

    BufferedImage rotateImage(BufferedImage sourceBI, float theta) {
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(theta), sourceBI.getWidth() / 2,
                sourceBI.getHeight() / 2);
        BufferedImageOp bio = new AffineTransformOp(at,
                AffineTransformOp.TYPE_BILINEAR);
        return bio.filter(sourceBI, null);
    }
}

class DisplayPanel extends JPanel {

    BufferedImage displayImage;
    BufferedImage biSrc, biDest, bi;
    Graphics2D big;
    RescaleOp rescale;
    float scaleFactor = 1.0f;
    float offset = 10;
    boolean brighten, contrastInc;
    LookupTable lookupTable;

    DisplayPanel(BufferedImage bis, boolean option) {
        //setBackground(Color.gray); // panel background color
        loadImage(bis);
        setSize(displayImage.getWidth(this), displayImage.getWidth(this)); // panel
        if (option == true) {
            createBufferedImage(option);
        } else {
            createBufferedImages2();
            bi = biSrc;
        }
    }

    public void createBufferedImages2() {
        biSrc = new BufferedImage(displayImage.getWidth(this), displayImage
                .getHeight(this), BufferedImage.TYPE_INT_RGB);

        big = biSrc.createGraphics();
        big.drawImage(displayImage, 0, 0, this);

        biDest = new BufferedImage(displayImage.getWidth(this), displayImage
                .getHeight(this), BufferedImage.TYPE_INT_RGB);
    }

    public void createBufferedImage(boolean option) {
        bi = new BufferedImage(displayImage.getWidth(this), displayImage
                .getHeight(this), BufferedImage.TYPE_INT_ARGB);

        big = bi.createGraphics();
        big.drawImage(displayImage, 0, 0, this);
    }

    public void sharpen() {
        float data[] = {-1.0f, -1.0f, -1.0f, -1.0f, 9.0f, -1.0f, -1.0f, -1.0f,
            -1.0f};
        Kernel kernel = new Kernel(3, 3, data);
        ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
                null);
        convolve.filter(biSrc, biDest);
        bi = biDest;
    }

    public void blur() {
        float data[] = {0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f,
            0.0625f, 0.125f, 0.0625f};
        Kernel kernel = new Kernel(3, 3, data);
        ConvolveOp convolve = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP,
                null);
        convolve.filter(biSrc, biDest);
        bi = biDest;
    }

    public void reset(boolean option) {
        big.setColor(Color.black);
        big.clearRect(0, 0, bi.getWidth(this), bi.getHeight(this));
        big.drawImage(displayImage, 0, 0, this);
        bi = biSrc;
    }

    public void reverseLUT() {
        byte reverse[] = new byte[256];
        for (int i = 0; i < 256; i++) {
            reverse[i] = (byte) (255 - i);
        }
        lookupTable = new ByteLookupTable(0, reverse);
    }

    public void reset() {
        big.setColor(Color.black);
        big.clearRect(0, 0, bi.getWidth(this), bi.getHeight(this));
        big.drawImage(displayImage, 0, 0, this);
    }

    public void applyFilter() {
        LookupOp lop = new LookupOp(lookupTable, null);
        lop.filter(bi, bi);
    }

    public DisplayPanel(BufferedImage sbi) {
        setBackground(Color.black);
        loadImage(sbi);
        setSize(displayImage.getWidth(this),
                displayImage.getWidth(this));
        createBufferedImages();

    }

    public BufferedImage getImage() {
        return bi;
    }

    public void loadImage(BufferedImage sbi) {
        displayImage = sbi;
    }

    public void createBufferedImages() {
        biSrc = new BufferedImage(displayImage.getWidth(this),
                displayImage.getHeight(this),
                BufferedImage.TYPE_INT_RGB);

        big = biSrc.createGraphics();
        big.drawImage(displayImage, 0, 0, this);

        biDest = new BufferedImage(displayImage.getWidth(this),
                displayImage.getHeight(this),
                BufferedImage.TYPE_INT_RGB);
        bi = biSrc;
    }

    public void changeOffSet(int flag, float light) {
        //if (flag == 1 || flag == 0) {
        if (offset < 255 && offset > -255) {
            offset = light;
        }
        //}
    }

    public void changeScaleFactor(int flag, float cont) {
        //if (flag == 0 || flag == 1) {
        if (scaleFactor < 2 && scaleFactor > 0) {
            scaleFactor = cont + 1.0f;
        }
        //}
    }

    public void rescale() {
        //System.out.println(offset+"+"+scaleFactor);
        rescale = new RescaleOp(scaleFactor, offset, null);
        rescale.filter(biSrc, biDest);
        bi = biDest;
    }

    public void update(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        paintComponent(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(bi, 0, 0, this);
    }
}
