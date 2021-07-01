package com.eknm.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Class to transform number to  needed view
 */
public class Refactor2 {
    private Refactor2() {
    }

    /**
     * Default count of symbols to round up
     */
    private static final int DEFAULT_ACCURACY_FOR_STRING = 16;

    /**
     * Max value to show  res  with E
     */
    private static final BigDecimal MIN_VALUE_TO_E = new BigDecimal("0.0000000000000001");
    /**
     * Pattern for DecimalFormat
     */
    private static final String EXPONENT_PATTERN = "0.0E0";
    /**
     * Pattern for DecimalFormat
     */
    private static final String DEFAULT_PATTERN = "0.0";

    /**
     * Zero
     */
    private static final String ZERO = "0";
    /**
     * min  value to show  res  with E
     */
    private static final BigDecimal MAX_VALUE_TO_E = new BigDecimal("10000000000000000");

    /**
     * Method to transform number to to  needed view
     * *  * @param big  data
     * * @return string with data in needed view
     */
    public static String refactor(BigDecimal big) {

        if (big.compareTo(BigDecimal.ZERO) == 0)
            return ZERO;

        DecimalFormat decimalFormat;
        if ((big.abs().compareTo(MAX_VALUE_TO_E) >= 0) || big.abs().compareTo(MIN_VALUE_TO_E) < 0) {
            decimalFormat = decimalFormatWithStartConfig(EXPONENT_PATTERN, 0);
            decimalFormat = configMax(decimalFormat, 1, DEFAULT_ACCURACY_FOR_STRING - 1);
        } else {
            decimalFormat = decimalFormatWithStartConfig(DEFAULT_PATTERN, 0);

            if (big.abs().compareTo(BigDecimal.ONE) > 0) {
                int first = big.abs().toPlainString().split("\\.")[0].length();
                decimalFormat = configMax(decimalFormat, first, DEFAULT_ACCURACY_FOR_STRING - first);
            } else {
                decimalFormat = configMax(decimalFormat, 1, DEFAULT_ACCURACY_FOR_STRING);
            }
        }
        return format(decimalFormat, big);

    }

    public static DecimalFormat configMax(DecimalFormat decimalFormat, int first, int second) {
        decimalFormat.setMaximumIntegerDigits(first);
        decimalFormat.setMaximumFractionDigits(second);
        return decimalFormat;
    }

    public static String format(DecimalFormat decimalFormat, BigDecimal big) {
        String res = decimalFormat.format(big).replaceAll("E-", "e-").replaceAll("E", "e+");
        if (!res.contains(",")) {
            res = res.replaceAll("e", ",e");
        }
        return res;
    }

    public static DecimalFormat decimalFormatWithStartConfig(String pattern, int minFractionDigits) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        decimalFormat.setMinimumFractionDigits(minFractionDigits);
        return decimalFormat;
    }
}