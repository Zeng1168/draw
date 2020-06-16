package controller.drawMath;

import entity.ShapeBox;

import view.drawMath.DrawBox;

public class BoxController implements  DrawBox.Listener{
    private DrawBox drawBox;
    private ShapeBox shapeBox;

    public BoxController(DrawBox drawBox){this.drawBox=drawBox;}
    @Override
    public void onDraw(int l, int w, int h) {
        shapeBox=new ShapeBox();
        shapeBox.setName("未命名长方体");
        shapeBox.setL(l);
        shapeBox.setH(h);
        shapeBox.setW(w);
        calculateInfo();

        drawBox.drawShape(shapeBox);
        drawBox.updateInfoArea(shapeBox);
    }
    // 信息计算
    private void calculateInfo() {
        // 计算长方体的表面积
        shapeBox.setArea(shapeBox.getL()*shapeBox.getH()*2.0+shapeBox.getH()*shapeBox.getW()*2+shapeBox.getW()*shapeBox.getL()*2);

        // 计算长方体的体积
       shapeBox.setVolume(shapeBox.getL()*shapeBox.getW()*shapeBox.getH()*1.0);

    }
}
