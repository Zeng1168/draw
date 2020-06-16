package controller.drawMath;

import entity.ShapeTriangle;
import utils.ShapeUtil;
import view.drawMath.DrawTriangle;

import java.util.Date;

public class TriangleController implements DrawTriangle.Listener {
    DrawTriangle drawTriangle;
    ShapeTriangle shapeTriangle;

    public TriangleController(DrawTriangle drawTriangle) {
        this.drawTriangle = drawTriangle;
    }

    @Override
    public void onDraw1(int x1, int y1, int x2, int y2, int x3, int y3) {
        shapeTriangle = new ShapeTriangle();
        shapeTriangle.setName("未命名三角形");
        shapeTriangle.setX1(x1);
        shapeTriangle.setY1(y1);
        shapeTriangle.setX2(x2);
        shapeTriangle.setY2(y2);
        shapeTriangle.setX3(x3);
        shapeTriangle.setY3(y3);
        calculateLengthByABC(); // 计算长度
        calculateInfo();    // 计算周长、面积参数

        System.out.println(shapeTriangle.toString());

        drawTriangle.drawShape(shapeTriangle);
        drawTriangle.updateLengthABC(shapeTriangle);
        drawTriangle.updateInfoArea(shapeTriangle);
    }

    /**  根据三点算三边长度 */
    private void calculateLengthByABC(){
        // 计算距离
        shapeTriangle.setLengthAB(ShapeUtil.distance(shapeTriangle.getX1(), shapeTriangle.getY1(), shapeTriangle.getX2(), shapeTriangle.getY2()));
        shapeTriangle.setLengthBC(ShapeUtil.distance(shapeTriangle.getX3(), shapeTriangle.getY3(), shapeTriangle.getX2(), shapeTriangle.getY2()));
        shapeTriangle.setLengthCA(ShapeUtil.distance(shapeTriangle.getX1(), shapeTriangle.getY1(), shapeTriangle.getX3(), shapeTriangle.getY3()));
    }

    /**   计算周长、面积   */
    private void calculateInfo(){
        // 计算周长
        shapeTriangle.setPerimeter(shapeTriangle.getLengthAB() + shapeTriangle.getLengthBC() + shapeTriangle.getLengthCA());

        // 计算面积
        Double p = (double)(shapeTriangle.getLengthAB() + shapeTriangle.getLengthBC() + shapeTriangle.getLengthCA())/2;
        Double s = Math.sqrt(p*(p - shapeTriangle.getLengthAB())*(p - shapeTriangle.getLengthBC())*(p - shapeTriangle.getLengthCA()));
        shapeTriangle.setArea(s);
    }
}
