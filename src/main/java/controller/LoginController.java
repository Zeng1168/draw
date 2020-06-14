package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import service.UserService;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;

public class LoginController extends BaseController<UserService> {

    @FXML
    TextField username;

    @FXML
    TextField password;

    /**
     * 登录按钮监听
     * @param e
     */
    @FXML
    public void btnLogin(ActionEvent e){
        String strUsername = username.getText();
        String strPsw = password.getText();

        if(strUsername.equals("")){
            AlertUtil.alertWarn("登陆状态提示", null,"用户名不能为空！");
        }else if(strPsw.equals("")) {
            AlertUtil.alertWarn("登陆状态提示", null,"密码不能为空！");
        }else {
            if(getServiceInstance()){

                // 请求网络数据
                MyResponse response = service.userLogin(strUsername, strPsw);
                if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 登录成功
                    try {
                        // 打开绘图主界面
                        new DrawPlatformController();
                        System.out.println("登录成功！");

                        // 关闭登录界面
                        // 通过stage方式操作窗口，因为一个新的窗口就是一个新的stage
                        Stage stage = (Stage)username.getScene().getWindow();
                        stage.close();
                        Platform.exit();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }else { // 登录失败
                    AlertUtil.alertWarn("登陆状态提示", null,response.getMsg());
                }
            }
        }

    }

    /**
     * 注册按钮监听
     * @param e
     */
    @FXML
    public void btnRegist(ActionEvent e) {

        try {
            AnchorPane page = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Regist.fxml"));
            Stage stage = new Stage();
            stage.setTitle("注册界面");
            stage.setScene(new Scene(page,600,400));
            stage.show();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }



    @FXML
    public void btnCancle(ActionEvent e){
        System.exit(0);
    }

}


