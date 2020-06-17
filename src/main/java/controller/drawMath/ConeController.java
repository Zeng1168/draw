package controller.drawMath;

import api.ShapeBoxApi;
import api.ShapeConeApi;
import entity.ShapeCone;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.drawMath.DrawCone;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ConeController implements DrawCone.Listener {
    private DrawCone drawCone;
    private ShapeCone shapeCone;

    public ConeController(DrawCone drawCone) {
        this.drawCone = drawCone;
    }

    public ConeController(DrawCone drawCone, ShapeCone shapeCone) {
        this.drawCone = drawCone;
        this.shapeCone = shapeCone;
    }


    public void onDraw() {
        calculateInfo();
        drawCone.drawShape(shapeCone);
        drawCone.updateInfoArea(shapeCone);
        drawCone.setInput(shapeCone);
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

    @Override
    public void saveToDataBase() {
        String name=(String) JOptionPane.showInputDialog(drawCone, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

        Map<String, String> params = new HashMap<>();
        params.put("h", shapeCone.getH() + "");
        params.put("r", shapeCone.getR() + "");
        params.put("name", name);

        // 请求网络数据
        ShapeConeApi api = new ShapeConeApi();

        MyResponse response = api.insert(params);
        if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
            AlertUtil.infoDialog(response.getMsg());
        }else { // 失败
            AlertUtil.errorDialog(response.getMsg());
        }
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
