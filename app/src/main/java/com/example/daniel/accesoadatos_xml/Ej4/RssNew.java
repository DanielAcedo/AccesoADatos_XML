package com.example.daniel.accesoadatos_xml.Ej4;

import java.util.Calendar;

/**
 * Created by daniel on 8/12/16.
 */

public class RssNew {
    private String title;
    private String link;
    private Calendar pubDate;

    public RssNew(){}

    public RssNew(String title, String link, Calendar pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Calendar getPubDate() {
        return pubDate;
    }

    public void setPubDate(Calendar pubDate) {
        this.pubDate = pubDate;
    }
}
