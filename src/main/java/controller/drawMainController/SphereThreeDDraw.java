package controller.drawMainController;

import com.sun.j3d.utils.geometry.Sphere;
import entity.SphereThreeD;

import javax.media.j3d.BranchGroup;

public class SphereThreeDDraw {
    SphereThreeD sphere3d;

    public SphereThreeDDraw(){
        sphere3d=new SphereThreeD();
    }

    public BranchGroup draw() {
        BranchGroup drawGroup = new BranchGroup();
        Sphere sphere = new Sphere(sphere3d.getRadius());
        drawGroup.addChild(sphere);
        return drawGroup;
    }
}
