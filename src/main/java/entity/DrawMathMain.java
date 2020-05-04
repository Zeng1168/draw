package entity;

import utils.DrawMathMode;
import utils.DrawMode;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class DrawMathMain {
    // 绘图参数
    private DrawMathMode drawMode;  // 绘图模式
    private Float lineStroke;   // 线条粗细
    private Color penColor; // 画笔颜色
    private Integer X1,X2,Y1,Y2;
    private Float length,width,height,radius;





    public DrawMathMode getDrawMode() {
        return drawMode;
    }

    public void setDrawMode(DrawMathMode drawMode) {
        this.drawMode = drawMode;
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

    public Integer getX1() {
        return X1;
    }

    public void setX1(Integer x1) {
        X1 = x1;
    }

    public Integer getX2() {
        return X2;
    }

    public void setX2(Integer x2) {
        X2 = x2;
    }

    public Integer getY1() {
        return Y1;
    }

    public void setY1(Integer y1) {
        Y1 = y1;
    }

    public Integer getY2() {
        return Y2;
    }

    public void setY2(Integer y2) {
        Y2 = y2;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }
}
