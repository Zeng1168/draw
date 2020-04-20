package controller.drawMainController;

import com.sun.j3d.utils.geometry.Box;
import entity.Box3d;

import javax.media.j3d.BranchGroup;
import java.awt.*;

public class BoxDraw {
   Box3d box3d;



    public BoxDraw(){
        box3d =new Box3d();


    }
    //写成BranchGroup类型方便加入
    public BranchGroup draw() {

        Box box = new Box(0.1f, 0.2f, box3d.getHeight(), null);
        BranchGroup bg = new BranchGroup();
        bg.addChild(box);
        return bg;
    }



}





