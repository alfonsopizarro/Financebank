package com.example.financebank;


import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyXAxisValueFormatter extends IndexAxisValueFormatter {
    @Override
    public String getFormattedValue(float value) {
        // Convierte float value a date string
        // convierte from segundos back to milisegundos to format tiempo  enseña al usuario
        long emissionsMilliSince1970Time = ((long) value) * 1000;
        // Show time in local version
        Date timeMilliseconds = new Date(emissionsMilliSince1970Time);
        DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateTimeFormat.format(timeMilliseconds);
    }
}