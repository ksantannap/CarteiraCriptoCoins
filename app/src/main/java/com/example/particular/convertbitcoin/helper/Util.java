package com.example.particular.convertbitcoin.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;

/**
 * Created by Particular on 29/08/2017.
 */

public class Util {

    public static String formatarMoeda(String value) {
        BigDecimal valor = new BigDecimal(value);
        DecimalFormat decFormat = new DecimalFormat("#,##0.00");
        return decFormat.format(valor);

    }

}
