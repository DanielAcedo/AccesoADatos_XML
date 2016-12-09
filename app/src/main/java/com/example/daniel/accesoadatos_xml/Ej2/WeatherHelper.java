package com.example.daniel.accesoadatos_xml.Ej2;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 9/12/16.
 */

public class WeatherHelper {

    private static final String SKYSTATE_IMAGE_PREFIX = "http://www.aemet.es/imagenes/png/estado_cielo/";
    public static final String WEATHER_MALAGA_PATH = "http://www.aemet.es/xml/municipios/localidad_29067.xml";

    public static List<Prediction> getPredictions(File file) throws XmlPullParserException, IOException, ParseException {

        List<Prediction> predictions = new ArrayList<>();
        Prediction currentPrediction = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(new FileReader(file));

        boolean insidePrediction = false;
        boolean insideDay = false;
        boolean insideTemp = false;
        boolean validDay = false;

        String tag = "";

        int event = parser.next();

        while (event != XmlPullParser.END_DOCUMENT){

            switch (event){
                case XmlPullParser.START_TAG:
                    tag = parser.getName();

                    if(tag.equals("prediccion")){
                        insidePrediction = true;
                    }

                    else if (tag.equals("dia") && insidePrediction){
                        insideDay = true;
                        String day = parser.getAttributeValue(0);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(dateFormat.parse(day));

                        validDay = isValidDay(cal);

                        if(validDay){
                            currentPrediction = new Prediction();
                            currentPrediction.setDay(cal);
                        }

                    }
                    else if (tag.equals("temperatura") && insideDay && validDay){
                        insideTemp = true;
                    }
                    else if(tag.equals("estado_cielo") && insideDay && validDay){
                        String hours = parser.getAttributeValue(0);
                        String image = parser.nextText();

                        if(!image.equals(""))
                            currentPrediction.getSkyStates().put(hours, SKYSTATE_IMAGE_PREFIX+image+".png");
                    }
                    else if(tag.equals("maxima") && insideTemp){
                        currentPrediction.setTempMax(Integer.parseInt(parser.nextText()));
                    }
                    else if(tag.equals("minima") && insideTemp){
                        currentPrediction.setTempMin(Integer.parseInt(parser.nextText()));
                    }

                    break;

                case XmlPullParser.END_TAG:
                    tag = parser.getName();

                    if(tag.equals("prediccion")){
                        insidePrediction = false;
                    }
                    else if (tag.equals("dia") && insidePrediction){
                        insideDay = false;

                        if(validDay){
                            predictions.add(currentPrediction);
                            currentPrediction = null;
                        }

                    }
                    else if (tag.equals("temperatura") && insideDay){
                        insideTemp = false;
                    }
                    break;
            }

            event = parser.next();
        }

        return predictions;
    }

    private static boolean isValidDay(Calendar cal){
        boolean ok = false;

        Calendar calPlusDay = Calendar.getInstance();
        calPlusDay.add(Calendar.DAY_OF_MONTH, 1);

        if((cal.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
                || cal.get(Calendar.DAY_OF_MONTH) == calPlusDay.get(Calendar.DAY_OF_MONTH)){
            ok = true;
        }

        return ok;
    }
}
