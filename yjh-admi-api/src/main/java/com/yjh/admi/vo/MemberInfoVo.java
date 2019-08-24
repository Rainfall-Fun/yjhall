package com.yjh.admi.vo;

import java.time.LocalDateTime;

/**
 * Created by 彭椿悦 on 2019/8/10 10:07
 */
public class MemberInfoVo {
    private int id;
    private String username;
    private String mobile;
    private String gender;
    private LocalDateTime birthday;
    private String userLevel;
    private String status;
    private String audits;

    @Override
    public String toString() {
        return "MemberInfoVo{" +
                "id=" + id +
                ", username=" + username +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", userLevel='" + userLevel + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getAudits() {
        return audits;
    }

    public void setAudits(String audits) {
        this.audits = audits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
