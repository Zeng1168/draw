package controller.drawMath;

import entity.ShapeCircle;
import utils.ShapeUtil;
import view.drawMath.DrawCircle;
import view.drawMath.IDraw;

import javax.swing.*;

public class CircleController implements DrawCircle.Listener {
    DrawCircle drawCircle;
    ShapeCircle shapeCircle;

    public CircleController(DrawCircle drawCircle) {
        this.drawCircle = drawCircle;
    }

    /** 根据ABC三点绘图  */

    @Override
    public void onDraw1(int x1, int y1, int radius) {

            shapeCircle=new ShapeCircle();
            shapeCircle.setName("未命名圆形");
            shapeCircle.setX1(x1);
            shapeCircle.setY1(y1);
            shapeCircle.setRadius(radius);
            //  calculateLengthByABC(); // 计算长度
            calculateInfo();    // 计算周长、面积参数

            System.out.println(shapeCircle.toString());

            //drawTriangle.updateABC(shapeTriangle);  // 更新到输入框
            drawCircle.drawShape(shapeCircle);  // 绘制
            drawCircle.updateInfoArea(shapeCircle); // 更新侧边栏信息




    }

    /**   计算周长、面积   */
    private void calculateInfo(){
        // 计算周长
        shapeCircle.setPerimeter(shapeCircle.getRadius()*2*Math.PI);

        // 计算面积
        shapeCircle.setArea(Math.pow(shapeCircle.getRadius(),2)*Math.PI);
      //  Double p = (double)(shapeTriangle.getLengthAB() + shapeTriangle.getLengthBC() + shapeTriangle.getLengthCA())/2;

    }
}
