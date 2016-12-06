package com.example.daniel.accesoadatos_xml.Ej3;

import android.content.Context;

import com.example.daniel.accesoadatos_xml.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by daniel on 6/12/16.
 */

public class BikeStationUtils {

    private static String bikeStationListUri ="http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/estacion-bicicleta.xml";

    public interface callBackBikeAPI{
        void onFinish(List<BikeStation> bikeStations);

        void onException(Exception ex);
    }

    public static void getBikesAPI(Context context, final callBackBikeAPI callback){
        RestClient.get(bikeStationListUri, new FileAsyncHttpResponseHandler(context) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                callback.onException(new Exception("Error en la conexión a API"));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                try {
                    callback.onFinish(analyzeBikesXML(file));
                } catch (XmlPullParserException e) {
                    callback.onException(e);
                } catch (IOException e) {
                    callback.onException(e);
                }
            }
        });
    }

    private static List<BikeStation> analyzeBikesXML(File xml) throws XmlPullParserException, IOException {
        List<BikeStation> bikeStations = new ArrayList<BikeStation>();
        BikeStation currentBikeStation = null;

        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(new FileInputStream(xml), null);

        int event = parser.next();

        boolean insideStation = false;

        while(event != XmlPullParser.END_DOCUMENT){
            switch (event){

                case XmlPullParser.START_TAG:

                    if(parser.getName().equals("estacion")){
                        insideStation = true;
                        currentBikeStation = new BikeStation();
                    }
                    else if (parser.getName().equals("uri") && insideStation){
                        currentBikeStation.setMapUri(parser.nextText());
                    }
                    else if (parser.getName().equals("title") && insideStation){
                        currentBikeStation.setTitle(parser.nextText());
                    }
                    else if (parser.getName().equals("estado") && insideStation){
                        currentBikeStation.setState(parser.nextText());
                    }
                    else if (parser.getName().equals("bicisDisponibles") && insideStation){
                        currentBikeStation.setBikesAvailable(Integer.parseInt(parser.nextText()));
                    }
                    else if (parser.getName().equals("anclajesDisponibles") && insideStation){
                        currentBikeStation.setAnchorsAvailable(Integer.parseInt(parser.nextText()));
                    }
                    else if (parser.getName().equals("lastUpdated") && insideStation){
                        String input = parser.nextText();

                        currentBikeStation.setLastUpdated(DateTime.parse(input).toCalendar(Locale.getDefault()));
                    }
                    break;

                case XmlPullParser.END_TAG:

                    if(parser.getName().equals("estacion")){
                        insideStation = false;
                        bikeStations.add(currentBikeStation);
                        currentBikeStation = null;
                    }

                    break;
            }

            event = parser.next();
        }

        return bikeStations;
    }
}
