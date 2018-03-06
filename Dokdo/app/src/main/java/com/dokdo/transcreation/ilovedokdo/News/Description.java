package com.dokdo.transcreation.ilovedokdo.News;

/**
 * Created by Seungyong Son on 2018-03-05.
 */

public class Description {
    public static String description;

    Description(String description){
        this.description = description;
    }

    public String getDescription() {
        if(description != null) return description;
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
