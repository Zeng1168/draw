package controller.drawMain;

import entity.ShapeCircle;
import entity.Point;
import utils.ImageUtil;
import utils.ShapeUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CircleDraw {
    ShapeCircle shapeCircle;

    public CircleDraw(Point p1, Point p2, Integer penColor, Integer lineStroke){
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
}
