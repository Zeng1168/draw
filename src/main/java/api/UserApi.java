package api;

import utils.http.HttpTools;
import utils.http.MyResponse;

import java.util.Map;

public class UserApi {

    public MyResponse userLogin(Map<String, String> params){
        return  HttpTools.doPost("userAccount/passwordLogin", params);
    }

    public MyResponse userSign(Map<String, String> params){
        return  HttpTools.doPost("userAccount/userSign", params);
    }

    public MyResponse passwordChange(Map<String, String> params){
        return  HttpTools.doPost("user/passwordChange", params);
    }
}
