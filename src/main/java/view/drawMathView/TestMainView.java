package view.drawMathView;


import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import controller.drawMainController.RectangleDraw;
import entity.Point;
import entity.Rectangle;
import entity.Triangle;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
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


    BufferedImage image;
    Graphics2D g2d;


    public TestMainView() {
        image=new BufferedImage(680,460,BufferedImage.TYPE_4BYTE_ABGR);
        g2d=image.createGraphics();
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,image.getWidth(),image.getHeight());
        repaint();

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
        panelDrawBroad.repaint();
        if(object==line){
            panelParams.removeAll();
            panelParams.repaint();
            panelParams.add(boxhPos1,BorderLayout.NORTH);
            panelParams.add(boxhPos2,BorderLayout.SOUTH);
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
            image=new BufferedImage(680,460,BufferedImage.TYPE_3BYTE_BGR);
            g2d=image.createGraphics();
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
                    panelDrawBroad.repaint();
                    PosInfo.setText("("+X1+","+Y1+")");
                    ZcInfo.setText(""+(X2-X1));
                }
            });


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
                    //RectangleDraw rectangleDraw=new RectangleDraw(new Point(X1,Y1),new Point(width-X1,length-Y1),Color.BLUE,2.0f);
                    //image=rectangleDraw.draw(image.getWidth(),image.getHeight());
                    g2d.drawRect(X1,Y1,width,length);
                    panelDrawBroad.repaint();

                    PosInfo.setText("("+X1+","+Y1+")");
                    ZcInfo.setText(""+(2*width+2*length));
                    AreaInfo.setText(""+(width*length));


                }
            });


        }else if(object==triangle){
            panelParams.removeAll();
            panelParams.repaint();
            panelParams.add(boxhPos1,BorderLayout.NORTH);
            panelParams.add(boxhPos2,BorderLayout.SOUTH);
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
            g2d.fillRect(0,0,680,460);
            g2d.setColor(Color.BLACK);


            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int X1=Integer.parseInt(fieldX1.getText());
                    int Y1=Integer.parseInt(fieldY1.getText());
                    int X2=Integer.parseInt(fieldX2.getText());
                    int Y2=Integer.parseInt(fieldY2.getText());

                    g2d.drawLine( (X1+X2)/2, Y1,X1, Y2);
                    g2d.drawLine(X1, Y2, X2, Y2);
                    g2d.drawLine((X1+X2)/2, Y1,
                           X2, Y2);
                    panelDrawBroad.repaint();
                    PosInfo.setText("("+X1+","+Y1+")");
                    ZcInfo.setText(""+((X2-X1)+Math.pow((Math.pow((Y2-Y1),2)+Math.pow(0.5*(X2-X1),2)),0.5)));
                    AreaInfo.setText(""+0.5*(X2-X1)*(Y2-Y1));
                }
            });


        }else if(object==circle){
            panelParams.removeAll();
            panelParams.repaint();
            panelParams.add(boxhPos1,BorderLayout.SOUTH);
            panelParams.add(boxhR,BorderLayout.NORTH);
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
            g2d.fillRect(0,0,680,460);
            g2d.setColor(Color.BLACK);


            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int X1=Integer.parseInt(fieldX1.getText());
                    int Y1=Integer.parseInt(fieldY1.getText());
                    int radius=Integer.parseInt(fieldRadius.getText());

                    g2d.drawOval(X1,Y1,radius,radius);
                    panelDrawBroad.repaint();
                    PosInfo.setText("("+X1+","+Y1+")");
                    ZcInfo.setText(""+3.14*2*radius);
                    AreaInfo.setText(""+3.14*radius*radius);
                }
            });


        }else if(object==cone){
            panelParams.removeAll();
            panelParams.repaint();
            panelParams.add(boxhR,BorderLayout.NORTH);
            panelParams.add(boxhHei,BorderLayout.SOUTH);
            panelParams.add(confirm,BorderLayout.EAST);
            panelParams.revalidate();
            this.add(panelParams,BorderLayout.NORTH);
            infoPanle.removeAll();
            infoPanle.repaint();
            infoPanle.add(labelPos);
            infoPanle.add(PosInfo);
            infoPanle.add(labelVolumn);
            infoPanle.add(VolumnInfo);
            this.add(infoPanle,BorderLayout.EAST);

            confirm.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Float radius=Float.parseFloat(fieldRadius.getText());
                    Float hight=Float.parseFloat(fieldHei.getText());
                    BranchGroup group=new BranchGroup();
                    Cone cone=new Cone(radius,hight);
                    group.addChild(cone);
                    create3d(group);

                }
            });

        }else if(object==box){

        }else if(object==sphere){

        }





        }


    public void create3d(BranchGroup drawGroup){

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();


//创建一个 Canvas3D 对象并将其加到frame中去

        Canvas3D canvas = new Canvas3D(config);
        this.add(canvas,BorderLayout.CENTER);


//创建一个SimpleUniverse对象,用来管理”view” 分支

        SimpleUniverse u = new SimpleUniverse(canvas);

        u.getViewingPlatform().setNominalViewingTransform();


//将 “content” 分支加入到SimpleUniverse中去

        BranchGroup scene = createContentBranch(drawGroup);

        u.addBranchGraph(scene);


    }
    public BranchGroup createContentBranch (BranchGroup drawGroup)

    {

        BranchGroup root = new BranchGroup();



//创建一个tansformGroup

        TransformGroup tansformGroup = new TransformGroup();

        tansformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        root.addChild(tansformGroup);


//创建鼠标旋转行为

        MouseRotate rotate = new MouseRotate();


        rotate.setTransformGroup(tansformGroup);

        rotate.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000));

        tansformGroup.addChild(rotate);


//设置立方体颜色

        //ColorCube colorCube = new ColorCube(0.3);
        Color3f light1Color = new Color3f(Color.yellow);
        BoundingSphere bounds =
                new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
        Vector3f light1Direction  = new Vector3f(0, 0, -1);
        DirectionalLight light1
                = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        Color backgroundColor=new Color(200,120,110);
        Background bg = new Background(backgroundColor.getRed()/255f, backgroundColor.getGreen()/255f, backgroundColor.getBlue()/255f);
        bg.setApplicationBounds(bounds);
        root.addChild(bg);
        root.addChild(light1);
        //创建3D图形
        Sphere sphere=new Sphere(0.3f);
        Cone cone=new Cone(0.3f,0.4f);
        ColorCube cube=new ColorCube();
        com.sun.j3d.utils.geometry.Box box=new com.sun.j3d.utils.geometry.Box(0.1f,0.2f,0.3f,null);
        Cylinder cylinder=new Cylinder(0.3f,0.4f);

        tansformGroup.addChild(drawGroup);


        root.compile();

        return root;

    }




    private class PanelBroad extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(image,0,0,null);
        }
    }



    }



