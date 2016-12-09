package com.example.daniel.accesoadatos_xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.daniel.accesoadatos_xml.Ej1.Ej1Activity;
import com.example.daniel.accesoadatos_xml.Ej2.Ej2Activity;
import com.example.daniel.accesoadatos_xml.Ej3.Ej3Activity;
import com.example.daniel.accesoadatos_xml.Ej4.Ej4Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()){
            case R.id.btnEj1:
                intent = new Intent(MainActivity.this, Ej1Activity.class);
                startActivity(intent);
                break;

            case R.id.btnEj2:
                intent = new Intent(MainActivity.this, Ej2Activity.class);
                startActivity(intent);
                break;

            case R.id.btnEj3:
                intent = new Intent(MainActivity.this, Ej3Activity.class);
                startActivity(intent);
                break;

            case R.id.btnEj4:
                intent = new Intent(MainActivity.this, Ej4Activity.class);
                startActivity(intent);
                break;
        }
    }
}
