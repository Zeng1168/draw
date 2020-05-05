package view.drawMathView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class TestMainView extends JFrame implements ActionListener {

    JMenuBar menuBar=new JMenuBar();
    JMenu tFile=new JMenu("文件");
    //JMenu tEdit=new JMenu("编辑");
//    JMenu tOption=new JMenu("选项");
//    JMenu tHelp=new JMenu("帮助");
//    JMenu tUser=new JMenu("用户");
    JMenu t2d=new JMenu("2d绘图");
    JMenu t3d=new JMenu("3d绘图");

    // 文件菜单
    JMenuItem fOpen=new JMenuItem("打开本地文件");
    JMenuItem fOpenDB=new JMenuItem("打开数据库文件");
    JMenuItem fNew=new JMenuItem("新建");
    JMenuItem fSaveDB=new JMenuItem("保存到数据库");
    JMenuItem fSave=new JMenuItem("保存到文件");

    //2d
    JMenuItem line=new JMenuItem("直线");
    JMenuItem circle=new JMenuItem("圆形");
    JMenuItem rectangle=new JMenuItem("长方形");
    JMenuItem triangle=new JMenuItem("三角形");

    //3d
    JMenuItem cone=new JMenuItem("圆锥");
    JMenuItem box=new JMenuItem("长方体");
    JMenuItem sphere=new JMenuItem("球");

    //区域
    PanelBroad panelDrawBroad=new PanelBroad();
    JPanel panelParams=new JPanel();
    JPanel infoPanle=new JPanel();


    //参数区域内容
    JLabel labelX1, labelX2, labelY1, labelY2;
    JLabel labelLen;
    JLabel labelWid;
    JLabel labelHei;
    JLabel labelRadius;

    TextField fieldX1, fieldX2, fieldY1, fieldY2;
    TextField fieldLen;
    TextField fieldWid;
    TextField fieldHei;
    TextField fieldRadius;

    JButton confirm;

    //信息区域内容
    JLabel labelPos;//
    JLabel PosInfo;
    JLabel labelArea;//
    JLabel AreaInfo;
    JLabel labelZc;//
    JLabel ZcInfo;
    JLabel labelVolumn;//
    JLabel VolumnInfo;


    //布局

    Box boxhPos1, boxhPos2, boxhLenWid, boxhHei, boxhR;
    Box boxLine=Box.createVerticalBox(),
            boxCircle=Box.createVerticalBox(),
            boxRectangle=Box.createVerticalBox(),
            boxTriangle=Box.createVerticalBox();
    Box boxCone=Box.createVerticalBox(),
            boxBox=Box.createVerticalBox(),
            boxSphere=Box.createVerticalBox();

    Box boxvTest=Box.createVerticalBox();

    BufferedImage image;
    Graphics2D g2d;


    public TestMainView() {
        image=new BufferedImage(680,460,BufferedImage.TYPE_4BYTE_ABGR);
        g2d=image.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,image.getWidth(),image.getHeight());

        panelDrawBroad.setBounds(0,0,680,460);
        panelDrawBroad.setVisible(true);
        infoPanle.setLayout(new GridLayout(4,2));
        infoPanle.setVisible(true);

        //
        labelX1 = new JLabel("X");
        fieldX1 = new TextField("0",4);
        labelY1 = new JLabel("Y");
        fieldY1 = new TextField("0",4);
        //
        labelX2 = new JLabel("X2");
        fieldX2 = new TextField("0",4);
        labelY2 = new JLabel("Y2");
        fieldY2 = new TextField("0",4);

        //
        labelLen = new JLabel("长");
        fieldLen = new TextField(20);
        labelWid = new JLabel("宽");
        fieldWid = new TextField(20);
        labelHei = new JLabel("高");
        fieldHei = new TextField(20);
        labelRadius = new JLabel("半径");
        fieldRadius = new TextField(20);
        confirm = new JButton("确认");


        boxhPos1 = Box.createHorizontalBox();
        boxhPos2 = Box.createHorizontalBox();
        boxhLenWid = Box.createHorizontalBox();
        boxhHei = Box.createHorizontalBox();
        boxhR=Box.createHorizontalBox();



        //信息
        labelPos=new JLabel("位置：");
        PosInfo=new JLabel();
        labelArea=new JLabel("面积：");
        AreaInfo=new JLabel();
        labelZc=new JLabel("周长：");
        ZcInfo=new JLabel();
        labelVolumn=new JLabel("体积：");
        VolumnInfo=new JLabel();


        this.setJMenuBar(menuBar);
        menuBar.add(tFile);
        menuBar.add(t2d);
        menuBar.add(t3d);

        t2d.add(line);
        t2d.add(rectangle);
        t2d.add(triangle);
        t2d.add(circle);

        t3d.add(cone);
        t3d.add(sphere);
        t3d.add(box);

        boxhPos1.add(labelX1);
        boxhPos1.add(fieldX1);
        boxhPos1.add(labelY1);
        boxhPos1.add(fieldY1);

        boxhPos2.add(labelX2);
        boxhPos2.add(fieldX2);
        boxhPos2.add(labelY2);
        boxhPos2.add(fieldY2);

        boxhLenWid.add(labelLen);
        boxhLenWid.add(fieldLen);
        boxhLenWid.add(labelWid);
        boxhLenWid.add(fieldWid);

        boxhHei.add(labelHei);
        boxhHei.add(fieldHei);

        boxhR.add(labelRadius);
        boxhR.add(fieldRadius);



        line.addActionListener(this);
        rectangle.addActionListener(this);
        triangle.addActionListener(this);
        circle.addActionListener(this);

        cone.addActionListener(this);
        sphere.addActionListener(this);
        box.addActionListener(this);

        this.add(panelDrawBroad,BorderLayout.CENTER);

        boxvTest.add(boxhLenWid);
        boxvTest.add(boxhPos1);
        boxvTest.add(boxhHei);
        boxvTest.add(boxhPos2);
        boxvTest.add(boxhR);

        boxLine.add(boxhPos1);
        boxLine.add(boxhPos2);

        boxRectangle.add(boxhPos1);
        boxRectangle.add(boxhLenWid);

        boxTriangle.add(boxhPos1);
        boxTriangle.add(boxhPos2);

        boxCircle.add(boxhR);

        boxCone.add(boxhR);
        boxCone.add(boxhHei);

        boxBox.add(boxhLenWid);
        boxBox.add(boxhHei);

        boxSphere.add(boxhR);
        panelParams.setLayout(new BorderLayout());
        panelParams.setVisible(true);



        // 设置
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setBounds((screenWidth-800)/2,(screenHeight-600)/2,800,600);	// 设置窗体位置和大小
        this.setVisible(true);	// 设置窗体可见性

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object object=e.getSource();
        if(object==line){
            panelParams.removeAll();
            panelParams.repaint();
            panelParams.add(boxhPos1,BorderLayout.SOUTH);
            panelParams.add(boxhPos2,BorderLayout.NORTH);
            panelParams.add(confirm,BorderLayout.EAST);
            panelParams.revalidate();
            this.add(panelParams,BorderLayout.NORTH);
            infoPanle.removeAll();
            infoPanle.repaint();
            infoPanle.add(labelPos);
            infoPanle.add(PosInfo);
            infoPanle.add(labelZc);
            infoPanle.add(ZcInfo);
            this.add(infoPanle,BorderLayout.EAST);
            BufferedImage image2=new BufferedImage(680,460,BufferedImage.TYPE_3BYTE_BGR);
            g2d=image2.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,680,460);
            g2d.setColor(Color.BLACK);


            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int X1=Integer.parseInt(fieldX1.getText());
                    int Y1=Integer.parseInt(fieldY1.getText());
                    int X2=Integer.parseInt(fieldX2.getText());
                    int Y2=Integer.parseInt(fieldY2.getText());

                    g2d.drawLine(X1,Y1,X2,Y2);
                    image=image2;
                    PosInfo.setText("("+X1+","+Y1+")");
                    ZcInfo.setText(""+(X2-X1));
                }
            });
           this.repaint();
            panelDrawBroad.repaint();

        }else if(object==rectangle){
            panelParams.removeAll();
            panelParams.repaint();
            panelParams.add(boxhPos1,BorderLayout.NORTH);
            panelParams.add(boxhLenWid,BorderLayout.SOUTH);
            panelParams.add(confirm,BorderLayout.EAST);
            panelParams.revalidate();
            this.add(panelParams,BorderLayout.NORTH);
            infoPanle.removeAll();
            infoPanle.repaint();
            infoPanle.add(labelPos);
            infoPanle.add(PosInfo);
            infoPanle.add(labelZc);
            infoPanle.add(ZcInfo);
            infoPanle.add(labelArea);
            infoPanle.add(AreaInfo);
            this.add(infoPanle,BorderLayout.EAST);
            image=new BufferedImage(680,460,BufferedImage.TYPE_3BYTE_BGR);
            g2d=image.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0,0,image.getWidth(),image.getHeight());
            g2d.setColor(Color.BLACK);

            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int X1=Integer.parseInt(fieldX1.getText());
                    int Y1=Integer.parseInt(fieldY1.getText());
                    int length=Integer.parseInt(fieldLen.getText());
                    int width=Integer.parseInt(fieldWid.getText());
                    g2d.drawRect(X1,Y1,width,length);

                    PosInfo.setText("("+X1+","+Y1+")");
                    ZcInfo.setText(""+(2*width+2*length));
                    AreaInfo.setText(""+(width*length));
                    panelDrawBroad.removeAll();

                }
            });
            this.repaint();
            panelDrawBroad.repaint();
            panelDrawBroad.revalidate();
            this.revalidate();


        }





        }


    private class PanelBroad extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image,0,0,null);
        }
    }



    }



