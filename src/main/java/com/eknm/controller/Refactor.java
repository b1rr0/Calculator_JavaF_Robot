package com.eknm.controller;


import com.eknm.model.CalculationException;
import com.eknm.model.Calculator;
import java.math.BigDecimal;

/**
 * Class to transform number to  needed view
 */
public class Refactor {
    private Refactor() {
    }

    /**
     * Default count of symbols to round up
     */
    private static final int DEFAULT_ACCURACY_FOR_STRING = 16;

    /**
     * Count of symbols to change method of round
     */
    private static final int BORDER_TO_CHANGE = 3;

    /**
     * Count of symbols to show another view
     */
    private static final int COUNT_TO_SHOW_ANOTHER_VIEW = 14;

    /**
     * Min value to show  res  with E
     */
    private static final BigDecimal MIN_VALUE_TO_E = new BigDecimal("0.0000000000000001");
    /**
     * Minus
     */
    private static final String MINUS = "-";
    /**
     * Zero
     */
    private static final String ZERO = "0";

    /**
     * Method to transform number to to  needed view
     * *  * @param big  data
     * * @return string with data in needed view
     */
    public static String refactor(BigDecimal big) {

        return addSpace(Refactor2.refactor(big));
 /*

        int accuracyForString;
        if (big.toString().startsWith(MINUS)) {
            accuracyForString = DEFAULT_ACCURACY_FOR_STRING + 1;
        } else {
            accuracyForString = DEFAULT_ACCURACY_FOR_STRING;
        }
        String bigDecimalAsString = big.toString();

        DecimalFormat decimalFormat;
        System.out.println(new DecimalFormat("###,###.###").format(big));


        String res;
        if (big.compareTo(BigDecimal.ZERO) == 0) {
            res = ZERO;
        } else if (bigDecimalAsString.contains(".") || bigDecimalAsString.contains("E-")) {
            res = logicWithComa(bigDecimalAsString, big, accuracyForString);
        } else {
            res = logicWithOutComa(bigDecimalAsString, accuracyForString);
        }
        res = res.replaceAll("\\.", ",").replaceAll("E", "e");
        return addSpace(res);

        */
    }

    /**
     * Method to parse bigDecimal from string
     *
     * @param string string
     * @return BigDecimal value of string
     */
    public static BigDecimal bigDecimalFromString(String string) {
        string = string.replaceAll(" ", "");
        string = string.replaceAll(",", ".");

        if (Controller.MAP_OF_EXCEPTION_EQUIVALENTS.values().contains(string)) {
            return null;
        }

        if (string.equals("0.")) {
            return new BigDecimal(string).setScale(2);
        }
        return new BigDecimal(string);
    }


    /**
     * Method to transform string to string with gaps
     *
     * @param data
     * @return string with gaps
     */
    public static String addSpace(String data) {
        data = data.replaceAll(" ", "");
        StringBuilder stringBuilder = new StringBuilder();
        String tmpData = data;
        data = checkForZero(data);

        if (data.contains(",")) {
            tmpData = data.split(",")[0];
        }

        int count = 0;
        for (int i = tmpData.length() - 1; i >= 0; i--) {
            if (count == BORDER_TO_CHANGE) {
                count = 0;
                stringBuilder.append(" ");
            }

            if (tmpData.charAt(i) == '-' && stringBuilder.charAt(stringBuilder.length() - 1) == ' ') {
                stringBuilder.setLength(stringBuilder.length() - 1);
            }
            stringBuilder.append(tmpData.charAt(i));
            count++;

        }
        tmpData = stringBuilder.reverse().toString();

        if (data.contains(",")) {
            tmpData += ",";
        }

        if (data.split(",").length > 1) {
            tmpData += data.split(",")[1];
        }
        return tmpData;
    }

    private static String checkForZero(String data) {
        if (data.contains("e")) {
            String[] strings = data.split("e");
            while (strings[0].charAt(strings[0].length() - 1) == '0') {
                strings[0] = strings[0].substring(0, strings[0].length() - 1);
            }
            data = strings[0] + "e" + strings[1];
        }
        return data;
    }



    private static String logicWithComa(String bigDecimalAsString, BigDecimal big, int accuracyForString) {
        if (bigDecimalAsString.contains("E-")) {
            return logicWithEMinus(big, accuracyForString);
        }
        String[] strings = bigDecimalAsString.split("\\.");
        if (strings[0].length() >= accuracyForString - 1) {
            String data = big.setScale(0, Calculator.ROUND_MODE).toString().split("\\.")[0];
            return logicWithOutComa(data, accuracyForString);
        }
        return checkForNeedToTransformOrNot(strings[0].length(), big, accuracyForString);
    }


    private static String logicWithEMinus(BigDecimal big, int accuracyForString) {
        String[] strings = big.toString().split("E-");
        String ans = "";
        if ((big.compareTo(MIN_VALUE_TO_E) >= 0) && dellLastZero(strings[0]).length() < BORDER_TO_CHANGE) {
            BigDecimal res = big.setScale(accuracyForString, Calculator.ROUND_MODE);
            ans = dellLastZero(res.toPlainString());
        } else if ((Integer.valueOf(strings[1]) <= accuracyForString) &&
                (big.compareTo(big.setScale(accuracyForString, Calculator.ROUND_MODE)) == 0)) {
            ans = dellLastZero(big.toPlainString());
        } else {
            ans = getDefaultSituation(strings, accuracyForString);
        }
        return ans;
    }

    private static String getDefaultSituation(String[] strings, int accuracyForString) {
        int accuracy = 1;
        if (strings[0].startsWith(MINUS)) {
            accuracy++;
        }
        String res = cutSuperfluous(accuracy, new BigDecimal(strings[0]), accuracyForString);

        if (!res.contains(".")) {
            res += ".";
        }
        res += "E-" + strings[1];

        BigDecimal bigDecimal = new BigDecimal(res);
        strings = bigDecimal.toString().split("E-");
        int tmpInt;
        tmpInt = Integer.valueOf(strings[1]);
        if (accuracyForString + 1 - (strings[0].length() + tmpInt) >= 0
                && !strings[0].startsWith(ZERO) && tmpInt <= accuracyForString) {
            return dellLastZero(bigDecimal.toPlainString());
        }
        return res;
    }

    private static int getCount(String string) {
        int count = 0;
        while (string.charAt(count) != '.') {
            count++;
        }
        return count++;
    }

    private static int getAccuracyForString(String string) {
        int accuracy = 0;
        if (string.startsWith(MINUS)) {
            accuracy++;
        }
        return accuracy++;
    }

    private static String checkForNeedToTransformOrNot(int acc, BigDecimal big, int accuracyForString) {
        String string = big.toString();

        if (!string.replaceAll(MINUS, "").startsWith(ZERO)) {
            return cutSuperfluous(acc, big, accuracyForString);
        }

        String anotherWay = cutSuperfluous(getAccuracyForString(string), big, accuracyForString);
        String stringHelper = "";
        int count = getCount(string);
        String stringDifference = "";
        int positionOfStartNumbert = 0;


        for (int i = count + 1; i <= count + DEFAULT_ACCURACY_FOR_STRING; i++) {

            if (string.charAt(i) != '0') {
                positionOfStartNumbert = i - count + 1;
                for (int i1 = i; i1 <= i + DEFAULT_ACCURACY_FOR_STRING; i1++) {
                    if (i1 == i + 1) {
                        stringDifference += ".";
                    }
                    if (string.length() > i1) {
                        stringDifference += string.charAt(i1);
                    }
                }
                break;
            }
        }

        BigDecimal bigDecimal = new BigDecimal(stringDifference).
                setScale(DEFAULT_ACCURACY_FOR_STRING - 1, Calculator.ROUND_MODE);
        stringDifference = bigDecimal.toString();


        if (anotherWay.length() < COUNT_TO_SHOW_ANOTHER_VIEW) {
            return anotherWay;
        }
        return bestOfTwoWays(stringDifference, bigDecimal, anotherWay, stringHelper, positionOfStartNumbert, count);

    }

    private static String bestOfTwoWays(String stringDifference, BigDecimal bigDecimal, String anotherWay,
                                        String stringHelper, int positionOfStartNumber, int count) {

        while (stringDifference.contains(".") && ((stringDifference.charAt(stringDifference.length() - 1) == '0') ||
                stringDifference.charAt(stringDifference.length() - 1) == '.')) {
            if (bigDecimal.scale() > 0) {
                bigDecimal = bigDecimal.setScale(bigDecimal.scale() - 1);
                stringDifference = bigDecimal.toString();
            } else {
                bigDecimal = bigDecimal.setScale(stringDifference.length());
            }
        }

        while (anotherWay.charAt(count) == '0' || anotherWay.charAt(count) == '.') {
            count++;
        }

        if (stringDifference.length() - 1 > anotherWay.length() - count && positionOfStartNumber > BORDER_TO_CHANGE) {
            stringDifference = stringHelper + stringDifference + "E-" + positionOfStartNumber;
            return stringDifference;
        } else {
            return anotherWay;

        }
    }

    private static String cutSuperfluous(int accuracy, BigDecimal big, int accuracyForString) {
        if (!big.toString().replaceAll(MINUS, "").startsWith(ZERO)) {
            accuracy++;
        }
        if (big.toString().length() >= accuracyForString) {
            big = big.setScale(accuracyForString + 1 - accuracy, Calculator.ROUND_MODE);
        }


        BigDecimal bigFluous = null;
        try {
            bigFluous = Calculator.checkForOverflow(big);
        } catch (CalculationException e) {
            e.printStackTrace();
        }
        String string = bigFluous.toString();

        while (string.contains(".") && ((string.charAt(string.length() - 1) == '0')
                || string.charAt(string.length() - 1) == '.')) {
            if (bigFluous.scale() > 0) {
                bigFluous =
                        bigFluous.setScale(bigFluous.scale() - 1);
                string = bigFluous.toString();
            } else {
                bigFluous = bigFluous.setScale(string.length());
            }

        }

        return string;
    }


    private static String logicWithOutComa(String string, int accuracyForString) {

        if (string.length() > DEFAULT_ACCURACY_FOR_STRING - 1) {
            string = new BigDecimal(string).setScale(accuracyForString - string.length(), Calculator.ROUND_MODE).toString();
        }
        if (string.contains("E+")) {
            String[] strings = string.split("E");
            string = dellLastZero(strings[0]) + "E" + strings[1];
        }
        return string;
    }


    private static String dellLastZero(String s) {
        while (s.charAt(s.length() - 1) == '0') {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }
}