package com.example.daniel.accesoadatos_xml.Ej4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.R;

public class Ej4Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsSiteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ej4);

        recyclerView = (RecyclerView)findViewById(R.id.rcv_sites);
        recyclerView.setLayoutManager(new GridLayoutManager(Ej4Activity.this, 3));
        adapter = new NewsSiteAdapter(new NewsSiteAdapter.OnClickCallback() {
            @Override
            public void onClick(NewsSite site) {
                Intent intent = new Intent(Ej4Activity.this, ViewNewsActivity.class);
                intent.putExtra(ViewNewsActivity.NEWSITE_TAG, site);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);


    }
}
