package service;

import entity.Point;
import entity.ShapeCircle;
import utils.ImageUtil;
import utils.ShapeUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ShapeCircleService {
    ShapeCircle shapeCircle;

    /**
     * 鼠标绘制模式下计算参数
     * @param p1
     * @param p2
     * @param penColor
     * @param lineStroke
     */
    public void initParams(Point p1, Point p2, Integer penColor, Integer lineStroke){
        shapeCircle = new ShapeCircle();

        // 计算并设置圆心坐标
        Point mid = ShapeUtil.midPoint(p1, p2);
        shapeCircle.setX(mid.x);
        shapeCircle.setY(mid.y);
        shapeCircle.setRadius( ShapeUtil.distance(p1, p2)/2);

        shapeCircle.setColor(penColor);
        shapeCircle.setStroke(lineStroke);
    }

    // 绘制图形在image上
    public BufferedImage draw(int sizeX, int sizeY){
        BufferedImage buffImg = ImageUtil.createBlankLacencyImage(sizeX, sizeY);
        Graphics2D g2 = buffImg.createGraphics();   // 获取Graphics2D

        g2.setColor(new Color(shapeCircle.getColor()));
        g2.setStroke(new BasicStroke(shapeCircle.getStroke()));
        g2.drawOval(shapeCircle.getX() - shapeCircle.getRadius(), shapeCircle.getY() - shapeCircle.getRadius(), shapeCircle.getRadius()*2, shapeCircle.getRadius()*2);
        g2.dispose();

        return buffImg;
    }

    public Map<String, String> discription(){
        Map<String, String> map = new HashMap<>();
        map.put("圆形坐标X", String.valueOf(shapeCircle.getX()));
        map.put("圆形坐标Y", String.valueOf(shapeCircle.getY()));
        map.put("半径", String.valueOf(shapeCircle.getRadius()));
        double length = 3.14*2*shapeCircle.getRadius();
        map.put("周长", String.valueOf(length));
        double s = 3.14*3.14*shapeCircle.getRadius();
        map.put("面积", String.valueOf(s));

        return map;
    }
}
