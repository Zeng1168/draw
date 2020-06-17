package controller.drawMath;

import api.ShapeBoxApi;
import api.ShapeCircleApi;
import entity.ShapeCircle;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.drawMath.DrawCircle;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CircleController implements DrawCircle.Listener {
    DrawCircle drawCircle;
    ShapeCircle shapeCircle;

    public CircleController(DrawCircle drawCircle) {
        this.drawCircle = drawCircle;
    }

    public CircleController(DrawCircle drawCircle, ShapeCircle shapeCircle) {
        this.drawCircle = drawCircle;
        this.shapeCircle = shapeCircle;
    }

    public void onDraw(){
        calculateInfo();    // 计算周长、面积参数
        drawCircle.drawShape(shapeCircle);  // 绘制
        drawCircle.updateInfoArea(shapeCircle); // 更新侧边栏信息
        drawCircle.setInput(shapeCircle);  // 更新到输入框
    }

    /** 根据ABC三点绘图  */

    @Override
    public void onDraw1(int x1, int y1, int radius) {

            shapeCircle=new ShapeCircle();
            shapeCircle.setName("未命名圆形");
            shapeCircle.setX(x1);
            shapeCircle.setY(y1);
            shapeCircle.setRadius(radius);
            //  calculateLengthByABC(); // 计算长度
            calculateInfo();    // 计算周长、面积参数

            System.out.println(shapeCircle.toString());

            //drawTriangle.updateABC(shapeTriangle);  // 更新到输入框
            drawCircle.drawShape(shapeCircle);  // 绘制
            drawCircle.updateInfoArea(shapeCircle); // 更新侧边栏信息
    }

    @Override
    public void saveToDataBase() {
        String name=(String) JOptionPane.showInputDialog(drawCircle, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

        Map<String, String> params = new HashMap<>();
        params.put("x", shapeCircle.getX() + "");
        params.put("y", shapeCircle.getY() + "");
        params.put("radius", shapeCircle.getRadius() + "");
        params.put("name", name);

        // 请求网络数据
        ShapeCircleApi api = new ShapeCircleApi();

        MyResponse response = api.insert(params);
        if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
            AlertUtil.infoDialog(response.getMsg());
        }else { // 失败
            AlertUtil.errorDialog(response.getMsg());
        }
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
