package com.dokdo.seungyongson.myapplication.FireBase;

/**
 * Created by Seungyong Son on 2018-01-21.
 */

public class ChatData {
    private String userName;
    private String message;

    public ChatData() { }

    public ChatData(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
