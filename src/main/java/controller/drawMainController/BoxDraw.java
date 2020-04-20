package controller.drawMainController;

import entity.Box;

import javax.media.j3d.BranchGroup;
import java.awt.*;

public class BoxDraw {
   Box box;



    public BoxDraw(Color penColor){
        box=new Box();
       // initial_setup(new Color3f(penColor));

//        /**
//         * 设置起始位置和宽高度
//         * 小的作为起始位置，大的减去小的即为宽高度
//         **/
//        if(p1.getX() < p2.getX() ){
//            box.setX(p1.getX());
//            box.setWidth(p2.getX() - p1.getX());
//        }else{
//            box.setX(p2.getX());
//            box.setWidth(p1.getX() - p2.getX());
//        }
//
//        if(p1.getY() < p2.getY()){
//            box.setY(p1.getY());
//            box.setLength(p2.getY() - p1.getY());
//        }else{
//            box.setY(p2.getY());
//            box.setLength(p1.getY() - p2.getY());
//        }


        box.setColor(penColor);

    }
//    protected void initial_setup(Color3f color)
//    {
//        canvas3D=createCanvas3D(true);
//        canvas3D.getScreen3D()
//                .setSize(offScreenWidth, offScreenHeight);
//        canvas3D.getScreen3D().setPhysicalScreenHeight(
//                0.0254 / 90 * offScreenHeight);
//        canvas3D.getScreen3D().setPhysicalScreenWidth(
//                0.0254 / 90 * offScreenWidth);
//
////        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
////        canvas3D = new Canvas3D(config);
//        simpleUniverse= new SimpleUniverse(canvas3D);
//        group = new BranchGroup();
//        Color3f light1Color = new Color3f(color);
//        BoundingSphere bounds =
//                new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000.0);
//        Vector3f light1Direction  = new Vector3f(0, 0, -1);
//        DirectionalLight light1
//                = new DirectionalLight(light1Color, light1Direction);
//        light1.setInfluencingBounds(bounds);
//        Color backgroundColor=new Color(200, 151, 82);
//        Background bg = new Background(backgroundColor.getRed()/255f, backgroundColor.getGreen()/255f, backgroundColor.getBlue()/255f);
//        bg.setApplicationBounds(bounds);
//        group.addChild(bg);
//        group.addChild(light1);
//
//        transformGroup=new TransformGroup();
//        transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//
//        rotate=new MouseRotate();
//        rotate.setTransformGroup(transformGroup);
//
//        rotate.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000));
//        transformGroup.addChild(rotate);
//
//
//
//
//    }
//    protected Canvas3D createCanvas3D(boolean offscreen) {
//        GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
//        gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
//        GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
//                .getScreenDevices();
//
//        Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D), offscreen);
//        c3d.setSize(640, 480);
//
//        return c3d;
//    }

    public BranchGroup draw(){

        com.sun.j3d.utils.geometry.Box box1=new com.sun.j3d.utils.geometry.Box(0.3f,0.2f,box.getHeight(),null);
        BranchGroup bg = new BranchGroup();
        bg.setCapability(BranchGroup.ALLOW_DETACH);
        bg.addChild(box1);
       // bg.setUserData("Cube");
        return bg;



}




}
