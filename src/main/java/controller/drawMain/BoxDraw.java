package controller.drawMain;

import com.sun.j3d.utils.geometry.Box;
import entity.BoxThreeD;

import javax.media.j3d.BranchGroup;

public class BoxDraw {
   BoxThreeD box3d;



    public BoxDraw(){
        box3d =new BoxThreeD();


    }
    //写成BranchGroup类型方便加入
    public BranchGroup draw() {

        Box box = new Box(0.1f, 0.2f, box3d.getHeight(), null);
        BranchGroup bg = new BranchGroup();
        bg.addChild(box);
        return bg;
    }



}





