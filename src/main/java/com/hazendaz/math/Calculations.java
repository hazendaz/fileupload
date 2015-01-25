package com.hazendaz.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Calculations {

    public static void main(final String[] args) {

        BigDecimal bigDecimal = new BigDecimal(100);
        bigDecimal = bigDecimal.divide(new BigDecimal(106), 16, BigDecimal.ROUND_UP);
        // System.out.println(String.valueOf(bigDecimal));

        final BigDecimal holdDecimal = bigDecimal;

        double doubleValue = 100.0 / 106;
        // System.out.println(doubleValue);

        final double holdValue = doubleValue;

        while (doubleValue < 10) {
            doubleValue = doubleValue + holdValue;
            System.out.println(doubleValue);
        }

        while (Double.parseDouble(bigDecimal.toString()) < 10) {
            bigDecimal = bigDecimal.add(holdDecimal);
            System.out.println(bigDecimal);
        }

        final Locale[] locales = NumberFormat.getAvailableLocales();
        final double myNumber = -1234.56;
        NumberFormat form;
        for (int j = 0; j < 4; ++j) {
            System.out.println("FORMAT");
            for (final Locale locale : locales) {
                if (locale.getCountry().length() == 0) {
                    continue; // Skip language-only locales
                }
                System.out.print(locale.getDisplayName());
                switch (j) {
                    case 0:
                        form = NumberFormat.getInstance(locale);
                        break;
                    case 1:
                        form = NumberFormat.getIntegerInstance(locale);
                        break;
                    case 2:
                        form = NumberFormat.getCurrencyInstance(locale);
                        break;
                    default:
                        form = NumberFormat.getPercentInstance(locale);
                        break;
                }
                if (form instanceof DecimalFormat) {
                    System.out.print(": " + ((DecimalFormat) form).toPattern());
                }
                System.out.print(" -> " + form.format(myNumber));
                try {
                    System.out.println(" -> " + form.parse(form.format(myNumber)));
                } catch (final ParseException e) {
                    // do nothing
                }
            }
        }

    }
}
