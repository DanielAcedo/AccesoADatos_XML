package com.example.daniel.accesoadatos_xml.Ej3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.R;

import org.joda.time.DateTime;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Ej3Activity.this, ViewStationActivity.class);
                Bundle bundle = new Bundle();

                Calendar cal = ((BikeStation)listView.getItemAtPosition(i)).getLastUpdated();
                String calFormat = String.format("%1$02d/%2$02d/%3$d %4$02d:%5$02d:%6$02d",cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

                bundle.putString(ViewStationActivity.TAG_NAME, ((BikeStation)listView.getItemAtPosition(i)).getTitle());
                bundle.putString(ViewStationActivity.TAG_STATE, ((BikeStation)listView.getItemAtPosition(i)).getState());
                bundle.putInt(ViewStationActivity.TAG_BIKES, ((BikeStation)listView.getItemAtPosition(i)).getBikesAvailable());
                bundle.putInt(ViewStationActivity.TAG_ANCHORS, ((BikeStation)listView.getItemAtPosition(i)).getAnchorsAvailable());
                bundle.putString(ViewStationActivity.TAG_LASTUPDATED, calFormat);
                bundle.putString(ViewStationActivity.TAG_URI, ((BikeStation)listView.getItemAtPosition(i)).getMapUri());
                bundle.putString(ViewStationActivity.TAG_COORDINATES, ((BikeStation)listView.getItemAtPosition(i)).getCoordinates());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

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

        final ProgressDialog dialog = new ProgressDialog(Ej3Activity.this);

        dialog.setTitle("Cargando estaciones...");
        dialog.show();

        BikeStationUtils.getBikesAPI(Ej3Activity.this, new BikeStationUtils.callBackBikeAPI() {
            @Override
            public void onFinish(List<BikeStation> bikeStations) {
                List<BikeStation> bikeStationList = bikeStations;
                adapter = new BikeStationAdapter(Ej3Activity.this, bikeStationList);

                listView.setAdapter(adapter);

                dialog.dismiss();
            }

            @Override
            public void onException(Exception ex) {
                dialog.dismiss();
                Toast.makeText(Ej3Activity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
