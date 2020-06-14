package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;

import java.awt.*;

public class RegistController extends BaseController<UserService> {

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
    public void btnRegist(){

        String strUsername = username.getText();
        String strPsw = password.getText();
        String strPhone=phone.getText();

        if(strUsername.equals("")){
            AlertUtil.alertWarn("注册状态提示", null,"用户名不能为空");
        }else if(strPsw.equals("")) {
            AlertUtil.alertWarn("注册状态提示", null,"密码不能为空");
        }else if (strPhone.equals("")){
            AlertUtil.alertWarn("注册状态提示", null,"电话号码不能为空");
        }
            else{
            if(getServiceInstance()){
                // 请求网络
                MyResponse signResult = service.userSign(strUsername, strPsw);
                if(signResult.getStatus() == ResultCode.SUCCESS.getCode()){
                    AlertUtil.alertInfo("注册状态提示", null,"注册成功！");
                }else {
                    AlertUtil.alertWarn("注册状态提示", null,signResult.getMsg());
                }
            }
        }
    }

    @FXML
    public void btnCancle(){
        Stage stage = (Stage)cancel.getScene().getWindow();
        stage.close();

    }



}
