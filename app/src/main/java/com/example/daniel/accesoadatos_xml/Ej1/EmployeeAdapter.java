package com.example.daniel.accesoadatos_xml.Ej1;

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

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public EmployeeAdapter(Context context, List<Employee> employees){
        super(context, R.layout.employee_layout, employees);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        EmployeeHolder holder = null;

        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.employee_layout, null);

            holder = new EmployeeHolder();

            holder.txv_employeeName = (TextView)v.findViewById(R.id.txv_employeeName);
            holder.txv_employeeAge = (TextView)v.findViewById(R.id.txv_employeeAge);
            holder.txv_employeePosition = (TextView)v.findViewById(R.id.txv_employeePosition);
            holder.txv_employeeSalary = (TextView)v.findViewById(R.id.txv_employeeSalary);

            v.setTag(holder);
        }else{
            holder = (EmployeeHolder) v.getTag();
        }

        holder.txv_employeeName.setText(getItem(position).getName());
        holder.txv_employeePosition.setText("Puesto: "+getItem(position).getPosition());
        holder.txv_employeeAge.setText("Edad: "+String.valueOf(getItem(position).getAge()));
        holder.txv_employeeSalary.setText("Salario: "+String.format("%.2f", getItem(position).getSalary())+"€");

        return v;
    }

    public static class EmployeeHolder{
        TextView txv_employeeName, txv_employeePosition, txv_employeeAge, txv_employeeSalary;
    }
}
