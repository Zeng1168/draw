package api;

import utils.http.HttpTool;
import utils.http.MyResponse;

import java.util.Map;

public class ShapeSphereApi {
    // 根据ID查询
    public MyResponse selectById(Map<String, String> params){
        return  HttpTool.doGet("shapeSphere/selectById", params);
    }

    // 查询当前用户绘图
    public MyResponse selectAll(){
        return  HttpTool.doGet("shapeSphere/selectAll");
    }

    // 添加
    public MyResponse insert(Map<String, String> params){
        return  HttpTool.doPost("shapeSphere/insert", params);
    }

    // 删除
    public MyResponse delete(Map<String, String> params){
        return  HttpTool.doGet("shapeSphere/delete", params);
    }
}
