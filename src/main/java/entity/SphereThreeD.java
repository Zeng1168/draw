package entity;

import java.awt.*;

public class SphereThreeD {
    private Integer x;  // 位置坐标x
    private Integer y;  // 位置坐标y
    private  Float radius=0.3f;//半径
    private Color color;    // 颜色
    private Double volume;

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

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public Double getVolume() {
        volume=4*Math.PI*radius*radius/3;
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
