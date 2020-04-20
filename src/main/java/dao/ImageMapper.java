package dao;

import entity.Image;
import utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageMapper {
    public List<Image> queryAll(){
        List<Image> images = new ArrayList();
        //获取连接
        Connection conn = DB.getCon();
        //准备sql
        String sql="select * from image";
        //获取PreparedStatement
        PreparedStatement ps= null;
        try {
            ps = conn.prepareStatement(sql);
            //执行sql
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Image image=new Image();
                image.setId(rs.getInt(1));
                image.setUserId(rs.getInt(2));
                image.setTime(rs.getDate(3));
                image.setImage(rs.getString(4));
                images.add(image);
            }
        } catch (SQLException e) {
            System.out.println("获取预编译语句失败！" + e.toString());
        }

        //关闭连接
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return images;
    }

    public int insertImage(Image image){
        int num = 0;

        //获取连接
        Connection conn = DB.getCon();
        //准备sql
        String sql="insert into image(user_id, time, image) values(?,?,?)";
        //获取PreparedStatement
        PreparedStatement ps= null;
        try {
            ps = conn.prepareStatement(sql);
            //填充占位符
            ps.setInt(1, image.getUserId());
            ps.setString(2, DateUtil.dateTimeFormate(image.getTime()));
            ps.setString(3, image.getImage());
            //执行sql
            num = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //关闭连接
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }


    public int deteleImage(Integer id){
        int num = 0;

        //获取连接
        Connection conn = DB.getCon();
        //准备sql
        String sql="delete from image where id=?";
        //获取PreparedStatement
        PreparedStatement ps= null;
        try {
            ps = conn.prepareStatement(sql);
            //填充占位符
            ps.setInt(1, id);
            //执行sql
            num = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //关闭连接
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

}
