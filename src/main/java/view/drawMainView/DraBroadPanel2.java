package view.drawMainView;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import entity.MousePoint;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class DraBroadPanel2 extends JPanel implements MouseMotionListener, MouseListener {

    private DrawBroadPanel.DrawBroadListener listener;
    private BufferedImage image;
    private BranchGroup sceneBranchGroup = null;
    BranchGroup drawGroup=null;
    private Canvas3D canvas3D = null;
    private SimpleUniverse simpleUniverse;
    private ImageComponent2D imageComponent = null;
    private static final int offScreenWidth = 640;
    private static final int offScreenHeight = 480;

    public DraBroadPanel2(BufferedImage image){
        this.image = image;

        // 画板初始化
//        this.setBounds(0, 0, image.getWidth(), image.getHeight());
//        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        this.setViewportView(new DrawPanel());
          GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
            Canvas3D canvas = new Canvas3D(config);
            this.add(canvas,BorderLayout.CENTER);
            SimpleUniverse u = new SimpleUniverse(canvas);
            u.getViewingPlatform().setNominalViewingTransform();
            BranchGroup scene = createContentBranch();
            u.addBranchGraph(scene);
            setSize(400, 400);
            setVisible(true);

        this.addMouseMotionListener(this);  // 设置鼠标监听
        this.addMouseListener(this);
        this.setVisible(true);  // 设置可见性

    }
    public BranchGroup createContentBranch ()

    {
        BranchGroup root = new BranchGroup();
//创建一个tansformGroup
        TransformGroup tansformGroup = new TransformGroup();
        tansformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        root.addChild(tansformGroup);
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
        com.sun.j3d.utils.geometry.Box box=new Box(0.1f,0.2f,0.3f,null);
        Cylinder cylinder=new Cylinder(0.3f,0.4f);
        tansformGroup.addChild(box);
        root.compile();
        return root;

    }



    public void paintImage(BufferedImage image){
        this.image = image;
        this.repaint();
    }

    public BranchGroup getDrawGroup() {
        return drawGroup;
    }

    public void setDrawGroup(BranchGroup drawGroup) {
        this.drawGroup = drawGroup;
    }

    /** 鼠标事件监听  */
    // 监听鼠标拖动
    @Override
    public void mouseDragged(MouseEvent e) {
        if(listener != null){
            listener.mouseDragged(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标移动
    @Override
    public void mouseMoved(MouseEvent e) {
        if(listener != null){
            listener.mouseMoved(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if(listener != null){
            listener.mouseClicked(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标按下
    @Override
    public void mousePressed(MouseEvent e) {
        if(listener != null){
            listener.mousePressed(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        if(listener != null){
            listener.mouseReleased(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标进入绘图区域
    @Override
    public void mouseEntered(MouseEvent e) {
        if(listener != null){
            listener.mouseEntered(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 鼠标离开绘图区域
    @Override
    public void mouseExited(MouseEvent e) {
        if(listener != null){
            listener.mouseExited(new MousePoint(e.getX(), e.getY()));
        }
    }

    // 设置监听
    public void setDrawBroadListener(DrawBroadPanel.DrawBroadListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface DrawBroadListener{
        void mouseDragged(MousePoint p);
        void mouseMoved(MousePoint p);
        void mouseClicked(MousePoint p);
        void mousePressed(MousePoint p);
        void mouseReleased(MousePoint p);
        void mouseEntered(MousePoint p);
        void mouseExited(MousePoint p);
    }


    class ImagePrinter implements Printable, ImageObserver {
        BufferedImage bImage;

        public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {

            if (pi >= 1) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2d = (Graphics2D) g;
            //g2d.translate(pf.getImageableX(), pf.getImageableY());
            AffineTransform t2d = new AffineTransform();
            t2d.translate(pf.getImageableX(), pf.getImageableY());
            double xscale = pf.getImageableWidth() / (double) bImage.getWidth();
            double yscale = pf.getImageableHeight() / (double) bImage.getHeight();
            double scale = Math.min(xscale, yscale);
            t2d.scale(scale, scale);
            try {
                g2d.drawImage(bImage, t2d, this);
            } catch (Exception ex) {
                ex.printStackTrace();
                return Printable.NO_SUCH_PAGE;
            }
            return Printable.PAGE_EXISTS;
        }

        void print() {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            PageFormat pageFormat = printJob.defaultPage();
            pageFormat.setOrientation(PageFormat.LANDSCAPE);
            pageFormat = printJob.validatePage(pageFormat);
            printJob.setPrintable(this, pageFormat);
            if (printJob.printDialog()) {
                try {
                    printJob.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public boolean imageUpdate(Image img, int infoflags, int x, int y,
                                   int width, int height) {
            return false;
        }

        ImagePrinter(BufferedImage bImage) {
            this.bImage = bImage;
        }
    }





        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }


}
