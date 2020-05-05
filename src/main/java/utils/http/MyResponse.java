package utils.http;

import com.alibaba.fastjson.JSON;

public class MyResponse {
    private Integer status;
    private String msg;
    private String data;

    public static MyResponse errorResponse(String msg, Integer status){
        MyResponse response = new MyResponse();
        response.setMsg(msg);
        response.setStatus(status);
        return response;
    }

    public static MyResponse errorResponse(String msg){
        return errorResponse(msg,1);
    }

    public static MyResponse failureResponse(String msg){
        return errorResponse(msg,ResultCode.FAILURE.getCode());
    }

    public Boolean failure(){
        return status != null && status == ResultCode.FAILURE.getCode();
    }

    public Boolean ok(){
        if(status != null && status == 0) return true;
        else return false;
    }

    public Object getBody(Class cls){
        if(data == null || (data + "").equals("")) return null;
        try {
            System.out.println("+++++++++++");
            System.out.println(data);
            Object tranData = JSON.parseObject(data+"",cls);
            return tranData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"status\":")
                .append(status);
        sb.append(",\"msg\":\"")
                .append(msg).append('\"');
        sb.append(",\"data\":\"")
                .append(data).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
