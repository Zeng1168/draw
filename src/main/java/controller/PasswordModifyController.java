package controller;

import api.UserApi;
import utils.AlertUtil;
import utils.http.MyResponse;
import utils.http.ResultCode;
import view.PasswordModifyView;

import java.util.HashMap;
import java.util.Map;

/**
 * 密码修改控制类
 */
public class PasswordModifyController implements PasswordModifyView.UserModifyListener {
    private PasswordModifyView passwordModifyView;

    public PasswordModifyController(PasswordModifyView passwordModifyView) {
        this.passwordModifyView = passwordModifyView;
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
            Map<String, String> params = new HashMap<>();
            params.put("oldPassword", passwordOld);
            params.put("password", password);

            // 请求网络数据
            UserApi userApi = new UserApi();

            MyResponse response = userApi.passwordChange(params);
            if(response.getStatus() == ResultCode.SUCCESS.getCode()){    // 成功
                AlertUtil.infoDialog(response.getMsg());
                passwordModifyView.dispose();
            }else { // 登录失败
                AlertUtil.warningDialog(response.getMsg());
            }

        }
    }
}
