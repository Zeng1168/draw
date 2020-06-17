package controller.drawMath;


import api.ShapeBoxApi;
import api.ShapeSphereApi;
import entity.ShapeSphere;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.drawMath.DrawSphere;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SphereController implements DrawSphere.Listener {
    private DrawSphere drawSphere;
    private ShapeSphere shapeSphere;

    public SphereController(DrawSphere drawSphere) {
        this.drawSphere = drawSphere;
    }

    public SphereController(DrawSphere drawSphere, ShapeSphere shapeSphere) {
        this.drawSphere = drawSphere;
        this.shapeSphere = shapeSphere;
    }

    public void onDraw() {
        calculateInfo();
        drawSphere.drawShape(shapeSphere);
        drawSphere.updateInfoArea(shapeSphere);
        drawSphere.setInput(shapeSphere);
    }

    @Override
    public void onDraw(int r) {
        shapeSphere = new ShapeSphere();
        shapeSphere.setName("未命名球");
        shapeSphere.setR(r);


        calculateInfo();

        // 绘制到界面
        drawSphere.drawShape(shapeSphere);
        drawSphere.updateInfoArea(shapeSphere);
    }

    @Override
    public void saveToDataBase() {
        String name=(String) JOptionPane.showInputDialog(drawSphere, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

        Map<String, String> params = new HashMap<>();
        params.put("r", shapeSphere.getR() + "");
        params.put("name", name);

        // 请求网络数据
        ShapeSphereApi api = new ShapeSphereApi();

        MyResponse response = api.insert(params);
        if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
            AlertUtil.infoDialog(response.getMsg());
        }else { // 失败
            AlertUtil.errorDialog(response.getMsg());
        }

    }

    // 信息计算
    private void calculateInfo() {

        shapeSphere.setArea(Math.pow(shapeSphere.getR(),2)*4*Math.PI);
        // 计算体积 v=4/3*pi*r^3
        shapeSphere.setVolume(Math.pow(shapeSphere.getR(),3)*Math.PI*(4/3));
    }
}
