package entity;

import java.awt.*;

public class Line {
    private Integer x1; // 起始坐标
    private Integer y1;
    private Integer x2; // 截止坐标
    private Integer y2;
    private Color color;    // 颜色
    private Float stroke; // 厚度

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Float getStroke() {
        return stroke;
    }

    public void setStroke(Float stroke) {
        this.stroke = stroke;
    }
}
