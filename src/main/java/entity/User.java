package entity;

import java.util.Date;

public class User {

    private Integer id;
    private String username;
    private String phone;
    private String password;
    private Date signTime;
    private Integer isUseful;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }
}
