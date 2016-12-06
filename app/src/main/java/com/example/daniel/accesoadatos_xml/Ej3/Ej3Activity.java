package com.example.daniel.accesoadatos_xml.Ej3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.R;

import java.util.Calendar;
import java.util.List;

public class Ej3Activity extends AppCompatActivity {

    private ListView listView;
    private BikeStationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej3);
        setTitle("Lista de Estaciones Bicicleta");

        listView = (ListView)findViewById(R.id.listView);

        BikeStationUtils.getBikesAPI(Ej3Activity.this, new BikeStationUtils.callBackBikeAPI() {
            @Override
            public void onFinish(List<BikeStation> bikeStations) {
                List<BikeStation> bikeStationList = bikeStations;
                adapter = new BikeStationAdapter(Ej3Activity.this, bikeStationList);

                listView.setAdapter(adapter);
            }

            @Override
            public void onException(Exception ex) {
                Toast.makeText(Ej3Activity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
