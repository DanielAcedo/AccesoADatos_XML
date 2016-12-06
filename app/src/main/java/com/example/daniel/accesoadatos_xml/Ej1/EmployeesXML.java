package com.example.daniel.accesoadatos_xml.Ej1;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 6/12/16.
 */

public class EmployeesXML {

    static public class StadisticResult{
        public int averageAge;
        public double maxSalary;
        public double minSalary;

        @Override
        public String toString() {
            return "Edad media: "+averageAge+"\n"+
                    "Salario máximo: " + String.format("%.2f", maxSalary) + "\n"+
                    "Salario mínimo: " + String.format("%.2f", minSalary) + "\n";
        }
    }

    public static List<Employee> analyseEmployees(XmlResourceParser file) throws IOException, XmlPullParserException {
        XmlPullParser parser = file;
        List<Employee> employees = null;
        Employee currentEmployee = null;
        boolean inEmployee = false;

        int event = parser.next();

        while(event != XmlPullParser.END_DOCUMENT){
            switch (event){

                case XmlPullParser.START_DOCUMENT:
                    employees = new ArrayList<Employee>();
                    break;

                case XmlPullParser.START_TAG:
                    String tag = parser.getName();

                    if(tag.equals("employee")) {
                        inEmployee = true;
                        currentEmployee = new Employee();
                    }

                    if(tag.equals("name") && inEmployee)
                        currentEmployee.setName(parser.nextText());

                    else if (tag.equals("position") && inEmployee)
                        currentEmployee.setPosition(parser.nextText());

                    else if (tag.equals("age") && inEmployee)
                        currentEmployee.setAge(Integer.parseInt(parser.nextText()));

                    else if (tag.equals("salary") && inEmployee)
                        currentEmployee.setSalary(Double.parseDouble(parser.nextText()));

                    break;

                case XmlPullParser.END_TAG:
                    tag = parser.getName();

                    if(tag.equals("employee")) {
                        inEmployee = false;
                        employees.add(currentEmployee);
                        currentEmployee = null;
                    }

                    break;
            }

            event = parser.next();
        }

        return employees;
    }

    public static StadisticResult getEmployeesStadistics(List<Employee> employees){
        StadisticResult result = new StadisticResult();

        int ageSum = 0;
        double minSalary = employees.get(0).getSalary();
        double maxSalary = 0.0;

        for (Employee employee : employees) {
            if(employee.getSalary() > maxSalary)
                maxSalary = employee.getSalary();

            if(employee.getSalary() < minSalary)
                minSalary = employee.getSalary();

            ageSum+= employee.getAge();
        }

        result.averageAge = ageSum / employees.size();
        result.maxSalary = maxSalary;
        result.minSalary = minSalary;

        return result;
    }
}
