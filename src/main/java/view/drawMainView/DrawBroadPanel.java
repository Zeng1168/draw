package view.drawMainView;

import entity.Point;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

/**
 * 绘图区域
 *
 * 使用自定义监听器，监听各个工具栏动作
 */
public class DrawBroadPanel extends JPanel implements MouseMotionListener, MouseListener{
    private DrawBroadListener listener;
    private BufferedImage image;
    private BranchGroup sceneBranchGroup = null;
    BranchGroup drawGroup=null;
    RotationInterpolator rotator=null;
    private Canvas3D offScreenCanvas3D = null;
    private ImageComponent2D imageComponent = null;
    private static final int offScreenWidth = 640;
    private static final int offScreenHeight = 480;

    public DrawBroadPanel(BufferedImage image){
        this.image = image;

        // 画板初始化
        this.setBounds(0, 0, image.getWidth(), image.getHeight());
        //this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // this.setViewportView(new DrawPanel());
        //this.setLayout(new BorderLayout());
        this.addMouseMotionListener(this);  // 设置鼠标监听
        this.addMouseListener(this);
        this.setVisible(true);  // 设置可见性

        init();
    }

    protected void init() {
        VirtualUniverse universe = createVirtualUniverse();

        Locale locale = createLocale(universe);

        BranchGroup sceneBranchGroup = createSceneBranchGroup();

        Background background = createBackground();

        if (background != null)
            sceneBranchGroup.addChild(background);

        ViewPlatform vp = createViewPlatform();
        BranchGroup viewBranchGroup = createViewBranchGroup(
                getViewTransformGroupArray(), vp);

        locale.addBranchGraph(sceneBranchGroup);
        addViewBranchGroup(locale, viewBranchGroup);
        createView(vp);
    }
    /**
     * Callback to allow the Canvas3D to be added to a Panel.
     */
    protected void addCanvas3D(Canvas3D c3d) {
        add(c3d);
    }

    protected View createView(ViewPlatform vp) {
        View view = new View();

        PhysicalBody pb = createPhysicalBody();
        PhysicalEnvironment pe = createPhysicalEnvironment();

        view.setPhysicalEnvironment(pe);
        view.setPhysicalBody(pb);

        if (vp != null)
            view.attachViewPlatform(vp);

        view.setBackClipDistance(getBackClipDistance());
        view.setFrontClipDistance(getFrontClipDistance());

        // create the visible canvas
        Canvas3D c3d = createCanvas3D(false);
        view.addCanvas3D(c3d);

        // create the off screen canvas
        view.addCanvas3D(createOffscreenCanvas3D());

        // add the visible canvas to a component
        this.addCanvas3D(c3d);

        return view;
    }

    /**
     * Create a Background for the Canvas3D.
     */
    protected Background createBackground() {
        Background back = new Background(new Color3f(0.9f, 0.9f, 0.9f));
        back.setApplicationBounds(createApplicationBounds());
        return back;
    }
    /**
     * Create a Bounds object for the scene.
     */
    protected Bounds createApplicationBounds() {
        return new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
    }

    /**
     * Create a Canvas3D.
     *
     * @param offscreen
     *            true to specify an offscreen canvas
     */
    protected Canvas3D createCanvas3D(boolean offscreen) {
        GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
        gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
        GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getScreenDevices();

        Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D), offscreen);
        c3d.setSize(500, 500);

        return c3d;
    }

    /**
     * Initialize an offscreen Canvas3D.
     */
    protected Canvas3D createOffscreenCanvas3D() {
        offScreenCanvas3D = createCanvas3D(true);
        offScreenCanvas3D.getScreen3D()
                .setSize(offScreenWidth, offScreenHeight);
        offScreenCanvas3D.getScreen3D().setPhysicalScreenHeight(
                0.0254 / 90 * offScreenHeight);
        offScreenCanvas3D.getScreen3D().setPhysicalScreenWidth(
                0.0254 / 90 * offScreenWidth);

        RenderedImage renderedImage = new BufferedImage(offScreenWidth,
                offScreenHeight, BufferedImage.TYPE_3BYTE_BGR);
        imageComponent = new ImageComponent2D(ImageComponent.FORMAT_RGB8,
                renderedImage);
        imageComponent.setCapability(ImageComponent2D.ALLOW_IMAGE_READ);
        offScreenCanvas3D.setOffScreenBuffer(imageComponent);

        return offScreenCanvas3D;
    }
    /**
     * Callback to get the scale factor for the View side of the scenegraph
     */
    protected double getScale() {
        return 3;
    }

    /**
     * Get the TransformGroup for the View side of the scenegraph
     */
    public TransformGroup[] getViewTransformGroupArray() {
        TransformGroup[] tgArray = new TransformGroup[1];
        tgArray[0] = new TransformGroup();

        // move the camera BACK a little...
        // note that we have to invert the matrix as
        // we are moving the viewer
        Transform3D t3d = new Transform3D();
        t3d.setScale(getScale());
        t3d.setTranslation(new Vector3d(0.0, 0.0, -20.0));
        t3d.invert();
        tgArray[0].setTransform(t3d);

        return tgArray;
    }

    /**
     * Adds the View side of the scenegraph to the Locale
     */
    protected void addViewBranchGroup(Locale locale, BranchGroup bg) {
        locale.addBranchGraph(bg);
    }

    /**
     * Create a Locale for the VirtualUniverse
     */
    protected Locale createLocale(VirtualUniverse u) {
        return new Locale(u);
    }

    /**
     * Create the scene side of the scenegraph
     */
    protected BranchGroup createSceneBranchGroup() {
        // create the root of the scene side scenegraph
        BranchGroup objRoot = new BranchGroup();

        // create a TransformGroup to rotate the objects in the scene
        // set the capability bits on the TransformGroup so that it
        // can be modified at runtime
        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

        // create a spherical bounding volume
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                100.0);

        // create a 4x4 transformation matrix
        Transform3D yAxis = new Transform3D();

        // create an Alpha interpolator to automatically generate
        // modifications to the rotation component of the transformation matrix
        Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
                4000, 0, 0, 0, 0, 0);

//        // create a RotationInterpolator behavior to effect the TransformGroup
//        rotator = new RotationInterpolator(rotationAlpha, objTrans, yAxis,
//                0.0f, (float) Math.PI * 2.0f);
//
//        // set the scheduling bounds on the behavior
//        rotator.setSchedulingBounds(bounds);

        // add the behavior to the scenegraph
        objTrans.addChild(rotator);

        // create the BranchGroup which contains the objects
        // we add/remove to and from the scenegraph
        sceneBranchGroup = new BranchGroup();

        // allow the BranchGroup to have children added at runtime
        sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
        sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
        sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);

        // add both the cube and the sphere to the scenegraph
        sceneBranchGroup.addChild(drawGroup);
//        sceneBranchGroup.addChild(createSphere());

        // create the colors for the lights
        Color3f lColor1 = new Color3f(0.7f, 0.7f, 0.7f);
        Vector3f lDir1 = new Vector3f(-1.0f, -1.0f, -1.0f);
        Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);

        // create the ambient light
        AmbientLight aLgt = new AmbientLight(alColor);
        aLgt.setInfluencingBounds(bounds);

        // create the directional light
        DirectionalLight lgt1 = new DirectionalLight(lColor1, lDir1);
        lgt1.setInfluencingBounds(bounds);

        // add the lights to the scenegraph
        objRoot.addChild(aLgt);
        objRoot.addChild(lgt1);

        // wire the scenegraph together
        objTrans.addChild(sceneBranchGroup);
        objRoot.addChild(objTrans);

        // return the root of the scene side of the scenegraph
        return objRoot;
    }
    /**
     * Creates the PhysicalBody for the View
     */
    protected PhysicalBody createPhysicalBody() {
        return new PhysicalBody();
    }

    /**
     * Creates the PhysicalEnvironment for the View
     */
    protected PhysicalEnvironment createPhysicalEnvironment() {
        return new PhysicalEnvironment();
    }

    /**
     * Returns the View Platform Activation Radius
     */
    protected float getViewPlatformActivationRadius() {
        return 100;
    }

    /**
     * Creates the View Platform for the View
     */
    protected ViewPlatform createViewPlatform() {
        ViewPlatform vp = new ViewPlatform();
        vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
        vp.setActivationRadius(getViewPlatformActivationRadius());

        return vp;
    }

    /**
     * Returns the distance to the rear clipping plane.
     */
    protected double getBackClipDistance() {
        return 100.0;
    }

    /**
     * Returns the distance to the near clipping plane.
     */
    protected double getFrontClipDistance() {
        return 1.0;
    }

    /**
     * Creates the View side BranchGroup. The ViewPlatform is wired in beneath
     * the TransformGroups.
     */
    protected BranchGroup createViewBranchGroup(TransformGroup[] tgArray,
                                                ViewPlatform vp) {
        BranchGroup vpBranchGroup = new BranchGroup();

        if (tgArray != null && tgArray.length > 0) {
            Group parentGroup = vpBranchGroup;
            TransformGroup curTg = null;

            for (int n = 0; n < tgArray.length; n++) {
                curTg = tgArray[n];
                parentGroup.addChild(curTg);
                parentGroup = curTg;
            }

            tgArray[tgArray.length - 1].addChild(vp);
        } else
            vpBranchGroup.addChild(vp);

        return vpBranchGroup;
    }

    /**
     * Creates the VirtualUniverse for the application.
     */
    protected VirtualUniverse createVirtualUniverse() {
        return new VirtualUniverse();
    }

    public BranchGroup getDrawGroup() {
        return drawGroup;
    }

    public void setDrawGroup(BranchGroup drawGroup) {
        this.drawGroup = drawGroup;
    }

    // 重新绘制图像
    public void paintImage(BufferedImage image){
        this.image = image;
        this.repaint();
    }

    /** 鼠标事件监听  */
    // 监听鼠标拖动
    @Override
    public void mouseDragged(MouseEvent e) {
        if(listener != null){
            listener.mouseDragged(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标移动
    @Override
    public void mouseMoved(MouseEvent e) {
        if(listener != null){
            listener.mouseMoved(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标点击
    @Override
    public void mouseClicked(MouseEvent e) {
        if(listener != null){
            listener.mouseClicked(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标按下
    @Override
    public void mousePressed(MouseEvent e) {
        if(listener != null){
            listener.mousePressed(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        if(listener != null){
            listener.mouseReleased(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标进入绘图区域
    @Override
    public void mouseEntered(MouseEvent e) {
        if(listener != null){
            listener.mouseEntered(new Point(e.getX(), e.getY()));
        }
    }

    // 鼠标离开绘图区域
    @Override
    public void mouseExited(MouseEvent e) {
        if(listener != null){
            listener.mouseExited(new Point(e.getX(), e.getY()));
        }
    }

    // 设置监听
    public void setDrawBroadListener(DrawBroadListener listener){
        this.listener = listener;
    }

    // 自定义监听器
    public interface DrawBroadListener{
        void mouseDragged(Point p);
        void mouseMoved(Point p);
        void mouseClicked(Point p);
        void mousePressed(Point p);
        void mouseReleased(Point p);
        void mouseEntered(Point p);
        void mouseExited(Point p);
    }

    // 画图容器 内部类
    private class DrawPanel extends JPanel {
        // 将图形绘制到组件上
        @Override
        public void paint(Graphics g) {
            g.drawImage(image, 0, 0,image.getWidth(),image.getHeight(),null);
        }
    }
}
