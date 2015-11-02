/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package book4u;

/**
 *
 * @author CHANG
 */
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PicFrameEdit extends JPanel implements java.io.Serializable {
    // ImageEdit instance Variable

    JPanel picPanel;
    JLabel label1, angleLabel, picLabel, picMode, Light, Constract;
    JComboBox angleSelect, modeSelect;
    String[] angle = {"預設", "0度", "90度", "180度", "270度", "順時針旋轉", "逆時針旋轉"};
    String[] mode = {"預設", "黑白", "灰階"};
    JCheckBox topDownRotate;
    JCheckBox leftRightRotate;
    JRadioButton reverseColor;
    JRadioButton reset;
    JCheckBox sharpen;
    JCheckBox blur;
    ButtonGroup bgroup = new ButtonGroup();
    JSlider light, contract;
    JTextField lightText, contractText;
    private static final Color colorRGB = new Color(98, 134, 167);
    String ShapeType;
    //
    ImageVFlipDemo TBR, imageProcess;
    int controli, controlj, controlk, controls;
    int itd = 0, ilr = 0, ira = 0;
    int flag = 0;
    int reFlag = 0;
    JPanel pp;
    JScrollPane jsp;
    float lloffset = 10.0f;
    float llscaleFactor = 1.0f;
    CardLayout card;
    //bufImage pic;
    BufferedImage bi567;
    boolean yytest = false;
    String exPath = "0";
    int ShapeInfomation;
    int PanelNum = -1;
    //

    PicFrameEdit() {
        super(new BorderLayout());
        imageProcess = new ImageVFlipDemo();
    }

    public JPanel imageEditPanel() {
        picPanel = new JPanel();
        picPanel.setLayout(new GridBagLayout());
        picPanel.setBackground(colorRGB);
        //frame.setVisible(true);
        //frame.setSize(500,500);
        //

        pp = new JPanel();
        /*
         JLabel orig = new JLabel(new ImageIcon(bi));
         JPanel ss = new JPanel();
         ss.add(orig);
         */

        // 
        label1 = new JLabel("方向");
        label1.setBackground(colorRGB);
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 0;
        c1.anchor = GridBagConstraints.WEST;
        //
        angleLabel = new JLabel("角度");
        angleLabel.setBackground(colorRGB);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 1;
        c2.anchor = GridBagConstraints.WEST;
        //
        angleSelect = new JComboBox(angle);

        angleSelect.setSelectedIndex(0);
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 1;
        c3.anchor = GridBagConstraints.EAST;
        //
        topDownRotate = new JCheckBox("上下旋轉");
        topDownRotate.setBackground(colorRGB);
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        c4.gridy = 2;
        c4.anchor = GridBagConstraints.WEST;
        //
        leftRightRotate = new JCheckBox("左右旋轉");
        leftRightRotate.setBackground(colorRGB);
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 3;
        c5.anchor = GridBagConstraints.WEST;
        //
        LineComponent line = new LineComponent(0, 20, 200, 20, Color.WHITE);  //fx,fy,tx,ty
        line.setStroke(new BasicStroke(3f));
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 0;
        c6.gridy = 4;
        c6.gridwidth = 10;
        c6.anchor = GridBagConstraints.WEST;
        //
        picLabel = new JLabel("圖像增強:");
        picLabel.setBackground(colorRGB);
        GridBagConstraints c7 = new GridBagConstraints();
        c7.gridx = 0;
        c7.gridy = 5;
        c7.anchor = GridBagConstraints.WEST;
        //
        reverseColor = new JRadioButton("Reverse Color");
        reverseColor.setBackground(colorRGB);
        GridBagConstraints c8 = new GridBagConstraints();
        c8.gridx = 0;
        c8.gridy = 6;
        c8.anchor = GridBagConstraints.WEST;
        //
        reset = new JRadioButton("回復");
        reset.setBackground(colorRGB);
        GridBagConstraints c9 = new GridBagConstraints();
        c9.gridx = 0;
        c9.gridy = 7;
        c9.anchor = GridBagConstraints.WEST;
        //
        picMode = new JLabel("模式");
        picMode.setBackground(colorRGB);
        GridBagConstraints c10 = new GridBagConstraints();
        c10.gridx = 0;
        c10.gridy = 8;
        c10.anchor = GridBagConstraints.WEST;
        //
        modeSelect = new JComboBox(mode);
        modeSelect.setSelectedIndex(0);
        GridBagConstraints c11 = new GridBagConstraints();
        c11.gridx = 0;
        c11.gridy = 8;
        c11.anchor = GridBagConstraints.EAST;
        //
        Light = new JLabel("明亮度:");
        Light.setBackground(colorRGB);
        GridBagConstraints c12 = new GridBagConstraints();
        c12.gridx = 0;
        c12.gridy = 9;
        c12.anchor = GridBagConstraints.WEST;
        //
        light = new JSlider(-255, 255, 0);
        light.setBackground(colorRGB);
        GridBagConstraints c13 = new GridBagConstraints();
        c13.gridx = 0;
        c13.gridy = 10;
        c13.anchor = GridBagConstraints.WEST;
        //
        lightText = new JTextField(10);
        lightText.setText("0");

        GridBagConstraints c14 = new GridBagConstraints();
        c14.gridx = 0;
        c14.gridy = 11;
        c14.gridwidth = 3;
        c14.anchor = GridBagConstraints.WEST;
        //
        Constract = new JLabel("對比度");
        Constract.setBackground(colorRGB);
        GridBagConstraints c15 = new GridBagConstraints();
        c15.gridx = 0;
        c15.gridy = 12;
        c15.anchor = GridBagConstraints.WEST;
        //
        contract = new JSlider(-100, 100, 0);
        contract.setBackground(colorRGB);
        GridBagConstraints c16 = new GridBagConstraints();
        c16.gridx = 0;
        c16.gridy = 13;
        c16.anchor = GridBagConstraints.WEST;
        //
        contractText = new JTextField(10);
        contractText.setText("0");
        GridBagConstraints c17 = new GridBagConstraints();
        c17.gridx = 0;
        c17.gridy = 14;
        c17.anchor = GridBagConstraints.WEST;
        //
        LineComponent line2 = new LineComponent(0, 20, 200, 20, Color.WHITE);  //fx,fy,tx,ty
        line2.setStroke(new BasicStroke(3f));
        GridBagConstraints c18 = new GridBagConstraints();
        c18.gridx = 0;
        c18.gridy = 15;
        c18.gridwidth = 5;
        c18.anchor = GridBagConstraints.WEST;
        //
        sharpen = new JCheckBox("尖銳化");
        sharpen.setBackground(colorRGB);
        GridBagConstraints c19 = new GridBagConstraints();
        c19.gridx = 0;
        c19.gridy = 16;
        c19.anchor = GridBagConstraints.WEST;
        //
        blur = new JCheckBox("模糊化");
        blur.setBackground(colorRGB);
        GridBagConstraints c20 = new GridBagConstraints();
        c20.gridx = 0;
        c20.gridy = 17;
        c20.anchor = GridBagConstraints.WEST;
        //  	

        //      

        PicFrameEdit.ImageHandler handler = new PicFrameEdit.ImageHandler();

        topDownRotate.addItemListener(handler);
        leftRightRotate.addItemListener(handler);
        reverseColor.addItemListener(handler);
        reset.addItemListener(handler);
        sharpen.addItemListener(handler);
        blur.addItemListener(handler);
        angleSelect.addItemListener(handler);
        modeSelect.addItemListener(handler);

        lightText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(lightText.getText()) < 0 || Integer.parseInt(lightText.getText()) > 255) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0 and small than 255",
                            "Error Massage", JOptionPane.ERROR_MESSAGE);
                    lightText.setText("0");
                } else {
                    light.setValue(Integer.parseInt(lightText.getText()));
                }
            }
        });
        light.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lightText.setText(String.valueOf(light.getValue()));
                lloffset = Integer.parseInt(String.valueOf(light.getValue())) / 1.0f;
                controls = 1;
                //flag = 1;
                //  pp.removeAll();

                if (yytest) {
                    for (Component c : pp.getComponents()) {
                        if (c instanceof labelStore) {
                            pp.remove(c);
                        }
                    }
                    try {
                        imageProcess.ImageVFlip(controli, controlj, controlk, controls, 1, lloffset, llscaleFactor, reFlag, modified);
                        if (ShapeType == "oval") {
                            imageProcess.OvalPane(ShapeInfomation);
                        } else if (ShapeType == "polygon") {
                            imageProcess.polygon(ShapeInfomation);
                        } else if (ShapeType == "roundRectangle") {
                            imageProcess.RoundRecPane(ShapeInfomation);
                        } else if (ShapeType == "circle") {
                            imageProcess.CirclePane();
                        }
                    } catch (IOException ex) {
                    }
                    BufferedImage tr = scale(imageProcess.getImage(), pp.getWidth(), pp.getHeight());
                    label = new labelStore(new ImageIcon(tr), path, type);
                    label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                    pp.add(label);
                    pp.updateUI();
                    //f.modified = true;
                    //pp.add(f);
                    //mainframe.border.call(pp, path, tr, mainframe);
                }
                pp.updateUI();
            }
        });
        light.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                String typed = lightText.getText();
                light.setValue(0);
                if (!typed.matches("\\d+") || typed.length() > 4) {
                    System.out.print("ho");
                    return;
                }
                int value = Integer.parseInt(typed);
                light.setValue(value);
            }
        });
        contract.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                contractText.setText(String.valueOf(contract.getValue()));
                llscaleFactor = Integer.parseInt(String.valueOf(contract.getValue())) / 100.0f;
                //flag = 0;
                controls = 1;
                //pp.removeAll();
                if (yytest) {
                    for (Component c : pp.getComponents()) {
                        if (c instanceof labelStore) {
                            pp.remove(c);
                        }
                    }
                    try {
                        imageProcess.ImageVFlip(controli, controlj, controlk, controls, 0, lloffset, llscaleFactor, reFlag, modified);
                        if (ShapeType == "oval") {
                            imageProcess.OvalPane(ShapeInfomation);
                        } else if (ShapeType == "polygon") {
                            imageProcess.polygon(ShapeInfomation);
                        } else if (ShapeType == "roundRectangle") {
                            imageProcess.RoundRecPane(ShapeInfomation);
                        } else if (ShapeType == "circle") {
                            imageProcess.CirclePane();
                        }
                    } catch (IOException ex) {
                    }
                    BufferedImage tr = scale(imageProcess.getImage(), pp.getWidth(), pp.getHeight());
                    label = new labelStore(new ImageIcon(tr), path, type);
                    label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                    pp.add(label);
                    pp.updateUI();
                    // f.modified = true;
                    // pp.add(f);
                    //mainframe.border.call(pp, path, tr, mainframe);
                }
                pp.updateUI();

                //TBR.getPanel().updateUI();
            }
        });
        contractText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(contractText.getText()) < 0 || Integer.parseInt(contractText.getText()) > 80) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0 and small than 80",
                            "Error Massage", JOptionPane.ERROR_MESSAGE);
                    contractText.setText("0");
                } else {
                    contract.setValue(Integer.parseInt(contractText.getText()));
                }
            }
        });
        contract.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = contractText.getText();
                contract.setValue(0);
                if (!typed.matches("\\d+") || typed.length() > 3) {
                    return;
                }
                int value = Integer.parseInt(typed);
                contract.setValue(value);
            }
        });
        //		�������������������� �H�W���ƥ�B�z ��������������������
        //
        bgroup.add(reverseColor);
        bgroup.add(reset);
        picPanel.add(label1, c1);
        picPanel.add(angleLabel, c2);
        picPanel.add(angleSelect, c3);
        picPanel.add(topDownRotate, c4);
        picPanel.add(leftRightRotate, c5);
        picPanel.add(line, c6);
        picPanel.add(picLabel, c7);
        picPanel.add(reverseColor, c8);
        picPanel.add(reset, c9);
        picPanel.add(picMode, c10);
        picPanel.add(modeSelect, c11);
        picPanel.add(Light, c12);
        picPanel.add(light, c13);
        picPanel.add(lightText, c14);
        picPanel.add(Constract, c15);
        picPanel.add(contract, c16);
        picPanel.add(contractText, c17);
        picPanel.add(line2, c18);
        picPanel.add(sharpen, c19);
        picPanel.add(blur, c20);

        //

        //
        if (yytest) {
            //TBR = new ImageVFlipDemo(bi, path, tempBi2);
            //pp.add(TBR);
        }
        pp.updateUI();

        return picPanel;
    }

    private class ImageHandler implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            // pp.removeAll();
            if (e.getSource() == topDownRotate) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    itd = 1;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    itd = 0;
                }
            } else if (e.getSource() == leftRightRotate) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    ilr = 2;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    ilr = 0;
                }
            } else if (e.getSource() == reverseColor) {
                reFlag = 1;
            } else if (e.getSource() == reset) {
                reFlag = -1;
            } else if (e.getSource() == sharpen) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    flag = flag + 1;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    if (flag >= 1) {
                        flag = flag - 1;
                    }
                }
            } else if (e.getSource() == blur) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //System.out.println("flag " + flag);
                    flag = flag + 2;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {

                    if (flag >= 2) {
                        flag = flag - 2;
                    }
                }
            } else if (e.getSource() == angleSelect) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (angle[0].equals(angleSelect.getSelectedItem())) {
                        // ��ϸ�J 
                        controlj = 1;
                    } else if (angle[1].equals(angleSelect.getSelectedItem())) {
                        controlj = 0;

                    } else if (angle[2].equals(angleSelect.getSelectedItem())) {
                        controlj = 90;

                    } else if (angle[3].equals(angleSelect.getSelectedItem())) {
                        controlj = 180;
                    } else if (angle[4].equals(angleSelect.getSelectedItem())) {
                        controlj = 270;

                    } else if (angle[5].equals(angleSelect.getSelectedItem())) {
                        controlj = 90;

                    } else if (angle[6].equals(angleSelect.getSelectedItem())) {
                        controlj = -90;
                    }

                }
            } else if (e.getSource() == modeSelect) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (mode[0].equals(modeSelect.getSelectedItem())) {
                        controlk = 0;
                    } else if (mode[1].equals(modeSelect.getSelectedItem())) {
                        controlk = 1;

                    } else if (mode[2].equals(modeSelect.getSelectedItem())) {
                        controlk = 2;
                    }

                }
            }
            controli = itd + ilr;
            //System.out.println(controli);
            if (yytest) {
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                try {
                    imageProcess.ImageVFlip(controli, controlj, controlk, controls, flag, lloffset, llscaleFactor, reFlag, modified);
                    if (ShapeType == "oval") {
                        imageProcess.OvalPane(ShapeInfomation);
                    } else if (ShapeType == "polygon") {
                        imageProcess.polygon(ShapeInfomation);
                    } else if (ShapeType == "roundRectangle") {
                        imageProcess.RoundRecPane(ShapeInfomation);
                    } else if (ShapeType == "circle") {
                        imageProcess.CirclePane();
                    }
                } catch (IOException ex) {
                }
                BufferedImage tr = scale(imageProcess.getImage(), pp.getWidth(), pp.getHeight());
                label = new labelStore(new ImageIcon(tr), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                //f.modified = true;
                //pp.add(f);

            }
            //pp.updateUI();
            //card.show(pp,"2");

        }
    }
    // border edti instance variable
    private JPanel borderPanel;
    private JButton circleBtn, roundrectangleBtn, ovalBtn, polygonBtn, colorBtn;
    private JSlider roundSlide, OvalSlide, PolygonSlide;
    public JTextField roundText, ovalText, polygonText;
    private Color rsltColor;
    private boolean square = false, polygon = false, oval = false, circle = false;
    //private static final Color colorRGB = new Color(98, 134, 167);
    BufferedImage bi;
    BufferedImage tempBi2;
    String path;
    String modifypath;
    boolean modified = false;
    int type;
    mainFrame mainframe;

    public void initial() {
        ShapeType = "null";

        contract.setValue(0);
        light.setValue(0);
        //lloffset = 500.0f;
        //llscaleFactor = -10.0f;
        imageProcess.bEdited = false;
        imageProcess.edited = false;

        roundText.setText("0");
        roundText.setEnabled(false);
        roundSlide.setValue(0);
        roundSlide.setEnabled(false);

        ovalText.setText("0");
        ovalText.setEnabled(false);
        OvalSlide.setValue(0);
        OvalSlide.setEnabled(false);

        polygonText.setText("3");
        polygonText.setEnabled(false);
        PolygonSlide.setValue(3);
        PolygonSlide.setEnabled(false);
    }

    public JPanel borderEditPanel() {
        this.setLayout(new BorderLayout());

        JPanel borderFrame = new JPanel();
        borderFrame.setLayout(new GridBagLayout());
        borderFrame.setBackground(colorRGB);

        JLabel circleLabel = new JLabel("<html><u>1. 框架選擇 :</u></html>");
        circleLabel.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        GridBagConstraints c0 = new GridBagConstraints();
        c0.gridx = 0;
        c0.gridy = 0;
        c0.gridwidth = 3;
        c0.anchor = GridBagConstraints.WEST;

        //方形
        roundrectangleBtn = new JButton("方型");
        roundrectangleBtn.setFont(new Font("細明體", Font.BOLD, 14));
        roundrectangleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                square = true;
                polygon = false;
                oval = false;
                circle = false;

                OvalSlide.setEnabled(false);
                OvalSlide.setValue(0);
                ovalText.setEnabled(false);
                ovalText.setText("0");
                PolygonSlide.setEnabled(false);
                PolygonSlide.setValue(3);
                polygonText.setEnabled(false);
                polygonText.setText("0");

                roundSlide.setEnabled(true);
                roundText.setEnabled(true);

                // pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.RoundRecPane(0);
                //pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "roundRectangle";
                ShapeInfomation = 0;
            }
        });
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 2;
        c1.gridwidth = 3;
        c1.anchor = GridBagConstraints.WEST;

        JPanel roundPanel = new JPanel();
        roundPanel.setBackground(colorRGB);
        roundPanel.setLayout(new FlowLayout());
        JLabel corner = new JLabel("圓角 :");
        corner.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        roundPanel.add(corner);

        JPanel blank = new JPanel();
        blank.setBackground(colorRGB);
        blank.setPreferredSize(new Dimension(35, 30));
        roundPanel.add(blank);
        roundSlide = new JSlider(0, 200, 0);
        roundSlide.setPreferredSize(new Dimension(100, 30));
        roundSlide.setBackground(colorRGB);
        roundSlide.setEnabled(false);
        roundSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                roundText.setText(String.valueOf(roundSlide.getValue()));
                int corner = Integer.parseInt(String.valueOf(roundSlide.getValue()));

                // pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.RoundRecPane(corner);
                //pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "roundRectangle";
                ShapeInfomation = corner;
            }
        });
        roundPanel.add(roundSlide);
        roundText = new JTextField(3);
        roundText.setText("0");
        roundText.setEnabled(false);
        roundText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(roundText.getText()) < 0 || Integer.parseInt(roundText.getText()) > 80) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0 and small than 100",
                            "Error Massage", JOptionPane.ERROR_MESSAGE);
                    roundText.setText("0");
                } else {
                    roundSlide.setValue(Integer.parseInt(roundText.getText()));
                }
            }
        });
        roundPanel.add(roundText);
        roundPanel.setSize(150, 20);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy = 3;
        c2.gridwidth = 1;
        c2.anchor = GridBagConstraints.WEST;

        //圓型
        circleBtn = new JButton("圓型");
        circleBtn.setFont(new Font("細明體", Font.BOLD, 14));
        circleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                square = false;
                polygon = false;
                oval = false;
                circle = true;

                roundSlide.setEnabled(false);
                roundSlide.setValue(0);
                roundText.setEnabled(false);
                roundText.setText("0");
                OvalSlide.setEnabled(false);
                OvalSlide.setValue(0);
                ovalText.setEnabled(false);
                ovalText.setText("0");
                PolygonSlide.setEnabled(false);
                PolygonSlide.setValue(3);
                polygonText.setEnabled(false);
                polygonText.setText("0");

                //pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.CirclePane();
                //pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "circle";
                ShapeInfomation = 0;
            }
        });
        GridBagConstraints c8 = new GridBagConstraints();
        c8.gridx = 0;
        c8.gridy = 9;
        c8.gridwidth = 3;
        c8.anchor = GridBagConstraints.WEST;


        //橢圓型
        JPanel ovalPanel = new JPanel();
        ovalPanel.setPreferredSize(new Dimension(80, 35));
        ovalPanel.setBackground(colorRGB);
        ovalBtn = new JButton("橢圓型");
        ovalBtn.setFont(new Font("細明體", Font.BOLD, 14));
        ovalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                square = false;
                polygon = false;
                oval = true;
                circle = false;
                //方型&多邊形關閉
                roundSlide.setEnabled(false);
                roundSlide.setValue(0);
                roundText.setEnabled(false);
                roundText.setText("0");
                PolygonSlide.setEnabled(false);
                PolygonSlide.setValue(3);
                polygonText.setEnabled(false);
                polygonText.setText("0");
                //橢圓打開
                OvalSlide.setEnabled(true);
                ovalText.setEnabled(true);

                roundText.setText(String.valueOf(roundSlide.getValue()));;
                int round = Integer.parseInt(String.valueOf(roundSlide.getValue()));

                //pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.OvalPane(round);
                // pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "oval";
                ShapeInfomation = round;
            }
        });
        ovalPanel.add(ovalBtn);
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 0;
        c6.gridy = 7;
        c6.gridwidth = 3;
        c6.anchor = GridBagConstraints.WEST;
        //橢圓編輯
        JPanel ovalSlidePanel = new JPanel();
        ovalSlidePanel.setBackground(colorRGB);
        ovalSlidePanel.setLayout(new FlowLayout());
        JLabel roundOval = new JLabel("圓潤 :");
        roundOval.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        ovalSlidePanel.add(roundOval);

        JPanel blankOval = new JPanel();
        blankOval.setBackground(colorRGB);
        blankOval.setPreferredSize(new Dimension(35, 30));
        ovalSlidePanel.add(blankOval);
        OvalSlide = new JSlider(0, 100, 0);
        OvalSlide.setPreferredSize(new Dimension(100, 30));
        OvalSlide.setBackground(colorRGB);
        OvalSlide.setEnabled(false);
        OvalSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ovalText.setText(String.valueOf(OvalSlide.getValue()));
                int round = Integer.parseInt(String.valueOf(OvalSlide.getValue()));

                //pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.OvalPane(round);
                //pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "oval";
                ShapeInfomation = round;
            }
        });
        ovalSlidePanel.add(OvalSlide);
        ovalText = new JTextField(3);
        ovalText.setText("0");
        ovalText.setEnabled(false);
        ovalText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(ovalText.getText()) < 0 || Integer.parseInt(ovalText.getText()) > 80) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 0 and small than 100",
                            "Error Massage", JOptionPane.ERROR_MESSAGE);
                    ovalText.setText("0");
                } else {
                    OvalSlide.setValue(Integer.parseInt(ovalText.getText()));
                }
            }
        });
        ovalSlidePanel.add(ovalText);
        ovalSlidePanel.setSize(150, 20);

        GridBagConstraints c7 = new GridBagConstraints();
        c7.gridx = 0;
        c7.gridy = 8;
        c7.gridwidth = 3;
        c7.anchor = GridBagConstraints.WEST;

        //多邊形
        JPanel polygonPanel = new JPanel();
        polygonPanel.setPreferredSize(new Dimension(80, 35));
        polygonPanel.setBackground(colorRGB);
        polygonBtn = new JButton("多邊型");
        polygonBtn.setFont(new Font("細明體", Font.BOLD, 14));
        polygonBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (imageProcess.getImage().getWidth() != imageProcess.getImage().getHeight()) {
                    JOptionPane.showMessageDialog(null, "由於框架不是正方型，多邊形可能裁減圖片", "Warning Massage", JOptionPane.WARNING_MESSAGE);
                }
                square = false;
                polygon = true;
                oval = false;
                circle = false;
                //方型&橢圓關閉
                roundSlide.setEnabled(false);
                roundSlide.setValue(0);
                roundText.setEnabled(false);
                roundText.setText("0");
                OvalSlide.setEnabled(false);
                OvalSlide.setValue(0);
                ovalText.setEnabled(false);
                ovalText.setText("0");
                //多邊形打開
                PolygonSlide.setEnabled(true);
                polygonText.setEnabled(true);

                polygonText.setText(String.valueOf(PolygonSlide.getValue()));
                int edge = Integer.parseInt(String.valueOf(PolygonSlide.getValue()));

                //pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.polygon(edge);
                //pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "polygon";
                ShapeInfomation = edge;
            }
        });
        polygonPanel.add(polygonBtn);
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy = 5;
        c5.gridwidth = 3;
        c5.anchor = GridBagConstraints.WEST;

        //多邊形編輯
        JPanel polygonSlidePanel = new JPanel();
        polygonSlidePanel.setBackground(colorRGB);
        polygonSlidePanel.setLayout(new FlowLayout());
        JLabel polygonLabel = new JLabel("邊 :");
        polygonLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        polygonSlidePanel.add(polygonLabel);

        JPanel blankPolygon = new JPanel();
        blankPolygon.setBackground(colorRGB);
        blankPolygon.setPreferredSize(new Dimension(50, 30));
        polygonSlidePanel.add(blankPolygon);
        PolygonSlide = new JSlider(3, 20, 3);
        PolygonSlide.setPreferredSize(new Dimension(100, 30));
        PolygonSlide.setBackground(colorRGB);
        PolygonSlide.setEnabled(false);
        PolygonSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                polygonText.setText(String.valueOf(PolygonSlide.getValue()));
                int edge = Integer.parseInt(String.valueOf(PolygonSlide.getValue()));
                // pp.removeAll();
                for (Component c : pp.getComponents()) {
                    if (c instanceof labelStore) {
                        pp.remove(c);
                    }
                }
                pp.updateUI();
                imageProcess.polygon(edge);
                //pp.add(new labelStore(new ImageIcon(imageProcess.getImage()), path, type));
                label = new labelStore(new ImageIcon(imageProcess.getImage()), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pp.add(label);
                pp.updateUI();
                ShapeType = "polygon";
                ShapeInfomation = edge;
            }
        });
        polygonSlidePanel.add(PolygonSlide);
        polygonText = new JTextField(3);
        polygonText.setText("3");
        polygonText.setEnabled(false);
        polygonText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(polygonText.getText()) < 3 || Integer.parseInt(polygonText.getText()) > 10) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter number bigger than 3 and small than 10",
                            "Error Massage", JOptionPane.ERROR_MESSAGE);
                    polygonText.setText("0");
                } else {
                    PolygonSlide.setValue(Integer.parseInt(polygonText.getText()));
                }
            }
        });
        polygonSlidePanel.add(polygonText);
        polygonSlidePanel.setSize(150, 20);
        GridBagConstraints c9 = new GridBagConstraints();
        c9.gridx = 0;
        c9.gridy = 6;
        c9.gridwidth = 3;
        c9.anchor = GridBagConstraints.WEST;

        LineComponent line = new LineComponent(0, 20, 230, 20, Color.WHITE);  //fx,fy,tx,ty
        line.setStroke(new BasicStroke(3f));
        GridBagConstraints c10 = new GridBagConstraints();
        c10.gridx = 0;
        c10.gridy = 10;
        c10.gridwidth = 3;
        c10.anchor = GridBagConstraints.WEST;

        borderFrame.add(circleLabel, c0);
        borderFrame.add(roundrectangleBtn, c1);
        borderFrame.add(roundPanel, c2);
        borderFrame.add(polygonPanel, c5);
        borderFrame.add(polygonSlidePanel, c9);
        borderFrame.add(ovalPanel, c6);
        borderFrame.add(ovalSlidePanel, c7);
        borderFrame.add(circleBtn, c8);
        borderFrame.add(line, c10);
        return borderFrame;
        //this.add(borderFrame, BorderLayout.CENTER);

    }

    public BufferedImage scale(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
    labelStore label;

    void call(JPanel pan, String path, BufferedImage tempBi, mainFrame fff) {

        try {


            this.path = path;
            mainframe = fff;

            //Component c = pan.getComponent(0);//            type = ((labelStore) c).type;            modified = ((labelStore) c).modified;            tempBi2 = ((labelStore) c).getBufferedImage();
            //pan.removeAll();
            for (Component c : pan.getComponents()) {
                if (c instanceof labelStore) {
                    pp.remove(c);
                }
            }
            bi = ImageIO.read(new File(path));
            bi = scale(bi, pan.getWidth(), pan.getHeight());
            //System.out.println(pan.getWidth() + " & " + pan.getHeight());
            imageProcess.setImageResource(bi, path, tempBi2);
            //System.out.println(path);
            if (bi != null) {
                yytest = true;
                label = new labelStore(new ImageIcon(bi), path, type);
                label.setBounds(5, 5, pp.getWidth() - 10, pp.getHeight() - 10);
                pan.add(label);
                pan.updateUI();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.pp = pan;
        pp.updateUI();
        if (!exPath.equals(path)) {
            exPath = path;
            topDownRotate.setSelected(false);
            leftRightRotate.setSelected(false);
            sharpen.setSelected(false);
            blur.setSelected(false);
            reverseColor.setSelected(false);
            reset.setSelected(true);
            lightText.setText("0");
            contractText.setText("0");
            angleSelect.setSelectedIndex(0);
            modeSelect.setSelectedIndex(0);

            initial();

        }
    }
}