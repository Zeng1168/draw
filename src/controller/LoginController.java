package controller;

import controller.drawMainController.DrawMainController;
import mapper.UserMapper;
import entity.User;
import view.LoginView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoginController implements LoginView.LoginListener {
    private LoginView loginView;
    private UserMapper userMapper;


    public LoginController(){
        loginView = new LoginView();
        loginView.setLoginListener(this);
    }

    @Override
    public void onLogin(String name, String password) {
        if (name == null || name.equals("")) {
            loginView.showMessageDialog("用户名不能为空");
            loginView.focusUserInput();
        } else if (password == null || password.equals("")) {
            loginView.showMessageDialog("密码不能为空");
            loginView.focusPasswordInput();
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);

            userMapper = new UserMapper();
            if (userMapper.userLogin(user)) {

                // 生成初始图片
                BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
                Graphics g = image.getGraphics();
                g.setColor(Color.WHITE);
                g.fillRect(0,0,image.getWidth(), image.getHeight());

                new DrawMainController(image);
                loginView.dispose();
            } else {
                loginView.focusUserInput();
                loginView.showMessageDialog("用户名或密码不正确");
            }
        }
    }

    @Override
    public void onRegister(String name, String password) {
        if (name == null || name.equals("")) {
            loginView.showMessageDialog("用户名不能为空");
            loginView.focusUserInput();
        } else if (password == null || password.equals("")) {
            loginView.showMessageDialog("密码不能为空");
            loginView.focusPasswordInput();
        } else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);

            userMapper = new UserMapper();
            loginView.showMessageDialog(userMapper.insertUser(user));
        }

    }

    @Override
    public void onModifyUser() {
        new UserModifyController();
    }
}
