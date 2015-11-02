package book4u;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.colorchooser.ColorSelectionModel;

public class Dialog extends JDialog{
    private String FontSizeNum[] = {"32","36","40","44","48","52","56","60","64","68","72","76"};
    private JTextArea text;
    private JComboBox FontSizeDropDown;
    private JPanel panel;
    private int fonts = 32;
    private JButton YesBtn,NoBtn, colorSet;
    private Color textColor;
    public Dialog(final mainFrame mainFrame,final EditPanel editpanel,String title,boolean modal,final int type){
    	super(mainFrame,title,modal);
        panel=new JPanel();
        panel.setLayout(null);   
        //size label
        JLabel sizeWord = new JLabel("文字大小: ");
	    sizeWord.setBounds(10,0,100,50);
	    panel.add(sizeWord);
	    //size Dropdown
	    FontSizeDropDown = new JComboBox(FontSizeNum);
		FontSizeDropDown.setMaximumRowCount(4);
		FontSizeDropDown.setSelectedIndex(0);
		FontSizeDropDown.setPreferredSize(new Dimension(100,30));
		FontSizeDropDown.setBounds(10,40,60,25);
		FontSizeDropDown.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent event)
		    {
				if(event.getStateChange() == ItemEvent.SELECTED){
					
					 fonts = Integer.parseInt((String)FontSizeDropDown.getSelectedItem());
				 }		
				text.setFont(new Font("微軟正黑體", Font.BOLD , fonts)); 
		    }
		});
		panel.add(FontSizeDropDown);
		//setColor
		colorSet=new JButton("選擇顏色");
	    colorSet.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event) 
			{
				text.setForeground(this.chooseColor(text));
				 
			}
			private Color chooseColor(Component comp)
			{
			   Color rsltColor =JColorChooser.showDialog(comp,"顏色選擇",comp.getBackground());
			   textColor = rsltColor;
			   return rsltColor;
			}
		});
	    colorSet.setBounds(100,40,100,25);
	    panel.add(colorSet);
	    //textarea
	    text = new JTextArea("在這裡輸入文字");
	    text.setFont(new Font("微軟正黑體", Font.BOLD, 32));
		text.setLineWrap(true);	//自動換行
		text.setWrapStyleWord(true);  //換行時字母保持字母完整性
		text.setBounds(10,80,350,200);
	    panel.add(text);

	    //YesBtn
	    YesBtn = new JButton("確定");
	    YesBtn.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        // handle the jbutton event here  
	    	  String str = text.getText();
	    	  switch(type)
  			  {
	    	  	//空心字
  			  	case 1: 
  			  		editpanel.testText1.insertComponent(new OutlineComponent(str, fonts, textColor));
  			  		break;
  			  	//斜體字
  			  	case 2:
  			  		editpanel.testText1.insertComponent(new ShearFontComponent(str, fonts, textColor));
  			  		break;
  			  	//立體字
  			  	case 3:
  			  		editpanel.testText1.insertComponent(new D3FontComponent(str, fonts, textColor));
			  		break;
  			  	case 4:
  			  		editpanel.testText1.insertComponent(new ShadowText(str, fonts, textColor));
			  		break;
  			  	default:
  			  		break;
  			  }
	    	  	
	    	  Dialog.this.dispose();
	      } 
	    });
	    YesBtn.setBounds(180,290,80,30);
	    panel.add(YesBtn);
	    //NoBtn
	    NoBtn = new JButton("取消");
	    NoBtn.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  Dialog.this.dispose();
	      } 
	    });
	    NoBtn.setBounds(280,290,80,30);
	    panel.add(NoBtn);
	    this.getContentPane().add(panel);
	    this.setSize(380,360);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
    }
    
}