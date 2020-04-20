package entity;

import java.awt.*;

public class Cone3d {
    private  float r;
    private  float h;
    private Color color;
    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getArea() {
        return (Math.sqrt(r*r+h*h))*Math.PI*r+Math.PI*r*r;
    }

    public double getVolume() {
        return r*r*Math.PI*h/3;
    }
}
