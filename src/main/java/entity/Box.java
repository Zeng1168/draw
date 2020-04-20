package entity;

import java.awt.*;

public class Box {
    // 添加了
    private Integer x;  // 位置坐标x
    private Integer y;  // 位置坐标y
    private Integer width;  // 宽度
    private Integer length; // 长度
    private final Float height=0.3f;//高
    private Color color;    // 颜色

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Float getHeight() {
        return height;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
