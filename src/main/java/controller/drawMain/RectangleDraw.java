package controller.drawMain;

import entity.Point;
import entity.Rectangle;
import utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangleDraw {
    Rectangle rectangle;

    public RectangleDraw(Point p1, Point p2, Color penColor, Float lineStroke){
        rectangle = new Rectangle();


        /**
         * 设置起始位置和宽高度
         * 小的作为起始位置，大的减去小的即为宽高度
        **/
        if(p1.getX() < p2.getX() ){
            rectangle.setX(p1.getX());
            rectangle.setWidth(p2.getX() - p1.getX());
        }else{
            rectangle.setX(p2.getX());
            rectangle.setWidth(p1.getX() - p2.getX());
        }

        if(p1.getY() < p2.getY()){
            rectangle.setY(p1.getY());
            rectangle.setHeight(p2.getY() - p1.getY());
        }else{
            rectangle.setY(p2.getY());
            rectangle.setHeight(p1.getY() - p2.getY());
        }

        rectangle.setColor(penColor);
        rectangle.setStroke(lineStroke);
    }

    public BufferedImage draw(int sizeX, int sizeY){
        BufferedImage buffImg = ImageUtil.createBlankLacencyImage(sizeX, sizeY);
        Graphics2D g2 = buffImg.createGraphics();   // 获取Graphics2D

        g2.setColor(rectangle.getColor());
        g2.setStroke(new BasicStroke(rectangle.getStroke()));
        g2.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        g2.dispose();

        return buffImg;
    }
}
