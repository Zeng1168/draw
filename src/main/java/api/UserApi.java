package api;

import utils.http.HttpTool;
import utils.http.MyResponse;

import java.util.Map;

public class UserApi {

    // 登录
    public MyResponse userLogin(Map<String, String> params){
        return  HttpTool.doPost("userAccount/passwordLogin", params);
    }

    // 注册
    public MyResponse userSign(Map<String, String> params){
        return  HttpTool.doPost("userAccount/userSign", params);
    }

    // 修改密码
    public MyResponse passwordChange(Map<String, String> params){
        return  HttpTool.doPost("user/passwordChange", params);
    }

    // 注销
    public MyResponse logout(){
        return HttpTool.doPost("userAccount/logout", null);
    }

    // 查询详细信息
    public MyResponse selectDetails(){
        return HttpTool.doGet("user/selectDetails");
    }
}
