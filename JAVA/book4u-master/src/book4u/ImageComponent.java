package book4u;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
 
public class ImageComponent extends JComponent {

    BufferedImage image;
    public ImageComponent() { 
    }

    public ImageComponent(BufferedImage image) { 
        setImage(image);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        if (image != null) {
            this.image = image;
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(image, 0);
                tracker.waitForID(0);
            } catch (InterruptedException e) {
            }
        }
        repaint(); 
    }

    public BufferedImage getImage() {
        return image;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = getSize();
        if (image != null) {
            int imageWidth = image.getWidth(this);
            int imageHeight = image.getHeight(this);
            g.drawImage(image, (dim.width - imageWidth) / 2,
                    (dim.height - imageHeight) / 2, imageWidth, imageHeight,
                    this); 
        } else {
            g.setColor(getBackground());
            g.clearRect(0, 0, dim.width, dim.height);
        }
    }

    public Dimension getPreferredSize() {
        if (image != null) {
            return (new Dimension(image.getWidth(this), image.getHeight(this)));
        } else {
            return new Dimension(100, 100);
        }
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
}