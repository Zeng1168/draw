package controller;

import controller.drawMainController.DrawMainController;
import entity.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;
import utils.AlertUtil;
import utils.Result;
import utils.ResultCode;

public class LoginController extends BaseController<UserService> {

    @FXML
    TextField username;

    @FXML
    TextField password;

    @FXML
    public void btnLogin(ActionEvent e){
        String strUsername = username.getText();
        String strPsw = password.getText();

        if(strUsername.equals("")){
            AlertUtil.alertWarn("登陆状态提示", null,"用户名不能为空！");
        }else if(strPsw.equals("")) {
            AlertUtil.alertWarn("登陆状态提示", null,"密码不能为空！");
        }else {
                 User user=new User();
                 user.setName(strUsername);
                 user.setPassword(strPsw);

                 if(getServiceInstance()){
                     Result loginResult = service.userLogin(user);
                     if(loginResult.getStatus() == ResultCode.SUCCESS.getCode()){    // 登录成功
                         try {
                             new DrawMainController();
                             //通过stage方式操作窗口，因为一个新的窗口就是一个新的stage
                             Stage stage = (Stage)username.getScene().getWindow();
                             stage.close();
                             Platform.exit();
                         } catch (Exception e2) {
                             e2.printStackTrace();
                         }
                     }else { // 登录失败
                         AlertUtil.alertWarn("登陆状态提示", null,loginResult.getMsg());
                     }
                 }
        }

    }
    @FXML
    public void btnRegist(ActionEvent e) {
        String strUsername = username.getText();
        String strPsw = password.getText();

        if(strUsername.equals("")){
            AlertUtil.alertWarn("注册状态提示", null,"用户名不能为空");
        }else if(strPsw.equals("")) {
            AlertUtil.alertWarn("注册状态提示", null,"密码不能为空");
        }else{
            User user=new User();
            user.setName(strUsername);
            user.setPassword(strPsw);

            if(getServiceInstance()){
                Result signResult = service.userSign(user);
                if(signResult.getStatus() == ResultCode.SUCCESS.getCode()){
                    AlertUtil.alertInfo("注册状态提示", null,"注册成功！");
                }else {
                    AlertUtil.alertWarn("注册状态提示", null,signResult.getMsg());
                }
            }
        }
    }

    @FXML
    public void btnCancle(ActionEvent e){
        System.exit(0);
    }

}


