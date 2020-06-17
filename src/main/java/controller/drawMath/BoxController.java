package controller.drawMath;

import api.DrawPlanformApi;
import api.ShapeBoxApi;
import entity.ShapeBox;

import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.drawMath.DrawBox;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class BoxController implements  DrawBox.Listener{
    private DrawBox drawBox;
    private ShapeBox shapeBox;

    public BoxController(DrawBox drawBox){this.drawBox=drawBox;}

    public BoxController(DrawBox drawBox, ShapeBox shapeBox) {
        this.drawBox = drawBox;
        this.shapeBox = shapeBox;
    }

    public void onDraw(){
        calculateInfo();
        drawBox.drawShape(shapeBox);
        drawBox.updateInfoArea(shapeBox);
        drawBox.setInput(shapeBox);
    }

    @Override
    public void onDraw(int l, int w, int h) {
        shapeBox=new ShapeBox();
        shapeBox.setName("未命名长方体");
        shapeBox.setL(l);
        shapeBox.setH(h);
        shapeBox.setW(w);
        calculateInfo();

        drawBox.drawShape(shapeBox);
        drawBox.updateInfoArea(shapeBox);
    }

    @Override
    public void saveToDataBase() {
        String name=(String) JOptionPane.showInputDialog(drawBox, "请输入绘制图像名称：", "图像名称", JOptionPane.PLAIN_MESSAGE);

        Map<String, String> params = new HashMap<>();
        params.put("h", shapeBox.getH() + "");
        params.put("l", shapeBox.getL() + "");
        params.put("w", shapeBox.getW() + "");
        params.put("name", name);

        // 请求网络数据
        ShapeBoxApi api = new ShapeBoxApi();

        MyResponse response = api.insert(params);
        if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
            AlertUtil.infoDialog(response.getMsg());
        }else { // 失败
            AlertUtil.errorDialog(response.getMsg());
        }
    }

    // 信息计算
    private void calculateInfo() {
        // 计算长方体的表面积
        shapeBox.setArea(shapeBox.getL()*shapeBox.getH()*2.0+shapeBox.getH()*shapeBox.getW()*2+shapeBox.getW()*shapeBox.getL()*2);

        // 计算长方体的体积
       shapeBox.setVolume(shapeBox.getL()*shapeBox.getW()*shapeBox.getH()*1.0);

    }
}
