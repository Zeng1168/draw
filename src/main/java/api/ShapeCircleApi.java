package api;

import utils.http.HttpTool;
import utils.http.MyResponse;

import java.util.Map;

public class ShapeCircleApi {

    // 查询当前用户绘图
    public MyResponse selectAll(){
        return  HttpTool.doGet("shapeCircle/selectAll");
    }

    // 添加
    public MyResponse insert(Map<String, String> params){
        return  HttpTool.doPost("shapeCircle/insert", params);
    }

    // 删除
    public MyResponse delete(Map<String, String> params){
        return  HttpTool.doGet("shapeCircle/delete");
    }
}
