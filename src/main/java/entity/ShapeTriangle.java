package entity;

import java.util.Date;


// Created on 2020-06-16 09:15:55

public class ShapeTriangle {

    private Integer id;
    private Integer userId;
    private String name;
    private Date time;
    private Integer x1;
    private Integer y1;
    private Integer x2;
    private Integer y2;
    private Integer x3;
    private Integer y3;
    private Integer lengthAB;
    private Integer lengthBC;
    private Integer lengthCA;
    private Integer perimeter;
    private Double Area;

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
                .append(x1);
        sb.append(",\"y1\":")
                .append(y1);
        sb.append(",\"x2\":")
                .append(x2);
        sb.append(",\"y2\":")
                .append(y2);
        sb.append(",\"x3\":")
                .append(x3);
        sb.append(",\"y3\":")
                .append(y3);
        sb.append(",\"lengthAB\":")
                .append(lengthAB);
        sb.append(",\"lengthBC\":")
                .append(lengthBC);
        sb.append(",\"lengthCA\":")
                .append(lengthCA);
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

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

    public Integer getX3() {
        return x3;
    }

    public void setX3(Integer x3) {
        this.x3 = x3;
    }

    public Integer getY3() {
        return y3;
    }

    public void setY3(Integer y3) {
        this.y3 = y3;
    }

    public Integer getLengthAB() {
        return lengthAB;
    }

    public void setLengthAB(Integer lengthAB) {
        this.lengthAB = lengthAB;
    }

    public Integer getLengthBC() {
        return lengthBC;
    }

    public void setLengthBC(Integer lengthBC) {
        this.lengthBC = lengthBC;
    }

    public Integer getLengthCA() {
        return lengthCA;
    }

    public void setLengthCA(Integer lengthCA) {
        this.lengthCA = lengthCA;
    }

    public Integer getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Integer perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea() {
        return Area;
    }

    public void setArea(Double area) {
        Area = area;
    }
}
