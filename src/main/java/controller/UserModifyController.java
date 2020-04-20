package controller;

import entity.User;
import service.UserService;
import utils.AlertUtil;
import utils.Result;
import utils.ResultCode;
import view.UserModifyView;

public class UserModifyController extends BaseController<UserService> implements UserModifyView.UserModifyListener {
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

            if(getServiceInstance()){
                Result updateResult = service.changePassword(user);
                if(updateResult.getStatus() == ResultCode.SUCCESS.getCode()){
                    userModifyView.showMessageDialog(updateResult.getMsg());
                    userModifyView.dispose();
                }else {
                    userModifyView.showMessageDialog(updateResult.getMsg());
                }
            }

        }
    }
}
