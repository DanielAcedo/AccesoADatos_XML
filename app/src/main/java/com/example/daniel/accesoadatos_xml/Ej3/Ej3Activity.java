package com.example.daniel.accesoadatos_xml.Ej3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.R;

import java.util.Calendar;
import java.util.List;

public class Ej3Activity extends AppCompatActivity {

    private ListView listView;
    private BikeStationAdapter adapter;
    private Button btn_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej3);
        setTitle("Lista de Estaciones Bicicleta");

        listView = (ListView)findViewById(R.id.listView);
        btn_refresh = (Button)findViewById(R.id.btn_Refresh);
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        refresh();
    }

    private void refresh(){
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
