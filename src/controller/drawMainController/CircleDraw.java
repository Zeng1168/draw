package controller.drawMainController;

import entity.Circle;
import entity.MousePoint;
import utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CircleDraw {
    Circle circle;

    public CircleDraw(MousePoint p1, MousePoint p2, Color penColor, BasicStroke lineStroke){
        circle = new Circle();

        /**
         * 设置起始位置和宽高度
         * 小的作为起始位置，大的减去小的即为宽高度
        **/
        if(p1.getX() < p2.getX() ){
            circle.setX(p1.getX());
            circle.setWidth(p2.getX() - p1.getX());
        }else{
            circle.setX(p2.getX());
            circle.setWidth(p1.getX() - p2.getX());
        }

        if(p1.getY() < p2.getY()){
            circle.setY(p1.getY());
            circle.setHeight(p2.getY() - p1.getY());
        }else{
            circle.setY(p2.getY());
            circle.setHeight(p1.getY() - p2.getY());
        }

        circle.setColor(penColor);
        circle.setStroke(lineStroke);
    }

    // 绘制图形在image上
    public BufferedImage draw(BufferedImage image){
        // 复制一份用于绘制
        BufferedImage drawImage = ImageUtil.imageCopy(image);
        Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
        g2.setColor(circle.getColor());
        g2.setStroke(circle.getStroke());
        g2.drawOval(circle.getX(), circle.getY(), circle .getWidth(), circle.getHeight());

        return drawImage;
    }
}
