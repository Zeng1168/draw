package entity;

import java.util.Date;

public class ShapeRectangle {
    private Integer id;
    private Integer userId;
    private String name;
    private Date time;
    private Integer x;
    private Integer y;
    private Integer perimeter;
    private Integer Area;
    private Integer length;
    private Integer width;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"time\":\"")
                .append(time).append('\"');
        sb.append(",\"x1\":")
                .append(x);
        sb.append(",\"y1\":")
                .append(y);

        sb.append(",\"length\":")
                .append(length);
        sb.append(",\"width\":")
                .append(width);
        sb.append(",\"perimeter\":")
                .append(perimeter);
        sb.append(",\"Area\":")
                .append(Area);
        sb.append('}');
        return sb.toString();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Integer perimeter) {
        this.perimeter = perimeter;
    }

    public Integer getArea() {
        return Area;
    }

    public void setArea(Integer area) {
        Area = area;
    }


}
