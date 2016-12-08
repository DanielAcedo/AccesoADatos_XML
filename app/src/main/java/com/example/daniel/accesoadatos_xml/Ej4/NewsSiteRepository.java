package com.example.daniel.accesoadatos_xml.Ej4;

import com.example.daniel.accesoadatos_xml.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 8/12/16.
 */

public class NewsSiteRepository {
    private static List<NewsSite> newsSites;

    public static List<NewsSite> getNewsSites(){
        if(newsSites == null){
            initializeSites();
        }

        return newsSites;
    }

    private static void initializeSites(){
        newsSites = new ArrayList<>();
        newsSites.add(new NewsSite("ElOtroLado", R.drawable.elotroladoicon, "http://www.elotrolado.net/feed/"));
        newsSites.add(new NewsSite("PCGamer", R.drawable.pcgamericon, "http://www.pcgamer.com/rss/"));
        newsSites.add(new NewsSite("Gamasutra", R.drawable.gamasutraicon, "http://feeds.feedburner.com/GamasutraNews"));
    }

}
