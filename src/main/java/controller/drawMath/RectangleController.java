package controller.drawMath;

import entity.ShapeRectangle;
import entity.ShapeTriangle;
import view.drawMath.DrawRectangle;

public class RectangleController implements  DrawRectangle.Listener{
    DrawRectangle drawRectangle;
    ShapeRectangle shapeRectangle;

    public RectangleController(DrawRectangle drawRectangle){this.drawRectangle=drawRectangle;}

    @Override
    public void onDraw1(int x, int y, int length, int width) {
      shapeRectangle=new ShapeRectangle();
      shapeRectangle.setName("未命名矩形");
      shapeRectangle.setX(x);
      shapeRectangle.setY(y);
      shapeRectangle.setWidth(width);
      shapeRectangle.setLength(length);
      calculateInfo();
      drawRectangle.drawShape(shapeRectangle);
      System.out.println(shapeRectangle.toString());
      drawRectangle.updateInfoArea(shapeRectangle);
    }
    private void calculateInfo(){
        //计算周长
        shapeRectangle.setPerimeter(2*(shapeRectangle.getWidth()+shapeRectangle.getLength()));
        //计算面积
        shapeRectangle.setArea(shapeRectangle.getLength()+shapeRectangle.getWidth());
    }


}
