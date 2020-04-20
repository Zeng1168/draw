package controller;


import controller.drawMainController.DrawMainController;
import entity.User;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.UserService;
import utils.Result;
import utils.ResultCode;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginController extends Application {
    UserService userService;

    @FXML
    TextField username;

    @FXML
    TextField password;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("style/Login.fxml"));
        root.getStylesheets().add(getClass().getClassLoader().getResource("style/login.css").toExternalForm());
        primaryStage.setTitle("用户登陆");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

    }

    @FXML
    public void btnLogin(ActionEvent e){
        String strUsername = username.getText();
        String strPsw = password.getText();

        if(strUsername.equals("")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("登陆状态提示");
            alert.setHeaderText(null);
            alert.setContentText("用户名不能为空");
            alert.showAndWait();
        }else if(strPsw.equals("")) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("登陆状态提示");
            alert.setHeaderText(null);
            alert.setContentText("密码不能为空");
            alert.showAndWait();
        }else {
                 User user=new User();
                 user.setName(strUsername);
                 user.setPassword(strPsw);


                 userService = new UserService();
            System.out.println(userService.toString());
                    Result loginResult = userService.userLogin(user);
                    if(loginResult.getStatus() == ResultCode.SUCCESS.getCode()){    // 登录成功
                        try {
                            // 生成初始图片
                            BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
                            Graphics g = image.getGraphics();
                            g.setColor(Color.WHITE);
                            g.fillRect(0,0,image.getWidth(), image.getHeight());

                            new DrawMainController(image);

                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }else { // 登录失败
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("登陆状态提示");
                        alert.setHeaderText(null);
                        alert.setContentText(loginResult.getMsg());
                        alert.showAndWait();
                    }
        }

    }
    @FXML
    public void btnRegist(ActionEvent e) {
        String strUsername = username.getText();
        String strPsw = password.getText();


        if(strUsername.equals("")){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("注册状态提示");
            alert.setHeaderText(null);
            alert.setContentText("用户名不能为空");
            alert.showAndWait();


        }else if(strPsw.equals("")) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("注册状态提示");
            alert.setHeaderText(null);
            alert.setContentText("密码不能为空");
            alert.showAndWait();
        }else{
            User user=new User();
            user.setName(strUsername);
            user.setPassword(strPsw);
//            userMapper=new UserDao();
//            String result=userMapper.insertUser(user);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("注册状态提示");
            alert.setHeaderText(null);
//            alert.setContentText(result);
            alert.showAndWait();
            }
        }

    @FXML
    public void btnCancle(ActionEvent e){
        System.exit(0);

    }

}


