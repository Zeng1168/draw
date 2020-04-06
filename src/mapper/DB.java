package mapper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getCon(){
        // 数据库连接参数
        String userName = "root";
        String password = "123456";
        String url = "jdbc:mysql://localhost:3306/draw?serverTimezone=Hongkong&characterEncoding=utf8&useSSL=false";
        String driveName = "com.mysql.jdbc.Driver";

        Connection con = null;
        try {
            Class.forName(driveName);
        } catch (Exception e) {
            System.out.println("加载驱动异常！ " + e.toString());
        }

        try {
            con = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            System.out.println("数据库连接异常！" + e.toString());
        }

        return con;
    }
}
