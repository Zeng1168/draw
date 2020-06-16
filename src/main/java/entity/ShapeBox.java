package entity;

import java.util.Date;

public class ShapeBox {
    private Integer id;
    private Integer userId;
    private String name;
    private Date time;
    private Integer l;
    private Integer w;
    private Integer h;
    private Double area;    // 底面圆面积
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
        sb.append(",\"l\":")
                .append(l);
        sb.append(",\"w\":")
                .append(w);
        sb.append(",\"h\":")
                .append(h);
        sb.append(",\"area\":")
                .append(area);
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

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
}
