package entity;


public class ShapeCircle extends Shape {
    private Integer x;  // 圆心坐标x
    private Integer y;  // 圆心坐标y
    private Integer radius;  // 半径
    private Integer stroke; // 厚度

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

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getStroke() {
        return stroke;
    }

    public void setStroke(Integer stroke) {
        this.stroke = stroke;
    }
}

