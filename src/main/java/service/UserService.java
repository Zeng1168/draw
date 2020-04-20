package service;

import dao.UserDao;
import entity.User;
import org.apache.ibatis.session.SqlSession;
import utils.Result;
import utils.SqlSessionFactoryUtil;

public class UserService {
    UserDao userDao;

    public Result userLogin(User user){


        System.out.println(user.toString());
        SqlSession sqlSession = null;
        Result result = null;

        try {
            sqlSession = SqlSessionFactoryUtil.openSession();
            userDao = sqlSession.getMapper(UserDao.class);

            User queryUser = userDao.queryUserByName(user.getName());
            if(queryUser != null) {
                if(user.getPassword().equals(queryUser.getPassword())){
                    result = Result.ok("登录成功！", queryUser);
                }else {
                    result = Result.error("用户名或密码错误！", "");
                }
            }else {
                result = Result.error("用户不存在！","");
            }

        } catch (Exception e) {
            System.out.println(e);
            sqlSession.rollback();
        }finally{
            if(sqlSession != null){
                sqlSession.close();
            }
        }

        return result;

    }
}
