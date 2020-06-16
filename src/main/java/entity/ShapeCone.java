package entity;

import java.util.Date;

// Created on 2020-06-16 16:01:26

public class ShapeCone {

    private Integer id;
    private Integer userId;
    private String name;
    private Date time;
    private Integer r;
    private Integer h;
    private Double perimeterCircle;  // 底面圆周长
    private Double areaCircle;    // 底面圆面积
    private Double volume; // 体积

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
        sb.append(",\"r\":")
                .append(r);
        sb.append(",\"h\":")
                .append(h);
        sb.append(",\"perimeterCircle\":")
                .append(perimeterCircle);
        sb.append(",\"areaCircle\":")
                .append(areaCircle);
        sb.append(",\"volume\":")
                .append(volume);
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

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Double getPerimeterCircle() {
        return perimeterCircle;
    }

    public void setPerimeterCircle(Double perimeterCircle) {
        this.perimeterCircle = perimeterCircle;
    }

    public Double getAreaCircle() {
        return areaCircle;
    }

    public void setAreaCircle(Double areaCircle) {
        this.areaCircle = areaCircle;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
