package com.wanandroid.logic.login.moudle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者：Created by Administrator on 2018/4/18.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

@Entity
public class LogicGreenDaoBean {

    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long id;
    @Unique
    private String username;
    private String email;
    private String icon;

    private String password;
    private String type;

    @Override
    public String toString() {
        return "LogicGreenDaoBean{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Generated(hash = 371469936)
    public LogicGreenDaoBean(Long id, String username, String email, String icon,
                             String password, String type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.icon = icon;
        this.password = password;
        this.type = type;
    }

    @Generated(hash = 2127138784)
    public LogicGreenDaoBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
