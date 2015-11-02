package book4u;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
public class G2dClipboardDemo extends JDialog {
	//TotalPane
	public JPanel TotalPane;
	//Btn
	private JButton YesBtn,NoBtn;
	public Clipboard board;
    public G2dClipboardDemo(final mainFrame mainFrame,final EditPanel panel,String title,boolean modal,int width, int height)
    {
    	super(mainFrame,title,modal);
        TotalPane = new JPanel();
        TotalPane.setLayout(null);
        JPanel pane = new JPanel();
        board = new Clipboard(width,height,TotalPane,pane);
        pane.setBounds(10, 70, width, height);
        pane.setBackground(Color.WHITE);
        pane.add(board);
        //set pencil Crusor
        Image eraser= new ImageIcon(getClass().getResource("images/pencil.png")).getImage(); 
        Cursor myCursor= this.getToolkit().createCustomCursor(eraser,new Point(30,30),"pencil");  
        pane.setCursor(myCursor);
        //YesBtn
	    YesBtn = new JButton("確定");
	    YesBtn.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  try {
	    	        File temp = File.createTempFile("image", ".png");
	    	        ImageIO.write(board.image, "PNG", new FileOutputStream(temp));
	    	        panel.testText1.insertIcon(new ImageIcon(temp.getAbsolutePath()));
	    	    } catch (IOException ex) {
	    	        ex.printStackTrace();
	    	    }
	    	  G2dClipboardDemo.this.dispose();
	      } 
	    });
	    YesBtn.setBounds(width - 170,height +80,80,30);
	    
	    //NoBtn
	    NoBtn = new JButton("取消");
	    NoBtn.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  G2dClipboardDemo.this.dispose();
	      } 
	    });
	    NoBtn.setBounds(width - 70,height + 80,80,30);
	    TotalPane.add(YesBtn);
	    TotalPane.add(NoBtn);
        TotalPane.add(pane);
        
        this.add(TotalPane);
        this.setSize(width+ 40, height+160);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setVisible(true);
    }
}
class Clipboard extends JComponent {
    private Graphics2D g2d;
    public BufferedImage image;
    private Point mPoint;
    private JButton drawColorBtn;
    private Color pencilColor = Color.BLACK;
    float drawStroke = 2.0f;
  	//塗鴉版線條粗細
  	private ImageIcon pencil1 = new ImageIcon(getClass().getResource("images/pencil_1.png"));
  	private ImageIcon pencil2 = new ImageIcon(getClass().getResource("images/pencil_2.png"));
  	private ImageIcon pencil3 = new ImageIcon(getClass().getResource("images/pencil_3.png"));
  	private ImageIcon pencil4 = new ImageIcon(getClass().getResource("images/pencil_4.png"));
  	private ImageIcon pencil5 = new ImageIcon(getClass().getResource("images/pencil_5.png"));
  	ImageIcon[] pencilimages = {pencil1,pencil2,pencil3,pencil4,pencil5};
  	Integer[] pencilimageIndex = new Integer[pencilimages.length];
  	private JComboBox pencilSizeDropDown;
  	//橡皮擦
  	private JCheckBox eraserBox;
    public Clipboard(int width, int height,JPanel TotalPane,final JPanel pane) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setColor(pencilColor);
        g2d.setStroke(new BasicStroke(drawStroke));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mPoint = e.getPoint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mPoint = null;
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point p = e.getPoint();
                if (mPoint != null) {
                    g2d.drawLine(mPoint.x, mPoint.y, p.x, p.y);
                    for (Component sibling : getParent().getComponents()) {
                        sibling.repaint();
                    }
                }
                mPoint = p;
            }
        });
        
        //畫筆色彩
        JLabel drawColor = new JLabel("顏色:");
        drawColor.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        drawColor.setBounds(10, 0, 80, 30);
        drawColorBtn=new JButton("●");
	drawColorBtn.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        drawColorBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				eraserBox.setSelected(false);
				drawColorBtn.setForeground(this.chooseColor(drawColorBtn));
				g2d.setColor(pencilColor);
			}
			private Color chooseColor(Component comp)
			{
			   pencilColor =JColorChooser.showDialog(comp,"顏色選擇",comp.getBackground());
			   return pencilColor;
			}
		});
        drawColorBtn.setBounds(10, 30, 50, 25);
        TotalPane.add(drawColor);
        TotalPane.add(drawColorBtn);
        //畫筆粗細
        JLabel PencilSize = new JLabel("畫筆/橡皮擦粗細: ");
        PencilSize.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        PencilSize.setBounds(80, 0, 150, 30);
        TotalPane.add(PencilSize);
        //粗細DropDown
        for (int i = 0; i < pencilimageIndex.length; i++) 
        {
        	pencilimageIndex[i] = i;
        }
        pencilSizeDropDown = new JComboBox(pencilimageIndex);    	
        ComboBoxRenderer pencilrenderer= new ComboBoxRenderer(pencilimages);
        pencilrenderer.setPreferredSize(new Dimension(80, 15));
        pencilSizeDropDown.setRenderer(pencilrenderer);
        pencilSizeDropDown.setMaximumRowCount(pencilimageIndex.length);
        pencilSizeDropDown.addItemListener(new ItemListener()
	    {
	    	public void itemStateChanged(ItemEvent event)
		    {
	    		if(event.getStateChange() == ItemEvent.SELECTED)
	    		{	
	    			
	    			switch(pencilSizeDropDown.getSelectedIndex())
	    			{
	    				case 0:
	    					drawStroke = 2.0f;
	    					g2d.setStroke(new BasicStroke(drawStroke));
	    					break;
	    				case 1:
	    					drawStroke = 3.0f;
	    					g2d.setStroke(new BasicStroke(drawStroke));
	    					break;
	    				case 2:
	    					drawStroke = 5.0f;
	    					g2d.setStroke(new BasicStroke(drawStroke));
	    					break;
	    				case 3:
	    					drawStroke = 7.0f;
	    					g2d.setStroke(new BasicStroke(drawStroke));
	    					break;
	    				case 4:
	    					drawStroke = 9.0f;
	    					g2d.setStroke(new BasicStroke(drawStroke));
	    					break;
	    				default:
	    					break;
	    			}
	    		}
		    }
	    });
        pencilSizeDropDown.setBounds(80, 30, 120, 25);
        TotalPane.add(pencilSizeDropDown);
        //橡皮擦
        eraserBox = new JCheckBox("橡皮擦");
        eraserBox.addItemListener(new ItemListener()
        {
			@Override
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.SELECTED)
		        {	 
					//set eraser Crusor
			        Image eraser= new ImageIcon(getClass().getResource("images/eraser.png")).getImage(); 
			        Cursor myCursor= pane.getToolkit().createCustomCursor(eraser,new Point(30,30),"eraser");  
			        pane.setCursor(myCursor);
					g2d.setColor(Color.WHITE);
		        }
		        else if(e.getStateChange() == ItemEvent.DESELECTED)
		        {
		        	//set eraser Crusor
			        Image eraser= new ImageIcon(getClass().getResource("images/pencil.png")).getImage(); 
			        Cursor myCursor= pane.getToolkit().createCustomCursor(eraser,new Point(30,30),"pencil");  
			        pane.setCursor(myCursor);
					g2d.setColor(Color.WHITE);
		        	g2d.setColor(pencilColor);
		        }
			}
        });
        eraserBox.setBounds(225, 25, 120, 40);
        TotalPane.add(eraserBox);
    }

    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);

    }
}
class ComboBoxRenderer extends JLabel implements ListCellRenderer 
{
	ImageIcon[] imagePic;
	public ComboBoxRenderer(ImageIcon[] image) 
	{
		imagePic = image;
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
	}
 
	public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus) 
	{
		//Get the selected index. (The index param isn't
		//always valid, so just use the value.)
		int selectedIndex = ((Integer)value).intValue();
		 
		//Set the icon and text.
		ImageIcon icon = imagePic[selectedIndex];
		setIcon(icon);
		if (icon != null) {
			//setText(language);
			setFont(list.getFont());
		}
		return this;
	}
	public void paint(Graphics g) {
        setBackground(Color.WHITE);
        super.paint(g);
    }
}
