package book4u;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartFrame extends JFrame {

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        StartFrame s = new StartFrame();

    }

    public StartFrame() {

        setTitle("Book 4U");
        setBak(); // 調用背景方法
        Container c = getContentPane(); // 獲取JFrame面板
        JPanel jp = new JPanel(); // 創建個JPanel
        c.setLayout(null);
        jp.setOpaque(false); // 把JPanel設置為透明 這樣就不會遮住後面的背景 這樣你就能在JPanel隨意加元件了
        c.add(jp);

        // ////////////////////////////////////////////////////////
        ImageIcon img = new ImageIcon("start.png");
        img.setImage(img.getImage().getScaledInstance(img.getIconWidth() / 3,
                img.getIconHeight() / 3, Image.SCALE_DEFAULT));
        JButton start = new JButton(img);
        start.setOpaque(true);
        start.setContentAreaFilled(false);
        start.setBorder(null);
        start.setBounds(0, 370, 100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
                 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
                 */
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>
                /* Create and display the form */
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        //將loaddata設定給loadbar秀

                        final LoadData load = new LoadData();//建立LoadData 
                        load.endValue = 40;//要讀取的檔案有40個

                        //將loaddata設定給loadbar秀
                        final LoadBar lb = new LoadBar();//建立loadbar

                        lb.setLoadObj(load);//將load記錄load資料進度的物件設定到bar裡
                        lb.startLoad();//啟動更新bar
                        Thread t = new Thread() {
                            public void run() {
                                try {
                                    new mainFrame().setVisible(true);
                                    lb.dispose();
                                } catch (Exception e) {
                                    lb.dispose();
                                    JOptionPane.showMessageDialog(null, "載入失敗", "錯誤訊息 ", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        };
                        t.start();
                        Thread tl = new Thread() {
                            LoadData lobj = load;

                            public void run() {
                                while (lobj.currentProeess < lobj.endValue) {
                                    try {
                                        lobj.currentProeess++;//此行必須要設定，代表目前load進來第n個檔
                                        Thread.sleep(100);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        };
                        try {
                            tl.start();
                        } catch (Exception e) {
                        }
                        dispose();
                    }
                });
            }
        });
        // ////////////////////////////////////////////////////////////////
        ImageIcon img_load = new ImageIcon(
                "Load.png");

        img_load.setImage(img_load.getImage().getScaledInstance(
                img_load.getIconWidth() / 3, img_load.getIconHeight() / 3,
                Image.SCALE_DEFAULT));
        JButton load = new JButton(img_load);

        load.setOpaque(
                true);
        load.setContentAreaFilled(
                false);
        load.setBorder(
                null);
        load.setBounds(
                100, 370, 100, 100);
        load.addActionListener(new ActionListener() {
            private mainFrame main;
            public void actionPerformed(ActionEvent e) {
              new mainFrame().openFile();
              //main.openFile();

            }
        });


        // ///////////////
        ImageIcon img_exit = new ImageIcon(
                "Exit.png");

        img_exit.setImage(img_exit.getImage().getScaledInstance(
                img_exit.getIconWidth() / 3, img_exit.getIconHeight() / 3,
                Image.SCALE_DEFAULT));
        JButton exit = new JButton(img_exit);

        exit.setOpaque(
                true);
        exit.setContentAreaFilled(
                false);
        exit.setBorder(
                null);
        exit.setBounds(
                600, 370, 100, 100);

        exit.addActionListener(hbtHandler);

        add(start);

        add(load);

        add(exit);

        setLocation(
                170, 170);
        setSize(
                700, 480);
        setResizable(
                false);
        setVisible(
                true);
    }

    public void setBak() {
        ((JPanel) this.getContentPane()).setOpaque(false);

        ImageIcon img = new ImageIcon("Book4U.jpg");
        img.setImage(img.getImage().getScaledInstance(700, 450,
                Image.SCALE_DEFAULT));
        JLabel background = new JLabel(img);
        this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

    }
    private ButtonHandler hbtHandler = new ButtonHandler(); // 為按鈕
    // 註冊listener,用來處理按鈕事件

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent Event) {
            System.exit(0);
        }
    }
    // 註冊listener,用來處理按鈕事件
}