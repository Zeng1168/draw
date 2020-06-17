package controller;

import api.UserApi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;

import java.util.HashMap;
import java.util.Map;

public class RegistController {

    @FXML
    javafx.scene.control.TextField username;
    @FXML
    TextField phone;
    @FXML
    TextField password;
    @FXML
    Button regist;
    @FXML
    Button cancel;
    @FXML
    Button login;



    @FXML
    public void btnRegist(){
        String strUsername = username.getText();
        String strPsw = password.getText();
        String strPhone = phone.getText();

        if(strUsername.equals("")){
            AlertUtil.alertWarn("注册状态提示", null,"用户名不能为空");
        }else if(strPsw.equals("")) {
            AlertUtil.alertWarn("注册状态提示", null,"密码不能为空");
        }else if (strPhone.equals("")){
            AlertUtil.alertWarn("注册状态提示", null,"电话号码不能为空");
        }else {
            Map<String, String> params = new HashMap<>();
            params.put("username", strUsername);
            params.put("password", strPsw);
            params.put("phone", strPhone);

            // 请求网络数据
            UserApi userApi = new UserApi();
            MyResponse signResult = userApi.userSign(params);
            if(signResult.getStatus() == ResultCode.SUCCESS.getCode()){
                AlertUtil.alertInfo("注册状态提示", null,"注册成功！");
            }else {
                AlertUtil.alertWarn("注册状态提示", null,signResult.getMsg());
            }
        }
    }

    @FXML
    public void btnCancle(){
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnLogin(){
        ((Stage)phone.getScene().getWindow()).close();

        try {
            AnchorPane page = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("ODS画板-用户登录");
            stage.setScene(new Scene(page,600,400));
            stage.show();
        }catch (Exception e1){
            e1.printStackTrace();
        }

    }
}
