package view.drawMath;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import controller.drawMath.ConeController;
import entity.ShapeCone;
import utils.AlertUtil;
import utils.DataCheck;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 绘制圆锥
 */
public class DrawCone extends JPanel implements IDraw  {
    private ConeController controller;  // 持有控制器

    private JPanel drawArea;  // 绘图区容器

    // 标题
    private String title = "圆锥绘制";

    // 参数输入区
    private JTextField rInput;
    private JTextField hInput;
    private JButton startDrawBtn;

    // 信息显示区
    private JLabel nameInfo;    // 图形名称
    private JLabel rInfo;    // 底面圆半径r
    private JLabel hInfo;    // 高度h
    private JLabel perimeterCircleInfo;    // 底面圆周长
    private JLabel areaCircleInfo;   // 底面圆面积
    private JLabel volumeInfo;   // 体积


    public DrawCone() {
        /* 初始化阶段     */
        this.initComponent();   // 初始化窗口组件
        this.setListener(); // 为按钮设置监听
        /* 初始化完成    */

        // 与控制层和监听器关联
        controller = new ConeController(this);

        this.setVisible(true);


    }

    public void initComponent(){
        /* 111111111111111   以下为顶部标题、输入框组件   111111111111111 */
        // 创建各对象
        rInput = new JTextField();
        hInput = new JTextField();
        startDrawBtn = new JButton("绘制");

        /**  第一行输入参数组   r、h  */
        Box paramBox1 = Box.createHorizontalBox();
        paramBox1.add(Box.createHorizontalStrut(20));   // 左边距

        // 输入半径
        Box box1A = Box.createHorizontalBox();
        box1A.add(new JLabel("底面圆半径r:"));
        box1A.add(rInput);
        box1A.add(Box.createHorizontalStrut(30));
        paramBox1.add(box1A);

        // 输入B点
        Box box1B=Box.createHorizontalBox();
        box1B.add(new JLabel("高度h:"));
        box1B.add(hInput);
        box1B.add(Box.createHorizontalStrut(20));
        paramBox1.add(box1B);

        // 开始绘制按钮
        paramBox1.add(startDrawBtn);

        paramBox1.add(Box.createHorizontalStrut(200));   // 右边距
        /* 1111111111111111111 */


        /**  主容器 */
        Box topBox = Box.createVerticalBox();
        topBox.add(Box.createVerticalStrut(10));    // 上边距
        topBox.add(new JLabel(title));  // 放入标题
        topBox.add(Box.createVerticalStrut(10));    // 加一个间距
        topBox.add(paramBox1);  // 放入第二行输入框组
        topBox.add(Box.createVerticalStrut(10));    // 下边距
        /* 11111111111111111111111111111111*/


        drawArea = new JPanel();
        drawArea.setLayout(new BorderLayout());

        /* 3333333333   以下为右侧信息区组件   33333333333333 */
        // 创建各对象
        nameInfo = new JLabel();    // 图形名称
        rInfo= new JLabel();    // 底面圆半径r
        hInfo = new JLabel();    // 高度h
        perimeterCircleInfo = new JLabel();    // 底面圆周长
        areaCircleInfo = new JLabel();   // 底面圆面积
        volumeInfo = new JLabel();   // 体积

        Box rightInfoBox = Box.createHorizontalBox();
        Box infoBox = Box.createVerticalBox();

        int marginInfo = 10;
        infoBox.add(nameInfo);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(rInfo);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(hInfo);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(perimeterCircleInfo);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(areaCircleInfo);
        infoBox.add(Box.createVerticalStrut(marginInfo));
        infoBox.add(volumeInfo);
        infoBox.add(Box.createVerticalStrut(marginInfo));

        rightInfoBox.add(Box.createHorizontalStrut(10));
        rightInfoBox.add(infoBox);
        rightInfoBox.add(Box.createHorizontalStrut(30));
        /* 3333333333333333333333333333333333 */



        this.setLayout(new BorderLayout());
        this.add(topBox, BorderLayout.NORTH);
        this.add(rightInfoBox, BorderLayout.EAST);
        this.add(drawArea, BorderLayout.CENTER);
    }

    public void setListener(){
        startDrawBtn.addActionListener(e -> {
            //  获取输入参数
            String r = rInput.getText();
            String h = hInput.getText();

            // 参数合法性校验
            if(!DataCheck.isNumber(r)) AlertUtil.warningDialog("请正确输入底面圆半径r！");
            else if(!DataCheck.isNumber(h)) AlertUtil.warningDialog("请正确输入高度h！");
            else {
                controller.onDraw(Integer.valueOf(r), Integer.valueOf(h));
            }
        });
    }

    /** 画图锥 */
    public void drawShape(ShapeCone shapeCone){
        Canvas3D canvas = draw(shapeCone.getR(), shapeCone.getH());

        drawArea.removeAll();  // 清除绘图区组件
        drawArea.repaint();
        drawArea.add(canvas, BorderLayout.CENTER);
        drawArea.revalidate();
        System.out.println("绘制");
    }

    public  Canvas3D draw(float r,float h) {

        //canvas to draw on, ask SimpleUniverse what config to use
        Canvas3D canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        BranchGroup scene = new BranchGroup();

        // Create the bounding leaf node
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

        // Create the transform node
        TransformGroup transformGroup = new TransformGroup();  //可以旋转、放大、缩小 坐标系
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);  //参数实现移动或旋转
//        //set color
        Color3f bgColor = new Color3f(0.9f, 0.9f, 0.9f);
        Background bg = new Background(bgColor);
        bg.setApplicationBounds(bounds);
        scene.addChild(bg);
        //create universe, and attach our geometry to it.
        SimpleUniverse u = new SimpleUniverse(canvas);
        u.getViewingPlatform().setNominalViewingTransform();

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

        transformGroup.addChild(aLgt);               //将光照对象添加到objRoot中
        transformGroup.addChild(lgt1);

        Color3f directionalColor1 = new Color3f(1.0f, .5f, 0.0f);//设置光源
        Vector3f vec1 = new Vector3f(4.0f, -7.0f, -12.0f);          //设置光源方向
        Color3f directionalColor2 = new Color3f(-0.2f, 0.2f, 0.2f);//设置光源
        Vector3f vec2 = new Vector3f(-2.0f, 7.0f, 6.0f);          //设置光源方向
        DirectionalLight directionalLight1 = new DirectionalLight(directionalColor1, vec1);//使用直接光照
        DirectionalLight directionalLight2 = new DirectionalLight(directionalColor2, vec2);//使用直接光照
        directionalLight1.setInfluencingBounds(bounds);    //设置光照影响范围为bounds
        directionalLight2.setInfluencingBounds(bounds);    //设置光照影响范围为bounds
        transformGroup.addChild(directionalLight1);               //将光照对象添加到objRoot中
        transformGroup.addChild(directionalLight2);               //将光照对象添加到objRoot中

        // Create the drag behavior node      控制鼠标 左键
        MouseRotate behavior = new MouseRotate();
        behavior.setTransformGroup(transformGroup);
        transformGroup.addChild(behavior);
        behavior.setSchedulingBounds(bounds);

        // Create the zoom behavior node  控制鼠标 中键

        MouseZoom behavior2 = new MouseZoom();
        behavior2.setTransformGroup(transformGroup);
        transformGroup.addChild(behavior2);
        behavior2.setSchedulingBounds(bounds);
        // Create the zoom behavior node       控制鼠标 右键

        MouseTranslate behavior3 = new MouseTranslate();
        behavior3.setTransformGroup(transformGroup);
        transformGroup.addChild(behavior3);
        behavior3.setSchedulingBounds(bounds);

        Appearance app = new Appearance();
        // Material material = new Material();
        // material.setEmissiveColor(new Color3f(0.2f, 0.6f, 1.0f));
        Color3f objColor = new Color3f(1.0f, 0.7f, 0.8f);
        Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
        app.setMaterial(new Material(objColor, black, objColor, black, 80.0f));
        // app.setMaterial(material);

        ColorCube cube = new ColorCube(0.5f);
        com.sun.j3d.utils.geometry.Box box = new com.sun.j3d.utils.geometry.Box(0.5f, 0.5f, 0.5f, app);
        Sphere sphere = new Sphere(0.1f, app);
        Cone cone = new Cone(r/10, h/10, 3, app);//圆锥半径为0.3，高为0.7，片元记1
        Cone cone1=new Cone(.3f,.3f,app);
        transformGroup.addChild(cone);//圆锥
        // transformGroup.addChild(cone1);//圆锥
        //transformGroup.addChild(new Cylinder(0.2f,.4f,3,app));//圆柱
        transformGroup.addChild(sphere);//方块
        scene.addChild(transformGroup);
        u.addBranchGraph(scene);
        return  canvas;
    }


    /**  更新信息区  */
    public void updateInfoArea(ShapeCone shapeCone){
        nameInfo.setText("名称：" + shapeCone.getName());
        rInfo.setText("底面圆半径r：" + shapeCone.getR());
        hInfo.setText("高度h：" + shapeCone.getH());
        perimeterCircleInfo.setText("底面圆周长：" + String.format("%.2f", shapeCone.getPerimeterCircle()));
        areaCircleInfo.setText("底面圆面积：" + String.format("%.2f", shapeCone.getAreaCircle()));
        volumeInfo.setText("体积：" + String.format("%.2f", shapeCone.getVolume()));
    }

    @Override
    public void clean() {
        rInput.setText("");
        hInput.setText("");
    }

    @Override
    public void saveToDataBase() {

    }

    @Override
    public void openDataBase() {

    }


    // 自定义监听器
    public interface Listener{
        void onDraw(int r, int h);
    }
}
