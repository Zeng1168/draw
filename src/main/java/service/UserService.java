package service;

import dao.UserDao;
import entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utils.Result;

public class UserService extends BaseService<UserDao> {

    public Result userLogin(User user){
        Result result = null;
        if(openSqlSession()){
            User queryUser = dao.queryUserByName(user.getName());
            if(queryUser != null) {
                // 加密的密码对比
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if(encoder.matches(user.getPassword(), queryUser.getPassword())){
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

    public Result userSign(User user){
        Result result = null;
        if(openSqlSession()){
            User existUser = dao.queryUserByName(user.getName());
            if(existUser == null){
                // 进行密码加密
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));

                int num = dao.insertUser(user);
                if(num == 1){
                    result = Result.ok("注册成功！", "");
                }else {
                    result = Result.error("注册失败！", "");
                }
            }else {
                result = Result.error("用户已存在！", "");
            }
            closeSqlsession();
        }else {
            result = Result.error("数据库连接异常！", "");
        }

        return result;
    }

    public Result changePassword(User user){
        Result result = null;
        if(openSqlSession()){
            User existUser = dao.queryUserByName(user.getName());
            if(existUser != null){
                // 加密的密码对比
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

                if(encoder.matches(user.getPasswordOld(), existUser.getPassword())){
                    int num = dao.updatePassword(user);
                    if(num == 1){
                        result = Result.ok("密码修改成功！", "");
                    }else {
                        result = Result.error("密码修改失败！", "");
                    }
                }else {
                    result = Result.error("原密码输入有误！", "");
                }
            }else {
                result = Result.error("用户不存在！", "");
            }
            closeSqlsession();
        }else {
            result = Result.error("数据库连接异常！", "");
        }

        return result;
    }
}
