package api;

import utils.http.HttpTool;
import utils.http.MyResponse;

import java.util.Map;

public class UserApi {

    public MyResponse userLogin(Map<String, String> params){
        return  HttpTool.doPost("userAccount/passwordLogin", params);
    }

    public MyResponse userSign(Map<String, String> params){
        return  HttpTool.doPost("userAccount/userSign", params);
    }

    public MyResponse passwordChange(Map<String, String> params){
        return  HttpTool.doPost("user/passwordChange", params);
    }
}
