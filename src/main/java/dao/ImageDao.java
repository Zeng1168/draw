package dao;

import entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ImageDao {

    @Select("SELECT * FROM image")
    List<Image> queryAll();

    @Insert("INSERT INTO image(user_id, time, image) VALUES(#{userId}, #{time}, #{image})")
    int insertImage(Image image);

    @Delete("DELETE FROM image WHERE id=#{id}")
    int deteleImage(Integer id);

}
