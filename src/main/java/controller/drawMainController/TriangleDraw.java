package controller.drawMainController;

import entity.Point;
import entity.Triangle;
import utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TriangleDraw {
    Triangle triangle;

    public TriangleDraw(Point p1, Point p2, Color penColor, Float lineStroke){
        triangle=new Triangle();

        /**
         * 设置起始位置和宽高度
         * 小的作为起始位置，大的减去小的即为宽高度
        **/
        if(p1.getX() < p2.getX() ){
            triangle.setX1(p1.getX());
            triangle.setX2(p2.getX());
        }else{
            triangle.setX1(p2.getX());
            triangle.setX2( p2.getX());
        }

        if(p1.getY() < p2.getY()){
            triangle.setY1(p1.getY());
            triangle.setY2(p2.getY());
        }else{
            triangle.setY1(p2.getY());
            triangle.setY2(p1.getY());
        }

        triangle.setColor(penColor);
        triangle.setStroke(lineStroke);
    }


    // 绘制图形在image上
    public BufferedImage draw(BufferedImage image){
        // 复制一份用于绘制
        BufferedImage drawImage = ImageUtil.imageCopy(image);
        Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
        g2.setColor(triangle.getColor());
        g2.setStroke(new BasicStroke(triangle.getStroke()));
        g2.drawLine( (triangle.getX1()+triangle.getX2())/2, 
        		triangle.getY1(),triangle.getX1(), triangle.getY2());
        g2.drawLine(triangle.getX1(), triangle.getY2(), triangle.getX2(), triangle.getY2());
        g2.drawLine((triangle.getX1()+triangle.getX2())/2, triangle.getY1(), 
        		triangle.getX2(), triangle.getY2());

        return drawImage;
    }
}
