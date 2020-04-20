package dao;

import entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UserDao {

    // 按照用户名查询用户
    @Select("SELECT * FROM user WHERE name=#{name} LIMIT 1")
    User queryUserByName(String name);

    // 添加用户
    @Insert("INSERT INTO user(name, password) VALUES(#{name}, #{password})")
    int insertUser(User user);

    // 查询所有用户
    @Select("SELECT * FROM user")
    List<User> queryAllUser();

    // 修改密码
    @Update("UPDATE user SET password=#{password} WHERE name=#{name}")
    int updatePassword(User user);

}
