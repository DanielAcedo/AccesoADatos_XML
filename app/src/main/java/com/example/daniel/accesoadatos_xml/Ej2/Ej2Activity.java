package com.example.daniel.accesoadatos_xml.Ej2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniel.accesoadatos_xml.R;
import com.example.daniel.accesoadatos_xml.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Ej2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej2);

        RestClient.get(WeatherHelper.WEATHER_MALAGA_PATH, new FileAsyncHttpResponseHandler(Ej2Activity.this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                try {
                    List<Prediction> predictions = WeatherHelper.getPredictions(file);
                    int a = 1;
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
