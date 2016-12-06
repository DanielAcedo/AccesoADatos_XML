package com.example.daniel.accesoadatos_xml.Ej3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.daniel.accesoadatos_xml.R;

import java.util.List;

/**
 * Created by daniel on 6/12/16.
 */

public class BikeStationAdapter extends ArrayAdapter<BikeStation> {
    public BikeStationAdapter(Context context, List<BikeStation> bikeStationList) {
        super(context, R.layout.bikestation_layout, bikeStationList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        BikeStationHolder holder = null;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.bikestation_layout, null);
            holder = new BikeStationHolder();

            holder.txv_name = (TextView)v.findViewById(R.id.txv_stationName);
            holder.txv_state = (TextView)v.findViewById(R.id.txv_stationState);

            v.setTag(holder);
        }else{
            holder = (BikeStationHolder) v.getTag();
        }

        holder.txv_name.setText(getItem(position).getTitle());
        holder.txv_state.setText(getItem(position).getState());

        return v;

    }

    public static class BikeStationHolder{
        TextView txv_name, txv_state;
    }
}
