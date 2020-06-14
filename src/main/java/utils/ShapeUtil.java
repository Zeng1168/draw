package utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.module.Point;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ShapeUtil {

    // 计算两点距离
    public static Integer distance(Point p1, Point p2){
        int a = p1.x - p2.x;
        int b = p1.y - p2.y;
        a = a*a;
        b = b*b;
        return  (int) Math.sqrt(a+b);
    }

    //  重设起始点和截止点
    public static List<Point> revisePoint(Point p1, Point p2){
        Point start = new Point();
        Point end = new Point();
        List<Point> points = new ArrayList<Point>();

        if(p1.x < p2.x ){
            start.x = p1.x;
            end.x = p2.x;
        }else{
            start.x = p2.x;
            end.x = p1.x;
        }

        if(p1.y < p2.y){
            start.y = p1.y;
            end.y = p2.y;
        }else{
            end.y = p2.y;
            end.y = p1.y;
        }

        points.add(start);
        points.add(end);

        return points;
    }

    // 计算中点坐标
    public static Point midPoint(Point p1, Point p2){
        Point mid = new Point();
        mid.x = (p1.x + p2.x)/2;
        mid.y = (p1.y + p2.y)/2;

        return mid;
    }

    /**
     * 判断一个类是否存在某个属性（字段）
     *
     * @param field 字段
     * @param obj   类对象
     * @return true:存在，false:不存在, null:参数不合法
     */
    public static Boolean isExistField(String field, Object obj) {
        if (obj == null || StringUtils.isEmpty(field)) {
            return null;
        }
        Object o = JSON.toJSON(obj);
        JSONObject jsonObj = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObj = (JSONObject) o;
        }
        return jsonObj.containsKey(field);
    }
}
