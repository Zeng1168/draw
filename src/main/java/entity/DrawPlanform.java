package entity;

import java.util.Date;


// Created on 2020-05-04 19:32:05

public class DrawPlanform {

    private Integer id;
    private Integer userId;
    private String name;
    private Date time;
    private String image;
    private Integer isUseful;
    private Integer invitingCode;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append("\"userId\":")
                .append(userId);
        sb.append("\"name\":")
                .append(name);
        sb.append("\"time\":")
                .append(time);
        sb.append("\"image\":")
                .append(image);
        sb.append("\"isUseful\":")
                .append(isUseful);
        sb.append("\"invitingCode\":")
                .append(invitingCode);
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }

    public Integer getInvitingCode() {
        return invitingCode;
    }

    public void setInvitingCode(Integer invitingCode) {
        this.invitingCode = invitingCode;
    }



}
