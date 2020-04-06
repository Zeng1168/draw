package controller;

import mapper.UserMapper;
import entity.User;
import view.UserModifyView;

public class UserModifyController implements UserModifyView.UserModifyListener {
    private UserModifyView userModifyView;

    public UserModifyController() {
        userModifyView = new UserModifyView();
        userModifyView.setUserModifyListener(this);
    }

    @Override
    public void onModify(String name, String password, String passwordOld) {
        if(name == null || name.equals("")){
            userModifyView.showMessageDialog("用户名不能为空！");
            userModifyView.focusUserInput();
        }else if(password == null || password.equals("")){
            userModifyView.showMessageDialog("密码不能为空！");
            userModifyView.focusPasswordInput();
        }else if(passwordOld == null || passwordOld.equals("")){
            userModifyView.showMessageDialog("旧密码不能为空！");
            userModifyView.focusPasswordOldInput();
        }else {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setPasswordOld(passwordOld);

            UserMapper userMapper = new UserMapper();
            userModifyView.showMessageDialog(userMapper.updateUser(user));
            userModifyView.dispose();
        }
    }
}
