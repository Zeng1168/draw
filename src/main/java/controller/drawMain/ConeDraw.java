package controller.drawMain;

import com.sun.j3d.utils.geometry.Cone;
import entity.ConeThreeD;

import javax.media.j3d.BranchGroup;

public class ConeDraw {
    ConeThreeD cone3d;

    public ConeDraw() {
        cone3d=new ConeThreeD();
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
