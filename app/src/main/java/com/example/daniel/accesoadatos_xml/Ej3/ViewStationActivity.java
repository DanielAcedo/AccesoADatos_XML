package com.example.daniel.accesoadatos_xml.Ej3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.daniel.accesoadatos_xml.R;

public class ViewStationActivity extends AppCompatActivity {

    public static final String TAG_NAME = "name";
    public static final String TAG_STATE = "state";
    public static final String TAG_BIKES = "bikes";
    public static final String TAG_ANCHORS = "anchors";
    public static final String TAG_LASTUPDATED = "lastUpdated";
    public static final String TAG_URI = "uri";
    public static final String TAG_COORDINATES = "coordinates";

    private TextView txv_name, txv_state, txv_bikes, txv_anchors, txv_lastUpdated;
    private TextView txv_uri;

    private static final String googleMapURL = "http://maps.google.com?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_station);

        txv_name = (TextView)findViewById(R.id.txv_title);
        txv_state = (TextView)findViewById(R.id.txv_state);
        txv_bikes = (TextView)findViewById(R.id.txv_bikesAvailable);
        txv_anchors = (TextView)findViewById(R.id.txv_anchorsAvailable);
        txv_lastUpdated = (TextView)findViewById(R.id.txv_lastUpdated);
        txv_uri = (TextView) findViewById(R.id.txv_uri);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String name = bundle.getString(TAG_NAME);
            String state = bundle.getString(TAG_STATE);
            int bikes = bundle.getInt(TAG_BIKES);
            int anchors = bundle.getInt(TAG_ANCHORS);
            String lastUpdated = bundle.getString(TAG_LASTUPDATED);
            final String uri = bundle.getString(TAG_URI);
            String coordinates = bundle.getString(TAG_COORDINATES);

            txv_name.setText(name);
            txv_state.setText(state);
            txv_bikes.setText("Bicicletas disponibles:\n"+String.valueOf(bikes));
            txv_anchors.setText("Anclajes disponibles:\n"+String.valueOf(anchors));
            txv_lastUpdated.setText("Ultima actualización: "+lastUpdated);

            txv_uri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
        }
    }
}
