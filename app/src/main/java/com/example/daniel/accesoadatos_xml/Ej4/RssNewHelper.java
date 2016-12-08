package com.example.daniel.accesoadatos_xml.Ej4;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by daniel on 8/12/16.
 */

public class RssNewHelper {

    public static List<RssNew> analyzeRssNews(File file) throws XmlPullParserException, IOException, ParseException {
        List<RssNew> news = new ArrayList<RssNew>();
        RssNew currentNew = null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(new FileReader(file));

        int event = parser.next();
        boolean insideItem = false;

        while(event != XmlPullParser.END_DOCUMENT){

            switch (event){

                case XmlPullParser.START_TAG:
                    String tag = parser.getName();

                    if(tag.equals("item")){
                        insideItem = true;
                        currentNew = new RssNew();
                    }

                    if(tag.equals("title") && insideItem){
                        currentNew.setTitle(parser.nextText());
                    }
                    else if (tag.equals("link") && insideItem){
                        currentNew.setLink(parser.nextText());
                    }
                    else if (tag.equals("pubDate") && insideItem){
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateFormat.parse(parser.nextText()));
                        currentNew.setPubDate(cal);
                    }

                    break;

                case XmlPullParser.END_TAG:
                    tag = parser.getName();

                    if(tag.equals("item")){
                        insideItem = false;
                        news.add(currentNew);
                        currentNew = null;
                    }

                    break;
            }

            event = parser.next();
        }

        return news;
    }
}
