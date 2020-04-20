package mapper;

import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserMapper {

    public boolean userLogin(User user){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            con = DB.getCon();
            stmt =con.createStatement();
            rs=stmt.executeQuery("SELECT * FROM user WHERE name='" + user.getName() + "' and password='" + user.getPassword() + "'");
            if(rs.next())
                b = true;
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.print("登录异常！" + e.toString());
        }
        return b;
    }

    public String  insertUser(User user){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String result = "";
        int i=0,count=0;
        try {
            con = DB.getCon();
            stmt =con.createStatement();
            rs=stmt.executeQuery("select * from user where name='"+user.getName()+"'");
            if(rs.next())
            {  result="已有该用户";
                rs.beforeFirst();
            }
            else {
                i=stmt.executeUpdate("insert into user(name, password) values('"+user.getName()+"','"+user.getPassword()+"')");
                if(i>0) result="用户添加成功";
                else  result="用户添加失败";
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {	}
        return result;
    }

    public String updateUser(User user) {
        String result = "";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        int i = 0;

        try {
            con = DB.getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from user where name='" + user.getName() + "' and password='" + user.getPasswordOld() + "'");
            if (rs.next()) {
                i = stmt.executeUpdate("update user set password='" + user.getPassword() + "' where name='" + user.getName() + "'");
                if (i > 0) result = "修改成功";
                else result = "修改失败";
            } else result = "用户名或者密码错误";
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e1) {
            System.out.print("修改密码异常！" + e1.toString());
        }
        return result;
    }
}
