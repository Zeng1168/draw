package controller.drawMath;

import api.ShapeBoxApi;
import api.ShapeTriangleApi;
import entity.ShapeTriangle;
import utils.AlertUtil;
import utils.ShapeUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.drawMath.DrawTriangle;

import javax.swing.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TriangleController implements DrawTriangle.Listener {
    DrawTriangle drawTriangle;
    ShapeTriangle shapeTriangle;

    public TriangleController(DrawTriangle drawTriangle) {
        this.drawTriangle = drawTriangle;
    }

    public TriangleController(DrawTriangle drawTriangle, ShapeTriangle shapeTriangle) {
        this.drawTriangle = drawTriangle;
        this.shapeTriangle = shapeTriangle;
    }


    public void onDraw() {
        calculateLengthByABC();
        calculateInfo();    // 计算周长、面积参数
        drawTriangle.updateABC(shapeTriangle);  // 更新到输入框
        drawTriangle.drawShape(shapeTriangle);  // 绘制
        drawTriangle.updateInfoArea(shapeTriangle); // 更新侧边栏信息
        drawTriangle.setInput(shapeTriangle);
    }

    /** 根据ABC三点绘图  */
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

        drawTriangle.updateABC(shapeTriangle);  // 更新到输入框
        drawTriangle.drawShape(shapeTriangle);  // 绘制
        drawTriangle.updateInfoArea(shapeTriangle); // 更新侧边栏信息
    }

    /**  根据三边绘图  */
    @Override
    public void onDraw2(int ab, int bc, int ca) {
        // A点为坐标原点，B点在X轴上
        double Xc = (ca*(ca*ca + ab*ab - bc*bc))/(2*ab*ca);
        double Yc = Math.sqrt(ca*ca - Xc*Xc);

        onDraw1(0,0, ab, 0, new Double(Xc).intValue(), new Double(Yc).intValue());
    }

    @Override
    public void saveToDataBase() {
        String name=(String) JOptionPane.showInputDialog(drawTriangle, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

        Map<String, String> params = new HashMap<>();
        params.put("x1", shapeTriangle.getX1() + "");
        params.put("y1", shapeTriangle.getY1() + "");
        params.put("x2", shapeTriangle.getX2() + "");
        params.put("y2", shapeTriangle.getY2() + "");
        params.put("x3", shapeTriangle.getX3() + "");
        params.put("y3", shapeTriangle.getY3() + "");
        params.put("name", name);

        // 请求网络数据
        ShapeTriangleApi api = new ShapeTriangleApi();

        MyResponse response = api.insert(params);
        if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
            AlertUtil.infoDialog(response.getMsg());
        }else { // 失败
            AlertUtil.errorDialog(response.getMsg());
        }

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
