package api;

import utils.http.HttpTool;
import utils.http.MyResponse;

import java.util.Map;

public class DrawPlanformApi {
    // 查询当前用户绘图
    public MyResponse selectAll(){
        return  HttpTool.doGet("drawPlanform/selectAll");
    }

    // 添加
    public MyResponse insert(Map<String, String> params){
        return  HttpTool.doPost("drawPlanform/insert", params);
    }

    // 获取协同绘图邀请码
    public MyResponse getInvitingCode(Map<String, String> params){
        return  HttpTool.doGet("drawPlanform/getInvitingCode", params);
    }
}
