package com.example.daniel.accesoadatos_xml.Ej2;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.Ej4.Ej4Activity;
import com.example.daniel.accesoadatos_xml.R;
import com.example.daniel.accesoadatos_xml.RestClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class Ej2Activity extends AppCompatActivity {

    private LinearLayout firstDayHours, firstDayImages, secondDayHours, secondDayImages;
    private TextView txv_firstDayTemp, txv_secondDayTemp, txv_firstDayTitle, txv_secondDayTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej2);

        firstDayHours = (LinearLayout)findViewById(R.id.lly_firstDayHours);
        firstDayImages = (LinearLayout)findViewById(R.id.lly_firstDayImages);
        secondDayHours = (LinearLayout)findViewById(R.id.lly_secondDayHours);
        secondDayImages = (LinearLayout)findViewById(R.id.lly_secondDayImages);

        txv_firstDayTemp = (TextView)findViewById(R.id.txv_firstDayTemp);
        txv_secondDayTemp = (TextView)findViewById(R.id.txv_secondDayTemp);
        txv_firstDayTitle = (TextView)findViewById(R.id.txv_firstDayTitle);
        txv_secondDayTitle = (TextView)findViewById(R.id.secondDayTitle);


        final ProgressDialog progress = new ProgressDialog(Ej2Activity.this);
        progress.setTitle("Cargando predicción...");
        progress.show();

        RestClient.get(WeatherHelper.WEATHER_MALAGA_PATH, new FileAsyncHttpResponseHandler(Ej2Activity.this) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progress.dismiss();
                Toast.makeText(Ej2Activity.this, "Error al cargar la prediccion", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                try {
                    List<Prediction> predictions = WeatherHelper.getPredictions(file);

                    Prediction firstPrediction = predictions.get(0);
                    Prediction secondPrediction = predictions.get(1);

                    txv_firstDayTitle.setText(firstPrediction.getDay().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()) +" "+ String.valueOf(firstPrediction.getDay().get(Calendar.DAY_OF_MONTH)));
                    txv_secondDayTitle.setText(secondPrediction.getDay().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()) + " "+ String.valueOf(secondPrediction.getDay().get(Calendar.DAY_OF_MONTH)));
                    txv_firstDayTemp.setText(String.valueOf(firstPrediction.getTempMin())+"/"+String.valueOf(firstPrediction.getTempMax()));
                    txv_secondDayTemp.setText(String.valueOf(secondPrediction.getTempMin())+"/"+String.valueOf(secondPrediction.getTempMax()));

                    //First day
                    for(String key : firstPrediction.getSkyStates().keySet()) {
                        String imagePath = firstPrediction.getSkyStates().get(key);

                        //Text
                        TextView txv = new TextView(Ej2Activity.this);
                        txv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        txv.setText(key);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.weight = 0.5f;
                        txv.setBackground(getResources().getDrawable(R.drawable.ej2cellselector));
                        txv.setLayoutParams(params);
                        firstDayHours.addView(txv);

                        //Image
                        ImageView imv = new ImageView(Ej2Activity.this);
                        txv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.weight = 0.5f;
                        imv.setBackground(getResources().getDrawable(R.drawable.ej2cellselector));
                        imv.setLayoutParams(params);
                        firstDayImages.addView(imv);
                        Picasso.with(Ej2Activity.this).load(imagePath).into(imv);
                    }

                    //Second day
                    for(String key : secondPrediction.getSkyStates().keySet()) {
                        String imagePath = secondPrediction.getSkyStates().get(key);

                        //Text
                        TextView txv = new TextView(Ej2Activity.this);
                        txv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        txv.setText(key);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.weight = 0.5f;
                        txv.setBackground(getResources().getDrawable(R.drawable.ej2cellselector));
                        txv.setLayoutParams(params);
                        secondDayHours.addView(txv);

                        //Image
                        ImageView imv = new ImageView(Ej2Activity.this);
                        txv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.weight = 0.5f;
                        imv.setBackground(getResources().getDrawable(R.drawable.ej2cellselector));
                        imv.setLayoutParams(params);
                        secondDayImages.addView(imv);
                        Picasso.with(Ej2Activity.this).load(imagePath).into(imv);
                    }

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                progress.dismiss();
            }
        });
    }
}
