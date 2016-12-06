package com.example.daniel.accesoadatos_xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daniel.accesoadatos_xml.Ej1.Employee;
import com.example.daniel.accesoadatos_xml.Ej1.EmployeeAdapter;
import com.example.daniel.accesoadatos_xml.Ej1.EmployeesXML;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txv_text;
    private ListView listView;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv_text = (TextView) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.listView);

        try {
            List<Employee> employees = EmployeesXML.analyseEmployees(getResources().getXml(R.xml.employees));

            adapter = new EmployeeAdapter(MainActivity.this, employees);
            listView.setAdapter(adapter);
            txv_text.setText(EmployeesXML.getEmployeesStadistics(employees).toString());

        }catch (IOException | XmlPullParserException e){
            Toast.makeText(MainActivity.this, "Error al leer empleados", Toast.LENGTH_SHORT).show();
        }

    }
}
