package com.dokdo.transcreation.ilovedokdo.News;

/**
 * Created by Seungyong Son on 2018-03-05.
 */

public class Title {
    public static String title;

    Title(String title){
        this.title = title;
    }

    public String getTitle() {
        if(title != null) return title;
        return null;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
