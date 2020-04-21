package controller.drawMainController;

import entity.Circle;
import entity.Point;
import utils.ImageUtil;
import utils.ShapeMathUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CircleDraw {
    Circle circle;

    public CircleDraw(Point p1, Point p2, Integer penColor, Integer lineStroke){
        circle = new Circle();

        // 计算并设置圆心坐标
        Point mid = ShapeMathUtil.midPoint(p1, p2);
        circle.setX(mid.x);
        circle.setY(mid.y);
        circle.setRadius( ShapeMathUtil.distance(p1, p2)/2);

        circle.setColor(penColor);
        circle.setStroke(lineStroke);
    }

    // 绘制图形在image上
    public BufferedImage draw(int sizeX, int sizeY){
        BufferedImage buffImg = ImageUtil.createBlankLacencyImage(sizeX, sizeY);
        Graphics2D g2 = buffImg.createGraphics();   // 获取Graphics2D

        g2.setColor(new Color(circle.getColor()));
        g2.setStroke(new BasicStroke(circle.getStroke()));
        g2.drawOval(circle.getX() - circle.getRadius(), circle.getY() - circle.getRadius(), circle.getRadius()*2, circle.getRadius()*2);
        g2.dispose();

        return buffImg;
    }
}
