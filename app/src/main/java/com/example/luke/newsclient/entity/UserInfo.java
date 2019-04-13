package com.example.luke.newsclient.entity;

public class UserInfo {
    private String userName;
    private String passWord;
    private String imagePath;
    private String motto;
    private Integer generateId;

    public UserInfo(String userName, String passWord, String imagePath, String motto) {
        this.userName = userName;
        this.passWord = passWord;
        this.imagePath = imagePath;
        this.motto = motto;
    }

    public UserInfo() {

    }

    public Integer getGenerateId() {
        return generateId;
    }

    public void setGenerateId(Integer generateId) {
        this.generateId = generateId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }
}
