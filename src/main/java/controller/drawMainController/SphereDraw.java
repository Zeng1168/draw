package controller.drawMainController;

import com.sun.j3d.utils.geometry.Sphere;
import entity.Sphere3d;

import javax.media.j3d.BranchGroup;

public class SphereDraw {
    Sphere3d sphere3d;

    public SphereDraw(){
        sphere3d=new Sphere3d();
    }

    public BranchGroup draw() {
        BranchGroup drawGroup = new BranchGroup();
        Sphere sphere = new Sphere(sphere3d.getRadius());
        drawGroup.addChild(sphere);
        return drawGroup;
    }
}
