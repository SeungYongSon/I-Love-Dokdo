package com.dokdo.transcreation.ilovedokdo.News;

/**
 * Created by Seungyong Son on 2018-03-05.
 */

public class Link {
    public static String link;

    Link(String link){
        this.link = link;
    }

    public String getLink() {
        if(link != null) return link;
        return null;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
