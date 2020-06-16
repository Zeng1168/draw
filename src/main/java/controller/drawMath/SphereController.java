package controller.drawMath;


import entity.ShapeSphere;
import view.drawMath.DrawSphere;

public class SphereController implements DrawSphere.Listener {
    private DrawSphere drawSphere;
    private ShapeSphere shapeSphere;

    public SphereController(DrawSphere drawSphere) {
        this.drawSphere = drawSphere;
    }


    @Override
    public void onDraw(float r) {
        shapeSphere = new ShapeSphere();
        shapeSphere.setName("未命名球");
        shapeSphere.setR(r);


        calculateInfo();

        // 绘制到界面
        drawSphere.drawShape(shapeSphere);
        drawSphere.updateInfoArea(shapeSphere);
    }

    // 信息计算
    private void calculateInfo() {

        shapeSphere.setArea(Math.pow(shapeSphere.getR(),2)*4*Math.PI);
        // 计算体积 v=4/3*pi*r^3
        shapeSphere.setVolume(Math.pow(shapeSphere.getR(),3)*Math.PI*(4/3));
    }
}
