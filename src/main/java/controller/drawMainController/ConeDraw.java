package controller.drawMainController;

import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
import entity.Cone3d;

import javax.media.j3d.BranchGroup;

public class ConeDraw {
    Cone3d cone3d;

    public ConeDraw() {
        cone3d=new Cone3d();
        cone3d.setH(0.9f);
        cone3d.setR(0.4f);
    }

    public BranchGroup draw() {
        BranchGroup drawGroup = new BranchGroup();
        Cone cone=new Cone(cone3d.getR(),cone3d.getH(),null);
        drawGroup.addChild(cone);
        return drawGroup;
    }
}
