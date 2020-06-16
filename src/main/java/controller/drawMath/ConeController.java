package controller.drawMath;

import entity.ShapeCone;
import view.drawMath.DrawCone;

public class ConeController implements DrawCone.Listener {
    private DrawCone drawCone;
    private ShapeCone shapeCone;

    public ConeController(DrawCone drawCone) {
        this.drawCone = drawCone;
    }

    @Override
    public void onDraw(int r, int h) {
        shapeCone = new ShapeCone();
        shapeCone.setName("未命名圆锥");
        shapeCone.setR(r);
        shapeCone.setH(h);

        calculateInfo();

        // 绘制到界面
        drawCone.drawShape(shapeCone);
        drawCone.updateInfoArea(shapeCone);
    }

    // 信息计算
    private void calculateInfo() {
        // 计算底面圆周长 L=2π*r
        shapeCone.setPerimeterCircle(shapeCone.getR()*Math.PI*2);

        // 计算底面圆面积 s=π*r*r
        shapeCone.setAreaCircle(shapeCone.getR()*Math.PI*Math.PI);

        // 计算圆锥体积 v=s*h/3
        shapeCone.setVolume(shapeCone.getH()*shapeCone.getAreaCircle()/3);
    }
}
