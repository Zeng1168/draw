package service;

import api.DrawPlanformApi;
import utils.http.MyResponse;

import java.util.HashMap;
import java.util.Map;

public class DrawPlanformService extends BaseService<DrawPlanformApi>  {

    public MyResponse insertImage(String image, String name){
        Map<String, String> params = new HashMap<>();
        params.put("image", image);
        params.put("name", name);
        if(getApiInstance()) {
            return api.insert(params);
        }else return MyResponse.failureResponse("网络请求失败！");
    }
}
