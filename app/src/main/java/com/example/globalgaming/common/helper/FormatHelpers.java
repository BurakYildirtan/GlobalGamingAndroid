package com.example.globalgaming.common.helper;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatHelpers {

    public static String formatPriceAndCurrency(Double price, String currency) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(price) + " " + currency;
    }

    public static Double calculatePriceWithSale(Double price, Double saleInPercent) {
        return price * ( 1 -  saleInPercent / 100 );
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDataDateToViewDate(String dataDate) {
        try {
            SimpleDateFormat formatDataDate = new SimpleDateFormat("MMM d, yyyy, hh:mm:ss a");
            SimpleDateFormat formatViewDate = new SimpleDateFormat("dd.MM.yyyy");

            Date viewDate = formatDataDate.parse(dataDate);
            assert viewDate != null;
            return formatViewDate.format(viewDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    @SuppressLint("SimpleDateFormat")
    public static String formatViewDateToDataDate(String viewDate) {
        try {
            SimpleDateFormat formatViewDate = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat formatDataDate = new SimpleDateFormat("MMM d, yyyy, hh:mm:ss a");

            Date dataDate = formatViewDate.parse(viewDate);
            assert dataDate != null;
            return formatDataDate.format(dataDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
