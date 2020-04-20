package controller.drawMainController;

import entity.Line;
import entity.MousePoint;
import utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LineDraw {
    Line line;

    public LineDraw(MousePoint p1, MousePoint p2, Color penColor, Float lineStroke){
        line = new Line();

        line.setX1(p1.getX());
        line.setY1(p1.getY());
        line.setX2(p2.getX());
        line.setY2(p2.getY());

        line.setColor(penColor);
        line.setStroke(lineStroke);
    }

    // 绘制图形在image上
    public BufferedImage draw(BufferedImage image){
        // 复制一份用于绘制
        BufferedImage drawImage = ImageUtil.imageCopy(image);
        Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
        g2.setColor(line.getColor());
        g2.setStroke(new BasicStroke(line.getStroke()));
        g2.drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());

        return drawImage;
    }
}
