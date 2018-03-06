package com.dokdo.transcreation.ilovedokdo.News;

/**
 * Created by Seungyong Son on 2018-03-05.
 */

public class NewsInfo {
    private String title;
    private String link;
    private String description;

    public NewsInfo(String title, String link, String description){
        this.title = title;
        this.link = link;
        this.description = description;
    }

    public String getTitle() {return title;}

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
