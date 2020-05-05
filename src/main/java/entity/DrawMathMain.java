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
