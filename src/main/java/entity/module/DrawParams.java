package entity.module;

import utils.DrawPlatformMode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class DrawParams {
    // 绘图参数
    private DrawPlatformMode drawPlatformMode;  // 绘图模式
    private Float lineStroke;   // 线条粗细
    private Color penColor; // 画笔颜色
    private Color backgroundColor;  // 背景颜色
    private Integer groundSizeX;    // 画布宽度
    private Integer groundSizeY;    // 画布高度
    private Integer rubberSize;     // 橡皮半径

    private BufferedImage image;    // 绘制的图像
    private Point previousPoint;   // 鼠标经过的上一个坐标
    private BufferedImage previousImage;    // 上一个绘制的图像
    private Point pressedPoint;    // 鼠标刚按下的坐标
    private BufferedImage pressedImage; // 鼠标刚按下的图像
    private Stack recordStack;   // image历史记录
    private Stack cancelStack;   // image撤销记录

    public DrawParams() {
        recordStack = new Stack();
        cancelStack = new Stack();
    }

    public DrawPlatformMode getDrawPlatformMode() {
        return drawPlatformMode;
    }

    public void setDrawPlatformMode(DrawPlatformMode drawPlatformMode) {
        this.drawPlatformMode = drawPlatformMode;
    }

    public Float getLineStroke() {
        return lineStroke;
    }

    public void setLineStroke(Float lineStroke) {
        this.lineStroke = lineStroke;
    }

    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Integer getGroundSizeX() {
        return groundSizeX;
    }

    public void setGroundSizeX(Integer groundSizeX) {
        this.groundSizeX = groundSizeX;
    }

    public Integer getGroundSizeY() {
        return groundSizeY;
    }

    public void setGroundSizeY(Integer groundSizeY) {
        this.groundSizeY = groundSizeY;
    }

    public Integer getRubberSize() {
        return rubberSize;
    }

    public void setRubberSize(Integer rubberSize) {
        this.rubberSize = rubberSize;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Point getPreviousPoint() {
        return previousPoint;
    }

    public void setPreviousPoint(Point previousPoint) {
        this.previousPoint = previousPoint;
    }

    public BufferedImage getPreviousImage() {
        return previousImage;
    }

    public void setPreviousImage(BufferedImage previousImage) {
        this.previousImage = previousImage;
    }

    public Point getPressedPoint() {
        return pressedPoint;
    }

    public void setPressedPoint(Point pressedPoint) {
        this.pressedPoint = pressedPoint;
    }

    public BufferedImage getPressedImage() {
        return pressedImage;
    }

    public void setPressedImage(BufferedImage pressedImage) {
        this.pressedImage = pressedImage;
    }


    // 对栈的set、get  (push、pop)
    public Object popRecordStack() {
        return recordStack.empty()?null:recordStack.pop();
    }

    public void pushRecordStack(BufferedImage image) {
        recordStack.push(image);
    }

    public Object popCancelStack() {
        return cancelStack.empty()?null:cancelStack.pop();
    }

    public void pushCancelStack(BufferedImage image) {
        cancelStack.push(image);
    }
}
