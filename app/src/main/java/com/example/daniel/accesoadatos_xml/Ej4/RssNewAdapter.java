package com.example.daniel.accesoadatos_xml.Ej4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.daniel.accesoadatos_xml.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by daniel on 8/12/16.
 */

public class RssNewAdapter extends ArrayAdapter<RssNew> {

    public RssNewAdapter(Context context, List<RssNew> rssNews){
        super(context, R.layout.rssnew_layout, new ArrayList<RssNew>(rssNews));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        RssNewHolder holder = null;

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.rssnew_layout,null);
            holder = new RssNewHolder();

            holder.txv_title = (TextView) v.findViewById(R.id.txv_title);
            holder.txv_date = (TextView) v.findViewById(R.id.txv_date);

            v.setTag(holder);
        }
        else{
            holder = (RssNewHolder)v.getTag();
        }

        holder.txv_title.setText(getItem(position).getTitle());
        Calendar cal = getItem(position).getPubDate();
        holder.txv_date.setText(String.format("%1$02d/%2$02d/%3$d %4$02d:%5$02d:%6$02d",cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND)));

        return v;
    }

    public static class RssNewHolder{
        TextView txv_title;
        TextView txv_date;
    }
}
