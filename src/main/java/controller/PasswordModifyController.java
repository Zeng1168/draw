package controller;

import service.UserService;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.PasswordModifyView;

/**
 * 密码修改控制类
 */
public class PasswordModifyController extends BaseController<UserService> implements PasswordModifyView.UserModifyListener {
    private PasswordModifyView passwordModifyView;

    public PasswordModifyController() {
        passwordModifyView = new PasswordModifyView();
        passwordModifyView.setUserModifyListener(this);
    }

    @Override
    public void onModify(String password, String passwordOld) {
        if(password == null || password.equals("")){
            passwordModifyView.showMessageDialog("密码不能为空！");
            passwordModifyView.focusPasswordInput();
        }else if(passwordOld == null || passwordOld.equals("")){
            passwordModifyView.showMessageDialog("旧密码不能为空！");
            passwordModifyView.focusPasswordOldInput();
        }else {
            if(getServiceInstance()){
                MyResponse response = service.changePassword(passwordOld, password);
                if(response.getStatus() == ResultCode.SUCCESS.getCode()){   // 修改成功
                    passwordModifyView.showMessageDialog(response.getMsg());
                    passwordModifyView.dispose();
                }else { // 修改失败
                    passwordModifyView.showMessageDialog(response.getMsg());
                }
            }

        }
    }
}
