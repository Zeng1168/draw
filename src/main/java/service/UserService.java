package service;

import api.UserApi;
import utils.http.MyResponse;

import java.util.HashMap;
import java.util.Map;

public class UserService extends BaseService<UserApi> {

    // 用户登录
    public MyResponse userLogin(String username, String password){
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        if(getApiInstance()) {
            return api.userLogin(params);
        }else return MyResponse.failureResponse("网络请求失败！");
    }

    // 注册
    public MyResponse userSign(String username, String password,String phone){
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("phone",phone);
        if(getApiInstance()){
            return api.userSign(params);
        }else return MyResponse.failureResponse("网络请求失败！");
    }

    // 修改密码
    public MyResponse changePassword(String oldPassword, String password){
        Map<String, String> params = new HashMap<>();
        params.put("oldPassword", oldPassword);
        params.put("password", password);
        if(getApiInstance()){
            return api.passwordChange(params);
        }else return MyResponse.failureResponse("网络请求失败！");
    }
}
