package controller.drawMainController;

import entity.Point;
import utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FillRectangleDraw extends RectangleDraw {

    public FillRectangleDraw(Point p1, Point p2, Color penColor, Float lineStroke) {
        super(p1, p2, penColor, lineStroke);
    }

    public BufferedImage draw(BufferedImage image){
        // 复制一份用于绘制
        BufferedImage drawImage = ImageUtil.imageCopy(image);
        Graphics2D g2 = (Graphics2D)drawImage.getGraphics();
        g2.setColor(rectangle.getColor());
        g2.setStroke(new BasicStroke(rectangle.getStroke()));
        g2.fill3DRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight(), false);

        return drawImage;

    }
}
