package com.deyi.entity;

import java.util.Date;

public class Account {
    private Integer userId;

    private String userName;

    private String passWord;

    private String phoneNumber;

    private String city;

    private String question;

    private String answer;

    private Date entryTime;

    private Date modTime;

    public Account(Integer userId, String userName, String passWord, String phoneNumber, String city, String question, String answer, Date entryTime, Date modTime) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.question = question;
        this.answer = answer;
        this.entryTime = entryTime;
        this.modTime = modTime;
    }

    public Account() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", passWord=").append(passWord);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", city=").append(city);
        sb.append(", question=").append(question);
        sb.append(", answer=").append(answer);
        sb.append(", entryTime=").append(entryTime);
        sb.append(", modTime=").append(modTime);
        sb.append("]");
        return sb.toString();
    }
}