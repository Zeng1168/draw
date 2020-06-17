package controller;

import api.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.*;
import entity.DrawPlanform;
import sun.misc.BASE64Decoder;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.DataQueryView;
import view.drawMath.DrawMathView;
import view.drawPlatform.DrawPlatformView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataQueryController implements DataQueryView.Listener {
    private DataQueryView dataQueryView;

    public DataQueryController(DataQueryView dataQueryView) {
        this.dataQueryView = dataQueryView;
    }

    @Override
    public void onQuery() {
        List<DataBrief> list = null;
        MyResponse response = null;

        if(dataQueryView.getDrawMathMode() == null){
            DrawPlanformApi api = new DrawPlanformApi();
            response = api.selectAll();
        }else {
            switch (dataQueryView.getDrawMathMode()){
                case TRIANGLE: {
                    ShapeTriangleApi api = new ShapeTriangleApi();
                    response = api.selectAll();
                } break;
                case CONE:{
                    ShapeConeApi api = new ShapeConeApi();
                    response = api.selectAll();
                }break;
                case CIRCLE:{
                    ShapeCircleApi api = new ShapeCircleApi();
                    response = api.selectAll();
                }break;
                case RETANGLE:{
                    ShapeRectagleApi api = new ShapeRectagleApi();
                    response = api.selectAll();
                }break;
                case SPHERE:{
                    ShapeSphereApi api = new ShapeSphereApi();
                    response = api.selectAll();
                }break;
                case CUBOID:{
                    ShapeBoxApi api = new ShapeBoxApi();
                    response = api.selectAll();
                }
            }
        }

        if(response == null) {
            AlertUtil.errorDialog("查询失败，请检查网络连接！");
        }else if(response.getStatus() == ResultCode.SUCCESS.getCode()){
            System.out.println(response.getData());
            list = JSON.parseObject(response.getData(), new TypeReference<List<DataBrief>>(){});
            dataQueryView.setData(list);
        }else{
            AlertUtil.errorDialog("服务器异常，查询失败！");
        }
    }

    @Override
    public void onEdit(Integer id) {
        MyResponse response = null;

        Map<String, String> params = new HashMap<>();
        params.put("id", ""+id);

        if(dataQueryView.getDrawMathMode() == null){
            DrawPlanformApi api = new DrawPlanformApi();
            response = api.selectById(params);
        }else {
            switch (dataQueryView.getDrawMathMode()){
                case TRIANGLE: {
                    ShapeTriangleApi api = new ShapeTriangleApi();
                    response = api.selectById(params);
                } break;
                case CONE:{
                    ShapeConeApi api = new ShapeConeApi();
                    response = api.selectById(params);
                }break;
                case CIRCLE:{
                    ShapeCircleApi api = new ShapeCircleApi();
                    response = api.selectById(params);
                }break;
                case RETANGLE:{
                    ShapeRectagleApi api = new ShapeRectagleApi();
                    response = api.selectById(params);
                }break;
                case SPHERE:{
                    ShapeSphereApi api = new ShapeSphereApi();
                    response = api.selectById(params);
                }break;
                case CUBOID:{
                    ShapeBoxApi api = new ShapeBoxApi();
                    response = api.selectById(params);
                }
            }
        }

        if(response == null) {
            AlertUtil.errorDialog("查询失败，请检查网络连接！");
        }else if(response.getStatus() == ResultCode.SUCCESS.getCode()){
            if(dataQueryView.getDrawMathMode() == null) {
                DrawPlanform drawPlanform = JSON.parseObject(response.getData(), DrawPlanform.class);
                BASE64Decoder decoder = new BASE64Decoder();
                try {
                    byte[] bytes1 = decoder.decodeBuffer(drawPlanform.getImage());
                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
                    BufferedImage bfimage = ImageIO.read(bais);

                    new DrawPlatformView(bfimage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                DrawMathView drawMathView1 = new DrawMathView();
                drawMathView1.dataLoading(dataQueryView.getDrawMathMode(), response.getData());
            }

            dataQueryView.dispose();    // 关闭数据窗口
        }else{
            AlertUtil.errorDialog("服务器异常，查询失败！");
        }
    }

    @Override
    public void onDelete(Integer id) {
        MyResponse response = null;

        Map<String, String> params = new HashMap<>();
        params.put("id", ""+id);

        if(dataQueryView.getDrawMathMode() == null){
            DrawPlanformApi api = new DrawPlanformApi();
            response = api.delete(params);
        }else {
            switch (dataQueryView.getDrawMathMode()){
                case TRIANGLE: {
                    ShapeTriangleApi api = new ShapeTriangleApi();
                    response = api.delete(params);
                } break;
                case CONE:{
                    ShapeConeApi api = new ShapeConeApi();
                    response = api.delete(params);
                }break;
                case CIRCLE:{
                    ShapeCircleApi api = new ShapeCircleApi();
                    response = api.delete(params);
                }break;
                case RETANGLE:{
                    ShapeRectagleApi api = new ShapeRectagleApi();
                    response = api.delete(params);
                }break;
                case SPHERE:{
                    ShapeSphereApi api = new ShapeSphereApi();
                    response = api.delete(params);
                }break;
                case CUBOID:{
                    ShapeBoxApi api = new ShapeBoxApi();
                    response = api.delete(params);
                }
            }
        }

        if(response == null) {
            AlertUtil.errorDialog("查询失败，请检查网络连接！");
        }else if(response.getStatus() == ResultCode.SUCCESS.getCode()){
            AlertUtil.infoDialog(response.getMsg());
        }else{
            AlertUtil.errorDialog(response.getMsg());
        }
    }
}
