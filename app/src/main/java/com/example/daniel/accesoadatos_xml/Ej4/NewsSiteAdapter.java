package com.example.daniel.accesoadatos_xml.Ej4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daniel.accesoadatos_xml.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 8/12/16.
 */

public class NewsSiteAdapter extends RecyclerView.Adapter<NewsSiteAdapter.NewsSiteHolder> {

    private List<NewsSite> newsSites;

    interface OnClickCallback{
        void onClick(NewsSite site);
    }

    private OnClickCallback callback;

    public NewsSiteAdapter(OnClickCallback callback){
        newsSites = new ArrayList<>(NewsSiteRepository.getNewsSites());
        this.callback = callback;
    }

    @Override
    public NewsSiteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsite_layout, null);

        NewsSiteHolder holder = new NewsSiteHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NewsSiteHolder holder, final int position) {
        holder.txv_title.setText(newsSites.get(position).getName());
        holder.imv_icon.setImageResource(newsSites.get(position).getIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClick(newsSites.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsSites.size();
    }

    public static class NewsSiteHolder extends RecyclerView.ViewHolder{
        TextView txv_title;
        ImageView imv_icon;

        public NewsSiteHolder(View view){
            super(view);

            txv_title = (TextView) view.findViewById(R.id.txv_title);
            imv_icon = (ImageView) view.findViewById(R.id.imv_icon);
        }
    }
}
