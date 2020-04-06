package controller.drawMainController;

import entity.MousePoint;
import entity.Rectangle;
import utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RectangleDraw {
    Rectangle rectangle;

    public RectangleDraw(MousePoint p1, MousePoint p2, Color penColor, BasicStroke lineStroke){
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

    // 绘制图形在image上
    public BufferedImage draw(BufferedImage image){
        // 复制一份用于绘制
        BufferedImage drawImage = ImageUtil.imageCopy(image);
        Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
        g2.setColor(rectangle.getColor());
        g2.setStroke(rectangle.getStroke());
        g2.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());

        return drawImage;
    }
}
