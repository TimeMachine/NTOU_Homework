package book4u;

import book4u.LoadData;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoadBar extends JFrame implements Runnable{
    private boolean isRun;
    private JProgressBar progressBar;
    private LoadData loadObj;
    private JLabel percent;
    
    public LoadBar() {
        LoadBarInit();
    }
    
    public LoadBar(String title) {
        super(title);
        LoadBarInit();
    }
    
    public void LoadBarInit(){
        Container  c = getContentPane();
        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(null);
        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        progressBar.setBounds(20,25,200,20);
        progressPanel.add(progressBar,BorderLayout.CENTER);
        progressBar.setOpaque(false);
        percent = new JLabel("0%");
        percent.setBounds(110, 0, 40, 20);
        progressPanel.add(percent,BorderLayout.CENTER);
        c.add(progressPanel); 
        
        setBounds(300,370,250,100);
        setResizable(false);//視窗放大按鈕無效
        //setAlwaysOnTop(true);
       // setDefaultCloseOperation(HIDE_ON_CLOSE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void setLoadObj(LoadData lobj){
        loadObj = lobj;
    }
    
    public void startLoad(){
        isRun = true;
        Thread load = new Thread(this);
        load.start();
    }
    
    public void stopLoad(){
        isRun = false;
    }
    
    public void run(){
        int value = loadObj.currentProeess;
        int end = loadObj.endValue;
        while(isRun && (value < end)){
            value = loadObj.currentProeess;
            int percentValue = (value * 100)/end;
            progressBar.setValue(percentValue);
            percent.setText(percentValue+"%");
            Thread.yield();
        }
        progressBar.setValue(100);
        //System.out.println("load compile...");
        //System.exit(0);//demo時才要離開程式
        //clear();
    }
    
    public void clear(){
        loadObj = null;
        setVisible(false);
    }


    /**
     * @param args
     */

}