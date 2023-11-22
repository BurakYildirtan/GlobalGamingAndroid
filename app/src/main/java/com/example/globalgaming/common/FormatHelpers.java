package com.example.globalgaming.common;

import java.text.DecimalFormat;
import java.text.Format;

public class FormatHelpers {

    public static String formatPriceAndCurrency(Double price, String currency) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(price) + " " + currency;
    }

    public static Double calculatePriceWithSale(Double price, Double saleInPercent) {
        return price * ( 1 -  saleInPercent / 100 );
    }
}
