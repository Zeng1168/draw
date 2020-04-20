package service;

import dao.UserDao;
import entity.User;
import utils.Result;

public class UserService extends BaseService<UserDao> {

    public Result userLogin(User user){
        Result result = null;

        if(openSqlSession()){
            User queryUser = dao.queryUserByName(user.getName());
            if(queryUser != null) {
                if(user.getPassword().equals(queryUser.getPassword())){
                    result = Result.ok("登录成功！", queryUser);
                }else {
                    result = Result.error("用户名或密码错误！", "");
                }
            }else {
                result = Result.error("用户不存在！","");
            }

            closeSqlsession();
        }else {
            result = Result.error("数据库连接异常！", "");
        }

        return result;

    }
}
