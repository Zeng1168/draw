package controller;

import service.UserService;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.UserModifyView;

public class UserModifyController extends BaseController<UserService> implements UserModifyView.UserModifyListener {
    private UserModifyView userModifyView;

    public UserModifyController() {
        userModifyView = new UserModifyView();
        userModifyView.setUserModifyListener(this);
    }

    @Override
    public void onModify(String password, String passwordOld) {
        if(password == null || password.equals("")){
            userModifyView.showMessageDialog("密码不能为空！");
            userModifyView.focusPasswordInput();
        }else if(passwordOld == null || passwordOld.equals("")){
            userModifyView.showMessageDialog("旧密码不能为空！");
            userModifyView.focusPasswordOldInput();
        }else {
            if(getServiceInstance()){
                MyResponse response = service.changePassword(passwordOld, password);
                if(response.getStatus() == ResultCode.SUCCESS.getCode()){
                    userModifyView.showMessageDialog(response.getMsg());
                    userModifyView.dispose();
                }else {
                    userModifyView.showMessageDialog(response.getMsg());
                }
            }

        }
    }
}
