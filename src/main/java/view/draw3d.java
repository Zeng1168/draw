package view;

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class draw3d {
   private float r,h;

    public void setrh(float r,float h){
       this.r=r;
       this.h=h;
    }

    public  Canvas3D draw() {

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
       // Color3f bgColor = new Color3f(0.6f, 0.9f, 0.7f);
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

}
