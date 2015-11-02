package book4u;
import java.awt.*;
import java.awt.event.*;

import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
//選取顏色
import javax.swing.colorchooser.ColorSelectionModel;
//wordArt Dialog
public class EditPanel extends JPanel {
	private JRadioButton leftRadio ;
	private JRadioButton centerRadio ;
	private JRadioButton rightRadio ;
	private JButton boldBtn,italicBtn,underlineBtn;
	private JComboBox FontSizeDropDown;
	private String FontSizeNum[] = {"20","22","24","26","28","36","48","52","56","60","64","68","72","76","80","84","88","92","96"};
	private JComboBox typeFaceDropDown;
	private JButton ClipboardBtn,colorSet;
	private String typeFaceNames[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();  //取得系統字型
	public JTextPane testText1;
	private JTextField ClipboardHeight,ClipboardWidth;
	private Color rsltColor = Color.BLACK;
	private static final Color colorRGB = new Color(98, 134, 167);
	int fonts = 20;
	float drawStroke = 1.0f;
	int bold = Font.PLAIN;
	int italic=Font.PLAIN;
	String fontfamily = "Serif";
	//文字藝術師
	private ImageIcon choice = new ImageIcon(getClass().getResource("images/choice.jpg"));
	private ImageIcon imghollowWord = new ImageIcon(getClass().getResource("images/hollowWord.jpg"));
	private ImageIcon ShearFont = new ImageIcon(getClass().getResource("images/ShearFont.png"));
	private ImageIcon D3font = new ImageIcon(getClass().getResource("images/3Dfont.png"));
	private ImageIcon D2shadow = new ImageIcon(getClass().getResource("images/2Dshadow.png"));
	ImageIcon[] images = {choice,imghollowWord,ShearFont,D3font,D2shadow};
	Integer[] imageIndex = new Integer[images.length];
	private JComboBox wordArt;
	private JPanel testTextPanel;

	public void call(JPanel frame, JTextPane textPane)
	{
		this.testText1 = textPane;
		testText1.setBounds(textPane.getX(), textPane.getY(), textPane.getWidth(), textPane.getHeight()); //設定文字方塊在BorderLayout.Center中的位置
		testText1.setFont(new Font(fontfamily,Font.PLAIN,fonts));
		testTextPanel.add(testText1);	
		frame.add(testText1,0);
	}
	public EditPanel(final mainFrame frame)
	{	
		this.setLayout(new BorderLayout());
		/*--Test Panel--*/		
		testText1 = new JTextPane();
		
		testTextPanel = new JPanel();		
		testTextPanel.setLayout(null);
		
		/*---text編輯功能---*/
		JPanel textPanel = new JPanel();
		//字體標題
		JLabel typeFace = new JLabel("<html><u>1. 字體 :</u></html>");
		typeFace.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		GridBagConstraints c0 = new GridBagConstraints();
		c0.gridx = 0;
	    c0.gridy = 0;
	    c0.gridwidth= 3;
        c0.anchor = GridBagConstraints.WEST;
		//字體
		typeFaceDropDown = new JComboBox(typeFaceNames);
		typeFaceDropDown.setMaximumRowCount(4);
		typeFaceDropDown.setSelectedIndex(4);
		typeFaceDropDown.setPreferredSize(new Dimension(170,25));
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
	    c1.gridy = 1;
	    c1.gridwidth= 2;
	    c1.anchor = GridBagConstraints.WEST;
		//大小
		FontSizeDropDown = new JComboBox(FontSizeNum);
		FontSizeDropDown.setMaximumRowCount(4);
		FontSizeDropDown.setSelectedIndex(0);
		FontSizeDropDown.setPreferredSize(new Dimension(50,25));
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 2;
	    c2.gridy = 1;
	    c2.anchor = GridBagConstraints.WEST;
	    //置中,靠左,靠右
	    ButtonGroup radioGroup = new ButtonGroup();
	    leftRadio = new JRadioButton("靠左",true);
	    leftRadio.setBackground(colorRGB);
	    centerRadio = new JRadioButton("置中");
	    centerRadio.setBackground(colorRGB);
	    rightRadio = new JRadioButton("靠右");
	    rightRadio.setBackground(colorRGB);
	    radioGroup.add(leftRadio);
	    radioGroup.add(centerRadio);
	    radioGroup.add(rightRadio);
	    GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
	    c3.gridy = 2;
	    c3.anchor = GridBagConstraints.WEST;
	    GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 1;
	    c4.gridy = 2;
	    c4.anchor = GridBagConstraints.CENTER;
	    GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 2;
	    c5.gridy = 2;
	    c5.anchor = GridBagConstraints.EAST;
	    
	    //粗體, 底線, 斜體
	     //粗體, 底線, 斜體
	    boldBtn = new JButton();
            javax.swing.Action boldAction=new StyledEditorKit.BoldAction();
            boldBtn.setAction(boldAction);
            boldBtn.setText("B");
            italicBtn = new JButton();
            javax.swing.Action italicAction=new StyledEditorKit.ItalicAction();
            italicBtn.setAction(italicAction);
            italicBtn.setText("I");
            underlineBtn = new JButton();
            javax.swing.Action underlineAction=new StyledEditorKit.UnderlineAction();
            underlineBtn.setAction(underlineAction);
            underlineBtn.setText("U");
	    GridBagConstraints c7 = new GridBagConstraints();
	    c7.gridx = 0;
	    c7.gridy = 3;
	    c7.anchor = GridBagConstraints.WEST;
	    GridBagConstraints c8 = new GridBagConstraints();
	    c8.gridx = 1;
	    c8.gridy = 3;
	    c8.anchor = GridBagConstraints.CENTER;
	    GridBagConstraints c9 = new GridBagConstraints();
	    c9.gridx = 2;
	    c9.gridy = 3;
	    c9.anchor = GridBagConstraints.EAST;
	    
	    //顏色
	    JLabel color = new JLabel("顏色:  ");
	    color.setFont(new Font("微軟正黑體", Font.BOLD, 16));
	    GridBagConstraints c10 = new GridBagConstraints();
	    c10.gridx = 0;
	    c10.gridy = 4;
	    c10.gridwidth= 3;
	    c10.anchor = GridBagConstraints.WEST;
	    
	    colorSet=new JButton("●");
	    colorSet.setFont(new Font("微軟正黑體", Font.BOLD, 16));
	    colorSet.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				 colorSet.setForeground(this.chooseColor(testText1));
				 javax.swing.Action action=new StyledEditorKit.ForegroundAction("color",rsltColor);
                                 action.actionPerformed(null);
			}
			private Color chooseColor(Component comp)
			{
			   rsltColor =JColorChooser.showDialog(comp,"顏色選擇",comp.getBackground());
			   return rsltColor;
			}
		});
		GridBagConstraints c11 = new GridBagConstraints();
	    c11.gridx = 1;
	    c11.gridy = 4;
	    c11.anchor = GridBagConstraints.WEST;
	    
	    
	    //分隔線
	    LineComponent line = new LineComponent(0, 20, 200, 20, Color.WHITE);  //fx,fy,tx,ty
        line.setStroke(new BasicStroke(3f));
        GridBagConstraints c13 = new GridBagConstraints();
        c13.gridx = 0;
	    c13.gridy = 5;
	    c13.gridwidth= 3;
        c13.anchor = GridBagConstraints.WEST;
	    //進階設定label
	    JLabel advanceSetup = new JLabel("<html><u>2. 文字藝術師 :</u></html>");
	    advanceSetup.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		GridBagConstraints c12 = new GridBagConstraints();
		c12.gridx = 0;
	    c12.gridy = 6;
	    c12.gridwidth= 3;
        c12.anchor = GridBagConstraints.WEST;
        //文字藝術師
        for (int i = 0; i < imageIndex.length; i++) 
        {
        	imageIndex[i] = i;
        }
        wordArt = new JComboBox(imageIndex);    	
        ComboBoxRenderer renderer= new ComboBoxRenderer(images);
        renderer.setPreferredSize(new Dimension(200, 30));
        wordArt.setRenderer(renderer);
        wordArt.setMaximumRowCount(images.length);
        GridBagConstraints c14 = new GridBagConstraints();
	    c14.gridx = 0;
	    c14.gridy = 7;
	    c14.gridwidth= 3;
	    c14.anchor = GridBagConstraints.WEST;
	    
	    //分隔線
	    LineComponent line2 = new LineComponent(0, 30, 200, 30, Color.WHITE);  //fx,fy,tx,ty
        line2.setStroke(new BasicStroke(3f));
        GridBagConstraints c18 = new GridBagConstraints();
        c18.gridx = 0;
	    c18.gridy = 8;
	    c18.gridwidth= 3;
        c18.anchor = GridBagConstraints.WEST;
        
	    //塗鴉版
	    JLabel ClipboardLabel = new JLabel("<html><u>3. 塗鴉版 :</u></html>");
	    ClipboardLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		GridBagConstraints c15 = new GridBagConstraints();
		c15.gridx = 0;
	    c15.gridy = 9;
	    c15.gridwidth= 3;
	    c15.anchor = GridBagConstraints.WEST;
	    
	    //塗鴉板大小
	    JLabel ClipboardShapeLabel = new JLabel("  Width * Height : ");
	    ClipboardLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		GridBagConstraints c17 = new GridBagConstraints();
		c17.gridx = 0;
	    c17.gridy = 10;
	    c17.gridwidth= 3;
        c17.anchor = GridBagConstraints.WEST;
        
        JPanel heightAndWidth = new JPanel();
        heightAndWidth.setBackground(colorRGB);
        ClipboardWidth = new JTextField("300",4);
        ClipboardWidth.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        ClipboardWidth.setHorizontalAlignment(ClipboardWidth.CENTER);
        JLabel multi = new JLabel("*");
        ClipboardHeight = new JTextField("200",4);
        ClipboardHeight.setFont(new Font("微軟正黑體", Font.BOLD, 14));
        ClipboardHeight.setHorizontalAlignment(ClipboardHeight.CENTER);
        heightAndWidth.add(ClipboardWidth);
        heightAndWidth.add(multi);
        heightAndWidth.add(ClipboardHeight);
        GridBagConstraints c19 = new GridBagConstraints();
		c19.gridx = 0;
	    c19.gridy = 11;
	    c19.gridwidth= 3;
        c19.anchor = GridBagConstraints.WEST;
        //建立Btn
	    ClipboardBtn = new JButton("建立塗鴉版");
	    ClipboardBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				new G2dClipboardDemo(frame,EditPanel.this,"塗鴉版",true, Integer.parseInt(ClipboardWidth.getText()),Integer.parseInt(ClipboardHeight.getText()));
			}
		});
	    GridBagConstraints c16 = new GridBagConstraints();
		c16.gridx = 0;
	    c16.gridy = 14;
	    c16.gridwidth= 3;
        c16.anchor = GridBagConstraints.WEST;
	    
        
		//加入textPanel
	    textPanel.setBackground(colorRGB);
		textPanel.setLayout(new GridBagLayout());
		textPanel.add(typeFace,c0);
		textPanel.add(typeFaceDropDown,c1);
		textPanel.add(FontSizeDropDown,c2);
		textPanel.add(leftRadio,c3);
		textPanel.add(centerRadio,c4);
		textPanel.add(rightRadio,c5);
		textPanel.add(boldBtn,c7);
		textPanel.add(italicBtn,c8);
		textPanel.add(underlineBtn,c9);
		textPanel.add(color,c10);
		textPanel.add(colorSet,c11);
		textPanel.add(advanceSetup,c12);
		textPanel.add(line,c13);
		textPanel.add(wordArt,c14);
		textPanel.add(line2,c18);
		textPanel.add(ClipboardLabel,c15);
		textPanel.add(ClipboardShapeLabel,c17);
		textPanel.add(heightAndWidth,c19);
		textPanel.add(ClipboardBtn,c16);
		
		this.add(textPanel,BorderLayout.CENTER);
		//register events for TextEdit
		TextFontHandler handler = new TextFontHandler();
		typeFaceDropDown.addItemListener(handler);
		FontSizeDropDown.addItemListener(handler);
		leftRadio.addItemListener(handler);
	    centerRadio.addItemListener(handler);
	    rightRadio.addItemListener(handler);
	    wordArt.addItemListener(new ItemListener()
	    {
	    	public void itemStateChanged(ItemEvent event)
		    {
	    		if(event.getStateChange() == ItemEvent.SELECTED)
	    		{	
	    			System.out.println(wordArt.getSelectedIndex());
	    			switch(wordArt.getSelectedIndex())
	    			{
	    				case 1:
	    					//空心字
	    					new Dialog(frame,EditPanel.this,"編輯文字藝術師文字",true,1);
	    					break;
	    				case 2:
	    					//斜體字
	    					new Dialog(frame,EditPanel.this,"編輯文字藝術師文字",true,2);
	    					break;
	    				case 3:
	    					//立體字
	    					new Dialog(frame,EditPanel.this,"編輯文字藝術師文字",true,3);
	    					break;
	    				case 4:
	    					//陰影
	    					new Dialog(frame,EditPanel.this,"編輯文字藝術師文字",true,4);
	    					break;
	    				default:
	    					break;
	    			}
	    			wordArt.setSelectedIndex(0);
	    		}
	    		
	    			
		    }
	    });
	    
	    
		
	}
	private class TextFontHandler implements ItemListener
	{

		public void itemStateChanged(ItemEvent e)
	    {		
		    if(e.getSource() == centerRadio)
			{
		         if(e.getStateChange() == ItemEvent.SELECTED)
		         {
		        	 StyledDocument doc = testText1.getStyledDocument();
		        	 SimpleAttributeSet center = new SimpleAttributeSet();
		        	 StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		        	 doc.setParagraphAttributes(0, doc.getLength(), center, false);
		         }
		    }
			else if(e.getSource() == rightRadio)
			{
		         if(e.getStateChange() == ItemEvent.SELECTED)
		         {
		        	 StyledDocument doc = testText1.getStyledDocument();
		        	 SimpleAttributeSet center = new SimpleAttributeSet();
		        	 StyleConstants.setAlignment(center, StyleConstants.ALIGN_RIGHT);
		        	 doc.setParagraphAttributes(0, doc.getLength(), center, false);
		         }
		    }
			else if(e.getSource() == leftRadio)
			{
		         if(e.getStateChange() == ItemEvent.SELECTED)
		         {
		        	 StyledDocument doc = testText1.getStyledDocument();
		        	 SimpleAttributeSet center = new SimpleAttributeSet();
		        	 StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
		        	 doc.setParagraphAttributes(0, doc.getLength(), center, false);
		         }
		    }
			if(e.getSource() == FontSizeDropDown)
			{
				for(int i=0;i<FontSizeNum.length;i++){
		            if(FontSizeNum[FontSizeDropDown.getSelectedIndex()].equals(FontSizeNum[i])){
		                //System.out.println("Done");
		                String size=FontSizeNum[i];
		                int value=Integer.valueOf(FontSizeNum[i]);
		                javax.swing.Action action=new StyledEditorKit.FontSizeAction(size,value);
		                action.actionPerformed(null);
		                //FontSize.setAction(new StyledEditorKit.FontSizeAction(size,value));
		                break;
		            }
		        }
			}
			if(e.getSource() == typeFaceDropDown)
			{
				
				 if(e.getStateChange() == ItemEvent.SELECTED){
					 fontfamily = (String)typeFaceDropDown.getSelectedItem();
					 javax.swing.Action action=new StyledEditorKit.FontFamilyAction(fontfamily,fontfamily);
	                 action.actionPerformed(null);
				 }
			}
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
}
