package controller.drawMath;

import api.ShapeBoxApi;
import api.ShapeRectagleApi;
import entity.ShapeRectangle;
import entity.ShapeTriangle;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.drawMath.DrawRectangle;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class RectangleController implements  DrawRectangle.Listener{
    DrawRectangle drawRectangle;
    ShapeRectangle shapeRectangle;

    public RectangleController(DrawRectangle drawRectangle){this.drawRectangle=drawRectangle;}

    public RectangleController(DrawRectangle drawRectangle, ShapeRectangle shapeRectangle) {
        this.drawRectangle = drawRectangle;
        this.shapeRectangle = shapeRectangle;
    }

    public void onDraw() {
        calculateInfo();
        drawRectangle.drawShape(shapeRectangle);
        drawRectangle.updateInfoArea(shapeRectangle);
        drawRectangle.setInput(shapeRectangle);
    }

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

    @Override
    public void saveToDataBase() {
        String name=(String) JOptionPane.showInputDialog(drawRectangle, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

        Map<String, String> params = new HashMap<>();
        params.put("h", shapeRectangle.getX() + "");
        params.put("l", shapeRectangle.getY() + "");
        params.put("length", shapeRectangle.getLength() + "");
        params.put("width", shapeRectangle.getWidth() + "");
        params.put("name", name);

        // 请求网络数据
        ShapeRectagleApi api = new ShapeRectagleApi();

        MyResponse response = api.insert(params);
        if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
            AlertUtil.infoDialog(response.getMsg());
        }else { // 失败
            AlertUtil.errorDialog(response.getMsg());
        }

    }

    private void calculateInfo(){
        //计算周长
        shapeRectangle.setPerimeter(2*(shapeRectangle.getWidth()+shapeRectangle.getLength()));
        //计算面积
        shapeRectangle.setArea(shapeRectangle.getLength()+shapeRectangle.getWidth());
    }


}
